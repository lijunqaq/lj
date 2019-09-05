package com.yizhu.lj.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private String userID;

    private String managerID;

    private String name;

    private Date createTime;

    private Double salary;

    private Date birthday;

    private String sex;

    private String station;

    private String telephone;

    private Integer degree;

    private String remark;

    private String email;

    private String addBy;

    private String addDept;

    private Date addTime;

    private String modifyBy;

    private Date modifyTime;

    private String picture;


}