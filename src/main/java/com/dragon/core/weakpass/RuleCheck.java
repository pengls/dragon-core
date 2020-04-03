package com.dragon.core.weakpass;

/**
 * @ClassName: RuleCheck
 * @Description: 规则检查
 * @Author: pengl
 * @Date: 2020/4/3 22:16
 * @Version V1.0
 */
public interface RuleCheck {
    /**
     * check weak password
     * @return
     */
    boolean check();
    /**
     * RuleType
     * @return
     */
    RuleType ruleType();
}
