package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.RoleModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleModuleMapper {
    int deleteByPrimaryKey(@Param("roleID") String roleID, @Param("moduleID") String moduleID);

    int insert(RoleModule record);

    int insertSelective(RoleModule record);

    List<RoleModule> selectByRole(String roleID);
}