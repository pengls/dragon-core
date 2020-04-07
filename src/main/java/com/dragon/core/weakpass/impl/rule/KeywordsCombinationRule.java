package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: KeywordsCombinationRule
 * @Description: 关键字和规则组合
 * @Author: pengl
 * @Date: 2020/4/7 21:40
 * @Version V1.0
 */
@Data
public class KeywordsCombinationRule implements WeakRule {
    /**
     * 关键字：逗号分隔
     */
    private String keywords;

    /**
     * 是否忽略大小写，默认true
     */
    private boolean ignoreCase = true;

    /**
     * 需要组合的规则列表
     */
    private List<WeakRule> rules = Lists.newArrayList();

    public KeywordsCombinationRule keywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public KeywordsCombinationRule rules(List<WeakRule> rules) {
        this.rules = rules;
        return this;
    }

    public KeywordsCombinationRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    public KeywordsCombinationRule rule(WeakRule rule) {
        this.rules.add(rule);
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.KEYWORDS_COMBINATION;
    }
}
