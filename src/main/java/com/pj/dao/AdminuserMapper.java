package com.pj.dao;

import com.pj.bean.Adminuser;
import com.pj.bean.AdminuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminuserMapper {
    int countByExample(AdminuserExample example);

    int deleteByExample(AdminuserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Adminuser record);

    int insertSelective(Adminuser record);

    List<Adminuser> selectByExample(AdminuserExample example);

    Adminuser selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Adminuser record, @Param("example") AdminuserExample example);

    int updateByExample(@Param("record") Adminuser record, @Param("example") AdminuserExample example);

    int updateByPrimaryKeySelective(Adminuser record);

    int updateByPrimaryKey(Adminuser record);
}