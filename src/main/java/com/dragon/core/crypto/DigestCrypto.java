package com.dragon.core.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: DigestCrypto
 * @Description: 消息摘要算法
 * @Author: pengl
 * @Date: 2020/3/28 15:36
 * @Version V1.0
 */
public abstract class DigestCrypto implements Crypto{

    @Override
    public String encryptString(String data) {
        return encryptString(CryptoParam.builder().data(data).charset(DEFAULT_CHARSET).build());
    }

    @Override
    public String encryptString(CryptoParam param){
        param.checkData();
        String data = param.getData();
        if(StringUtils.isBlank(data)){
            return null;
        }
        return Hex.encodeHexString(getMessageDigest(current()).digest(data.getBytes(param.getCharset())));
    }

    /**
     * @MethodName: getMessageDigest
     * @Description: jdk MessageDigest
     * @Author: pengl
     * @Date: 2020/3/27 21:19
     * @Version V1.0
     */
    private MessageDigest getMessageDigest(Algorithm algorithm) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(algorithm.getCode());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return messageDigest;
    }
}
