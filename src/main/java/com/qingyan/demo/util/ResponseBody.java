package com.qingyan.demo.util;

import com.qingyan.demo.enums.ResponseCode;

import java.io.Serializable;

/**
 * 统一返回格式
 */
public class ResponseBody<T> extends ResponceData implements Serializable {


    public static <T> ResponseBody<T> ok() {
        return ResponseBody.set(ResponseCode.OK.getValue(), ResponseCode.OK.getReasonPhrase(), null);
    }

    public static <T> ResponseBody<T> ok(T data) {
        return ResponseBody.set(ResponseCode.OK.getValue(), ResponseCode.OK.getReasonPhrase(), data);
    }

    public static <T> ResponseBody<T> ok(String message, T data) {
        return ResponseBody.set(ResponseCode.OK.getValue(), message, data);
    }


    public static <T> ResponseBody<T> fail() {
        return ResponseBody.set(ResponseCode.FAIL.getValue(), ResponseCode.FAIL.getReasonPhrase(), null);
    }

    public static <T> ResponseBody<T> fail(T data) {
        return ResponseBody.set(ResponseCode.FAIL.getValue(), ResponseCode.FAIL.getReasonPhrase(), data);
    }

    public static <T> ResponseBody<T> fail(ResponseCode responseCode, T data) {
        return ResponseBody.set(responseCode.getValue(), responseCode.getReasonPhrase(), data);
    }

    public static <T> ResponseBody<T> fail(int code, String message, T data) {
        return ResponseBody.set(code, message, data);
    }


    private static <T> ResponseBody<T> set(int code, String message, T data) {
        ResponseBody<T> responseBody = new ResponseBody<>();
        responseBody.setCode(code);
        responseBody.setMessage(message);
        responseBody.setData(data);
        return responseBody;
    }
}
