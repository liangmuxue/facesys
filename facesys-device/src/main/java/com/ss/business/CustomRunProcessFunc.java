package com.ss.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.mapper.LocalCameraMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;


import net.bramp.ffmpeg.RunProcessFunction;

/**
 * 自定义RunProcessFunction，用于进程监控和停止
 * 
 * @author 梁慕学
 * @version 1.00
 */
public class CustomRunProcessFunc extends RunProcessFunction {
	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	protected LocalCameraMapper cameraService;

	private List<LiveStreamListener> listeners = new ArrayList<LiveStreamListener>();

	@Override
	public Process run(List<String> args) throws IOException {
		if (args.get(1).equals("-version")) {
			return super.run(args);
		}
		// 查找对应命令的输入流，并取得对应的摄像头序列号，匹配后把进程进行挂接
		List<String> l = args.stream().filter(item -> item.startsWith("rtsp:")).collect(Collectors.toList());
		if (l.size() > 0) {
			String rtspAddress = l.get(0);
			// 根据推流地址取得摄像头ip，并通过ip查找到对应的摄像头
			final String ip = rtspAddress.split("@")[1].split(":")[0];
			// final String ip = "192.168.1.105";
			// 如果本地摄像头没有这个ip，则不进行
			if (ip == null) {
				logger.warn("no camera ip:" + ip);
				return null;
			}
			// 查找到之前设置的监听器，放入进程
			List<LiveStreamListener> ls = listeners.stream().filter(item -> item.getIp().equals(ip))
					.collect(Collectors.toList());
			if (ls.size() == 0) {
				logger.warn("no camera stream listener:" + ip);
				return null;
			}
			Process p = super.run(args);
			// 进程对象放入listener
			LiveStreamListener listener = ls.get(0);
			listener.setProcess(p);
			return p;
		}
		return super.run(args);
	}

	public LiveStreamListener getListenerFromCode(String deviceCode) {
		List<LiveStreamListener> ls = listeners.stream().filter(item -> item.getDeviceCode().equals(deviceCode))
				.collect(Collectors.toList());
		if (ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	public CustomRunProcessFunc add(LiveStreamListener pl) {
		this.listeners.add(pl);
		return this;
	}

	public boolean delListener(LiveStreamListener pl){
		boolean remove = this.listeners.remove(pl);
		return remove;
	}
}
