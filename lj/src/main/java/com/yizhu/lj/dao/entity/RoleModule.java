package com.yizhu.lj.dao.entity;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("rolemodule")
public class RoleModule {
    @TableId
    private String roleID;

    private String moduleID;


}