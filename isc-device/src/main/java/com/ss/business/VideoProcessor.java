package com.ss.business;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ss.access.IDeviceDo;
import com.ss.listener.ProgressListenerImpl;
import com.ss.mapper.LocalCameraMapper;
import com.ss.model.Camera;
import com.ss.utils.DeviceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
/**
* 设备推流
* @author chao
* @create 2019/9/12
* @email lishuangchao@ss-cas.com
**/
@Service
@Scope("singleton")
public class VideoProcessor {
	private static final Logger log = LoggerFactory.getLogger(VideoProcessor.class);

	@Autowired
	protected LocalCameraMapper cameraService;

	private FFmpegExtender ffmpeg;

	private Map<String, LiveStreamListener> listenerMap = new HashMap<>();

	/**
	 * 初始化
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

	/**
	 * 启动推流
	 * @param code
	 * @param deviceDo
	 * @throws Exception
	 */
	public void startRemoteLive(String code, IDeviceDo deviceDo) throws Exception {
		log.warn("开始推流 1");
		if (this.ffmpeg == null) {
			log.warn("开始推流 2");
			this.init();
		}
		Camera camera = this.cameraService.findByCodeOffLine(code);
		log.warn("开始推流 3");
		if (camera == null) {
			log.warn("设备不存在");
			return;
		}
		log.warn("开始推流 4");
//		String command = this.createStreamCommand(camera);
//		log.debug("command:" + command);
		String ffprobeCommandPath = DeviceUtil.getFfprobeCommandPath();
		FFprobe ffprobe = new FFprobe(ffprobeCommandPath);
		FFmpegExecutor executor = new FFmpegExecutor(this.ffmpeg, ffprobe);
		// 摄像头rtsp地址
		String cameraAddress = camera.getStreamSource();
		String remoteUrl = DeviceUtil.getRemoteUrl();
		// 输出流，远程视频服务地址
		String rtmpLink = remoteUrl + "/" + camera.getCameraId();
		log.warn("remoteUrl:" + remoteUrl);
		log.warn("cameraAddress:" + cameraAddress);
//		boolean streamInLive = liveStreamListener.isStreamInLive(cameraAddress);
//		System.out.println(streamInLive);
		FFmpegProbeResult in = ffprobe.probe(cameraAddress);
		log.warn("rtmpLink:" + rtmpLink);
		FFmpegBuilder builder = new FFmpegBuilder().addExtraArgs("-an").setInput(in).addOutput(rtmpLink)
				.setFormat("flv").setAudioCodec("aac").setVideoCodec("copy").addExtraArgs("-strict").addExtraArgs("-2").setVideoResolution(1920, 1080).done();
		// 创建监听器
		CustomRunProcessFunc cpf = (CustomRunProcessFunc) this.ffmpeg.getFunc();
		LiveStreamListener listener = new LiveStreamListener(camera.getCameraIp(), camera.getCameraId());
		cpf.add(listener);
		listenerMap.put(code, listener);
		// 进行推拉流
		FFmpegJob job = executor.createJob(builder, new ProgressListenerImpl(code, in, ffmpeg, camera, deviceDo));
		this.cameraService.online(code);
		job.run();
	}

	/**
	 * 停止推流
	 * @param code
	 */
//	public void stopRemoteLive(WebSocketSession session,String businessId, String code) {
	public void stopRemoteLive(String code) {
		log.debug("停止推流 1" +code);
		CustomRunProcessFunc cpf = (CustomRunProcessFunc) this.ffmpeg.getFunc();
		log.debug("停止推流 2" +code);
		if(cpf.getListenerFromCode(code)==null||cpf.getListenerFromCode(code).getProcess()==null){
			return;
		}
		cpf.getListenerFromCode(code).getProcess().destroy();
		boolean listener = cpf.delListener(listenerMap.get(code));
		if (listener){
			listenerMap.remove(code);
		}
		this.cameraService.offline(code);
		log.debug("停止推流 3" +code);
	}

	/*
	 * 生成推拉流指令字符串
	 */
	public String createStreamCommand(Camera camera) {
		//String remoteUrl = properties.getProperty("local.camera.remoteUrl");
		String remoteUrl = DeviceUtil.getRemoteUrl();
		// 摄像头rtsp地址
//		String cameraAddress = "rtsp://" + camera.getAccessName() + ":" + camera.getAccessPass() + "@" + camera.getIp()
//				+ ":" + camera.getPort().toString() + "/h264/ch1/main/av_stream";
		String cameraAddress = camera.getStreamSource();
		// 远程流媒体服务地址
		String rtmpLink = remoteUrl + "/" + camera.getCameraId();
		String command = "ffmpeg -i";
		String options = "-f flv -r -s -an";
		command = command + " \"" + cameraAddress + "\" " + options + " \"" + rtmpLink + "\"";
		return command;
	}
}
