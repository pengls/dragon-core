package com.dragon.core.timer;

public interface TimerTask {
    void run(final Timeout timeout) throws Exception;
}
