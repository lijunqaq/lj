package com.example.lj.service.impl;

import com.example.lj.dao.entity.LoginLog;
import com.example.lj.dao.mapper.LoginLogMapper;
import com.example.lj.dto.IpNumOutDTO;
import com.example.lj.service.LoginLogService;
import com.example.lj.utils.PrimaryKeyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/1
 */
@Service
public class LogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 保存日志
     *
     * @param loginLog
     */
    @Override
    public void saveLog(LoginLog loginLog) {
        loginLog.setKeyID(PrimaryKeyHandler.getKeyIDByUID());
        loginLogMapper.insertSelective(loginLog);
    }

    @Override
    public List<IpNumOutDTO> findIpNums() {
        return loginLogMapper.selectByIP();


    }
}
