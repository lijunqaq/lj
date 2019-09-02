package com.yizhu.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yizhu.lj.dao.entity.RoleModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleModuleMapper extends BaseMapper<RoleModule>{
    int deleteByPrimaryKey(@Param("roleID") String roleID, @Param("moduleID") String moduleID);

   // int insert(RoleModule record);

    int insertSelective(RoleModule record);

    List<RoleModule> selectByRole(String roleID);
}