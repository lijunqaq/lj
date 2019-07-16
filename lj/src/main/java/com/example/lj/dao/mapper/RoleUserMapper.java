package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.RoleUser;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper {
    int deleteByPrimaryKey(@Param("userID") String userID, @Param("roleID") String roleID);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByUserID(String userID);
}