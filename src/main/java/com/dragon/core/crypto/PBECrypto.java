package com.dragon.core.crypto;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @ClassName: PBECrypto
 * @Description: PBECrypto
 * @Author: pengl
 * @Date: 2020/3/28 16:48
 * @Version V1.0
 */
public abstract class PBECrypto implements Crypto {
    /**
     * default salt, size:8 (PBEWithMd5AndDes)
     */
    protected static final String DEFAULT_SALT_8 = "ks*&%$)1";

    private static final int SALT_SIZE = 8;

    private static final int CYCLE_TIMES = 100;

    @Override
    public SecretKey toKey(final String key) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(current().getCode());
            return keyFactory.generateSecret(pbeKeySpec);
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    @Override
    public String encryptString(String data) {
        warn();
        return encryptString(CryptoParam.builder().data(data).build());
    }

    @Override
    public String decryptString(String data) {
        warn();
        return decryptString(CryptoParam.builder().data(data).build());
    }

    @Override
    public String encryptString(CryptoParam param) {
        param.checkData();
        if (StringUtils.isBlank(param.getData())) {
            return null;
        }
        setDefault(param);
        PBEParameterSpec paramSpec = new PBEParameterSpec(param.getSalt().getBytes(), CYCLE_TIMES);
        try {
            Cipher cipher = Cipher.getInstance(current().getCode());
            cipher.init(Cipher.ENCRYPT_MODE, toKey(param.getKey()), paramSpec);
            return Hex.encodeHexString(cipher.doFinal(param.getData().getBytes(param.getCharset())));
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    @Override
    public String decryptString(CryptoParam param) {
        if (StringUtils.isBlank(param.getData())) {
            return null;
        }
        setDefault(param);
        PBEParameterSpec paramSpec = new PBEParameterSpec(param.getSalt().getBytes(), CYCLE_TIMES);
        try {
            byte[] mingwen = Hex.decodeHex(param.getData());
            Cipher cipher = Cipher.getInstance(current().getCode());
            cipher.init(Cipher.DECRYPT_MODE, toKey(param.getKey()), paramSpec);
            return new String(cipher.doFinal(mingwen), param.getCharset());
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }

    private CryptoParam setDefault(CryptoParam param) {
        param.setKey(StringUtils.isBlank(param.getKey()) ? DEFAULT_KEY : param.getKey());
        String salt = StringUtils.isBlank(param.getSalt()) ? DEFAULT_SALT_8 : param.getSalt();
        if (salt.length() != SALT_SIZE) {
            throw new CryptoException("the salt size must be " + SALT_SIZE);
        }
        param.setSalt(salt);
        return param;
    }
}
