package com.example.lj.dao.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.lj.dao.entity.Dept;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {
    int deleteByPrimaryKey(String keyID);

//    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String keyID);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> selectDeptList();

    Dept findByDept(Dept dept);

    int insertByBatch(List<Dept> depts);
}