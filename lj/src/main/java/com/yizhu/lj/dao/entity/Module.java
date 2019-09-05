package com.yizhu.lj.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Module {
    private String moduleID;

    private String parentID;

    private String parentName;

    private String name;

    private Long layerNum;

    private Long isLeaf;

    private String ico;

    private String cpermission;

    private String curl;

    private Long ctype;

    private Long state;

    private String belong;

    private String cwihch;

    private Long quoteNum;

    private String remark;

    private Long orderNo;

    private String createBy;

    private String createDept;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


}