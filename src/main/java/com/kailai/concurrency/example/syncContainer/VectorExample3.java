package com.kailai.concurrency.example.syncContainer;

import com.kailai.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Slf4j
@NotThreadSafe
public class VectorExample3 {
    private static void test1(Vector<Integer> v1) {
        for (Integer i: v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()){
//            Integer next = iterator.next();
//            if (next.equals(1)){
//                iterator.remove();
//            }
//        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1) {
                list.remove(i);
            }
            System.out.println(list);
            System.out.println(i);
//            可以remove，但是会跳过一个元素
        }

        System.out.println(list);


    }

}
