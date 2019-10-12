package com.kailai.concurrency.example.singleton;


import com.kailai.concurrency.annoations.Recommend;
import com.kailai.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式：最安全
 */

@ThreadSafe
@Recommend
public class SingletonExample7 {
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        // JVM保证这个方法只调用一次。
        Singleton() {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return singleton;
        }
    }

}
