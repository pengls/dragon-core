package com.dragon.core.weakpass;

import com.dragon.core.weakpass.impl.check.LengthCheck;
import com.dragon.core.weakpass.impl.check.PhysicalOrderCheck;

/**
 * @ClassName: RuleCheckFactory
 * @Description: RuleCheckFactory
 * @Author: pengl
 * @Date: 2020/4/3 22:02
 * @Version V1.0
 */
public abstract class RuleCheckFactory {

    /**
     * @MethodName: getRuleCheck
     * @Description: getRuleCheck Instance
     * @Author: pengl
     * @Date: 2020/4/3 22:16
     * @Version V1.0
     */
    public static final RuleCheck getRuleCheck(RuleType ruleType, WeakPassCheck weakPassCheck) {
        switch (ruleType) {
            case LENGTH:
                return new LengthCheck(weakPassCheck);
            case PHYSICAL_ORDER:
                return new PhysicalOrderCheck(weakPassCheck);
        }

        return null;
    }
}
