package com.kailai.concurrency.example.singleton;

// 懒汉模式 thread not saf 版

import com.kailai.concurrency.annoations.NotThreadSafe;
import com.kailai.concurrency.annoations.ThreadSafe;

@NotThreadSafe
// 双重检测机制 double lock check
public class SingletonExample4 {
    // 私有构造函数
    private SingletonExample4() {
    }

    // 单例对象
    private static SingletonExample4 instance = null;

    /**
     * instance 创建的过程：
     * 1.memory = allocate() 分配对象的内存空间 allocate memory space to instance
     * 2.ctorInstance() 初始化对象 initialize instance
     * 3.instance = memory 设置instance指向刚分配的内存 point instance to memory
     * 在多线程中会发生指令重排 即 instruction reorder, happens-before relationship
     *
     * 会发现的情况是线程B 在if (instance == null)判断时候，发现已有对象，就return instance
     * 但其实instance只是刚刚完成指向，但并未完成初始化，这样return的结果就会发生问题。
     */

    // 静态工厂
    public  static SingletonExample4 getInstance() {
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }



        return instance;
    }
}
