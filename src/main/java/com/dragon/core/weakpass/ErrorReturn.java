package com.dragon.core.weakpass;

public enum ErrorReturn {
    LENGTH_CHECK_1(-1, "密码长度不能小于{0}"),
    LENGTH_CHECK_2(-2, "密码长度不能大于{0}"),
    PHYSICAL_ORDER_CHECK_1(-3, "请不要直接使用键盘横向连续按键作为密码的部分！"),
    PHYSICAL_ORDER_CHECK_2(-4, "请不要直接使用键盘斜向连续按键作为密码的部分！");

    private int code;
    private String errorMsg;

    private ErrorReturn(int code, String errorMsg){
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
