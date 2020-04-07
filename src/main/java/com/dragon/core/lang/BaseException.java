package com.dragon.core.lang;

/**
 * @ClassName: BaseException
 * @Description: BaseException
 * @Author: pengl
 * @Date: 2020/4/1 21:10
 * @Version V1.0
 */
public abstract class BaseException extends RuntimeException {
    private int code;
    private String msg;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }


    public BaseException(String message) {
        super(message);
        this.code = -1;
        this.msg = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = -1;
        this.msg = message;
    }
}
