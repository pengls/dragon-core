package com.dragon.core.algorithm;

import org.junit.Test;

/**
 * @ClassName: KMPIndex
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/5/4 6:54
 * @Version V1.0
 */
public class KMPIndex {
    @Test
    public void t1(){
        String s = "我爱中国共产党";
        String ss = "共产党";
        System.out.println(s.indexOf(ss));
    }
}
