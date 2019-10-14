package com.kailai.concurrency.example.deadLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockDemo implements Runnable {
    private int flag = 1;
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("flag = 1");
                }
            }
        } else {
            synchronized (o2) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("flag = 0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock dk1 = new DeadLock();
        DeadLock dk2 = new DeadLock();
        dk1.flag = 1;
        dk2.flag = 0;
        new Thread(dk1).start();
        new Thread(dk2).start();
    }
}
