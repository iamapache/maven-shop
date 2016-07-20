package com.pj.bean;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.pj.bean.User.GroupA;
import com.pj.bean.User.GroupB;

//限制	说明
//@Null	限制只能为null
//@NotNull	限制必须不为null
//@AssertFalse	限制必须为false
//@AssertTrue	限制必须为true
//@DecimalMax(value)	限制必须为一个不大于指定值的数字
//@DecimalMin(value)	限制必须为一个不小于指定值的数字
//@Digits(integer,fraction)	限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction
//@Future	限制必须是一个将来的日期
//@Max(value)	限制必须为一个不大于指定值的数字
//@Min(value)	限制必须为一个不小于指定值的数字
//@Past	限制必须是一个过去的日期
//@Pattern(value)	限制必须符合指定的正则表达式
//@Size(max,min)	限制字符长度必须在min到max之间
//@Past	验证注解的元素值（日期类型）比当前时间早
//@NotEmpty	验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0）
//@NotBlank	验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格
//@Email	验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式
@GroupSequence( { GroupA.class, GroupB.class })  
public class User {
	
    private Integer uid;
    @NotEmpty(message="{user.username.lenth}",groups=GroupA.class)
    @Length(min=2, max=7, message="{user.username.size}")  
    @Pattern(regexp = "^[a-zA-Z_]\\w{4,19}$", message = "{user.username.illegal}")
    private String username;
    @Size(min=2,max=10,message="{user.password.lenth}",groups=GroupB.class)
    private String password;
    @NotEmpty(message="{user.name.lenth}")
    private String name;
    @Email(message="{user.email.lenth}")
    private String email;
    @NotEmpty(message="{user.phone.lenth}")
    private String phone;
    @NotEmpty(message="{user.addr.lenth}")
    private String addr;

    private Integer state;

    private String code;

    
    public interface GroupA {  
    }  
      
    public interface GroupB {  
    } 
    
    //验证的时候必须符合该序列规定的顺序
    
    
    
    
    
    
    
    
    
    
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    
    
}