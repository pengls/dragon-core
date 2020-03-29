package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.AsymmetricCrypto;

/**
 * @ClassName: Rsa
 * @Description: RAS
 * @Author: pengl
 * @Date: 2020/3/29 15:53
 * @Version V1.0
 */
public class Rsa extends AsymmetricCrypto {
    @Override
    public Algorithm current() {
        return Algorithm.RSA;
    }
}
