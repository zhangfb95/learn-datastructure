package com.juconcurrent.learn.datastructure.other.snowflake;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangfb
 */
public class SnowFlakeTest {

    @Test public void test() throws Exception {
        SnowFlake snowFlake = new SnowFlake(() -> 1L);

        final int threadCount = 10;
        final int length = 100000;
        Set<Long> allIds = new HashSet<>(threadCount * length);

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < length; j++) {
                    allIds.add(snowFlake.create());
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        assertEquals(length, allIds.size());
    }
}
