package com.dragon.core.lang.bit;

/**
 * @ClassName: BitSetUtil
 * @Description: BitSetUtil , set/unset/check int/long bit
 * @Author: pengl
 * @Date: 2020/6/27 20:45
 * @Version V1.0
 */
public final class BitSetUtil {

    public static int set(int option, int n) {
        return (option | (1 << n));
    }

    public static long set(long option, int n) {
        return (option | (1L << n));
    }

    public static int unset(int option, int n) {
        return (option & ~(1 << n));
    }

    public static long unset(long option, int n) {
        return (option & ~(1L << n));
    }

    public static boolean isSetted(int option, int n) {
        return ((option & (1 << n)) != 0);
    }

    public static boolean isSetted(long option, int n) {
        return ((option & (1L << n)) != 0);
    }

}
