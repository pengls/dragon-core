package com.dragon.core.lang.exception;

/**
 * @ClassName: BaseException
 * @Description: BaseException
 * @Author: pengl
 * @Date: 2020/4/1 21:10
 * @Version V1.0
 */
public class BaseException extends Exception {
    private int code;
    private String msg;

    public BaseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String msg) {
        super(msg);
        this.code = -1;
        this.msg = msg;
    }

    public BaseException(String msg, Throwable cause) {
        super(msg, cause);
        this.code = -1;
        this.msg = msg;
    }
}
