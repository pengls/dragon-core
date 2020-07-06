package com.dragon.core.threadpool;

import com.dragon.core.threadpool.metrics.jmx.ThreadPoolImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName: ThreadManager
 * @Description: ThreadManager
 * @Author: pengl
 * @Date: 2020/6/27 9:48
 * @Version V1.0
 */
@Slf4j
public final class ThreadPoolManager {
    private static final Map<String, ThreadPoolExecutor> EXECUTOR_MAP = Maps.newConcurrentMap();
    private static final ScheduledExecutorService scanThread = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setDaemon(true);
        thread.setName("THREAD-POOL-MANAGER");
        return thread;
    });

    static {
        startScanThread();
        new ThreadPoolImpl().registerMBean();
    }

    public static boolean regist(String poolName, ThreadPoolExecutor executor) {
        ThreadPoolExecutor ex = EXECUTOR_MAP.get(poolName);
        if (ex == null) {
            EXECUTOR_MAP.put(poolName, executor);
            log.info("ThreadPoolExecutor[{}] regist success.", poolName);
            return true;
        }
        log.error("ThreadPoolExecutor[{}] regist faild -> Repeated registration!", poolName);
        return false;
    }

    public static void setCorePoolSize(String poolName, int corePoolSize) {
        ThreadPoolExecutor ex = EXECUTOR_MAP.get(poolName);
        if (ex == null) {
            return;
        }
        ex.setCorePoolSize(corePoolSize);
    }

    public static void setMaximumPoolSize(String poolName, int maximumPoolSize) {
        ThreadPoolExecutor ex = EXECUTOR_MAP.get(poolName);
        if (ex == null) {
            return;
        }
        ex.setMaximumPoolSize(maximumPoolSize);
    }

    public static void setRejectedExecutionHandler(String poolName, RejectedExecutionHandler handler) {
        ThreadPoolExecutor ex = EXECUTOR_MAP.get(poolName);
        if (ex == null) {
            return;
        }
        ex.setRejectedExecutionHandler(handler);
    }

    private static void startScanThread() {
        scanThread.scheduleWithFixedDelay(() -> warn(), 1000, 5 * 60, TimeUnit.SECONDS);
    }

    private static void warn() {
        List<ThreadPoolMetrics> metrics = getManagedThreadPoolMetrics();
        if (!metrics.isEmpty()) {
            metrics.stream().filter(m -> m.getActiveCount() == m.getMaximumPoolSize()).forEach(m -> log.warn("ThreadPool[{}-{}] is full.", m.getPoolName()));
        }
    }

    public static List<ThreadPoolMetrics> getManagedThreadPoolMetrics() {
        Iterator<Map.Entry<String, ThreadPoolExecutor>> iterator = EXECUTOR_MAP.entrySet().iterator();
        List<ThreadPoolMetrics> res = Lists.newArrayListWithCapacity(EXECUTOR_MAP.keySet().size());
        while (iterator.hasNext()) {
            Map.Entry<String, ThreadPoolExecutor> entry = iterator.next();
            ThreadPoolExecutor executor = entry.getValue();
            if (executor.isShutdown() || executor.isTerminated()) {
                iterator.remove();
            }

            ThreadPoolMetrics metrics = ThreadPoolMetrics.builder().activeCount(executor.getActiveCount()).maximumPoolSize(executor.getMaximumPoolSize())
                    .poolName(entry.getKey()).poolSize(executor.getPoolSize()).queueSize(executor.getQueue().size())
                    .corePoolSize(executor.getCorePoolSize()).keepAliveTime(executor.getKeepAliveTime(TimeUnit.MILLISECONDS))
                    .taskCount(executor.getTaskCount()).completedTaskCount(executor.getCompletedTaskCount()).build();
            res.add(metrics);
        }
        return res;
    }
}
