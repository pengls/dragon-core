package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: LoopRule
 * @Description: 回文规则：ABCCBA
 * @Author: pengl
 * @Date: 2020/4/3 22:09
 * @Version V1.0
 */
@Data
public class LoopRule implements WeakRule {
    @Override
    public RuleType ruleType() {
        return RuleType.LOOP;
    }
}
