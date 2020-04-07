package com.dragon.core.weakpass;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName: RuleCheckContext
 * @Description: rule check 上下文
 * @Author: pengl
 * @Date: 2020/4/7 22:15
 * @Version V1.0
 */
@Data
@Builder
public class RuleCheckContext {
    /**
     * strategy ref
     */
    private RuleCheckStrategy ruleCheckStrategy;

    private String passData;

    private WeakRule rule;

    private boolean throwException;

    private Map<Integer, String> errorMap;

    private String ruleString;


    public boolean check() {
        return false;
    }
}
