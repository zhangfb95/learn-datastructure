package com.juconcurrent.learn.datastructure.other.snowflake;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertTrue;

/**
 * @author zhangfb
 */
public class SnowFlakeTest {

    @Test public void test() throws Exception {
        SnowFlake snowFlake = new SnowFlake(() -> 1L);

        final int threadCount = 100;
        final int length = 100000;
        ConcurrentMap<Long, String> allIds = new ConcurrentHashMap<>(threadCount * length);
        List<Long> existedIds = new CopyOnWriteArrayList<>();
        final long start = System.currentTimeMillis();

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < length; j++) {
                    Long id = snowFlake.create();
                    if (allIds.putIfAbsent(id, "") != null) {
                        existedIds.add(id);
                    }
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println(existedIds);
        System.out.println(allIds.size());
        System.out.println("spent: " + (System.currentTimeMillis() - start) + " ms");
        assertTrue(existedIds.isEmpty());
    }
}
