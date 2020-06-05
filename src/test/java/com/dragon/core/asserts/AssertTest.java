package com.dragon.core.asserts;

import com.dragon.core.lang.asserts.BaseAssert;
import com.dragon.core.lang.asserts.BaseAssertResponse;
import com.dragon.core.lang.exception.BaseRuntimeException;
import org.junit.Test;

import java.text.MessageFormat;

/**
 * @ClassName: AssertTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/6/5 15:28
 * @Version V1.0
 */
public class AssertTest {
    @Test
    public void t1(){
        //BaseAssertResponse.PARAM_EMPTY_ERROR.isNotEmpty("");
        BaseAssertResponse.VALID_ERROR.isEquals("1", "2");
    }

    @Test
    public void t2(){
        MyAssertResponse.USER_NOT_EXSIT.isUserExist("Kobe");
    }

    public interface MyAssert extends BaseAssert{
        default void isUserExist(String userName) {
            isNotBlank(userName);
            isEquals("Jordan", userName, userName);
        }
    }

    public enum MyAssertResponse implements MyAssert{
        USER_OR_PASS_ERROR(9900, "The User name or password error！"),
        USER_NOT_EXSIT(9901, "The User not exist: {0}");

        private int code;
        private String msg;

        MyAssertResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public BaseRuntimeException ofException(Object... args) {
            String msg = MessageFormat.format(this.getMsg(), args);
            return new BaseRuntimeException(getCode(), msg);
        }
    }
}
