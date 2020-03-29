package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.Crypto;
import com.dragon.core.crypto.CryptoParam;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: CRC32
 * @Description: CRC32
 * @Author: pengl
 * @Date: 2020/3/28 10:55
 * @Version V1.0
 */
public class CRC32 implements Crypto {

    @Override
    public String encrypt(String str) {
        return encrypt(CryptoParam.builder().data(str).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String encrypt(CryptoParam builder) {
        String data = builder.getData();
        if(StringUtils.isBlank(data)){
            return null;
        }
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(data.getBytes(builder.getCharset()));
        return Long.toHexString(crc32.getValue());
    }

    @Override
    public Algorithm current() {
        return Algorithm.CRC32;
    }
}
