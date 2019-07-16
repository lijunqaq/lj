package com.example.lj;

import com.example.lj.dao.entity.Dept;
import com.example.lj.dao.mapper.DeptMapper;
import com.example.lj.dao.mapper.LoginLogMapper;
import com.example.lj.service.DeptService;
import com.example.lj.service.LoginLogService;
import com.example.lj.service.UserService;
import com.example.lj.utils.RedisUtil;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test2 {
    @Autowired
    private UserService userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;


    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private LoginLogMapper loginLogMapper;
    ExecutorService pool  = Executors.newFixedThreadPool(10);
    @Test
    public void contextLoads() {
      for (int j=0;j<20;j++) {
            ArrayList<Dept> depts = Lists.newArrayList();
            for (int i = 0; i < 50000; i++) {
                Dept dept = new Dept();
                dept.setKeyID(UUID.randomUUID().toString());
                dept.setDeptName("未来集团" + i);
                dept.setState((long) 1);
                dept.setParentID(100 + "");
//            deptService.addDept(dept);
//            System.out.println("------------插入-----" + i);
//            pool.submit(()-> deptService.addDept(dept)
//            );}
                depts.add(dept);
            }
            System.out.println("------------插入-----" + depts.size());
            long start = System.currentTimeMillis();
//            pool.submit(()-> deptMapper.insertByBatch(depts));
          deptMapper.insertByBatch(depts);
            long end = System.currentTimeMillis();
            System.out.println("------------耗时:----" + (end - start));

        }

    }
    @Test
    public void contextLoads23() {
//        ArrayList<Dept> depts = new ArrayList<>();
//        for(int i=43142;i<43342;i++){
//            Dept dept = new Dept();
//            dept.setDeptName("未来集团"+i);
//            depts.add(dept);
//        }
//        long start = System.currentTimeMillis();
//        List<Dept> deptList=deptService.findDeptByList(depts);
//        long end = System.currentTimeMillis();
//        System.err.println("------------耗时:----" + (end - start));
//        System.err.println("------------数据:----" + deptList.size());
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        String join = StringUtils.join(list, ",");
        String join1 = org.apache.commons.lang.StringUtils.join(list, ",");
        String collect = list.stream().collect(Collectors.joining(","));
        HashMap<Object, Object> map = new HashMap<>();




    }

    @Test
    public void contextLoads24() {
        String str="1,2,3,4";
        String[] strs=str.split(",");
        long[] convert = (long[]) ConvertUtils.convert(strs, long.class);
        for (int i=0;i<convert.length;i++){
            System.err.println(convert[i]+"---------");
        }


    }

}

