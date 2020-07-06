package com.dragon.core.event;

import com.dragon.core.threadpool.ExecutorBuilder;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: LocalEvent
 * @Description: LocalEvent base on guava eventbus
 * @Author: pengl
 * @Date: 2020/7/3 14:38
 * @Version V1.0
 */
@Slf4j
public class LocalEvent {
    private static final ThreadPoolExecutor EVENT_EXECUTOR = new ExecutorBuilder().name("eventbus-threadpool").build();
    private static final AsyncEventBus ASYNC_EVENT_BUS = new AsyncEventBus("Default-EventBus", EVENT_EXECUTOR);

    static {
        ASYNC_EVENT_BUS.register(new DeadEventBusListener());
    }

    public static void asyncPost(Object event) {
        ASYNC_EVENT_BUS.post(event);
    }

    private static class DeadEventBusListener {
        @Subscribe
        public void deadEvent(DeadEvent deadEvent) {
            log.error("DeadEventListener Event[{}] has no consumer！", deadEvent.getEvent().getClass().getSimpleName());
        }
    }

    private static void regist(){

    }
}
