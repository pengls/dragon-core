package com.dragon.core.threadpool;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @ClassName: ThreadPoolMetrics
 * @Description: ThreadPoolMetrics
 * @Author: pengl
 * @Date: 2020/7/3 14:24
 * @Version V1.0
 */
@Data
@Builder
public class ThreadPoolMetrics {
    private String poolName;
    private int activeCount;
    private int poolSize;
    private int corePoolSize;
    private int maximumPoolSize;
    private int queueSize;
    private long taskCount;
    private long keepAliveTime;
    private long completedTaskCount;

    @Tolerate
    public ThreadPoolMetrics(){}
}
