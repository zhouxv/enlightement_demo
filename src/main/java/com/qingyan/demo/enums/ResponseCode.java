package com.qingyan.demo.enums;

/*
定义接口返回状态代码的枚举类型
 */

public enum ResponseCode {

    OK(200, "操作成功"),
    FAIL(520, "操作失败");

    private final int value;
    private final String reasonPhrase;

    ResponseCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
