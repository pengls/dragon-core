package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.LoopRule;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @ClassName: LoopCheck
 * @Description: 回文规则检查
 * @Author: pengl
 * @Date: 2020/4/4 14:25
 * @Version V1.0
 */
public class LoopCheck extends AbstractRuleCheckStrategy {

    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        LoopRule rule = (LoopRule) weakRule;
        if (checkLoop(passData, rule)) {
            return new RuleCheckResult(false, ErrorReturn.LOOP_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.LOOP_CHECK_1.getErrorMsg(), rule.getNum()));
        }
        return new RuleCheckResult();
    }

    private boolean checkLoop(String password, LoopRule rule) {
        password = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH) : password;
        int n = password.length();
        int limit_num = rule.getNum();
        limit_num = limit_num <= 0 ? (n / 2) : limit_num;

        for (int i = 0; i + (limit_num * 2) <= n; i++) {
            String str = password.substring(i, i + limit_num * 2);
            if (loop(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean loop(String text) {
        int length = text.length();
        for (int i = 0; i < length / 2; i++) {
            if (text.toCharArray()[i] != text.toCharArray()[length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
