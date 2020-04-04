package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.SameRule;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * @ClassName: SameCheck
 * @Description: 相邻重复检查 AAA
 * @Author: pengl
 * @Date: 2020/4/4 10:46
 * @Version V1.0
 */
public class SameCheck extends AbstractRuleCheck {

    public SameCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        SameRule rule = (SameRule) weakRule;
        if (checkSequentialSameChars(passData, rule)) {
            handleException(ErrorReturn.SAME_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.SAME_CHECK_1.getErrorMsg(), rule.getNum()));
            return false;
        }
        return true;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.SAME;
    }

    public boolean checkSequentialSameChars(String password, SameRule rule) {
        int n = password.length();
        char[] pwdCharArr = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH).toCharArray() : password.toCharArray();
        boolean flag = false;
        int limit_num = rule.getNum();
        int count;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }
}
