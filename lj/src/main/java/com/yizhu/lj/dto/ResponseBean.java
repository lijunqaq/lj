package com.yizhu.lj.dto;

import lombok.Data;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/12
 */
@Data
public class ResponseBean<T> {
    private int code;
    private String message;
    private T data;

    public ResponseBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBean(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseBean() {

    }


}
