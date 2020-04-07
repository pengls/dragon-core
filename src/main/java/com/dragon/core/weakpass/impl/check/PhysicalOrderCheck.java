package com.dragon.core.weakpass.impl.check;

import com.dragon.core.lang.Assert;
import com.dragon.core.weakpass.*;
import com.dragon.core.weakpass.impl.rule.PhysicalOrderRule;

/**
 * @ClassName: PhysicalOrderCheck
 * @Description: 物理键盘连续检测：横向、纵向
 * @Author: pengl
 * @Date: 2020/4/3 22:40
 * @Version V1.0
 */
public class PhysicalOrderCheck extends AbstractRuleCheckStrategy {

    public PhysicalOrderCheck(WeakRule rule, WeakPassCheck weakPassCheck) {
        super(rule, weakPassCheck);
    }

    @Override
    public boolean check() {
        String passData = weakPassCheck.getPassData();
        Assert.notBlank(passData, "password is blank");
        PhysicalOrderRule rule = (PhysicalOrderRule) weakRule;
        if (checkKeyboard(passData, KEYBOARD_HORIZONTAL_ARR, 1, rule)) {
            handleException(ErrorReturn.PHYSICAL_ORDER_CHECK_1.getCode(), ErrorReturn.PHYSICAL_ORDER_CHECK_1.getErrorMsg());
            return false;
        }
        if (checkKeyboard(passData, KEYBOARD_SLOPE_ARR, 2, rule)) {
            handleException(ErrorReturn.PHYSICAL_ORDER_CHECK_2.getCode(), ErrorReturn.PHYSICAL_ORDER_CHECK_2.getErrorMsg());
            return false;
        }
        return true;
    }

    /**
     * 键盘横向方向规则
     */
    private static final String[] KEYBOARD_HORIZONTAL_ARR = {
            "01234567890",
            "qwertyuiop",
            "asdfghjkl",
            "zxcvbnm",
    };
    /**
     * 键盘斜线方向规则
     */
    private static final String[] KEYBOARD_SLOPE_ARR = {
            "1qaz",
            "2wsx",
            "3edc",
            "4rfv",
            "5tgb",
            "6yhn",
            "7ujm",
            "8ik,",
            "9ol.",
            "0p;/",
            "=[;.",
            "-pl,",
            "0okm",
            "9ijn",
            "8uhb",
            "7ygv",
            "6tfc",
            "5rdx",
            "4esz"
    };

    private boolean checkKeyboard(String password, String[] attrs, int type, PhysicalOrderRule rule) {
        int n = password.length();
        int arrLen = attrs.length;
        int limit_num;
        if (type == 1) {
            limit_num = rule.getHorizontal_num();
            limit_num = limit_num <= 0 ? n : limit_num;
        } else {
            limit_num = rule.getSlope_num();
            limit_num = limit_num <= 0 ? 4 : limit_num;
        }


        for (int i = 0; i + limit_num <= n; i++) {
            String str = password.substring(i, i + limit_num);
            for (int j = 0; j < arrLen; j++) {
                String configStr = attrs[j];
                String revOrderStr = new StringBuffer(attrs[j]).reverse().toString();

                String UpperStr = attrs[j].toUpperCase();
                if ((configStr.indexOf(str) != -1) || (UpperStr.indexOf(str) != -1)) {
                    return true;
                }
                String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                if ((revOrderStr.indexOf(str) != -1) || (revUpperStr.indexOf(str) != -1)) {
                    return true;
                }
            }
        }
        return false;
    }

}
