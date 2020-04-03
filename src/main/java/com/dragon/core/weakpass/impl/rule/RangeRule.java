package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: RangeRule
 * @Description: 允许的区间规则：[a-z0-9A-Z]
 * @Author: pengl
 * @Date: 2020/4/3 22:10
 * @Version V1.0
 */
@Data
public class RangeRule implements WeakRule {
    @Override
    public RuleType ruleType() {
        return RuleType.RANGE;
    }
}
