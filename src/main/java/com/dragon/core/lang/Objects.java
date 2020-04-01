package com.dragon.core.lang;

import java.io.Closeable;
import java.io.IOException;

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
     * @Description: close resource
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
}
