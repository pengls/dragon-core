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
     * 横向不允许的最小连续个数， 如果不传或传入0，表示不验证此项
     */
    private int horizontal_num;
    /**
     * 斜向不允许的最小连续个数， 如果不传或传入0，表示不验证此项
     */
    private int slope_num;

    /**
     * 是否忽略大小写
     * TODO 不考虑此规则，要么全部大小，要么全部小写，如果是大小写混写的，不算作弱密码
     */
    //private boolean ignoreCase;

    public PhysicalOrderRule horizontal_num(int horizontal_num) {
        this.horizontal_num = horizontal_num;
        return this;
    }

    public PhysicalOrderRule slope_num(int slope_num) {
        this.slope_num = slope_num;
        return this;
    }

    /*public PhysicalOrderRule ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }*/

    @Override
    public RuleType ruleType() {
        return RuleType.PHYSICAL_ORDER;
    }
}
