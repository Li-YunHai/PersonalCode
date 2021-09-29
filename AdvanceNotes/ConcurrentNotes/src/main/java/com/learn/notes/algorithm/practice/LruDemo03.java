package com.learn.notes.algorithm.practice;

import java.util.*;


public class LruDemo03 {


    public static void main(String[] args) {
        LruDemo03 solution = new LruDemo03();
        int arr[][] =  {{1, 2, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        solution.LRU(arr, 3);
    }

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU (int[][] operators, int k) {
        int[] res;
        List<Integer> list = new ArrayList();
        LruCache cache = new LruCache(k);
        for(int i=0; i<operators.length; i++){
            if(operators[i][0] == 1){
                cache.set(operators[i][1], operators[i][2]);
            }
            if(operators[i][0] == 2){
                cache.get(operators[i][1]);
                list.add(cache.get(operators[i][1]));
            }
        }
        res = list.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }


}

class LruCache {

    LinkedHashMap<Integer,Integer> cache;

    LruCache(int capacity){
        cache = new LinkedHashMap<Integer,Integer>(capacity){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest){
                return size()>capacity;
            }
        };
    }

    public void set(int key, int value){
        if(cache.containsKey(key)){
            cache.remove(key);
        }
        cache.put(key, value);
    }

    public int get(int key){
        if(cache.containsKey(key)){
            int value = cache.get(key);
            cache.remove(key);
            cache.put(key, value);
            return 1;
        }
        return -1;
    }

    public int[] getCacheValues(){
        int [] res = new int[cache.size()];
        ArrayList<Integer> list = (ArrayList<Integer>) cache.values();
        res = list.stream().mapToInt(Integer::intValue).toArray();
        return res;
    }
}