package com.example.lj.dao.mapper;


import com.example.lj.dao.entity.LoginLog;
import com.example.lj.dto.IpNumOutDTO;

import java.util.List;

public interface LoginLogMapper {
    int deleteByPrimaryKey(String keyID);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(String keyID);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<IpNumOutDTO> selectByIP();
}