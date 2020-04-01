package com.dragon.core.crypto;

import org.apache.commons.codec.binary.Hex;
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
    public String encryptString(String data) {
        warn();
        return encryptString(CryptoParam.builder().data(data).key(DEFAULT_KEY).build());
    }

    @Override
    public String encryptString(CryptoParam param) {
        return mac(param);
    }

    private String mac(CryptoParam param) {
        param.checkData();
        if(StringUtils.isBlank(param.getData())){
            return null;
        }
        String key = StringUtils.isBlank(param.getKey()) ? DEFAULT_KEY : param.getKey();
        try {
            Mac mac = Mac.getInstance(current().getCode());
            mac.init(toKey(key));
            return Hex.encodeHexString(mac.doFinal(param.getData().getBytes(param.getCharset())));
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }
}
