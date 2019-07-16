package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleID);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleID);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleList();
}