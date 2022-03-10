package com.yizhu.lj.controller.user.excel;


import com.alibaba.excel.EasyExcel;
import com.yizhu.lj.dao.mapper.UserHospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author test
 * @since 2022-03-10
 */
@Controller
@RequestMapping("/userHospital")
public class UserHospitalController {
    @Autowired
    private UserHospitalMapper mapper;

    @GetMapping("/test")
    private void  test(){
        String strUrl = "D:\\人员字典.xlsx";


        EasyExcel.read(strUrl, Test.class, new ExcelListener(mapper)).sheet().doRead();
    }
}

