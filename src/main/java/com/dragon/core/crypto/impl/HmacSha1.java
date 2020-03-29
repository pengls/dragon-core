package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.HmacCrypto;

/**
 * @ClassName: HmacSha1
 * @Description: HmacSha1
 * @Author: pengl
 * @Date: 2020/3/27 21:31
 * @Version V1.0
 */
public class HmacSha1 extends HmacCrypto {
    @Override
    public Algorithm current() {
        return Algorithm.HmacSHA1;
    }
}
