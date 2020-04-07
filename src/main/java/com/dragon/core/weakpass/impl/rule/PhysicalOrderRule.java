package com.dragon.core.weakpass.impl.rule;

import com.dragon.core.weakpass.RuleType;
import com.dragon.core.weakpass.WeakRule;
import lombok.Data;

/**
 * @ClassName: PhysicalOrderRule
 * @Description: 键盘物理键相邻规则
 * @Author: pengl
 * @Date: 2020/4/3 22:10
 * @Version V1.0
 */
@Data
public class PhysicalOrderRule implements WeakRule {
    /**
     * 横向不允许的最小连续个数
     */
    private int horizontal_num;
    /**
     * 斜向不允许的最小连续个数
     */
    private int slope_num;

    public PhysicalOrderRule horizontal_num(int horizontal_num) {
        this.horizontal_num = horizontal_num;
        return this;
    }

    public PhysicalOrderRule slope_num(int slope_num) {
        this.slope_num = slope_num;
        return this;
    }

    @Override
    public RuleType ruleType() {
        return RuleType.PHYSICAL_ORDER;
    }
}
