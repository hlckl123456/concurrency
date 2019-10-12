package com.kailai.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.kailai.concurrency.annoations.NotThreadSafe;

import java.util.Map;

@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1, 3);
    }
}
