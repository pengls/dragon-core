package com.dragon.core.weakpass;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dragon.core.lang.Assert;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WeakPassCheck
 * @Description: WeakPassCheck
 * @Author: pengl
 * @Date: 2020/4/3 22:02
 * @Version V1.0
 */
@Slf4j
@Builder
@Data
public class WeakPassCheck {
    /**
     * 弱密码规则
     */
    @Singular
    private List<WeakRule> rules;
    /**
     * 密码
     */
    private String passData;
    /**
     * 校验不通过时是否抛异常
     */
    @Builder.Default
    private boolean throwException = false;
    /**
     * 校验不通过信息收集
     */
    private Map<Integer, String> errorMap;
    /**
     * 字符串方式定义的弱密码规则
     * 格式模版：
     * [{
     * "ruleType":"RegexRule",
     * "regex": "\\d{8}"
     * }, {
     * "ruleType":"LengthRule",
     * "max": 20,
     * "min": 10
     * }, {
     * "ruleType":"SameRule",
     * "ignoreCase": true,
     * "num": 10
     * }]
     */
    private String ruleString;

    public boolean check() {
        Assert.notBlank(passData, "password is blank");

        addStringRules();

        if (CollectionUtils.isEmpty(rules)) {
            return true;
        }

        for (WeakRule rule : rules) {
            RuleCheck ruleCheck = RuleCheckFactory.getRuleCheck(rule, this);
            Assert.notNull(ruleCheck, "unsupported rule type");
            if (!ruleCheck.check()) {
                return false;
            }
        }

        return true;
    }

    public void addStringRules() {
        if (StringUtils.isBlank(ruleString)) {
            return;
        }
        JSONArray ruleArray;
        try {
            ruleArray = JSONArray.parseArray(ruleString);
        } catch (Exception e) {
            log.error("[弱密码校验]规则字符串配置有误，ruleString：{} 无法解析为Array！", ruleString);
            return;
        }
        if (CollectionUtils.isEmpty(ruleArray)) {
            return;
        }

        /**
         * lombok在处理List的时候，会采用java.util.Collections里的内部List类：SingletonList/EmptyList
         * 这里需要做一个转换，不然无法add元素到List中的
         */
        List<WeakRule> newList = Lists.newArrayList(rules);
        rules = newList;

        for (int i = 0; i < ruleArray.size(); i++) {
            JSONObject obj = ruleArray.getJSONObject(i);
            String ruleType = obj.getString("ruleType");
            if (StringUtils.isBlank(ruleType)) {
                log.error("[弱密码校验]规则字符串配置有误，缺少ruleType：{}", obj.toJSONString());
                continue;
            }
            try {
                rules.add(obj.toJavaObject(RuleFactory.getRuleClass(ruleType)));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[弱密码校验]规则字符串解析异常：{}", e.getMessage(), e);
                continue;
            }
        }
    }
}

