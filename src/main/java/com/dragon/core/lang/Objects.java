package com.dragon.core.lang;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: Objects
 * @Description: Objects
 * @Author: pengl
 * @Date: 2020/4/1 20:31
 * @Version V1.0
 */
public final class Objects {

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

    public static String percentage(long a, long b, int decimal){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(decimal);
        return format.format((float) a / (float) b * 100);
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence)object).length() == 0;
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else {
            return object instanceof Map ? ((Map)object).isEmpty() : false;
        }
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
