package com.enlightenment.demo.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 统一返回格式
 */
public class ResponseBody extends JSONObject {


    public static ResponseBody ok() {
        return ResponseBody.set(ResponseCode.OK.getValue(), ResponseCode.OK.getReasonPhrase(), null);
    }

    public static ResponseBody ok(Object data) {
        return ResponseBody.set(ResponseCode.OK.getValue(), ResponseCode.OK.getReasonPhrase(), data);
    }

    public static ResponseBody ok(String message, Object data) {
        return ResponseBody.set(ResponseCode.OK.getValue(), message, data);
    }


    public static ResponseBody fail() {
        return ResponseBody.set(ResponseCode.FAIL.getValue(), ResponseCode.FAIL.getReasonPhrase(), null);
    }

    public static ResponseBody fail(Object data) {
        return ResponseBody.set(ResponseCode.FAIL.getValue(), ResponseCode.FAIL.getReasonPhrase(), data);
    }

    public static ResponseBody fail(ResponseCode responseCode, Object data) {
        return ResponseBody.set(responseCode.getValue(), responseCode.getReasonPhrase(), data);
    }

    public static ResponseBody fail(int code, String message, Object data) {
        return ResponseBody.set(code, message, data);
    }


    private static ResponseBody set(int code, String message, Object data) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.put("code", code);
        responseBody.put("message", message);
        responseBody.put("data", data);
        return responseBody;
    }

    public ResponseBody put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
