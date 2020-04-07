package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.DicRule;
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
public class DicCheck extends AbstractRuleCheckStrategy {
    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        DicRule rule = (DicRule) weakRule;
        String res = checkDics(passData, rule);
        if (null != res) {
            return new RuleCheckResult(false, ErrorReturn.DIC_CHECK_1.getCode(), MessageFormat.format(ErrorReturn.DIC_CHECK_1.getErrorMsg(), res));
        }
        return new RuleCheckResult();
    }

    private String checkDics(String password, DicRule rule) {
        if (StringUtils.isBlank(rule.getDicString())) {
            return null;
        }
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
            if (ele.equalsIgnoreCase(password)) {
                return ele;
            }
        }
        return null;
    }
}
