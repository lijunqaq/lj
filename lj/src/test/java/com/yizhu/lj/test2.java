package com.yizhu.lj;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.yizhu.lj.dao.entity.Dept;
import com.yizhu.lj.dao.mapper.DeptMapper;
import com.yizhu.lj.dao.mapper.LoginLogMapper;
import com.yizhu.lj.romote.InvokeException;
import com.yizhu.lj.romote.ResidentLastVisitDateDTO;
import com.yizhu.lj.romote.ResidentLastVisitDateQueryVO;
import com.yizhu.lj.romote.ResponseData;
import com.yizhu.lj.service.DeptService;
import com.yizhu.lj.service.LoginLogService;
import com.yizhu.lj.service.UserService;
import com.yizhu.lj.utils.RedisUtil;
import com.yizhu.lj.utils.RestTemplateInsiUtils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test2  {
    @Autowired
    private UserService userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    RestTemplateInsiUtils restTemplateUtils;

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
            pool.submit(()-> deptMapper.insertByBatch(depts));
       //    deptMapper.insertByBatch(depts);
            long end = System.currentTimeMillis();
            System.out.println("------------耗时:----" + (end - start));

        }

    }
    @Test
    public void contextLoads23() {
        ArrayList<Dept> depts = new ArrayList<>();
        for(int i=43142;i<43342;i++){
            Dept dept = new Dept();
            dept.setDeptName("未来集团"+i);
            depts.add(dept);
        }
        long start = System.currentTimeMillis();
        List<Dept> deptList=deptService.findDeptByList(depts);
        long end = System.currentTimeMillis();
        System.err.println("------------耗时:----" + (end - start));
        System.err.println("------------数据:----" + deptList.size());
//        ArrayList<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        String join = StringUtils.join(list, ",");
//        String join1 = org.apache.commons.lang.StringUtils.join(list, ",");
//        String collect = list.stream().collect(Collectors.joining(","));
//        HashMap<Object, Object> map = new HashMap<>();




    }

    @Test
    public void contextLoads24() {
        ResidentLastVisitDateQueryVO a = new ResidentLastVisitDateQueryVO();
        ResidentLastVisitDateQueryVO b = new ResidentLastVisitDateQueryVO();
        a.setYear(2019);
        BeanUtils.copyProperties(a,b);
        System.out.println(b.getYear());

        String str ="[\"04CC82E2710E4322B6A154CDE17FE16C\",\"3F021560F49B4EB79575A3F99ED1380B\"]";
        //String parse = (String)Arrays.asList(JSON.parse(str));
//        str.trim().replace("[","").replace("]","").replace("\"");
//        str.replace("[","");
//        str.replace("]","");
//        List<String> strings =
//        String join1 = Arrays.asList(JSON.parse(str)).stream().collect(Collectors.joining(","));


    }

    @Test
    public void contextLoads34() {
        String url = "/v1/inside/api/visitRecord/queryLastVisitDateByResidentIdAndYear";
        ResidentLastVisitDateQueryVO residentLastVisitDateQueryVO = new ResidentLastVisitDateQueryVO();
        residentLastVisitDateQueryVO.setResidentId(1l);
        residentLastVisitDateQueryVO.setYear(2019);
        residentLastVisitDateQueryVO.setGroupTypes("1,2,3,4,5,6,7,8,9");


        try {
            ResponseData<ResidentLastVisitDateDTO> rd = restTemplateUtils.invokeRestTemplateList(url, residentLastVisitDateQueryVO, ResidentLastVisitDateDTO.class, HttpMethod.POST);
            Gson gson = new Gson();
            gson.toJson( rd.getDataList());
        } catch (Exception e) {
            throw new InvokeException("接口调用异常:" + e.getMessage());
        }
    }

    @Test
    public void contextLoads241() {
        //当前时间
        Date date = DateUtil.date();
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, 20);
    }


    @Test
    public void ossTest() {

        Dept dept = deptService.selectById("000008ea-3e32-4ebe-8bbc-f3ef68afa8da");

        System.err.println("1");

    }


}

