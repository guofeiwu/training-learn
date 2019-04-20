package com.guofei.wu.weekthirteen;

import java.util.concurrent.Callable;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/27
 * @since 2018/8/27
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
