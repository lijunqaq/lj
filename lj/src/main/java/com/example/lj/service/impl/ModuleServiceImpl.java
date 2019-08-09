package com.example.lj.service.impl;

import com.example.lj.dao.entity.Module;
import com.example.lj.dao.mapper.ModuleMapper;
import com.example.lj.dto.Page;
import com.example.lj.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 〈〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/9
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */
@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    //分页查询
    @Override
    public Page<List<Module>> findBypage(Integer pageSize, Integer pageNo, String keyWord) {
        Page<List<Module>> listPage = new Page<>();
        PageHelper.startPage(pageNo,pageSize);
        List<Module> result = moduleMapper.findAll();
        PageInfo<Module> modulePageInfo = new PageInfo<>(result);
        listPage.setDataList(result);
        listPage.setPageNo(pageNo);
        listPage.setTotalCount(modulePageInfo.getTotal());
        return listPage;

    }

    //查询父模块  0：一级菜单  1：2级菜单
    @Override
    public List<Module> getParent(Integer ctype) {
        return null;
    }

    //添加父模块
    @Override
    public void addModule(Module module) {


    }

    //批量删除
    @Override
    public void deleteModule(String[] groupId) {

    }
    //更新模块

    @Override
    public void updateModule(Module module) {

    }

    @Override
    public List<Module> findAll() {
        return null;
    }
}
