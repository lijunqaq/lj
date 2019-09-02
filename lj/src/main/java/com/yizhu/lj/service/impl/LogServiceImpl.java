package com.yizhu.lj.service.impl;

import com.yizhu.lj.dao.entity.LoginLog;
import com.yizhu.lj.dao.mapper.LoginLogMapper;
import com.yizhu.lj.dto.IpNumOutDTO;
import com.yizhu.lj.service.LoginLogService;
import com.yizhu.lj.utils.PrimaryKeyHandler;
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
