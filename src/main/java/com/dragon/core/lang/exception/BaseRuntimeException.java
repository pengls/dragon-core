package com.dragon.core.lang.exception;

/**
 * @ClassName: BaseException
 * @Description: BaseException
 * @Author: pengl
 * @Date: 2020/4/1 21:10
 * @Version V1.0
 */
public class BaseRuntimeException extends RuntimeException {
    private int code;
    private String msg;

    public BaseRuntimeException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseRuntimeException(String msg) {
        super(msg);
        this.code = -1;
        this.msg = msg;
    }

    public BaseRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = -1;
        this.msg = msg;
    }
}
