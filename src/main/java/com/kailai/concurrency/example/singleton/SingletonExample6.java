package com.kailai.concurrency.example.singleton;

// 饿汉模式 hungry mode

import com.kailai.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample6 {
    // 私有构造函数
    private SingletonExample6() {

    }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态工厂
    public  static SingletonExample6 getInstance() {
        return instance;
    }
}
