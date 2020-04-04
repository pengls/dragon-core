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
    /**
     * 回文位数
     */
    private int num;
    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase;

    public LoopRule num(int num) {
        this.num = num;
        return this;
    }

    public LoopRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }


    @Override
    public RuleType ruleType() {
        return RuleType.LOOP;
    }
}
