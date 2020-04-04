package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: SameRule
 * @Description: 相邻重复规则：AAA
 * @Author: pengl
 * @Date: 2020/4/3 22:11
 * @Version V1.0
 */
@Data
public class SameRule implements WeakRule {
    /**
     * 不允许重复的最小个数
     */
    private int num;
    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase;

    public SameRule num(int num) {
        this.num = num;
        return this;
    }

    public SameRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.SAME;
    }
}
