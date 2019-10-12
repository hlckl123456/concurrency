package com.kailai.concurrency.atomic;

import com.kailai.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import static java.util.concurrent.Executors.newCachedThreadPool;

@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
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
        log.info("count:{}",isHappened.get());
    }

    private static void test() {
        // 如果让某段代码只执行一次
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}
