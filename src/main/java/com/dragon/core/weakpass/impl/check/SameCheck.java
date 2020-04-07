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
public class SameCheck extends AbstractRuleCheckStrategy {

    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        SameRule rule = (SameRule) weakRule;
        return checkSequentialSameChars(passData, rule);
    }

    private RuleCheckResult checkSequentialSameChars(String password, SameRule rule) {
        int n = password.length();
        char[] pwdCharArr = rule.isIgnoreCase() ? password.toLowerCase(Locale.ENGLISH).toCharArray() : password.toCharArray();
        int limit_num = rule.getNum();
        limit_num = limit_num <= 0 ? n : limit_num;
        int count;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        return limit_num == n ?
                                new RuleCheckResult(false, ErrorReturn.SAME_CHECK_2.getCode(), ErrorReturn.SAME_CHECK_2.getErrorMsg())
                                :
                                new RuleCheckResult(false, ErrorReturn.SAME_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.SAME_CHECK_1.getErrorMsg(), rule.getNum()));
                    }
                }
            }
        }
        return new RuleCheckResult();
    }
}
