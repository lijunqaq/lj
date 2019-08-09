package com.example.lj.service;


import com.example.lj.dao.entity.Module;
import com.example.lj.dto.Page;

import java.util.List;

public interface ModuleService {
    //分页查询
    Page<List<Module>> findBypage(Integer pageSize, Integer pageNo, String keyWord);

    //查询父模块
    List<Module> getParent(Integer ctype);

    //添加父模块
    void addModule(Module module);

    //批量删除
    void deleteModule(String[] groupId);

    //更新
    void updateModule(Module module);

    //查找所有模块
    List<Module> findAll();
}
