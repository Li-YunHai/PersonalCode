package com.learn.notes.algorithm.practice;

import java.lang.*;
import java.util.*;
public class LruDemo01 {

    LinkedHashMap cache;

    LruDemo01(int capacity){
        cache = new LinkedHashMap(capacity,0.75f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return super.removeEldestEntry(eldest);
            }
        };
    }


}
