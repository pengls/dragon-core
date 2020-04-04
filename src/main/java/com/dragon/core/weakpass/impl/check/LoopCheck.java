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
public class LoopCheck extends AbstractRuleCheck {

    public LoopCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        LoopRule rule = (LoopRule) weakRule;
        if (rule.getNum() > 0) {
            if (checkLoop(passData, rule)) {
                handleException(ErrorReturn.LOOP_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.LOOP_CHECK_1.getErrorMsg(), rule.getNum()));
                return false;
            }
        }
        return true;
    }

    private boolean checkLoop(String password, LoopRule rule) {
        password = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH) : password;
        int limit_num = rule.getNum();
        int n = password.length();
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

    @Override
    public RuleType ruleType() {
        return null;
    }
}
