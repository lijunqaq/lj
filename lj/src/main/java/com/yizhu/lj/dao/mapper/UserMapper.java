package com.yizhu.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yizhu.lj.dao.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(String keyID);

   // int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String keyID);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User findByUsername(String username);

    List<User> selectUserList();


}