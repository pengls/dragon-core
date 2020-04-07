package com.dragon.core.weakpass;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: WeakRuleClassFactory
 * @Description: 用于通过字符串找到对应的Rule class
 * @Author: pengl
 * @Date: 2020/4/4 12:13
 * @Version V1.0
 */
public final class WeakRuleClassFactory {
    private static final String PACKAGE_NAME = "com.dragon.core.weakpass.impl";

    private WeakRuleClassFactory() {
    }

    private static Map<String, Class> ruleClassMap = null;

    static {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<? extends WeakRule>> subClasses = reflections.getSubTypesOf(WeakRule.class);
        if (CollectionUtils.isNotEmpty(subClasses)) {
            ruleClassMap = Maps.newHashMapWithExpectedSize(subClasses.size());
            for (Class<? extends WeakRule> ruleClass : subClasses) {
                ruleClassMap.put(ruleClass.getSimpleName(), ruleClass);
            }
        }
    }

    public static final Class<? extends WeakRule> getWeakRuleClass(String ruleType) {
        if (null == ruleClassMap) {
            throw new WeakCheckException("WeakRule Not Found !");
        }
        Class ruleClass = ruleClassMap.get(ruleType);
        return ruleClass;
    }

}
