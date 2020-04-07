package com.dragon.core.weakpass;

public enum ErrorReturn {
    LENGTH_CHECK_1(1000, "长度不能小于{0}！"),
    LENGTH_CHECK_2(1001, "长度不能大于{0}！"),
    PHYSICAL_ORDER_CHECK_1(1002, "不能使用键盘横向连续按键！"),
    PHYSICAL_ORDER_CHECK_2(1003, "不能使用键盘斜向连续按键！"),
    SAME_CHECK_1(1004, "相同字符的个数不能超过{0}个！"),
    SAME_CHECK_2(1005, "不能使用相同的字符！"),
    LOGIC_ORDER_CHECK_1(1006, "顺序字符的个数不能超过{0}个！"),
    LOGIC_ORDER_CHECK_2(1007, "不能使用顺序的字符！"),
    REGEX_CHECK_1(1008, "格式不符合规范！"),
    LOOP_CHECK_1(1009, "存在{0}位的回文格式！"),
    DIC_CHECK_1(1010, "不能使用常见的弱密码字符串:{0}");

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
