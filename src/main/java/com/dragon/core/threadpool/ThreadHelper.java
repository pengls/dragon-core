package com.dragon.core.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: ThreadUtil
 * @Description: Thread pool Helper: create thread pool executor and regist pool to manager
 * @Author: pengl
 * @Date: 2020/6/23 21:29
 * @Version V1.0
 */
public final class ThreadHelper {
    private static final AtomicLong atomicLong = new AtomicLong(0);

    public static ThreadPoolExecutor newSingle() {
        return newSingle("default-single" + atomicLong.incrementAndGet());
    }

    public static ThreadPoolExecutor newSingle(String name) {
        return new ExecutorBuilder().corePoolSize(1).maxPoolSize(1).keepAliveTime(0).name(name).build();
    }

    public static ThreadPoolExecutor newDefaultExecutor() {
        return DefaultThreadPool.INSTANCE.getExecutor();
    }

    public static ThreadPoolExecutor newFixedExecutor(int threadSize) {
        return newFixedExecutor("default-fixed" + atomicLong.incrementAndGet(), threadSize);
    }

    public static ThreadPoolExecutor newFixedExecutor(String name, int threadSize) {
        return new ExecutorBuilder().corePoolSize(threadSize).maxPoolSize(threadSize).name(name).keepAliveTime(0).build();
    }

    public static ThreadPoolExecutor newCachedExecutor(int corePoolSize, int maxPoolSize) {
        return newCachedExecutor("default-cached" + atomicLong.incrementAndGet(), corePoolSize, maxPoolSize);
    }

    public static ThreadPoolExecutor newCachedExecutor(String name, int corePoolSize, int maxPoolSize) {
        return new ExecutorBuilder().corePoolSize(corePoolSize).maxPoolSize(maxPoolSize).name(name).build();
    }

    public static void execute(Runnable runnable) {
        DefaultThreadPool.INSTANCE.execute(runnable);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return DefaultThreadPool.INSTANCE.submit(task);
    }

    public static Future<?> submit(Runnable runnable) {
        return DefaultThreadPool.INSTANCE.submit(runnable);
    }

    public static ThreadPoolExecutor getDefaultExecutor() {
        return DefaultThreadPool.INSTANCE.getExecutor();
    }

}
