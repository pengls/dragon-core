package com.dragon.core.lang.bit;

/**
 * @ClassName: BitUtil
 * @Description: BitUtil
 * @Author: pengl
 * @Date: 2020/6/27 12:00
 * @Version V1.0
 */
public final class BitUtil {

    public static boolean isEven(int n) {
        return !isOdd(n);
    }

    /**
     * check the last bit is 1.
     *
     * @param n
     * @return
     */
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    public static String toBinaryString(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value);
        return bs.substring(1);
    }

    public static String toBinaryString(int num) {
        return toBinaryString(num, 1 << 5 - 1);
    }

    public static String toBinaryString(long num, int digits) {
        long value = 1 << digits | num;
        String bs = Long.toBinaryString(value);
        return bs.substring(1);
    }

    public static String toBinaryString(long num) {
        return toBinaryString(num, 1 << 5 - 1);
    }

}
