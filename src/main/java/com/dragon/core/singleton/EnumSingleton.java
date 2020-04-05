package com.dragon.core.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: EnumSingleton
 * @Description: 利用枚举实现单例：枚举是单例的泛型化，简洁且安全
 * @Author: pengl
 * @Date: 2020/4/5 21:32
 * @Version V1.0
 */
@Slf4j
public enum  EnumSingleton {
    INSTANCE;

    public void sayHi(String name){
        log.info("Hi, {}", name);
    }
}
