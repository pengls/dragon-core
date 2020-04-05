package com.dragon.core.singleton;

import org.junit.Test;

/**
 * @ClassName: SingletonTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/4/5 21:21
 * @Version V1.0
 */
public class SingletonTest {
    @Test
    public void tt1(){
        HungrySingleton.getInstance().sayHi("Zhang San");
        EnumSingleton.INSTANCE.sayHi("Zhang San");
        LazySingleton_1.getInstance().sayHi("Zhang San");
        LazySingleton_2.getInstance().sayHi("Zhang San");
    }
}
