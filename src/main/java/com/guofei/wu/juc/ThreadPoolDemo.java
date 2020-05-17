package com.guofei.wu.juc;

import com.guofei.wu.weeknine.sort.javasortutil.User;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @version v1.0
 * @since 2018/12/25
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(1);
//        service.submit(() ->
//                System.out.println("A")
//        );

        Future<Integer> submit = service.submit(() -> new Integer(1));


        service.shutdown();

//        System.out.println(submit.get());


        Arrays.asList(1, 2, 3).parallelStream().filter((i) ->
                i > 1
        ).forEach((t) -> System.out.println(t));


    }

}
