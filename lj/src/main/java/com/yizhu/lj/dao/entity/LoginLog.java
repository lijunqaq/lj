package com.yizhu.lj.dao.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLog {
    private String keyID;

    private String loginName;

    private String ipAdress;

    private Date loginTime;

}