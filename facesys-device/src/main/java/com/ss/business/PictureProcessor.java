package com.ss.business;

import com.ss.access.IDeviceCut;
import com.ss.listener.CutFlowListenerImpl;
import com.ss.mapper.LocalCameraMapper;
import com.ss.model.Camera;
import com.ss.utils.DeviceUtil;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* 设备抽帧
* @author chao
* @create 2019/9/12
* @email lishuangchao@ss-cas.com
**/
@Service
@Scope("singleton")
public class PictureProcessor {
	private static final Logger log = LoggerFactory.getLogger(PictureProcessor.class);
	@Autowired
	protected LocalCameraMapper cameraService;

	private FFmpegExtender ffmpeg;

	private Map<String, LiveStreamListener> listenerMap = new HashMap<>();

	/*
	 * 初始化入口，从数据库中载入摄像头列表
	 */
	public void init() {
		// 使用ffmpeg cli项目，进行fmpeg的流处理
		// 进行ffmpeg全局对象的初始化
		String ffmpegCommandPath = DeviceUtil.getFfmpegCommandPath();
		// 定义监听者
		CustomRunProcessFunc func = new CustomRunProcessFunc();
		// 把监听者绑定到ffmpeg对象
		try {
			this.ffmpeg = new FFmpegExtender(ffmpegCommandPath, func);
		} catch (IOException e) {
			log.error("ffmpeg init fail", e);
		}
	}


	public void startRemoteLive(String code, IDeviceCut deviceCut) throws Exception {
		log.warn("开始抽帧 1");
		if (this.ffmpeg == null) {
			log.warn("开始抽帧 2");
			this.init();
		}
		Camera camera = this.cameraService.findByCodeOffCut(code);
		log.warn("开始抽帧 3");
		if (camera == null) {
			log.warn("相机不存在");
			return;
		}
		log.warn("开始抽帧 4");
		String ffprobeCommandPath = DeviceUtil.getFfprobeCommandPath();
		FFprobe ffprobe = new FFprobe(ffprobeCommandPath);
		FFmpegExecutor executor = new FFmpegExecutor(this.ffmpeg, ffprobe);
		// 摄像头rtsp地址
		String cameraAddress = camera.getStreamSource();
		String cutFlowUrl = DeviceUtil.getCutFlowUrl();
		// 输出流，远程视频服务地址
		String rtmpLink = cutFlowUrl + "/" + camera.getCameraId()+ "/" + "%d.jpeg";
		log.warn("cutFlowUrl:" + cutFlowUrl);
		log.warn("cameraAddress:" + cameraAddress);
		FFmpegProbeResult in = ffprobe.probe(cameraAddress);
		log.warn("rtmpLink:" + rtmpLink);
		FFmpegBuilder builder = new FFmpegBuilder().addExtraArgs("-an").setInput(in).addOutput(rtmpLink).setFormat("image2").addExtraArgs("-vf").addExtraArgs("fps=fps=1").done();
		CustomRunProcessFunc cpf = (CustomRunProcessFunc) this.ffmpeg.getFunc();
		LiveStreamListener listener = new LiveStreamListener(camera.getCameraIp(), camera.getCameraId());
		cpf.add(listener);
        listenerMap.put(code, listener);
		FFmpegJob job = executor.createJob(builder, new CutFlowListenerImpl(code, in, ffmpeg, camera, deviceCut));
		this.cameraService.cutOnline(code);
		job.run();
	}

	public void stopRemoteLive(String code) {
		log.debug("停止抽帧 1" +code);
		CustomRunProcessFunc cpf = (CustomRunProcessFunc) this.ffmpeg.getFunc();
		log.debug("停止抽帧 2" +code);
		if(cpf.getListenerFromCode(code)==null||cpf.getListenerFromCode(code).getProcess()==null){
			return;
		}
		cpf.getListenerFromCode(code).getProcess().destroy();
        boolean listener = cpf.delListener(listenerMap.get(code));
        if (listener){
            listenerMap.remove(code);
        }
		this.cameraService.cutOffline(code);
		log.debug("停止抽帧 3" +code);
	}

}
