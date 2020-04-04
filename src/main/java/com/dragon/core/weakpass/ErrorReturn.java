package com.dragon.core.weakpass;

public enum ErrorReturn {
    LENGTH_CHECK_1(-1, "密码长度不能小于{0}！"),
    LENGTH_CHECK_2(-2, "密码长度不能大于{0}！"),
    PHYSICAL_ORDER_CHECK_1(-3, "请不要直接使用键盘横向连续按键作为密码的部分！"),
    PHYSICAL_ORDER_CHECK_2(-4, "请不要直接使用键盘斜向连续按键作为密码的部分！"),
    SAME_CHECK_1(-5, "相同字符的个数不能超过{0}个！"),
    LOGIC_ORDER_CHECK_1(-6, "顺序字符的个数不能超过{0}个！"),
    REGEX_CHECK_1(-7, "密码格式不符合要求！"),
    LOOP_CHECK_1(-8, "密码中存在{0}位的回文格式！"),
    DIC_CHECK_1(-9, "密码中存在不允许的字符:{0}");

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
