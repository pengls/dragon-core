package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.Crypto;
import com.dragon.core.crypto.CryptoParam;
import com.dragon.core.lang.Assert;

/**
 * @ClassName: CRC32
 * @Description: CRC32
 * @Author: pengl
 * @Date: 2020/3/28 10:55
 * @Version V1.0
 */
public class CRC32 implements Crypto {

    @Override
    public byte[] encrypt(CryptoParam param) {
        byte[] data = param.getData();
        Assert.notEmpty(data, "data is null or empty");
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(data);
        return Long.toHexString(crc32.getValue()).getBytes();
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return encrypt(CryptoParam.builder().data(data).build());
    }

    @Override
    public Algorithm current() {
        return Algorithm.CRC32;
    }
}
