package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.RegexRule;

import java.util.regex.Pattern;

/**
 * @ClassName: RegexCheck
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/4 11:38
 * @Version V1.0
 */
public class RegexCheck extends AbstractRuleCheckStrategy {
    public RegexCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        RegexRule rule = (RegexRule) weakRule;
        String regex = rule.getRegex();
        Assert.notBlank(regex, "regex is blank");
        if (!Pattern.matches(regex, passData)) {
            handleException(ErrorReturn.REGEX_CHECK_1.getCode(), ErrorReturn.REGEX_CHECK_1.getErrorMsg());
            return false;
        }
        return true;
    }

}
