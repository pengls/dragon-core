package com.dragon.core.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadUtil
 * @Description: Thread pool Util
 * @Author: pengl
 * @Date: 2020/6/23 21:29
 * @Version V1.0
 */
public final class ThreadUtil {

    public static ThreadPoolExecutor newSingle() {
        return new ExecutorBuilder().corePoolSize(1).maxPoolSize(1).build();
    }

    public static ThreadPoolExecutor newSingle(String nameFormat) {
        return new ExecutorBuilder().corePoolSize(1).maxPoolSize(1).nameFormat(nameFormat).build();
    }

    public static ThreadPoolExecutor newDefaultExecutor() {
        return new ExecutorBuilder().build();
    }

    public static ThreadPoolExecutor newExecutor(int threadSize) {
        return new ExecutorBuilder().corePoolSize(threadSize).maxPoolSize(threadSize).build();
    }

    public static ThreadPoolExecutor newExecutor(int corePoolSize, int maxPoolSize) {
        return new ExecutorBuilder().corePoolSize(corePoolSize).maxPoolSize(maxPoolSize).build();
    }

    public static ThreadPoolExecutor newExecutor(String nameFormat, int corePoolSize, int maxPoolSize) {
        return new ExecutorBuilder().corePoolSize(corePoolSize).maxPoolSize(maxPoolSize).nameFormat(nameFormat).build();
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
