package com.juconcurrent.learn.datastructure.other.snowflake;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangfb
 */
public class SnowFlakeTest {

    @Test public void test() {
        SnowFlake snowFlake = new SnowFlake(() -> 1L);
        final int length = 100000;
        Set<Long> allIds = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            allIds.add(snowFlake.create());
        }
        assertEquals(length, allIds.size());
    }
}
