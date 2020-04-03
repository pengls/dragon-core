package com.dragon.core.weakpass;

import com.dragon.core.lang.BaseException;

/**
 * @ClassName: WeakCheckException
 * @Description: WeakCheckException
 * @Author: pengl
 * @Date: 2020/4/3 22:03
 * @Version V1.0
 */
public class WeakCheckException extends BaseException {
    public WeakCheckException(String errorMsg) {
        super(errorMsg);
    }

    public WeakCheckException(String errorMsg, Throwable thr) {
        super(errorMsg, thr);
    }
}
