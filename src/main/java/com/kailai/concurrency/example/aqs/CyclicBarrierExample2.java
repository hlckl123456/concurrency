package com.kailai.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {
//    private static int threadCount = 200;

    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(3000);
        log.info("{} is ready", threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn("BrokenBarrierException");
        }
        log.info("{} continue", threadNum);

    }

}
