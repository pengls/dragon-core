package com.dragon.core.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: LazySingleton_2
 * @Description: 懒汉模式：使用类级内部类，利用static内部类加载机制，保证线程安全
 *               兼顾性能和安全
 * @Author: pengl
 * @Date: 2020/4/5 21:10
 * @Version V1.0
 */
@Slf4j
public class LazySingleton_2 {
    private LazySingleton_2() {
    }

    /**
     * 类级内部类
     */
    private static class LazySingletonHolder {
        private static LazySingleton_2 instance = new LazySingleton_2();
    }

    public static LazySingleton_2 getInstance() {
        return LazySingletonHolder.instance;
    }

    public void sayHi(String name){
        log.info("Hi, {}", name);
    }
}
