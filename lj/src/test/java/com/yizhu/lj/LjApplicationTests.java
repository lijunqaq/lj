package com.yizhu.lj;

import com.aliyuncs.exceptions.ClientException;
import com.yizhu.lj.dao.entity.Dept;
import com.yizhu.lj.dao.mapper.LoginLogMapper;
import com.yizhu.lj.service.DeptService;
import com.yizhu.lj.service.LoginLogService;
import com.yizhu.lj.service.UserService;
import com.yizhu.lj.utils.RedisUtil;
import com.yizhu.lj.utils.SmsUtils;
import org.hyperic.sigar.SigarException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LjApplicationTests {
    @Autowired
    private UserService userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DeptService deptService;


    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Test
    public void contextLoads() {
        try {
            SmsUtils.sendSms("小七","小猪佩奇身上纹","13981714022");
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void contextLoads2() throws SigarException {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        // CPU信息
        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
        Util.sleep(1000);
        long[] ticks = hal.getProcessor().getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        System.out.println(SystemInfo.getCurrentPlatformEnum());

    }
    @Test
    public void contextLoads23() {
//        System.out.println("11");
//            redisUtil.set("wuliuqi","iloveyou",5L);
//        String str ="linchang0001200013";
//        String md5 = Md5Helper.MD5(str);
//        System.out.println(md5);

//        List<ComputerOne> list = Arrays.asList(new ComputerOne("2", "哈哈"), new ComputerOne("1", "嘿嘿"), new ComputerOne("1", "呵呵"));
//        //从list集合中，取出字段name的列表
//
//        List<ComputerOne> collect = list.stream().filter(c -> c.getUserName().equals("1")).collect(Collectors.toList());
//
//
//        List<String> names = collect.stream().map(p -> p.getIp()).collect(Collectors.toList());
        for (int i=1;i<50000;i++){
            if(i!=100) {
                Dept dept = new Dept();
                dept.setKeyID(i + "");
                dept.setDeptName("未来集团" + i);
                dept.setState((long) 1);
                dept.setParentID(100 + "");
                deptService.addDept(dept);
                System.out.println("------------插入-----" + i);

            }}

    }

}

