package com.dragon.core.lang;

import org.junit.Test;

/**
 * @ClassName: AssertTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/3/31 21:24
 * @Version V1.0
 */
public class AssertTest {
    @Test
    public void assertTest(){
        //Assert.allNotBlank("fuck", "2", "1");
        //Assert.allNotBlank(() -> "fuck", "2", "");
        //Assert.startWith("abvc", "ac", "fuck");
        //Assert.startWithIgnoreCase("abvc", "A", "fuck");
    }

    @Test
    public void t1(){
        int a = 128;
        int b = 129;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

        int c = a & b;
        System.out.println(Integer.toBinaryString(c));

        int d = a | b;
        System.out.println(Integer.toBinaryString(d));

        int e = ~a;
        System.out.println(Integer.toBinaryString(e));

    }
}
