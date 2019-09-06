package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("dept")
public class Dept {
    @TableId
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