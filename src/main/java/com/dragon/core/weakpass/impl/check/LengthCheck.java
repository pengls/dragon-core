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
public class LengthCheck extends AbstractRuleCheck {

    public LengthCheck(WeakPassCheck weakPassCheck) {
        super(weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        int passLength = passData.length();
        LengthRule rule = (LengthRule) getWeakRule();
        int min = rule.getMin();
        if (min > 0) {
            if (passLength < min) {
                handleException(ErrorReturn.LENGTH_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.LENGTH_CHECK_1.getErrorMsg(), min));
                return false;
            }
        }

        int max = rule.getMax();
        if (max > 0) {
            if (passLength > max) {
                handleException(ErrorReturn.LENGTH_CHECK_2.getCode(), MessageFormat.format(ErrorReturn.LENGTH_CHECK_2.getErrorMsg(), max));
            }
        }
        return true;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.LENGTH;
    }
}