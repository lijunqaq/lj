package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("role")
public class Role {
    @TableId
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