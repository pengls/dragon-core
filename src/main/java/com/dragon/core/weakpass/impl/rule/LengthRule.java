package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: LengthRule
 * @Description: 长度规则
 * @Author: pengl
 * @Date: 2020/4/3 22:09
 * @Version V1.0
 */
@Data
public class LengthRule implements WeakRule {
    /**
     * 最小长度
     */
    private int min;
    /**
     * 最大长度
     */
    private int max;

    public LengthRule min(int min) {
        this.min = min;
        return this;
    }

    public LengthRule max(int max) {
        this.max = max;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.LENGTH;
    }
}
