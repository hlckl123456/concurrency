package com.kailai.concurrency.example.singleton;

// 懒汉模式  修改版

import com.kailai.concurrency.annoations.NotThreadSafe;
import com.kailai.concurrency.annoations.ThreadSafe;

@ThreadSafe
// 双重检测机制 double lock check
public class SingletonExample5 {
    // 私有构造函数
    private SingletonExample5() {
    }

    // 单例对象 volatile + 双重检测机制来防止指令重排
    private volatile static SingletonExample5 instance = null;

    /**
     * instance 创建的过程：
     * 1.memory = allocate() 分配对象的内存空间 allocate memory space to instance
     * 2.ctorInstance() 初始化对象 initialize instance
     * 3.instance = memory 设置instance指向刚分配的内存 point instance to memory
     *
     * 我们通过volatile来限制它发生指令重排，就可以完成线程安全
     * volatile的一个作用就是在双重检测中防止指令重排
     */

    // 静态工厂
    public  static SingletonExample5 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample5.class) {
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }



        return instance;
    }
}
