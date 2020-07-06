package com.dragon.core.threadpool;

import com.dragon.core.lang.Assert;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: ThreadPoolBuilder
 * @Description: ThreadPoolBuilder
 * @Author: pengl
 * @Date: 2020/6/23 21:43
 * @Version V1.0
 */
public class ExecutorBuilder {
    private static final int DEFAULT_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_MAX_POOL_SIZE = DEFAULT_CORE_POOL_SIZE * 2;
    private static final int DEFAULT_QUEUE_CAPACITY = 0x400;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 60;

    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;
    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;
    private long keepAliveTime = TimeUnit.SECONDS.toMillis(DEFAULT_KEEP_ALIVE_TIME);
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue(DEFAULT_QUEUE_CAPACITY);
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
    private Boolean allowCoreThreadTimeOut;
    private String name;
    private Boolean daemon;
    private Integer priority;
    private UncaughtExceptionHandler uncaughtExceptionHandler;

    public ExecutorBuilder corePoolSize(int corePoolSize) {
        Assert.isFalse(corePoolSize <= 0, "core pool size must greater than 0");
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ExecutorBuilder maxPoolSize(int maxPoolSize) {
        Assert.isFalse(maxPoolSize <= 0, "max pool size must greater than 0");
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public ExecutorBuilder keepAliveTime(long keepAliveTime, TimeUnit unit) {
        return keepAliveTime(unit.toMillis(keepAliveTime));
    }

    public ExecutorBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ExecutorBuilder workQueue(BlockingQueue<Runnable> workQueue) {
        Assert.isNotNull(workQueue, "work queue is null!");
        this.workQueue = workQueue;
        return this;
    }

    public ExecutorBuilder useArrayBlockingQueue() {
        return useArrayBlockingQueue(DEFAULT_QUEUE_CAPACITY);
    }

    public ExecutorBuilder useArrayBlockingQueue(int capacity) {
        return workQueue(new ArrayBlockingQueue<>(capacity));
    }

    public ExecutorBuilder useSynchronousQueue() {
        return useSynchronousQueue(false);
    }

    public ExecutorBuilder useSynchronousQueue(boolean fair) {
        return workQueue(new SynchronousQueue<>(fair));
    }

    public ExecutorBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ExecutorBuilder rejectedHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
        return this;
    }

    public ExecutorBuilder allowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    public ExecutorBuilder name(String name) {
        Assert.isNotBlank(name, "name must not empty!");
        this.name = name;
        return this;
    }

    public ExecutorBuilder daemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ExecutorBuilder priority(Integer priority) {
        Assert.isTrue((priority >= Thread.MIN_PRIORITY && priority <= Thread.MAX_PRIORITY), "priority value error.");
        this.priority = priority;
        return this;
    }

    public ExecutorBuilder uncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
        return this;
    }

    public ThreadPoolExecutor build() {
        Assert.isNotBlank(name, "name must not empty!");
        final ThreadFactory backingThreadFactory = this.threadFactory != null ? this.threadFactory : Executors.defaultThreadFactory();
        AtomicLong count = new AtomicLong(0);
        String formatName = format(name + "-%s", count.incrementAndGet());
        ThreadFactory threadFactory = runnable -> {
            Thread thread = backingThreadFactory.newThread(runnable);
            thread.setName(formatName);
            if (daemon != null) {
                thread.setDaemon(daemon);
            }
            if (priority != null) {
                thread.setPriority(priority);
            }
            if (uncaughtExceptionHandler != null) {
                thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return thread;
        };

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                workQueue,
                threadFactory,
                handler
        );
        if (null != this.allowCoreThreadTimeOut) {
            threadPoolExecutor.allowCoreThreadTimeOut(this.allowCoreThreadTimeOut);
        }

        ThreadPoolManager.regist(formatName, threadPoolExecutor);
        return threadPoolExecutor;
    }

    private static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }
}
