package com.kailai.concurrency.example.singleton;

// 饿汉模式 hungry mode

import com.kailai.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample2 {
    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂
    public  static SingletonExample2 getInstance() {
        return instance;
    }
}
