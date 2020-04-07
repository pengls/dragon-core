package com.dragon.core.weakpass;

/**
 * @ClassName: RuleCheckStrategy
 * @Description: 规则检查
 * @Author: pengl
 * @Date: 2020/4/3 22:16
 * @Version V1.0
 */
public interface RuleCheckStrategy {
    /**
     * check weak password
     * @return
     */
    boolean check();
}
