package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.LengthRule;

import java.text.MessageFormat;

/**
 * @ClassName: LengthCheck
 * @Description: 长度规则检查
 * @Author: pengl
 * @Date: 2020/4/3 22:07
 * @Version V1.0
 */
public class LengthCheck extends AbstractRuleCheckStrategy {
    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        int passLength = passData.length();
        LengthRule rule = (LengthRule) weakRule;
        int min = rule.getMin();
        if (min > 0 && passLength < min) {
            return new RuleCheckResult(false, ErrorReturn.LENGTH_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.LENGTH_CHECK_1.getErrorMsg(), min));
        }

        int max = rule.getMax();
        if (max > 0 && passLength > max) {
            return new RuleCheckResult(false, ErrorReturn.LENGTH_CHECK_2.getCode(), MessageFormat.format(ErrorReturn.LENGTH_CHECK_2.getErrorMsg(), max));
        }
        return new RuleCheckResult();
    }
}
