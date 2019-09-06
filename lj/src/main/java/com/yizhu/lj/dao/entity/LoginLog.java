package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("loginlog")
public class LoginLog {
    @TableId
    private String keyID;

    private String loginName;

    private String ipAdress;

    private Date loginTime;

}