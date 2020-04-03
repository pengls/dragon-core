package com.dragon.core.weakpass;

import com.dragon.core.lang.Assert;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WeakPassCheck
 * @Description: WeakPassCheck
 * @Author: pengl
 * @Date: 2020/4/3 22:02
 * @Version V1.0
 */
@Builder
@Data
public class WeakPassCheck {
    @Singular
    private List<WeakRule> rules;
    private String passData;
    @Builder.Default
    private boolean throwException = false;
    private Map<Integer, String> errorMap;

    public boolean check() {
        Assert.notBlank(passData, "password is blank");

        if (CollectionUtils.isEmpty(rules)) {
            return true;
        }

        for (WeakRule rule : rules) {
            RuleCheck ruleCheck = RuleCheckFactory.getRuleCheck(rule.ruleType(), this);
            if (!ruleCheck.check()) {
                return false;
            }
        }

        return true;
    }
}

