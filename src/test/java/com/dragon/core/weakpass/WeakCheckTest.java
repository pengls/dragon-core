package com.dragon.core.weakpass;

import com.alibaba.fastjson.JSON;
import com.dragon.core.weakpass.impl.rule.*;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author eason peng
 */
public class WeakCheckTest {
    @Test
    public void allTest(){
        String[] testPass = {
                null,
                "",
                "123456",
                "12345678",
                "abcdefgh",
                "123abc456",
                "1231adf@",
                "12341adf@",
                "fdahuier243335ddfa#$*&",
                "aBcd1859d4!@",
                "zaq13edfgt#",
                "Bgt5sj4#"
        };

    }


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

    @Test
    public void tt3(){
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new SameRule().num(5).ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("QqqqQ1231").build().check();
        if(!flag){
            System.out.println(errMap);
        }
    }

    @Test
    public void tt4(){
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new LogicOrderRule().num(5).ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("1234AbCDE4").build().check();
        if(!flag){
            System.out.println(errMap);
        }
    }

    @Test
    public void tt5(){
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new RegexRule().regex("\\d{8}"))
                .throwException(false)
                .errorMap(errMap)
                .passData("12345671").build().check();
        if(!flag){
            System.out.println(errMap);
        }
    }

    @Test
    public void tt6(){

        String json = "[{\"ruleType\":\"RegexRule\",\"regex\":\".*?[a-z]+.*?\"},{\"ruleType\":\"RegexRule\",\"regex\":\".*?[A-Z]+.*?\"}]";

        Map<Integer, String> errMap = Maps.newHashMap();

        boolean flag = WeakPassCheck.builder()
                .ruleString(json)
                .throwException(false)
                .errorMap(errMap)
                .passData("A123A").build().check();
        if(!flag){
            System.out.println(errMap);
        }

    }
}
