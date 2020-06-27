package com.dragon.core.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: StrUtils
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/2 22:37
 * @Version V1.0
 */
public final class StrUtil {

    public static String newString(byte[] bytes, Charset charset) {
        return bytes == null ? null : ByteUtil.toString(bytes, charset);
    }

    public static String newStringUtf8(byte[] bytes) {
        return ByteUtil.toString(bytes);
    }


}
