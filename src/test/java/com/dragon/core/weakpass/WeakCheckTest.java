package com.dragon.core.weakpass;

import com.dragon.core.weakpass.impl.rule.LengthRule;
import com.dragon.core.weakpass.impl.rule.PhysicalOrderRule;
import com.google.common.collect.Maps;
import org.junit.Test;
import java.util.Map;

/**
 * @author eason peng
 */
public class WeakCheckTest {
    @Test
    public void tt1(){
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder().rule(new LengthRule().min(8).max(20))
                .throwException(false)
                .errorMap(errMap)
                .passData("1234").build().check();
        if(!flag){
            System.out.println(errMap);
        }
    }

    @Test
    public void tt2(){
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new PhysicalOrderRule().horizontal_num(5).slope_num(4))
                .throwException(false)
                .errorMap(errMap)
                .passData("1QAZ2WS1").build().check();
        if(!flag){
            System.out.println(errMap);
        }
    }
}
