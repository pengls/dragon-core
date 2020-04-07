package com.dragon.core.weakpass;

import lombok.Data;

/**
 * @ClassName: RuleCheckResult
 * @Description: 规则检查结果
 * @Author: pengl
 * @Date: 2020/4/7 22:36
 * @Version V1.0
 */
@Data
public class RuleCheckResult {
    private boolean flag;
    private int code;
    private String msg;

    public RuleCheckResult() {
        flag = true;
        code = 0;
        msg = "success";
    }

    public RuleCheckResult(boolean result, int code, String msg) {
        this.flag = result;
        this.code = code;
        this.msg = msg;
    }
}
