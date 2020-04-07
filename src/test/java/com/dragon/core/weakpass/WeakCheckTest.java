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
    public void allTest() {
        String[] testPass = {
                "123456",  //长度不够
                "ABC10OJS", //没有小写
                "abc12sada", //没有大写
                "12345678", //没有字母
                "Adsw1342", //没有特殊字符
                "aaaaa1Ac&", //相同字符5个
                "aaaa1Ac%", //相同字符4个
                "abcdefgh1G@", //排序
                "asdfghj12QQ)", //物理键盘横向
                "abcddcbaABC123@", //回文
                "fuck123FUCK@", //字典
                "qweas21QKS@"
        };

        Map<Integer, String> errMap = Maps.newHashMap();
        for (String password : testPass) {
            try{
                boolean flag = WeakPassCheck.builder()
                        .rule(new LengthRule().min(8))
                        .rule(new RegexRule().regex(".*[a-z]+.*"))
                        .rule(new RegexRule().regex(".*[0-9]+.*"))
                        .rule(new RegexRule().regex(".*[A-Z].*"))
                        .rule(new RegexRule().regex(".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]+.*"))
                        .rule(new SameRule().num(5).ignoreCase(true))
                        .rule(new LogicOrderRule().num(5))
                        .rule(new PhysicalOrderRule().horizontal_num(6))
                        .rule(new LoopRule().num(4))
                        .rule(new DicRule().dics("welcome,abcdef,fuck"))
                        .passData(password)
                        .throwException(false)
                        .errorMap(errMap)
                        .build().check();
                if (!flag) {
                    System.out.println(errMap);
                    errMap.clear();
                }else{
                    System.out.println("SUCCESS ==>>> " + password);
                }
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }


    }


    @Test
    public void tt1() {
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder().rule(new LengthRule().min(8).max(20))
                .throwException(false)
                .errorMap(errMap)
                .passData("1234").build().check();
        if (!flag) {
            System.out.println(errMap);
        }
    }

    @Test
    public void tt2() {
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new PhysicalOrderRule().horizontal_num(5).slope_num(4))
                .throwException(false)
                .errorMap(errMap)
                .passData("1QAZ2WS1").build().check();
        if (!flag) {
            System.out.println(errMap);
        }
    }

    @Test
    public void tt3() {
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new SameRule().ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("QqqqQqqqqQ").build().check();
        if (!flag) {
            System.out.println(errMap);
        }
    }

    @Test
    public void tt4() {
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new LengthRule().min(8).max(20))
                .rule(new LogicOrderRule().num(5).ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("1234AbCDE4").build().check();
        if (!flag) {
            System.out.println(errMap);
        }
    }

    @Test
    public void tt5() {
        Map<Integer, String> errMap = Maps.newHashMap();
        boolean flag = WeakPassCheck.builder()
                .rule(new RegexRule().regex("\\d{8}"))
                .throwException(false)
                .errorMap(errMap)
                .passData("12345671").build().check();
        if (!flag) {
            System.out.println(errMap);
        }
    }

    @Test
    public void tt6() {

        String json = "[{\"ruleType\":\"RegexRule\",\"regex\":\".*?[a-z]+.*?\"},{\"ruleType\":\"RegexRule\",\"regex\":\".*?[A-Z]+.*?\"}]";

        Map<Integer, String> errMap = Maps.newHashMap();

        boolean flag = WeakPassCheck.builder()
                .ruleString(json)
                .throwException(false)
                .errorMap(errMap)
                .passData("A123A").build().check();
        if (!flag) {
            System.out.println(errMap);
        }

    }

    @Test
    public void tt7() {

        Map<Integer, String> errMap = Maps.newHashMap();

        boolean flag = WeakPassCheck.builder()
                .rule(new LoopRule().num(4).ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("WelcomeA123321a!2323").build().check();
        if (!flag) {
            System.out.println(errMap);
        }

    }

    @Test
    public void tt8() {

        Map<Integer, String> errMap = Maps.newHashMap();

        boolean flag = WeakPassCheck.builder()
                .rule(new DicRule().dics("abc,poa,AAC").ignoreCase(true))
                .throwException(false)
                .errorMap(errMap)
                .passData("ABC123").build().check();
        if (!flag) {
            System.out.println(errMap);
        }

    }
}
