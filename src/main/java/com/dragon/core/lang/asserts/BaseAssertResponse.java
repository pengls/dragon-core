package com.dragon.core.lang.asserts;

import com.dragon.core.lang.exception.BaseRuntimeException;
import lombok.Getter;
import java.text.MessageFormat;

/**
 * @ClassName: AssertResponse
 * @Description: AssertResponse
 * @Author: pengl
 * @Date: 2020/5/1 22:38
 * @Version V1.0
 */
@Getter
public enum BaseAssertResponse implements BaseAssert {
    SYSTEM_ERROR(10001, "Server Error!"),
    VALID_ERROR(10002, "Parameters Validate Error!"),
    PARAM_EMPTY_ERROR(10003, "Missing necessary parameters!");

    private int code;
    private String msg;

    BaseAssertResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public BaseRuntimeException ofException(Object... args) {
        String msg = MessageFormat.format(this.getMsg(), args);
        return new BaseRuntimeException(getCode(), msg);
    }
}
