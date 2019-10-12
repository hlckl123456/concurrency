package com.kailai.concurrency.example.singleton;

// 懒汉模式 lazy mode
public class SingletonExample1 {
    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态工厂
    public  static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }

        return instance;
    }
}
