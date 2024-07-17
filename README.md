https://share.weiyun.com/DCApLnXv
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import java.util.Calendar

class TaskManager private constructor() {

    private val handlers = mutableMapOf<String, Handler>()
    private val handlerThreads = mutableMapOf<String, HandlerThread?>()
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        @Volatile
        private var INSTANCE: TaskManager? = null

        fun getInstance(): TaskManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: TaskManager().also { INSTANCE = it }
            }
        }
    }

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("DailyTaskPrefs", Context.MODE_PRIVATE)
    }

    fun startTask(taskName: String, useMainThread: Boolean = true, callback: TaskCallback? = null, task: () -> Unit) {
        val handler: Handler
        val handlerThread: HandlerThread?

        if (useMainThread) {
            handler = Handler(Looper.getMainLooper())
            handlerThread = null
        } else {
            handlerThread = HandlerThread(taskName).apply { start() }
            handler = Handler(handlerThread.looper)
        }

        handlers[taskName] = handler
        handlerThreads[taskName] = handlerThread

        handler.postDelayed(createTaskWrapper(taskName, task, callback), calculateNextExecutionTime() - System.currentTimeMillis())
    }

    fun stopTask(taskName: String) {
        handlers[taskName]?.removeCallbacksAndMessages(null)
        handlerThreads[taskName]?.quitSafely()
    }

    private fun createTaskWrapper(taskName: String, task: () -> Unit, callback: TaskCallback?): Runnable {
        return object : Runnable {
            override fun run() {
                try {
                    val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
                    val lastExecutionDateKey = "lastExecutionDate_$taskName"
                    val lastExecutionDate = sharedPreferences.getInt(lastExecutionDateKey, -1)

                    if (today != lastExecutionDate) {
                        sharedPreferences.edit().putInt(lastExecutionDateKey, today).apply()
                        task.invoke()
                        callback?.onSuccess(taskName)
                    }

                } catch (e: Exception) {
                    callback?.onFailure(taskName, e)
                } finally {
                    val nextExecutionTime = calculateNextExecutionTime()
                    handlers[taskName]?.postDelayed(this, nextExecutionTime - System.currentTimeMillis())
                }
            }
        }
    }

    private fun calculateNextExecutionTime(): Long {
        val now = Calendar.getInstance()
        val next = Calendar.getInstance()

        next.set(Calendar.HOUR_OF_DAY, 8)
        next.set(Calendar.MINUTE, 0)
        next.set(Calendar.SECOND, 0)
        next.set(Calendar.MILLISECOND, 0)

        if (next.before(now)) {
            next.add(Calendar.DAY_OF_YEAR, 1)
        }

        return next.timeInMillis
    }
    interface TaskCallback {
        fun onSuccess(taskName: String)
        fun onFailure(taskName: String, exception: Exception)
    }

}
