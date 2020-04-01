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
    public String encryptString(String data) {
        return encryptString(CryptoParam.builder().data(data).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String decryptString(String data) {
        return decryptString(CryptoParam.builder().data(data).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String encryptString(CryptoParam builder) {
        return StringUtils.isBlank(builder.getData()) ? null : org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(builder.getData().getBytes(builder.getCharset()));
    }

    @Override
    public byte[] encryptBytes(CryptoParam param) {
        return org.apache.commons.codec.binary.Base64.encodeBase64URLSafe(param.getData().getBytes(param.getCharset()));
    }

    @Override
    public String decryptString(CryptoParam builder) {
        return StringUtils.isBlank(builder.getData()) ? null : org.apache.commons.codec.binary.StringUtils.newString(org.apache.commons.codec.binary.Base64.decodeBase64(builder.getData().getBytes()), builder.getCharset().name());
    }

    @Override
    public byte[] decryptBytes(CryptoParam param) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(param.getData().getBytes(param.getCharset()));
    }

    @Override
    public Algorithm current() {
        return Algorithm.Base64;
    }
}
