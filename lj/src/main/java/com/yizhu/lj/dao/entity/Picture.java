package com.yizhu.lj.dao.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijun
 * @since 2019-09-05
 */
@Data
@TableName("picture")
public class Picture implements Serializable {

    @TableId
    private Long id;


    private String url;


    private Integer source;

    private Integer state;

    @TableField("create_time")
    private Date createTime;

    @TableField("updata_time")
    private Date updataTime;


}
