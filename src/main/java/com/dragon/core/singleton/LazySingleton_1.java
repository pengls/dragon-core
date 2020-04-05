package com.dragon.core.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: LazySingleton_1
 * @Description: 懒汉模式：double check 锁 + volatile保证线程安全
 *               volatile关键字能保证内存可见性和避免指令重排，但是可能会屏蔽掉JVM中的一些必要的代码优化，导致性能不是很好
 * @Author: pengl
 * @Date: 2020/4/5 21:10
 * @Version V1.0
 */
@Slf4j
public class LazySingleton_1 {
    private LazySingleton_1() {
    }

    private static volatile LazySingleton_1 instance;

    public static LazySingleton_1 getInstance() {
        if (null == instance) {
            synchronized (LazySingleton_1.class) {
                if (null == instance) {
                    instance = new LazySingleton_1();
                }
            }
        }
        return instance;
    }

    public void sayHi(String name){
        log.info("Hi, {}", name);
    }
}
