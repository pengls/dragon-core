package com.dragon.core.jwt;

import com.dragon.core.lang.BaseException;

/**
 * @ClassName: JwtInvalidException
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/1 22:02
 * @Version V1.0
 */
public class JwtInvalidException extends BaseException {
    public JwtInvalidException(String message) {
        super(message);
    }
}
