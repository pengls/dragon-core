package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.Crypto;
import com.dragon.core.crypto.CryptoParam;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: Base64
 * @Description: base64 base on common-codec
 * @Author: pengl
 * @Date: 2020/3/27 21:11
 * @Version V1.0
 */
public class Base64 implements Crypto {
    @Override
    public String encrypt(String data) {
        return encrypt(CryptoParam.builder().data(data).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String decrypt(String data) {
        return decrypt(CryptoParam.builder().data(data).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String encrypt(CryptoParam builder) {
        return StringUtils.isBlank(builder.getData()) ? null : org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(builder.getData().getBytes(builder.getCharset()));
    }

    @Override
    public String decrypt(CryptoParam builder) {
        return StringUtils.isBlank(builder.getData()) ? null : org.apache.commons.codec.binary.StringUtils.newString(org.apache.commons.codec.binary.Base64.decodeBase64(builder.getData().getBytes()), builder.getCharset().name());
    }

    @Override
    public Algorithm current() {
        return Algorithm.Base64;
    }
}
