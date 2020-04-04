package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: RegexRule
 * @Description: 正则规则
 * @Author: pengl
 * @Date: 2020/4/4 11:36
 * @Version V1.0
 */
@Data
public class RegexRule implements WeakRule {
    private String regex;

    public RegexRule regex(String regex){
        this.regex = regex;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.REGEX;
    }
}
