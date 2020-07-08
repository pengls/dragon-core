package com.dragon.core.threadpool;

import lombok.extern.slf4j.Slf4j;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DeadlockDetector
 * @Description: DeadlockDetector
 * @Author: pengl
 * @Date: 2020/7/8 8:06
 * @Version V1.0
 */
@Slf4j
public final class DeadlockDetector {
    private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();
    private static final ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setDaemon(true);
        thread.setName("dead-lock-detector");
        return thread;
    });

    public static void start() {
        schedule.scheduleWithFixedDelay(() -> detectorDeadLock(), 1, 1 * 60, TimeUnit.SECONDS);
    }

    private static void detectorDeadLock() {
        long[] deadlockedThreads = THREAD_MX_BEAN.findDeadlockedThreads();
        if (deadlockedThreads == null || deadlockedThreads.length == 0) {
            log.info("DeadlockDetector : no dead lock find.");
            return;
        }
        log.error("DeadlockDetector : {} dead lock find.", deadlockedThreads.length);
        printDeadLockInfo(deadlockedThreads);
    }

    private static void printDeadLockInfo(long[] deadlockedThreads) {
        ThreadInfo[] threadInfos = THREAD_MX_BEAN.getThreadInfo(deadlockedThreads, true, false);
        if (threadInfos != null && threadInfos.length > 0) {
            for (ThreadInfo threadInfo : threadInfos) {
                System.out.println("t-id: " + threadInfo.getThreadId());
                System.out.println("t-name: " + threadInfo.getThreadName());
                System.out.println("t-lock-name: " + threadInfo.getLockName());
                System.out.println("t-lock-owner:" + threadInfo.getLockOwnerName());
                System.out.println("t-status: " + threadInfo.getThreadState());
                System.out.println("t-blocked-count: " + threadInfo.getBlockedCount());
                System.out.println("t-waited-count: " + threadInfo.getWaitedCount());
                System.out.println("t-info：" + threadInfo.toString());
            }
        }
    }
}
