package com.yizhu.lj.service;

import com.yizhu.lj.dao.entity.LoginLog;
import com.yizhu.lj.dto.IpNumOutDTO;

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
