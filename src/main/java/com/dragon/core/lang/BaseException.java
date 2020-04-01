package com.dragon.core.lang;

/**
 * @ClassName: BaseException
 * @Description: BaseException
 * @Author: pengl
 * @Date: 2020/4/1 21:10
 * @Version V1.0
 */
public abstract class BaseException extends RuntimeException{
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
