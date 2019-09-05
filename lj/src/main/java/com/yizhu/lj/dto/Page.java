package com.yizhu.lj.dto;

import lombok.Data;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/11
 */
@Data
public class Page<T> {
    private int pageNo;
    private int pageSize;
    private long totalCount;
    private T dataList;
}
