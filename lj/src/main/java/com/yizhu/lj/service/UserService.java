package com.yizhu.lj.service;

import com.yizhu.lj.dao.entity.User;
import com.yizhu.lj.dto.Page;

import java.util.List;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/1
 */
public interface UserService {
    User findByUsername(String username);

    Page<List<User>> findUserByPage(Integer pageSize, Integer pageNo, String keyWord);

    boolean addUser(User user);
    //查询用户相关信息

}
