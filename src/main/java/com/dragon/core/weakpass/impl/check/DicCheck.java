package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.DicRule;
import com.dragon.core.weakpass.impl.rule.SameRule;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @ClassName: SameCheck
 * @Description: 相邻重复检查 AAA
 * @Author: pengl
 * @Date: 2020/4/4 10:46
 * @Version V1.0
 */
public class DicCheck extends AbstractRuleCheck {

    public DicCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        DicRule rule = (DicRule) weakRule;
        String res = checkDics(passData, rule);
        if (null != res) {
            handleException(ErrorReturn.DIC_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.DIC_CHECK_1.getErrorMsg(), res));
            return false;
        }
        return true;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.DIC;
    }

    public String checkDics(String password, DicRule rule) {
        String dicString = rule.getDicString();
        if (StringUtils.isBlank(dicString)) {
            return null;
        }

        if (rule.isIgnoreCase()) {
            password = password.toLowerCase(Locale.ENGLISH);
            dicString = dicString.toLowerCase(Locale.ENGLISH).trim();
        }

        StringTokenizer tokenizer = new StringTokenizer(dicString, ",");
        while (tokenizer.hasMoreElements()) {
            String ele = tokenizer.nextElement().toString();
            if (password.indexOf(ele) != -1) {
                return ele;
            }
        }
        return null;
    }
}
