package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: DicRule
 * @Description: 字典校验
 * @Author: pengl
 * @Date: 2020/4/4 15:01
 * @Version V1.0
 */
@Data
public class DicRule implements WeakRule {
    /**
     * 以逗号分隔的字典值
     */
    private String dicString;

    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase;

    public DicRule dics(String dicString) {
        this.dicString = dicString;
        return this;
    }

    public DicRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.DIC;
    }
}
