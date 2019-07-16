package com.example.lj.service;

import com.example.lj.dao.entity.LoginLog;
import com.example.lj.dto.IpNumOutDTO;

import java.util.List;

/**
 * @Description:	装箱单
 * @Author:			Coach tam
 * @Company:		坚持灵活  灵活坚持
 * @CreateDate:		2018-8-28 16:03:21
 */
public interface LoginLogService {

    void saveLog(LoginLog loginLog);

    List<IpNumOutDTO> findIpNums();
}
