package com.dragon.core.crypto;

import lombok.Data;

/**
 * @ClassName: KeyPairs
 * @Description: string type key pair
 * @Author: pengl
 * @Date: 2020/3/28 20:44
 * @Version V1.0
 */
@Data
public class KeyPairs {
    private String publicKey;
    private String privateKey;

    public KeyPairs(){}

    public KeyPairs(String publicKey, String privateKey){
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
}
