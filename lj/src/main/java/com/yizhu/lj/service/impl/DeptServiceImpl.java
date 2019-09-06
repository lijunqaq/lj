package com.yizhu.lj.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yizhu.lj.dao.entity.Dept;
import com.yizhu.lj.dao.entity.Picture;
import com.yizhu.lj.dao.mapper.*;
import com.yizhu.lj.dto.ResponseBean;
import com.yizhu.lj.service.DeptService;
import com.yizhu.lj.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/1
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>  implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleModuleMapper roleModuleMapper;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    ExecutorService pool  = Executors.newFixedThreadPool(20);

    /**
     * 查询所有部门
     * @return
     */
    @Override
    public ResponseBean<List<Dept>> findAllDept() {
        ResponseBean<List<Dept>> result = new ResponseBean();
        List<Dept> depts = deptMapper.selectDeptList();
        if(depts!=null && depts.size()>1){
            return new ResponseBean<>(1,"",depts);
        }
      return  new ResponseBean<>(0,"没有查询到部门信息");
    }
    /**
     * 插入部门
     * @return
     */
    @Override
    public int addDept(Dept dept) {
        return deptMapper.insertSelective(dept);
    }
    /**
     * 批量查询
     * @return
     */
    @Override
    public List<Dept> findDeptByList(List<Dept> depts) {
        List<Dept> deptList = Lists.newArrayList();
        List<Future<Dept>> list = Lists.newArrayList();
        for(Dept dept:depts) {
            Future<Dept> submit =pool.submit(()->deptMapper.findByDept(dept));
            list.add(submit);
        }

        for(Future<Dept> fut:list){
            try {
                System.err.println(Thread.currentThread().getName()+"---"+fut.get());
                deptList.add(fut.get());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deptList;
    }
}
