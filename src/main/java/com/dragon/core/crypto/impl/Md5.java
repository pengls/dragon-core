package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.DigestCrypto;

/**
 * @ClassName: Md5
 * @Description: Md5 encrypt
 * @Author: pengl
 * @Date: 2020/3/27 21:17
 * @Version V1.0
 */
public class Md5 extends DigestCrypto {
    @Override
    public Algorithm current() {
        return Algorithm.MD5;
    }
}
