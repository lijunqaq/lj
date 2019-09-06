package com.yizhu.lj.service;

import com.baomidou.mybatisplus.service.IService;
import com.yizhu.lj.dao.entity.Dept;
import com.yizhu.lj.dao.entity.Picture;
import com.yizhu.lj.dto.ResponseBean;

import java.util.List;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/11
 */
public interface DeptService  extends IService<Dept> {
    ResponseBean<List<Dept>> findAllDept();

    int addDept(Dept dept);

    List<Dept> findDeptByList(List<Dept> depts);
}
