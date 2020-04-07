package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.DicRule;
import com.dragon.core.weakpass.impl.rule.KeywordsCombinationRule;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @ClassName: KeywordsCombinationCheck
 * @Description: 关键字组合规则检查器
 * @Author: pengl
 * @Date: 2020/4/7 21:50
 * @Version V1.0
 */
public class KeywordsCombinationCheck extends AbstractRuleCheckStrategy {

    @Override
    public RuleCheckResult check(String passData, WeakRule weakRule) {
        Assert.notBlank(passData, "password is blank");
        KeywordsCombinationRule rule = (KeywordsCombinationRule) weakRule;

        return null;
    }

    private boolean checkKeywordsCombination(String password, KeywordsCombinationRule rule) {
        String keyWords = rule.getKeywords();
        if (StringUtils.isBlank(keyWords)) {
            return false;
        }
        List<WeakRule> rules = rule.getRules();
        if (CollectionUtils.isEmpty(rules)) {
            return false;
        }

        if (rule.isIgnoreCase()) {
            password = password.toLowerCase(Locale.ENGLISH);
            keyWords = keyWords.toLowerCase(Locale.ENGLISH).trim();
        }

        /**
         * 匹配关键字
         */
        String keyword = "";
        StringTokenizer tokenizer = new StringTokenizer(keyWords, ",");
        while (tokenizer.hasMoreElements()) {
            String ele = tokenizer.nextElement().toString();
            if (password.startsWith(ele)) {
                keyword = ele;
            }
        }

        if (StringUtils.isBlank(keyword)) {
            return false;
        }

        int n = password.length();
        password = password.substring(n - keyword.length() , n);
        System.out.println("截取后密码：" + password);

        for(WeakRule weakRule : rules){

        }

        return false;
    }
}
