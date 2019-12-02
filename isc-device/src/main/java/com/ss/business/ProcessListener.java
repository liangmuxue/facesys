package com.ss.business;

import static java.util.Objects.nonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

/**
 * 进程监听类
 * 
 * @author 梁慕学
 * @version 1.00
 */
public class ProcessListener {

    private final ExecutorService pool = Executors.newFixedThreadPool(10);

    public static final ProcessListener DEFAULT_PROCESS_LISTENER = new ProcessListener(StringUtils.EMPTY);

    private final String url;
    private Process process;

    public ProcessListener(String url){
        this.url = url;
    }
    
    public Future<Process> getProcess() {
        return pool.submit(() -> {
            while (true) {
                if(nonNull(process)) return process;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        });
    }

    public String getUrl() {
        return url;
    }
    
    
}