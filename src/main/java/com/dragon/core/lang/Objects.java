package com.dragon.core.lang;

import java.io.Closeable;
import java.io.IOException;
import java.text.NumberFormat;

/**
 * @ClassName: Objects
 * @Description: Objects
 * @Author: pengl
 * @Date: 2020/4/1 20:31
 * @Version V1.0
 */
public class Objects {
    /**
     * @MethodName: nullSafeClose
     * @Description: 关闭 resource
     * @Author: pengl
     * @Date: 2020/4/1 20:32
     * @Version V1.0
     */
    public static void nullSafeClose(Closeable... closeables) {
        if (closeables == null) {
            return;
        }

        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * @MethodName: percentage
     * @Description: 百分比计算
     * @Author: pengl
     * @Date: 2020/4/4 22:16
     * @Version V1.0
     */
    public static String percentage(long a, long b, int decimal){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(decimal);
        return format.format((float) a / (float) b * 100);
    }
}
