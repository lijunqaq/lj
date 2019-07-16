package com.example.lj.service;

import com.example.lj.dao.entity.Role;
import com.example.lj.dto.ResponseBean;

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
