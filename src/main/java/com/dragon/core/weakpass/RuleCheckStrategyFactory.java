package com.dragon.core.weakpass;

import com.dragon.core.weakpass.impl.check.*;

/**
 * @ClassName: RuleCheckStrategyFactory
 * @Description: RuleCheckStrategyFactory
 * @Author: pengl
 * @Date: 2020/4/3 22:02
 * @Version V1.0
 */
public abstract class RuleCheckStrategyFactory {

    /**
     * @MethodName: getRuleCheck
     * @Description: getRuleCheck Instance
     * @Author: pengl
     * @Date: 2020/4/3 22:16
     * @Version V1.0
     */
    public static final RuleCheckStrategy getRuleCheckStrategy(WeakRule rule, WeakPassCheck weakPassCheck) {
        switch (rule.ruleType()) {
            case LENGTH:
                return new LengthCheck(rule, weakPassCheck);
            case PHYSICAL_ORDER:
                return new PhysicalOrderCheck(rule, weakPassCheck);
            case SAME:
                return new SameCheck(rule, weakPassCheck);
            case LOGIC_ORDER:
                return new LogicOrderCheck(rule, weakPassCheck);
            case REGEX:
                return new RegexCheck(rule, weakPassCheck);
            case LOOP:
                return new LoopCheck(rule, weakPassCheck);
            case DIC:
                return new DicCheck(rule, weakPassCheck);
        }
        return null;
    }
}
