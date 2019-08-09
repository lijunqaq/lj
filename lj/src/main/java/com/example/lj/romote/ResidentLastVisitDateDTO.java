package com.example.lj.romote;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 获取居民按人群当年度最近服务时间返参
 *
 * @author Lj
 * @date 2019/7/4
 */
@Data
@ApiModel(description = "获取居民按人群当年度最近服务时间返参")
public class ResidentLastVisitDateDTO {

    /** 居民id */
    @ApiModelProperty(value = "居民id")
    private Long residentId;

    /** 人群 */
    @ApiModelProperty(value = "人群(1,儿童健康、3, 老年人生活自理能力、5, 高血压患者、6, 糖尿病、7, 严重精神障碍患者、8, 肺结核、9, 老年人中医药健康、10,儿童中医药健康、11, 健康体检、12, 儿童视力检查、13, 分娩前、14,分娩后)", position = 2)
    private Integer groupType;

    /** 随访时间 */
    @ApiModelProperty(value = "随访时间", position = 3)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date visitDate;

    /** 末次月经时间 */
    @ApiModelProperty(value = "末次月经时间", position = 4)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date lastMenstrualPeriod;

    /** 分娩时间 */
    @ApiModelProperty(value = "分娩时间", position = 5)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date childbirthDate;

    /** 末次月经时间对应随访时间 */
    @ApiModelProperty(value = "末次月经时间对应随访时间", position = 6)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date lastMenstrualPeriodVisitDate;

    /** 分娩时间对应随访时间 */
    @ApiModelProperty(value = "分娩时间对应随访时间", position = 7)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date childbirthDateVisitDate;


}
