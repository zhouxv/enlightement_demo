package com.qingyan.demo.util;

import java.io.Serializable;

public class ResponceData<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

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
