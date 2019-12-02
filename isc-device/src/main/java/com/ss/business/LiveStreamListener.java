package com.ss.business;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ss.utils.DeviceUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.WebSocketSession;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

/**
 * 直播流监听以及处理
 * 
 * @author 梁慕学
 * @version 1.00
 */
public class LiveStreamListener {
	private Log logger = LogFactory.getLog(this.getClass());

	// @Autowired
	// protected LocalCameraService cameraService;

//	 @Autowired
//	 protected SocketClient client = null;

	private final ExecutorService pool = Executors.newFixedThreadPool(10);

	public static final ProcessListener DEFAULT_PROCESS_LISTENER = new ProcessListener(StringUtils.EMPTY);

	private String ip;// 推流ip
	private Process process;// 运行视频推流的进程对象
	private String businessId;// 此次拉流请求的业务序号
	private String deviceCode;// 拉流请求对应的摄像头序列号
	private String deviceUri;// 摄像头序型号字段
	private WebSocketSession session;

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

//	private SocketClient client;
//
//	public SocketClient getClient() {
//		return client;
//	}
//
//	public void setClient(SocketClient client) {
//		this.client = client;
//	}

//    public LiveStreamListener(){
//        this.client = (SocketClient)SpringContextUtils.getBeanByClass(SocketClient.class);
//    }
//
//	public LiveStreamListener(String ip) {
//        this.client = (SocketClient)SpringContextUtils.getBeanByClass(SocketClient.class);
//		this.ip = ip;
//		// 通过ip找出对应的序列号
//		// Camera c = cameraService.findByIp(ip);
//		// if(c!=null){
//		// this.deviceCode = c.getCode();
//		// }
//	}

	public LiveStreamListener(String ip, String deviceCode) {
        //this.client = (SocketClient)SpringContextUtils.getBeanByClass(SocketClient.class);
		this.ip = ip;
		this.deviceCode = deviceCode;
	}

//	public void startRemoteLiveCheck(FFmpegExtender ffmpeg) {
//		//String remoteUrl = properties.getProperty("local.camera.remoteUrl");
//		String remoteUrl = PropertiesUtil.getRemoteUrl();
//		String liveCheckMax = properties.getProperty("local.camera.startRemoteLiveCheck");
//		pool.submit(() -> {
//			// 接收到推流成功后，进行云端拉流尝试，拉流成功后再通知云端业务服务器
//			Integer nowTime=CommonUtil.getCurrTimes();
//			Integer endTime=CommonUtil.getCurrTimes();
//			while (endTime-nowTime<=Integer.parseInt(liveCheckMax)) {
//				String url = remoteUrl + "/" + deviceCode;// "rtmp://47.104.2.109:1935/hls/100384215";
//				boolean flag = isStreamInLive(url);
//				logger.debug("livetest startRemoteLiveCheck " + url + "  flag " + flag);
//				if (flag) {
////					try {
////						Thread.sleep(5000);
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//					// 发送拉流ready的通知
//					logger.debug("livetest startRemoteLiveChecksend");
//					endTime=nowTime;
//					this.sendPrgressInfo(2);
//					break;
//				}
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					logger.warn("sleep err", e);
//				}
//				endTime=CommonUtil.getCurrTimes();
//			}
//			if(endTime-nowTime>Integer.parseInt(liveCheckMax)){
//				//发送失败消息
//				LsMessage msg = new LsMessage();
//				msg.setCode("1001");
//				msg.setBusinessId(businessId);
//				msg.setDeviceCode(deviceCode);
//				msg.setCreateTime(CommonUtil.getCurrTimes());
//				msg.setSuccess(0);// 推流失败
//				client.sendMessage(msg);
//				//关闭进程
//				CustomRunProcessFunc cpf = (CustomRunProcessFunc) ffmpeg.getFunc();
//				logger.debug("stopRemoteLive 3 " +deviceCode);
//				if(cpf.getListenerFromCode(deviceCode)==null||cpf.getListenerFromCode(deviceCode).getProcess()==null){
//					return;
//				}
//				cpf.getListenerFromCode(deviceCode).getProcess().destroy();
//			}
//		});
//
//	}

	/*
	 * 检查云端流是否ready
	 * 
	 * @param String url 云端流地址
	 */
	public boolean isStreamInLive(String url) {
		//String ffprobeCommandPath = properties.getProperty("local.camera.ffprobeCommandPath");
		String ffprobeCommandPath = DeviceUtil.getFfprobeCommandPath();
		FFprobe ffprobe;
		try {
			ffprobe = new FFprobe(ffprobeCommandPath);
		} catch (IOException e) {
			logger.error("ffprobeCommandPath fail", e);
			return false;
		}
		FFmpegProbeResult probeResult;
		try {
			probeResult = ffprobe.probe(url);
			FFmpegFormat format = probeResult.getFormat();
			FFmpegStream stream = probeResult.getStreams().get(0);
			logger.debug("stream.codec_long_name:" + stream.codec_long_name);
			return true;
		} catch (IOException e) {
			logger.info("probeResult fail", e);
			return false;
		}

	}

	/*
	 * 发送拉流状态信息
	 * 
	 * @param Integer liveStatus
	 */
//	public void sendPrgressInfo(Integer liveStatus) {
//		LsMessage msg = new LsMessage();
//		msg.setCode("1001");
//		msg.setBusinessId(businessId);
//		msg.setSuccess(1);
//		msg.setDeviceCode(deviceCode);
//		msg.setCreateTime(CommonUtil.getCurrTimes());
//		msg.setLiveStatus(liveStatus);
////		logger.debug("livetest sendMessage1:");
////		String msgStr = JSONObject.fromObject(msg).toString();
////		logger.debug("livetest sendMessage2:" + msgStr);
////		TextMessage message = new TextMessage(msgStr);
////		logger.debug("livetest sendMessage:" + msgStr);
////		try {
////			this.session.sendMessage(message);
////		} catch (IOException e) {
////			logger.error("send msg fail for:" + msgStr, e);
////		}
//		logger.debug("livetest sendPrgressInfo 8.1 " + client);
//		try {
//			client.sendMessage(msg);
//		} catch (Exception e) {
//			logger.debug("livetest sendPrgressInfo 8.2 " + e);
//		}
//	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Process getProcess() {
		return process;
	}

	public String getDeviceUri() {
		return deviceUri;
	}

	public void setDeviceUri(String deviceUri) {
		this.deviceUri = deviceUri;
	}

}
