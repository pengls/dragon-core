package com.dragon.core.threadpool.metrics.jmx;

import com.dragon.core.threadpool.ThreadPoolManager;
import com.dragon.core.threadpool.ThreadPoolMetrics;
import lombok.extern.slf4j.Slf4j;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @ClassName: ThreadPoolImpl
 * @Description: ThreadPoolImpl
 * @Author: pengl
 * @Date: 2020/7/6 22:00
 * @Version V1.0
 */
@Slf4j
public class ThreadPoolImpl implements ThreadPoolMXBean {
    @Override
    public List<ThreadPoolMetrics> getThreadPoolMetrics() {
        return ThreadPoolManager.getManagedThreadPoolMetrics();
    }

    @Override
    public void registerMBean() {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            server.registerMBean(this, this.getObjectName());
        } catch (Exception e) {
            log.error("jmx threadpool metrics registerMBean error : {}", e.getMessage(), e);
        }
    }

    @Override
    public ObjectName getObjectName() {
        try {
            return ObjectName.getInstance("com.dragon.core.threadpool.metrics.jmx", "type", "ThreadPoolMetrics");
        } catch (MalformedObjectNameException e) {
            log.error("jmx threadpool metrics getObjectName error : {}", e.getMessage(), e);
        }
        return null;
    }
}
