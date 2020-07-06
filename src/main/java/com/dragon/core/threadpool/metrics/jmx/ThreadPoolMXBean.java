package com.dragon.core.threadpool.metrics.jmx;

import com.dragon.core.jmx.JmxMBean;
import com.dragon.core.threadpool.ThreadPoolMetrics;
import java.util.List;

/**
 * @ClassName: ThreadPoolMetricsMXBean
 * @Description: ThreadPoolMetricsMXBean
 * @Author: pengl
 * @Date: 2020/7/6 21:49
 * @Version V1.0
 */
public interface ThreadPoolMXBean extends JmxMBean {
    List<ThreadPoolMetrics> getThreadPoolMetrics();
}
