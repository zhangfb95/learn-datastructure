package com.juconcurrent.learn.datastructure.routing;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhangfb
 */
public class WeightedRoute {

    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("lisi", 2);
        map.put("zhangsan", 5);
        map.put("wangw", 10);

        Thread.sleep(0L);
        for (int i = 0; i < 20; i++) {
            String s = getWeightedInstance(map);
            System.out.println(s);
        }
    }

    private static <T> T getWeightedInstance(Map<T, Integer> weighMap) {
        int totalWeight = 0;
        for (Map.Entry<T, Integer> entry : weighMap.entrySet()) {
            totalWeight += entry.getValue();
        }
        int num = ThreadLocalRandom.current().nextInt(totalWeight);
        for (Map.Entry<T, Integer> entry : weighMap.entrySet()) {
            num -= entry.getValue();
            if (num < 0) {
                return entry.getKey();
            }
        }
        return null;
    }
}
