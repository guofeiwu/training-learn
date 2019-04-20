package com.guofei.wu.weekthirteen;

import java.util.concurrent.*;

/**
 * 通过创建线程池创建线程
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/27
 * @since 2018/8/27
 */
public class ThreadPoolWay {

    /**
     * 创建线程池
     *
     * @author Mason
     * @since 2018/8/28
     */
    public static void threadPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = null;
        // 创建单线程的线程池 核心线程数和最大线程数为1
        // executorService = Executors.newSingleThreadExecutor();
        // executorService.submit(new ImplementsRunnableWay()).get();

        // 创建固定核心线程数和最大线程数的线程池
//        executorService = Executors.newFixedThreadPool(2);

        // 创建一个带缓存的线程池 60s过期
//        executorService = Executors.newCachedThreadPool();
        // 延迟执行的线程池
//        executorService = Executors.newScheduledThreadPool(3);
        // 创建一个工作窃取线程池 jdk 1.8
//        executorService = Executors.newWorkStealingPool(10);
        // 单线程的线程池，可延迟执行
        executorService = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new ImplementsRunnableWay()).get();
        }
    }


    /**
     * 推荐使用这种方式创建线程池
     *
     * @param corePoolSize    核心线程数量 当有任务时，创建线程，若线程数量大于核心线程池时，将任务加入到队列中
     * @param maximumPoolSize 最大线程数量 ，当队列满了之后会继续创建线程，直到达到最大线程数
     * @param keepAliveTime   若当前线程数量大于核心线程数时，若没有新的任务，多出的线程存活的时间
     * @param unit            时间单位
     * @param workQueue       工作队列，也就是当核心线程数满之后任务加入的队列
     * @param threadFactory   线程工厂，创建一个新的线程
     * @param handler         处理程序，当队列满之后还有任务过来，对任务的处理方式
     * @return Executor
     * @author Mason
     * @since 2018/8/28
     */
    public static Executor threadPoolExecutorWay(int corePoolSize,
                                                 int maximumPoolSize,
                                                 long keepAliveTime,
                                                 TimeUnit unit,
                                                 BlockingQueue<Runnable> workQueue,
                                                 ThreadFactory threadFactory,
                                                 RejectedExecutionHandler handler) {
        Executor executor1 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        Executor executor2 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        Executor executor3 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        Executor executor4 = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        return executor1;
    }


    /**
     * 关于{@link BlockingQueue} 比较常用的有以下几种
     * {@link ArrayBlockingQueue} {@link LinkedBlockingQueue}
     * {@link PriorityBlockingQueue}
     *
     * ArrayBlockingQueue阻塞队列的方式是FIFO,头部是进入到队列中时间最久的，尾部是进入到队列中时间最短的。
     * 一旦创建大小就固定了，如果试图向一个满的队列中插入元素会造成操作阻塞，向一个空的队列获取一个元素依然会造成阻塞。
     *
     *
     *
     */


}
