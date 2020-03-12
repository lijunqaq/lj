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
import com.yizhu.lj.utils.utils.GoogleAuthenticator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Googletest {



    //当测试authTest时候，把genSecretTest生成的secret值赋值给它
    private static String secret="6ZGPT7SXHW5LRRIW";


     //生成secret
    public static void main(String[] args) {
        genSecretTest();
    }
    //@Test
    public static void genSecretTest() {// 生成密钥
        secret = GoogleAuthenticator.generateSecretKey();
        // 把这个qrcode生成二维码，用google身份验证器扫描二维码就能添加成功
        String qrcode = GoogleAuthenticator.getQRBarcode("l1077387582@qq.com", secret);
        System.out.println("qrcode:" + qrcode + ",key:" + secret);
    }

    @Test
    public void verifyTest() {
        String code ="062575";
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(secret, code, t);
        System.out.println("检查code是否正确？" + r);
    }
}

