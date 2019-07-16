package com.example.lj.service;

import com.example.lj.dao.entity.Dept;
import com.example.lj.dto.ResponseBean;

import java.util.List;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/11
 */
public interface DeptService {
    ResponseBean<List<Dept>> findAllDept();

    int addDept(Dept dept);

    List<Dept> findDeptByList(List<Dept> depts);
}
