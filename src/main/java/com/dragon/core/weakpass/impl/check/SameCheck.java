package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.SameRule;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.function.Consumer;

/**
 * @ClassName: SameCheck
 * @Description: 相邻重复检查 AAA
 * @Author: pengl
 * @Date: 2020/4/4 10:46
 * @Version V1.0
 */
public class SameCheck extends AbstractRuleCheckStrategy {

    public SameCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        SameRule rule = (SameRule) weakRule;
        if (checkSequentialSameChars(passData, rule, (r -> handleException(r.getCode(), MessageFormat.format(r.getErrorMsg(), rule.getNum()))))) {
            return false;
        }
        return true;
    }

    private boolean checkSequentialSameChars(String password, SameRule rule, Consumer<ErrorReturn> consumer) {
        int n = password.length();
        char[] pwdCharArr = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH).toCharArray() : password.toCharArray();
        boolean flag = false;
        int limit_num = rule.getNum();
        limit_num = limit_num <= 0 ? n : limit_num;
        int count;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        consumer.accept(limit_num == n ? ErrorReturn.SAME_CHECK_2 : ErrorReturn.SAME_CHECK_1);
                        return true;
                    }
                }
            }
        }
        return flag;
    }
}
