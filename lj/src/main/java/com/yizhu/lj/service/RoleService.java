package com.yizhu.lj.service;

import com.yizhu.lj.dao.entity.Role;
import com.yizhu.lj.dto.ResponseBean;

import java.util.List;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/11
 */
public interface RoleService {

    ResponseBean<List<Role>> findAllRole();
}
