package com.example.lj.service.impl;

import com.example.lj.dao.entity.Dept;
import com.example.lj.dao.entity.Role;
import com.example.lj.dao.mapper.ModuleMapper;
import com.example.lj.dao.mapper.RoleMapper;
import com.example.lj.dao.mapper.RoleModuleMapper;
import com.example.lj.dao.mapper.RoleUserMapper;
import com.example.lj.dto.ResponseBean;
import com.example.lj.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleModuleMapper roleModuleMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public ResponseBean<List<Role>> findAllRole() {
        ResponseBean<List<Role>> result = new ResponseBean();
        List<Role> roles = roleMapper.selectRoleList();
        if(roles!=null && roles.size()>1){
            return new ResponseBean<>(1,"",roles);
        }
        return  new ResponseBean<>(0,"没有查询到部门信息");
    }
}
