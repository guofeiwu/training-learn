package com.guofei.wu.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author guofei.wu
 * @version v1.0
 * @date 2022/3/11 10:53
 * @since v1.0
 */
public class CompletableFutureTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws Exception {
        System.out.println("main start ...");
//        CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start");
//        }, executor);

//        CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start ...");
//
//            int i = 10 / 0;
//
//            System.out.println(Thread.currentThread() + "---->task end ...");
//
//            return i;
//        }, executor).whenCompleteAsync((result, exception) -> {
//
//
//            System.out.println(Thread.currentThread() + "---->when task start ...");
//
//            System.out.println("when result:" + result + "====>exception:" + exception);
//
//
//            System.out.println(Thread.currentThread() + "---->when task end ...");
//
//
//        }, executor);


//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start ...");
//
//            int i = 10 / 0;
//
//            System.out.println(Thread.currentThread() + "---->task end ...");
//
//            return i;
//        }, executor).exceptionally(exception -> {
//            System.out.println("occur exception:" + exception);
//            return 3;
//        });
//
//        Integer result = future.get();
//
//        System.out.println("main end ..." + result);


//        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start ...");
//
//            int i = 10 / 0;
//
//            System.out.println(Thread.currentThread() + "---->task end ...");
//
//            return i;
//        }, executor);
//
//
//        CompletableFuture<? extends Serializable> completableFuture = future01.handleAsync((result, throwable) -> {
//            if (result != null) {
//                return result + "===>hahah";
//            }
//            if (throwable != null) {
//                return "occur exception" + throwable;
//            }
//            return 6;
//        });
//        System.out.println(completableFuture.get());


//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start ...");
//
//            int i = 10 / 2;
//
//            System.out.println(Thread.currentThread() + "---->task end ...");
//
//            return i;
//        }, executor);
//        future.thenAcceptAsync(result -> {
//            System.out.println(result);
//        }, executor);
//
//        CompletableFuture<String> stringCompletableFuture = future.thenApplyAsync(result -> {
//            return result + "3";
//        }, executor);
//
//        System.out.println(stringCompletableFuture.get());


        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "---->task start ...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 10 / 2;
            System.out.println(Thread.currentThread() + "---->task end ...");
            return i;
        }, executor);

        CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "---->task2 start ...");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "---->task2 end ...");

            return 1;
        }, executor);


        boolean complete = future01.complete(10);
        System.out.println("complete:" + complete);
        System.out.println(future01.get());


//        System.out.println(future01.cancel(true));


//        future01.thenCombineAsync(future02, (t, u) -> {
//            System.out.println(t);
//            System.out.println(u);
//            System.out.println(t + u);
//            return 0;
//        }, executor);

        Integer aaa = CompletableFuture.supplyAsync(() -> 5).thenCompose(t -> {
            System.out.println(t);
            return CompletableFuture.completedFuture(6);
        }).get();

        System.out.println("aaa:" + aaa);


//        future01.runAfterBoth(future02, () -> {
//            System.out.println("runAfterBoth");
//        });

//        future01.runAfterEitherAsync(future02, () -> {
//            System.out.println("runAfterEitherAsync...");
//        }, executor);

//        CompletableFuture.allOf(future01,future02);
//        CompletableFuture.anyOf(future01,future02).thenRun(()->{
//            System.out.println("anyOf");
//        });


//        future01.acceptEitherAsync(future02,res->{
//            System.out.println(res);
//        },executor);

        //任意任务完成，有返回值
//        CompletableFuture<Object> objectCompletableFuture = future01.applyToEitherAsync(future02, r -> {
//            System.out.println(r);
//            return r;
//        }, executor);
//
//        System.out.println("objectCompletableFuture:" + objectCompletableFuture.get());

//        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread() + "---->task start ...");
//            int i = 10 / 2;
//            System.out.println(Thread.currentThread() + "---->task end ...");
//            return i;
//        }, executor);


    }
}
