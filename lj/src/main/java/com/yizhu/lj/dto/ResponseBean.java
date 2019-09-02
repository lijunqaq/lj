package com.yizhu.lj.dto;

/**
 * 功能描述
 *
 * @author lijun
 * @date 2019/2/12
 */
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
