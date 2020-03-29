package com.dragon.core.crypto.impl;

import com.dragon.core.crypto.Algorithm;
import com.dragon.core.crypto.PBECrypto;

/**
 * @ClassName: PBEWithMd5AndDes
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/3/28 17:08
 * @Version V1.0
 */
public class PBEWithMd5AndDes extends PBECrypto {
    @Override
    public Algorithm current() {
        return Algorithm.PBEWithMd5AndDes;
    }
}
