package com.kailai.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;
import org.springframework.ui.context.Theme;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        Thread.sleep(1000);
        log.info("do something in main");
        String result = future.get();
        log.info("result {}", result);
    }
}
