package com.yizhu.lj.dao.entity;

import java.util.Date;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author test
 * @since 2022-03-10
 */
@Data
@TableName("user_hospital")
public class UserHospital implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    @TableField("user_code")
    private String userCode;
    @TableField("star_date")
    private Date starDate;
    @TableField("disable_date")
    private Date disableDate;
    @TableField("work_date")
    private Date workDate;
    @TableField("birth_date")
    private Date birthDate;
    private String category;
    private String shcool;
    private String major;



}
