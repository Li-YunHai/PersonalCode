package com.learn.notes.redis.lru;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 使用LinkedHashMap实现LRU算法
 */
public class LRUCache {

    private LinkedHashMap<String, Integer> cache;

    public LRUCache(int capacity) {
        /**
         * @Parm capacity 表长
         * @Parm loadFactor 负载因子
         * @Parm accessOrder 排序规则：
         *          true：按新增操作更新使用情况
         *          false：按查询操作更新使用情况
         */
        cache = new LinkedHashMap<String, Integer>(capacity, 0.75f, true){

            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(java.util.Map.Entry<String, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(String key, int value) {
        cache.put(key, value);
    }

    public int[] getCacheValues(){
        int [] res = new int[cache.size()];
        ArrayList<Integer> list = (ArrayList<Integer>) cache.values();
        res = list.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        System.out.println(lruCache.toString());

        lruCache.put("d", 4);
        System.out.println(lruCache.toString());
        lruCache.put("c", 3);
        System.out.println(lruCache.toString());
        lruCache.put("e", 5);
        System.out.println(lruCache.toString());
    }
}
