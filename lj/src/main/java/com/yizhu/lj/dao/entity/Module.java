package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("module")
public class Module {
    @TableId
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