package com.dragon.core.crypto;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @ClassName: RSACoder
 * @Description: RSACoder
 * @Author: pengl
 * @Date: 2020/3/28 20:16
 * @Version V1.0
 */
public abstract class RSACoder {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublickKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    private static final int KEY_SIZE = 1024;

    /**
     * @MethodName: initKey
     * @Description: init key
     * @Author: pengl
     * @Date: 2020/3/28 20:35
     * @Version V1.0
     */
    public static Map<String, Object> initKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = Maps.newHashMapWithExpectedSize(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);

            return keyMap;
        } catch (Exception e) {
            throw new CryptoException(e.getMessage());
        }
    }
    
    /**
     * @MethodName: keyPairs
     * @Description: get keyPairs
     * @Author: pengl
     * @Date: 2020/3/28 20:51
     * @Version V1.0
     */
    public static KeyPairs keyPairs() {
        Map<String, Object> keyMap = initKey();
        Key publicKey = (RSAPublicKey)keyMap.get(PUBLIC_KEY);
        Key privateKey = (RSAPrivateKey)keyMap.get(PRIVATE_KEY);
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        return new KeyPairs(publicKeyStr, privateKeyStr);
    }

    public static void main(String[] args){
        KeyPairs keyPairs = keyPairs();
        System.out.println(keyPairs.getPublicKey());
        System.out.println(keyPairs.getPrivateKey());
    }
}
