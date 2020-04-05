package com.dragon.core.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: HungrySingleton
 * @Description: 饿汉模式：借助类加载机制，线程安全
 * @Author: pengl
 * @Date: 2020/4/5 21:06
 * @Version V1.0
 */
@Slf4j
public class HungrySingleton {
    private HungrySingleton() {
    }

    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }

    public void sayHi(String name){
        log.info("Hi, {}", name);
    }

}
