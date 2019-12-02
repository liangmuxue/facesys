package com.ss.listener;

import com.ss.access.IDeviceCut;
import com.ss.business.FFmpegExtender;
import com.ss.business.LiveStreamListener;
import com.ss.model.Camera;
import com.ss.model.CutFlowState;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 设备抽帧监听
 * @author 李爽超 chao
 * @create 2019/09/05
 * @email lishuangchao@ss-cas.com
 **/

public class CutFlowListenerImpl implements ProgressListener {

    private static final Logger log = LoggerFactory.getLogger(CutFlowListenerImpl.class);

    int resFlag = 0;

    private Camera camera;

    private String code;

    private FFmpegProbeResult in;

    private FFmpegExtender ffmpeg;

    private IDeviceCut deviceCut;

    public CutFlowListenerImpl(String code, FFmpegProbeResult in, FFmpegExtender ffmpeg, Camera camera, IDeviceCut deviceCut){
        this.code = code;
        this.in = in;
        this.ffmpeg = ffmpeg;
        this.camera = camera;
        this.deviceCut = deviceCut;
    }

    @Override
    public void progress(Progress progress){

        final double duration_ns = in.getFormat().duration * TimeUnit.SECONDS.toNanos(1);
        String progressStatus = progress.status.toString();
        if ("continue".equals(progressStatus) && resFlag == 0) {
            // 启动云端流监听
            String url = camera.getStreamSource().substring(camera.getStreamSource().indexOf("/", 8) + 1);
            LiveStreamListener listener = ffmpeg.getListenerFromCode(camera.getCameraId());
            listener.setDeviceUri(url);
            log.warn("listener is:" + listener);
            if (listener != null) {
                log.warn("start live check");
                if(deviceCut != null){
                    List<CutFlowState> cutFlowStates = deviceCut.getCutFlowStates();
                    cutFlowStates.get(deviceCut.getSize() - 1).setState(1);
                    if (deviceCut.getSize() < cutFlowStates.size()) {
                        //下个设备开启抽帧
                        deviceCut.startCutFlow(cutFlowStates, deviceCut.getSize());
                    }
                }
            } else {
                log.warn("no listener to check:" + camera.getCameraId());
            }
            resFlag = 1;
        }
        double percentage = progress.out_time_ns / duration_ns;
        log.warn(String.format("[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx", percentage * 100,
                progress.status, progress.frame,
                FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS), progress.fps.doubleValue(),
                progress.speed));
    }
}
