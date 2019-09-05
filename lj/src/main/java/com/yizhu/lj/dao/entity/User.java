package com.yizhu.lj.dao.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private String keyID;
    private String picture;
    private String name;

    private Date createTime;

    private Double salary;

    private Date birthday;

    private String sex;

    private String email;

    private String station;

    private String telephone;

    private String userName;

    private String pawwWord;

    private Integer state;

    private String createBy;
    private String managerID;

    private String updateBy;



    private Date updateTime;
    /**
     * 部门信息
     */
    private String deptID;


    private String deptName;

    private List<Role> roles = new ArrayList<>();










}