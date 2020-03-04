package com.yizhu.lj;

import com.google.common.collect.Lists;
import com.yizhu.lj.dao.entity.Dept;
import com.yizhu.lj.dao.mapper.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/12/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FutureTest {


    @Autowired
    private DeptMapper deptMapper;
    @Test
    public void findDeptByList() {
        ExecutorService pool  = Executors.newFixedThreadPool(20);

        ArrayList<Dept> depts = new ArrayList<>();
        for(int i=43142;i<43342;i++){
            Dept dept = new Dept();
            dept.setDeptName("未来集团"+i);
            depts.add(dept);
        }


        List<Dept> deptList = Lists.newArrayList();
        List<Future<Dept>> list = Lists.newArrayList();
        long start = System.currentTimeMillis();
        depts.forEach(record->{
            Future<Dept> submit =pool.submit(()->deptMapper.findByDept(record));
            list.add(submit);
        });

        list.forEach(fue->{
            try {
                System.err.println(Thread.currentThread().getName()+"---"+fue.get());
                deptList.add(fue.get());

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();
        System.err.println("------------耗时:----" + (end - start));
        System.err.println("------------数据:----" + deptList.size());

    }
}
