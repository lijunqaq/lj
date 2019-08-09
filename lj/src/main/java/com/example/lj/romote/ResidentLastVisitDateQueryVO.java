package com.example.lj.romote;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 获取居民按人群当年度最近服务时间
 *
 * @author Lj
 * @date 2019/7/4
 */
@Data
@ApiModel(description = "获取居民按人群当年度最近服务时间参数vo")
public class ResidentLastVisitDateQueryVO {
    /** 居民id */
    @ApiModelProperty(value = "居民id", position = 1)
    @NotNull(message = "居民id不能为空")
    private Long residentId;
    /** 年份 */
    @ApiModelProperty(value = "年份", position = 2)
    @NotNull(message = "年份不能为空")
    private Integer year;
    /** 人群 */
    @ApiModelProperty(value = "人群", position = 3)
    @NotNull(message = "人群不能为空")
    private String groupTypes;
}
