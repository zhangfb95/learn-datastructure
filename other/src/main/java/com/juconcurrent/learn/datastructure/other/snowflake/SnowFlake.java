package com.juconcurrent.learn.datastructure.other.snowflake;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 雪花算法
 * <p>
 * 1. 最高位不使用，默认为0
 * 2. 41位时间序列，精确到毫秒
 * 3. 12位机器码
 * 4. 10位计数序列号
 *
 * @author zhangfb
 */
public class SnowFlake {

    private static final long START_TIME_STAMP = 1544970466369L;

    /**
     * 机器码生成器，用于生成12位机器码
     */
    private final MachineCodeGenerator machineCodeGenerator;

    /**
     * 默认时间偏移量
     */
    private volatile long timeOffset = timeOffset();

    /**
     * 计数器，用于生成1毫秒内的序列号。当一毫秒内生成的序列号超过10位，则顺延到下一毫秒
     */
    private AtomicLong counter = new AtomicLong(0L);

    public SnowFlake(MachineCodeGenerator machineCodeGenerator) {
        this.machineCodeGenerator = machineCodeGenerator;
    }

    public Long create() {
        while (true) {
            final long prev = timeOffset;
            final long curr = timeOffset();
            if (prev < curr) {
                timeOffset = curr;
                counter.set(0L);
                return ((curr) << 22) +
                       (machineCodeGenerator.generate() << 10);
            } else if (prev == curr) {
                long num = counter.getAndIncrement();
                if (num < 1024L) {
                    return ((curr) << 22) +
                           (machineCodeGenerator.generate() << 10) +
                           num;
                }
            }
        }
    }

    private long timeOffset() {
        return System.currentTimeMillis() - START_TIME_STAMP;
    }
}
