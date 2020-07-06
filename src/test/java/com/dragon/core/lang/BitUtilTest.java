package com.dragon.core.lang;

import com.dragon.core.lang.bit.BitSetUtil;
import com.dragon.core.lang.bit.BitUtil;
import org.junit.Test;

/**
 * @ClassName: BitUtilTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/6/27 12:02
 * @Version V1.0
 */
public class BitUtilTest {
    static final long FEATURE_1 = 0x1L;
    static final long FEATURE_2 = 0x2L;
    static final long FEATURE_3 = 0x4L;
    static final long FEATURE_4 = 0x8L;
    static final long FEATURE_5 = 0x10L;
    static final long FEATURE_6 = 0x20L;
    static final long FEATURE_7 = 0x40L;
    static final long FEATURE_8 = 0x80L;
    static final long FEATURE_9 = 0x100L;

    static final long FEATURE_LAST = 0x8000000000000000L;

    @Test
    public void t1(){
        System.out.println(BitUtil.isOdd(5));
        System.out.println(BitUtil.toBinaryString(FEATURE_1));
        System.out.println(BitUtil.toBinaryString(FEATURE_2));
        System.out.println(BitUtil.toBinaryString(FEATURE_3));
        System.out.println(BitUtil.toBinaryString(FEATURE_4));
        System.out.println(BitUtil.toBinaryString(FEATURE_5));
        System.out.println(BitUtil.toBinaryString(FEATURE_6));
        System.out.println(BitUtil.toBinaryString(FEATURE_7));
        System.out.println(BitUtil.toBinaryString(FEATURE_8));
        System.out.println(BitUtil.toBinaryString(FEATURE_9));
        System.out.println(BitUtil.toBinaryString(FEATURE_LAST));
    }

    @Test
    public void t2(){
        System.out.println(0x1);
    }

    @Test
    public void t3(){
        int status = 0;
        System.out.println("init status : " + BitUtil.toBinaryString(status, true));
        status = BitSetUtil.set(status, 2);
        System.out.println("after school sign : " + BitUtil.toBinaryString(status, true));

        System.out.println("check student sign : " + BitSetUtil.isSetted(status, 0));
        System.out.println("check company sign : " + BitSetUtil.isSetted(status, 1));
        System.out.println("check school sign : " + BitSetUtil.isSetted(status, 2));
        System.out.println("--------------------------");

        status = BitSetUtil.set(status, 0);
        System.out.println("after student sign : " + BitUtil.toBinaryString(status, true));

        System.out.println("check student sign : " + BitSetUtil.isSetted(status, 0));
        System.out.println("check company sign : " + BitSetUtil.isSetted(status, 1));
        System.out.println("check school sign : " + BitSetUtil.isSetted(status, 2));
        System.out.println("--------------------------");


        status = BitSetUtil.set(status, 1);
        System.out.println("after company sign : " + BitUtil.toBinaryString(status, true));

        System.out.println("check student sign : " + BitSetUtil.isSetted(status, 0));
        System.out.println("check company sign : " + BitSetUtil.isSetted(status, 1));
        System.out.println("check school sign : " + BitSetUtil.isSetted(status, 2));
    }



}
