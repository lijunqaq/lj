package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("roleuser")
public class RoleUser {
    @TableId
    private String userID;

    private String roleID;

}