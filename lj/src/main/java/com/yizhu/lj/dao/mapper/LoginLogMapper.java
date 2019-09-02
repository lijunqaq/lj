package com.yizhu.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yizhu.lj.dao.entity.LoginLog;
import com.yizhu.lj.dto.IpNumOutDTO;

import java.util.List;

public interface LoginLogMapper  extends BaseMapper<LoginLog> {
    int deleteByPrimaryKey(String keyID);

   // int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(String keyID);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    List<IpNumOutDTO> selectByIP();
}