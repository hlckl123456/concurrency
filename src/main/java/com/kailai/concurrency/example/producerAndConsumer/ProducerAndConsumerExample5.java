package com.kailai.concurrency.example.producerAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


// readlock 是没有condition变量的
// 这个场景本身就不适合ReentrantReadWriteLock，read是不会有任何改变的。

// 尝试用ReentrantReadWriteLock来实现
public class ProducerAndConsumerExample5 {
//    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//    final Lock writelock = lock.writeLock();
//    final Lock readlock = lock.readLock();
//    final Condition notFull = writelock.newCondition();
//    final Condition notEmpty = readlock..newCondition();
//
//
//    // 加入库存概念，可批量生产和消费
//    // 定义最大库存为10
//    // 并且在方法中putptr循环增加，默认如果容量已满，就用input替换最早加入的computer
//    // 这个写法规避了pop和append造成的空间浪费，同时间接的限制了最大容量，很好
//    final String[] stock = new String[10];
//    // 写入标记、读取标记、已有商品数量
//    int putptr, takeptr, count;
//
//    public void put(String computer) {
//        // lock代替synchronized
//        writelock.lock();
//        try {
//            // 若库存已满则生产者线程阻塞
//            while (count == stock.length) {
//                notFull.await();
//            }
//            // 库存中加入商品
//            stock[putptr] = computer;
//            // 库存已满，指针置零，方便下次重新写入
//            if (++putptr == stock.length) {
//                putptr = 0;
//            }
//            ++count;
//            System.out.println(computer + " 正在生产数据： -- 库存剩余：" + count);
//            notEmpty.signal();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            writelock.unlock();
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String take(String consumerName) {
//        readlock.lock();
//        try {
//            // 维护一个count，根据count就知道要不要继续消费下去
//            while (count == 0) {
//                notEmpty.await();
//            }
//            // 从库存中获取商品
//            String computer = stock[takeptr];
//            if (++takeptr == stock.length) {
//                takeptr = 0;
//            }
//            --count;
//            System.out.println(consumerName + " 正在消费数据：" + computer + " -- 库存剩余：" + count);
//            notFull.signal();
//            return computer;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            readlock.unlock();
//        }
//
//        // 无逻辑作用，放慢速度
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static void main(String[] args) {
//        ProducerAndConsumerExample5 computer = new ProducerAndConsumerExample5();
//        Thread p1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    computer.put("Dell");
//                }
//            }
//        });
//        Thread p2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    computer.put("Mac");
//                }
//            }
//        });
//
//        Thread c1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    computer.take("zhangsan");
//                }
//            }
//        });
//        Thread c2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    computer.take("李四");
//                }
//            }
//        });
//        // 两个生产者两个消费者同时运行
//        p1.start();
//        p2.start();
//        c1.start();
//        c2.start();
//    }
}

