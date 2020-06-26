package com.dragon.core.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: DefaultThreadPool
 * @Description: DefaultThreadPool
 * @Author: pengl
 * @Date: 2020/6/26 16:52
 * @Version V1.0
 */
public enum DefaultThreadPool {
    INSTANCE;
    final ThreadPoolExecutor EXECUTOR;

    DefaultThreadPool() {
        EXECUTOR = new ExecutorBuilder().nameFormat("DEFAULT-THREADPOOL-%s").build();
    }

    public void execute(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return EXECUTOR.submit(task);
    }

    public Future<?> submit(Runnable runnable) {
        return EXECUTOR.submit(runnable);
    }

    public ThreadPoolExecutor getExecutor(){
        return EXECUTOR;
    }
}
