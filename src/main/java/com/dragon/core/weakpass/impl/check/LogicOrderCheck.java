package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.LogicOrderRule;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * @ClassName: LogicOrderCheck
 * @Description: 逻辑相邻规则检查 ABC
 * @Author: pengl
 * @Date: 2020/4/4 11:01
 * @Version V1.0
 */
public class LogicOrderCheck extends AbstractRuleCheckStrategy {

    public LogicOrderCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        LogicOrderRule rule = (LogicOrderRule) weakRule;
        if (checkSequentialOrderChars(passData, rule, (r -> handleException(r.getCode(), MessageFormat.format(r.getErrorMsg(), rule.getNum()))))) {
            handleException(ErrorReturn.LOGIC_ORDER_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.LOGIC_ORDER_CHECK_1.getErrorMsg(), rule.getNum()));
            return false;
        }
        return true;
    }

    private boolean checkSequentialOrderChars(String password, LogicOrderRule rule, Consumer<ErrorReturn> consumer) {
        boolean flag = false;
        int n = password.length();
        int limit_num = rule.getNum();
        limit_num = limit_num <= 0 ? n : limit_num;
        int normal_count;
        int reversed_count;

        char[] pwdCharArr = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH).toCharArray() : password.toCharArray();

        for (int i = 0; i + limit_num <= n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                    normal_count++;
                    if (normal_count == limit_num - 1) {
                        consumer.accept(limit_num == n ? ErrorReturn.LOGIC_ORDER_CHECK_2 : ErrorReturn.LOGIC_ORDER_CHECK_1);
                        return true;
                    }
                }

                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                    reversed_count++;
                    if (reversed_count == limit_num - 1) {
                        consumer.accept(limit_num == n ? ErrorReturn.LOGIC_ORDER_CHECK_2 : ErrorReturn.LOGIC_ORDER_CHECK_1);
                        return true;
                    }
                }
            }
        }
        return flag;
    }
}
