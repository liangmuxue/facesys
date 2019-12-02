package com.ss.thread;

import com.ss.utils.DeviceUtil;
import org.springframework.stereotype.Component;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class CameraThreadPool {

    private static Integer threadPoolSize;
    private static Integer threadMaxPoolSize;
    private static Integer threadAliveTime;
    private static ThreadPoolExecutor executor = null;

    public static void init(Integer size, Integer pool, Integer time) {
        threadPoolSize = size;
        threadMaxPoolSize = pool;
        threadAliveTime = time;
    }

    public static ThreadPoolExecutor getThread() {
        if (null == executor) {
            if (null == threadPoolSize || null == threadMaxPoolSize || null == threadAliveTime) {
                init(Integer.valueOf(DeviceUtil.getThreadPoolSize()),
                        Integer.valueOf(DeviceUtil.getThreadMaxPoolSize()),
                        Integer.valueOf(DeviceUtil.getThreadAliveTime()));
            }

            ArrayBlockingQueue workQueue = new ArrayBlockingQueue(2);
            ThreadFactory threadFactory = new NameTreadFactory();

            executor = new ThreadPoolExecutor(threadPoolSize.intValue(), threadMaxPoolSize.intValue(), threadAliveTime.intValue(), TimeUnit.SECONDS, workQueue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

            executor.prestartAllCoreThreads();
        }
        return executor;
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "thread-" + this.mThreadNum.getAndIncrement());
            System.out.println("系统线程：" + t.getName() + " 已创建");
            return t;
        }

    }

}
