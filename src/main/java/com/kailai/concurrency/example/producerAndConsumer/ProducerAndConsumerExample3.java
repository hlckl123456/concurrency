package com.kailai.concurrency.example.producerAndConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Semaphore 信号量实现
public class ProducerAndConsumerExample3 {
    List<String> stock = new LinkedList();
    // 互斥量，控制共享数据的互斥访问
    private Semaphore mutex = new Semaphore(1);

    // canProduceCount可以生产的总数量。 通过生产者调用acquire，减少permit数目
    private Semaphore canProduceCount = new Semaphore(10);

    // canConsumerCount可以消费的数量。通过生产者调用release，增加permit数目
    private Semaphore canConsumerCount = new Semaphore(0);

    public void put(String computer) {
        try {
            // 可生产数量 -1
            canProduceCount.acquire();
            mutex.acquire();
            // 生产一台电脑
            stock.add(computer);
            System.out.println(computer + " 正在生产数据" + " -- 库存剩余：" + stock.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放互斥锁
            mutex.release();
            // 释放canConsumerCount，增加可以消费的数量
            canConsumerCount.release();
        }
        // 无逻辑作用，放慢速度
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void get(String consumerName) {
        try {
            // 可消费数量 -1
            canConsumerCount.acquire();
            mutex.acquire();
            // 从库存消费一台电脑
            String removedVal = stock.remove(0);
            System.out.println(consumerName + " 正在消费数据：" + removedVal + " -- 库存剩余：" + stock.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            // 消费后释放canProduceCount，增加可以生产的数量
            canProduceCount.release();
        }
    }

    public static void main(String[] args) {
        // 用于多线程操作的库存变量
        final ProducerAndConsumerExample3 stock = new ProducerAndConsumerExample3();
        // 定义两个生产者和两个消费者
        Thread dellProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    stock.put("Del");
                }
            }
        });
        Thread macProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    stock.put("Mac");
                }
            }
        });
        Thread consumer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    stock.get("zhangsan");
                }
            }
        });
        Thread consumer2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    stock.get("李四");
                }
            }
        });
        dellProducer.start();
        macProducer.start();
        consumer1.start();
        consumer2.start();
    }
}

