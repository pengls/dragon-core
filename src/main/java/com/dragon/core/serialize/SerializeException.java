package com.dragon.core.serialize;

import com.dragon.core.lang.exception.BaseRuntimeException;

/**
 * @ClassName: SerializeException
 * @Description: SerializeException
 * @Author: pengl
 * @Date: 2020/4/1 21:17
 * @Version V1.0
 */
public class SerializeException extends BaseRuntimeException {
    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
