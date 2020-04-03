package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: LogicOrderRule
 * @Description: 字符逻辑相邻规则 ABC
 * @Author: pengl
 * @Date: 2020/4/3 22:09
 * @Version V1.0
 */
@Data
public class LogicOrderRule implements WeakRule {
    /**
     * 允许相邻的位数
     */
    private int nums;
    public LogicOrderRule nums(int nums) {
        this.nums = nums;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.LOGIC_ORDER;
    }
}
