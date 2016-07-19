package com.pj.dao;

import com.pj.bean.User;
import com.pj.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);//按条件计数

    int deleteByExample(UserExample example);//按条件删除

    int deleteByPrimaryKey(Integer uid);//按主键删除

    int insert(User record);//插入(返回值为id值)

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);//按条件查询    

    User selectByPrimaryKey(Integer uid);//按主键查询

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);//按条件更新值不为null的字段    

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);//按条件更新

    int updateByPrimaryKeySelective(User record);//按主键更新   值不为null的字段    

    int updateByPrimaryKey(User record);//按主键更新   
}