package com.yizhu.lj.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2022/3/9
 */
@Data
public class Test implements Serializable {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("人员代码")
    private String code;
    @ExcelProperty("身份证号码")
    private String userCode;
    @ExcelProperty("生效日期")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date starDate;
    @ExcelProperty("失效日期")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date disableDate;
    @ExcelProperty("出生日期")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate;
    @ExcelProperty("参加工作日期")
    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    private Date workDate;
    @ExcelProperty("职工分类")
    private String category;
    @ExcelProperty("毕业学校")
    private String shcool;
    @ExcelProperty("所学专业")
    private String major;
}
