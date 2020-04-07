package com.dragon.core.weakpass;

import com.dragon.core.weakpass.impl.check.*;

/**
 * @ClassName: RuleCheckStrategyFactory
 * @Description: 策略工厂
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
    public static final RuleCheckStrategy getRuleCheckStrategy(WeakRule rule) {
        switch (rule.ruleType()) {
            case LENGTH:
                return new LengthCheck();
            case PHYSICAL_ORDER:
                return new PhysicalOrderCheck();
            case SAME:
                return new SameCheck();
            case LOGIC_ORDER:
                return new LogicOrderCheck();
            case REGEX:
                return new RegexCheck();
            case LOOP:
                return new LoopCheck();
            case DIC:
                return new DicCheck();
            case KEYWORDS_COMBINATION:
                return new KeywordsCombinationCheck();
        }
        return null;
    }
}
