package com.kailai.concurrency.example.commonUnsafe;

import com.kailai.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 有两个线程不安全的点
 * 1.容器扩容时，当两个线程同时执行到需要扩容的点，但是他们刚好都处在不用扩容的个数，即size - 1
 *     就会出现outOfBound的exception
 * 2.size统计也会出现问题，即同一个size被get了两次，少加了很多。
 */


@NotThreadSafe
@Slf4j
public class HashMapExample {

    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }

        // 保证之前的countDown减为0
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",map.size());

    }

    private static void update(int i) {
        map.put(i, i);
    }
}
