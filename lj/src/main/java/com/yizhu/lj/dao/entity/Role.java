package com.yizhu.lj.dao.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Role {
    private String roleID;

    private String name;

    private String remark;

    private Integer orderNo;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private List<Module> modules=new ArrayList<>();




}