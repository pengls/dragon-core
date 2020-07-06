package com.dragon.core.threadpool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @ClassName: ThreadTest
 * @Description: TODO
 * @Author: pengl
 * @Date: 2020/6/23 22:43
 * @Version V1.0
 */
public class ThreadTest {
    static ThreadPoolExecutor executor1 = new ExecutorBuilder().corePoolSize(1).maxPoolSize(2).name("T-A").daemon(true).useArrayBlockingQueue(5).build();
    static ThreadPoolExecutor executor2 = new ExecutorBuilder().corePoolSize(1).maxPoolSize(2).name("T-B").daemon(true).useArrayBlockingQueue(5).rejectedHandler(new ThreadPoolExecutor.CallerRunsPolicy()).build();

    @Test
    public void t1() throws InterruptedException {
        while (true) {
            executor1.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(500);
        }
    }

    @Test
    public void t2() throws InterruptedException {
        while (true) {
            executor2.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(500);
        }
    }

    @Test
    public void t3() throws ExecutionException, InterruptedException {
        Future<String> future = ThreadHelper.submit(() -> "Hello" );
        System.out.println(future.get());
        Thread.sleep(1000000000);
    }
}
