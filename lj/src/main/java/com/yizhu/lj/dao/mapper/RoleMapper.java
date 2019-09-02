package com.yizhu.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yizhu.lj.dao.entity.Role;

import java.util.List;

public interface RoleMapper  extends BaseMapper<Role> {
    int deleteByPrimaryKey(String roleID);

    //int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleID);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleList();
}