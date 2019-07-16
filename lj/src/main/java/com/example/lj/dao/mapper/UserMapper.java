package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String keyID);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String keyID);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User findByUsername(String username);

    List<User> selectUserList();


}