package com.dragon.core.crypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: Crypto
 * @Description: crypto interface
 * @Author: pengl
 * @Date: 2020/3/27 20:58
 * @Version V1.0
 */
public interface Crypto {
    /**
     * default charset
     */
    static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * default key
     */
    static final String DEFAULT_KEY = "asdi!@#$%^&*~()_+AYHB";

    /**
     * @MethodName: encryt
     * @Description: encryt data
     * @Author: pengl
     * @Date: 2020/3/27 20:59
     * @Version V1.0
     */
    String encrypt(String data);

    /**
     * @MethodName: decryt
     * @Description: decryt data
     * @Author: pengl
     * @Date: 2020/3/27 20:59
     * @Version V1.0
     */
    default String decrypt(String data){
        throw new CryptoException("Unsupported Method !");
    }

    /**
     * @MethodName: encrypt
     * @Description: encrypt
     * @Author: pengl
     * @Date: 2020/3/28 22:50
     * @Version V1.0
     */
    String encrypt(CryptoParam param);

    /**
     * @MethodName: decrypt
     * @Description: decrypt
     * @Author: pengl
     * @Date: 2020/3/28 23:16
     * @Version V1.0
     */
    default String decrypt(CryptoParam param){
        throw new CryptoException("Unsupported Method !");
    }


    /**
     * @MethodName: current
     * @Description: return current algorithm
     * @Author: pengl
     * @Date: 2020/3/27 21:04
     * @Version V1.0
     */
    Algorithm current();

    default SecretKey toKey(final String key){
        return new SecretKeySpec(key.getBytes(), current().getCode());
    }

    default void warn(){
    }

}
