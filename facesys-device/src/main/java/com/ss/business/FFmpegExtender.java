package com.ss.business;

import java.io.BufferedReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.ProcessFunction;

/**
 * FFmpeg扩展类，用于进程监听，进程停止等
 * 
 * @author 梁慕学
 * @version 1.00
 */
public class FFmpegExtender extends FFmpeg {
    private static final Logger log = LoggerFactory.getLogger(FFmpegExtender.class);
    
    ProcessFunction func = null;
    
    public FFmpegExtender() throws IOException {
        super();
    }
    
    public FFmpegExtender(String commandPath) throws IOException {
        super(commandPath);
    }
    
    public FFmpegExtender(String commandPath,ProcessFunction func) throws IOException {
        super(commandPath,func);
        this.func = func;
    }
    
    
    protected BufferedReader wrapInReader(Process p){
//        log.debug("wrapInReader in");
//        try {
//            IOUtils.copy(wrapInReader(p), System.out);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return super.wrapInReader(p);
    }

    public LiveStreamListener getListenerFromCode(String deviceCode){
        CustomRunProcessFunc pf = (CustomRunProcessFunc)this.getFunc();
        return pf.getListenerFromCode(deviceCode);
    }
    
    public ProcessFunction getFunc() {
        return func;
    }

    public void setFunc(ProcessFunction func) {
        this.func = func;
    }
    
    
}
