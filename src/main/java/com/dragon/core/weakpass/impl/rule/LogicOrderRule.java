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
     * 不允许逻辑相邻的最小个数
     */
    private int num;
    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase;

    public LogicOrderRule num(int num) {
        this.num = num;
        return this;
    }

    public LogicOrderRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.LOGIC_ORDER;
    }
}
