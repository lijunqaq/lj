package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userID);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userID);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}