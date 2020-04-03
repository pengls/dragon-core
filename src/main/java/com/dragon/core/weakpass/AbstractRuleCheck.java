package com.dragon.core.weakpass;

/**
 * @ClassName: AbstractRuleCheck
 * @Description: AbstractRuleCheck
 * @Author: pengl
 * @Date: 2020/4/3 22:01
 * @Version V1.0
 */
public abstract class AbstractRuleCheck implements RuleCheck {

    protected WeakPassCheck weakPassCheck;

    public AbstractRuleCheck(WeakPassCheck weakPassCheck) {
        this.weakPassCheck = weakPassCheck;
    }

    protected void handleException(int code, String msg) {
        if (weakPassCheck.isThrowException()) {
            throw new WeakCheckException(msg);
        }

        if (weakPassCheck.getErrorMap() != null) {
            weakPassCheck.getErrorMap().put(code, msg);
        }
    }

    protected WeakRule getWeakRule() {
        return weakPassCheck.getRules().stream().filter(rule -> rule.ruleType() == ruleType()).findAny().get();
    }
}
