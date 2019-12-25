package com.ss.facesys.schedule.collect.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.SpringUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.thread.CameraThreadPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
* 像机设备在线离线验证定时任务
* @author chao
* @create 2019/12/25
* @email lishuangchao@ss-cas.com
**/
public class IpJob implements SimpleJob {
    public static final Log LOG = LogFactory.getLog(IpJob.class);
    private CameraMapper cameraMapper = SpringUtil.getBean(CameraMapper.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        LOG.info("定时任务IpJob已经启动" + DateUtil.getCurrentSqlTimestampString());
        IpJob.CheckIp checkIp = new IpJob.CheckIp();
        //放入线程池
        CameraThreadPool.getThread().execute(checkIp);
    }

    public class CheckIp implements Runnable {

        @Override
        public void run() {
            Camera myCamera = new Camera();
            //查询所有未删除设备
            List<Camera> cameraList = IpJob.this.cameraMapper.findCameras(myCamera);
            List<Camera> tempCameras = new ArrayList<>();
            for (Camera camera : cameraList) {
                if (StringUtils.isNotBlank(camera.getCameraIp())) {
                    try {
                        //验证ip是否存在
                        boolean reachable = InetAddress.getByName(camera.getCameraIp()).isReachable(1000);
                        if (camera.getCameraState() == 0 && reachable) {
                            camera.setCameraState(1);
                            tempCameras.add(camera);
                        } else if (camera.getCameraState() == 1 && !reachable) {
                            camera.setCameraState(0);
                            tempCameras.add(camera);
                        }
                    } catch (IOException e) {
                        LOG.error("IpJob异常:" + e.toString(), e);
                        e.printStackTrace();
                    }
                }
            }
            if (tempCameras.size() > 0){
                //修改在线离线状态
                IpJob.this.cameraMapper.updateCameraState(tempCameras);
            }
        }
    }
}
