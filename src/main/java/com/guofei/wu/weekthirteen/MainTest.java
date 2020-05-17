package com.guofei.wu.weekthirteen;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 线程测试
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/27
 * @since 2018/8/27
 */
public class MainTest {

    @Test
    public void extendThreadTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            Thread thread = new ExtendThreadWay("name" + i);
            thread.start();
            System.out.println(thread.getName());
        }
    }

    @Test
    public void implementsRunnableTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            Thread thread = new Thread(new ImplementsRunnableWay(), "Windows " + i);
            thread.start();
        }
    }


    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            Callable callable = new MyCallable();
            FutureTask future = new FutureTask(callable);
            Thread thread = new Thread(future);
            thread.start();
            System.out.println(future.get());
        }
    }

    @Test
    public void executorsWayTest() throws ExecutionException, InterruptedException {
        ThreadPoolWay.threadPool();
    }


    @Test
    public void threadFactoryTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new MyThreadFactory());
        executorService.submit(new ImplementsRunnableWay()).get();
    }

    @Test
    public void test() {
        Executor executor = ThreadPoolWay.threadPoolExecutorWay(1,
                2,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(3), new MyThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        executor.execute(new ImplementsRunnableWay("my implements runnable way"));
    }

    @Test
    public void DateTest() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = new Long("1535621501000");
        Date date = new Date(l);
        System.out.println(sdf.format(date));
    }


}
