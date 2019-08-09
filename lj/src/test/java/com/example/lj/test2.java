package com.example.lj;

import com.example.lj.dao.entity.Dept;
import com.example.lj.dao.mapper.DeptMapper;
import com.example.lj.dao.mapper.LoginLogMapper;
import com.example.lj.romote.InvokeException;
import com.example.lj.romote.ResidentLastVisitDateDTO;
import com.example.lj.romote.ResidentLastVisitDateQueryVO;
import com.example.lj.romote.ResponseData;
import com.example.lj.service.DeptService;
import com.example.lj.service.LoginLogService;
import com.example.lj.service.UserService;
import com.example.lj.utils.RedisUtil;
import com.example.lj.utils.RestTemplateInsiUtils;
import com.example.lj.utils.utils.DateUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
          String str="Wed Jul 03 2019 00:00:00 GMT+0800";
        try {
            Date date = DateUtil.date(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String[] strs=str.split(",");
//        long[] convert = (long[]) ConvertUtils.convert(strs, long.class);
//        for (int i=0;i<convert.length;i++){
//            System.err.println(convert[i]+"---------");
//        }



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

}

