package com.yizhu.lj.dao.entity;

import lombok.Data;

@Data
public class Dept {
    private String keyID;

    private String deptName;

    private Long state;

    private String parentID;

    @Override
    public String toString() {
        return "Dept{" +
                "keyID='" + keyID + '\'' +
                ", deptName='" + deptName + '\'' +
                ", state=" + state +
                ", parentID='" + parentID + '\'' +
                '}';
    }
}