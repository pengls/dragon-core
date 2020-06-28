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

    public static String toBinaryString(int num) {
        return toBinaryString(num, false);
    }

    public static String toBinaryString(int num, boolean simple) {
        return toBinaryString(Integer.valueOf(num), simple);
    }

    public static String toBinaryString(long num) {
        return toBinaryString(num, false);
    }

    public static String toBinaryString(long num, boolean simple) {
        return toBinaryString(Long.valueOf(num), simple);
    }

    private static String toBinaryString(Number num, boolean simple) {
        int size = num instanceof Long ? Long.SIZE : Integer.SIZE;
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sBuilder.append(num.longValue() & 1);
            num = num.longValue() >>> 1;
        }
        String result = sBuilder.reverse().toString();
        if (simple) {
            int index = result.indexOf("1");

            result = index == -1 ? "0" : result.substring(index);
        }
        return result;
    }

}
