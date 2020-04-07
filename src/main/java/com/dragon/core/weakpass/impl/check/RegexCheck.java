package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.RegexRule;

import java.util.regex.Pattern;

/**
 * @ClassName: RegexCheck
 * @Description: 正则表达式匹配
 * @Author: pengl
 * @Date: 2020/4/4 11:38
 * @Version V1.0
 */
public class RegexCheck extends AbstractRuleCheckStrategy {

    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        RegexRule rule = (RegexRule) weakRule;
        String regex = rule.getRegex();
        Assert.notBlank(regex, "regex is blank");
        if (!Pattern.matches(regex, passData)) {
            return new RuleCheckResult(false, ErrorReturn.REGEX_CHECK_1.getCode(), ErrorReturn.REGEX_CHECK_1.getErrorMsg());
        }
        return new RuleCheckResult();
    }

}
