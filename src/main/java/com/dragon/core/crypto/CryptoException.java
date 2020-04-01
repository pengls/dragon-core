package com.dragon.core.crypto;

import com.dragon.core.lang.BaseException;

/**
 * @ClassName: CryptoException
 * @Description: crypto Exception
 * @Author: pengl
 * @Date: 2020/3/27 21:07
 * @Version V1.0
 */
public class CryptoException extends BaseException {
    public CryptoException(String errorMsg) {
        super(errorMsg);
    }

    public CryptoException(String errorMsg, Throwable tr) {
        super(errorMsg, tr);
    }
}
