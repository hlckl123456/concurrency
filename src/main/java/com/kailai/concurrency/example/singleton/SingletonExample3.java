package com.kailai.concurrency.example.singleton;

// 饿汉模式 hungry mode

import com.kailai.concurrency.annoations.ThreadSafe;

@ThreadSafe

public class SingletonExample3 {
    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
