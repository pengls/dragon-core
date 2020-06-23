package com.dragon.core.lang.id;

import com.dragon.core.lang.Assert;
import com.dragon.core.lang.date.SystemClock;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

/**
 * @ClassName: Snowflake
 * @Description: Twitter Snowflake
 * @Author: pengl
 * @Date: 2020/6/14 11:00
 * @Version V1.0
 */
public class Snowflake implements Serializable {
    private static final long serialVersionUID = 1L;

    private final long twepoch;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATA_CENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private static final long START_TIME = 1288834974657L;
    private static final int TIME_OFFSET = 10;

    private final long workerId;
    private final long dataCenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private boolean useSystemClock;

    public Snowflake(long workerId, long dataCenterId) {
        this(workerId, dataCenterId, false);
    }

    public Snowflake(long workerId, long dataCenterId, boolean isUseSystemClock) {
        this(null, workerId, dataCenterId, isUseSystemClock);
    }

    public Snowflake(Date epochDate, long workerId, long dataCenterId, boolean isUseSystemClock) {
        this.twepoch = epochDate == null ? START_TIME : epochDate.getTime();
        Assert.isTrue(workerId <= MAX_WORKER_ID && workerId > 0, MessageFormat.format("worker Id can't be greater than {0} or less than 0", MAX_WORKER_ID));
        Assert.isTrue(dataCenterId <= MAX_DATA_CENTER_ID && dataCenterId > 0, MessageFormat.format("datacenter Id can't be greater than {0} or less than 0", MAX_DATA_CENTER_ID));
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.useSystemClock = isUseSystemClock;
    }

    public long getWorkerId(long id) {
        return id >> WORKER_ID_SHIFT & ~(-1L << WORKER_ID_BITS);
    }

    public long getDataCenterId(long id) {
        return id >> DATA_CENTER_ID_SHIFT & ~(-1L << DATA_CENTER_ID_BITS);
    }

    public long getGenerateDateTime(long id) {
        return (id >> TIMESTAMP_LEFT_SHIFT & ~(-1L << 41L)) + twepoch;
    }

    public synchronized long nextId() {
        long timestamp = genTime();
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            Assert.isTrue(offset <= TIME_OFFSET, MessageFormat.format("Clock moved backwards. Refusing to generate id for {0}ms", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << TIMESTAMP_LEFT_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    public String nextIdStr() {
        return Long.toString(nextId());
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = genTime();
        while (timestamp <= lastTimestamp) {
            timestamp = genTime();
        }
        return timestamp;
    }

    private long genTime() {
        return this.useSystemClock ? SystemClock.INSTANCE.currentTimeMillis() : System.currentTimeMillis();
    }
}
