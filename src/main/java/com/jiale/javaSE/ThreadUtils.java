package com.jiale.javaSE;

import java.sql.Time;
import java.util.Locale;
import java.util.concurrent.*;

/**
 * corePoolSize，核心线程数量，决定是否创建新的线程来处理到来的任务
 * maximumPoolSize，最大线程数量，线程池中允许创建线程地最大数量
 * keepAliveTime，线程空闲时存活的时间
 * unit，空闲存活时间单位
 * workQueue，任务队列，用于存放已提交的任务
 * threadFactory，线程工厂，用于创建线程执行任务
 * handler，拒绝策略，当线程池处于饱和时，使用某种策略来拒绝任务提交
 *
 * 1）如果workerCount < corePoolSize ==> 创建线程执行提交的任务
 * 2）如果workerCount >= corePoolSize && 阻塞队列未满 ==> 添加至阻塞队列，等待后续线程来执行提交地任务
 * 3）如果workerCount >= corePoolSize && workerCount < maxinumPoolSize && 阻塞队列已满 ==> 创建非核心线程执行提交的任务
 * 4）如果workerCount >= maxinumPoolSize && 阻塞队列已满 ==> 执行拒绝策略
 */
public class ThreadUtils {
    private ThreadUtils(){

    }

    /**
     * corePoolSize，核心线程数量，决定是否创建新的线程来处理到来的任务
     * 当线程数超过corePoolSize就会放入阻塞队列中
     */
    private static final Integer corePoolSize = 4;
    /**
     * maximumPoolSize，最大线程数量，线程池中允许创建线程地最大数量
     * 当阻塞队列中满了之后，就会创建最大线程数的线程
     */
    private static final Integer maximumPoolSize = 8;
    /**
     * 自定义线程的阻塞队列
     * ArrayBlockingQueue: 基于数组的有界阻塞队列，此队列按FIFO原则对元素进行排序
     */
    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(1024);
    /**
     * 线程空闲时的存活时间
     * 当创建的线程大于corePoolSize，生效
     */
    private static final Long keepAliveTime = 30L;
    /**
     * 空闲存活时间单位
     */
    private static final TimeUnit unit = TimeUnit.SECONDS;
    /**
     * 线程池执行器
     */
    private static ThreadPoolExecutor executor;
    /**
     * 拒绝策略
     * CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
     * DiscardOldestPolicy: 将阻塞队列中的任务poll出来，然后执行当前任务
     * DiscardPolicy: 直接丢弃任务
     * AbortPolicy: 抛出异常，用户可根据具体任务来做出具体的判断
     */
    private static final RejectedExecutionHandler REJECTED_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    public static class MakeNameForThread implements  ThreadFactory{
        private final String threadNamePrefix;

        public MakeNameForThread(String threadNamePrefix) {
            String format = format(threadNamePrefix, 0);
            this.threadNamePrefix = format;
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(threadNamePrefix);
            return thread;
        }
    }
    /**
     * 提交任务
     * @param command 线程
     */
    public static void execute(Runnable command) {
        getCommonThreadPoolInfo();
        executor.execute(command);
    }

    /**
     * 提交任务
     * @param command 线程
     */
    public static Future<?> submit(Runnable command) {
        getCommonThreadPoolInfo();
        return executor.submit(command);
    }

    private static void getCommonThreadPoolInfo() {
        System.out.println("完成任务");
    }

    // 初始化线程池执行器
    static {
        if (null == executor) {
            ThreadFactory threadFactory = new MakeNameForThread("myTestThread-%d");
            executor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    ARRAY_BLOCKING_QUEUE,
                    threadFactory,
                    REJECTED_HANDLER);
        }
    }
    private static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }
}
