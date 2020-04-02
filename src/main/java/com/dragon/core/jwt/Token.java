package com.dragon.core.jwt;

/**
 * @ClassName: Token
 * @Description: Token
 * @Author: pengl
 * @Date: 2020/4/1 20:47
 * @Version V1.0
 */
public interface Token {
    /**
     * @MethodName: create
     * @Description: create a token string
     * @Author: pengl
     * @Date: 2020/4/1 20:49
     * @Version V1.0
     */
    String create();

    /**
     * @MethodName: verify
     * @Description: verify a token is valid
     * @Author: pengl
     * @Date: 2020/4/1 20:49
     * @Version V1.0
     */
    boolean verify(String jwt);

    /**
     * @MethodName: parse
     * @Description: parse a token ,return a bean
     * @Author: pengl
     * @Date: 2020/4/1 20:50
     * @Version V1.0
     */
    Object parse(String jwt, boolean checkExpire);

    /**
     * @MethodName: isExpire
     * @Description: isExpire
     * @Author: pengl
     * @Date: 2020/4/1 20:56
     * @Version V1.0
     */
    boolean isExpire();
}
