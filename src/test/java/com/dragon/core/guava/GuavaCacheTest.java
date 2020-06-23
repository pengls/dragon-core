package com.dragon.core.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: GuavaCacheTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/6/20 22:16
 * @Version V1.0
 */
public class GuavaCacheTest {
    @Test
    public void t1() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().concurrencyLevel(5).expireAfterAccess(1, TimeUnit.SECONDS).build();
        cache.put("a", "111");

        Thread.sleep(2000);
        System.out.println(cache.getIfPresent("a"));
    }
}
