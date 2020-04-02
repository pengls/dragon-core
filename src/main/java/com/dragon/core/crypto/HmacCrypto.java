package com.dragon.core.crypto;

import com.dragon.core.lang.Assert;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;

/**
 * @ClassName: HmacCrypto
 * @Description: 带密钥的消息摘要算法
 * @Author: pengl
 * @Date: 2020/3/28 15:40
 * @Version V1.0
 */
public abstract class HmacCrypto implements Crypto{

    @Override
    public byte[] encrypt(CryptoParam param) {
        return mac(param);
    }

    @Override
    public byte[] encrypt(byte[] data) {
        return encrypt(CryptoParam.builder().data(data).build());
    }


    private byte[] mac(CryptoParam param) {
        byte[] data = param.getData();
        Assert.notEmpty(data, "data is null or empty");
        String key = StringUtils.isBlank(param.getKey()) ? DEFAULT_KEY : param.getKey();
        try {
            Mac mac = Mac.getInstance(current().getCode());
            mac.init(toKey(key));
            return mac.doFinal(param.getData());
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }
}
