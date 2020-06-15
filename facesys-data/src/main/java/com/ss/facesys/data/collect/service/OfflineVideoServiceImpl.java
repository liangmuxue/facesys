package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.business.CustomRunProcessFunc;
import com.ss.business.FFmpegExtender;
import com.ss.business.LiveStreamListener;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IOfflineVideoService;
import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.collect.mapper.OfflineVideoMapper;
import com.ss.facesys.data.resource.common.web.OfflineVideoVO;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.HttpConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.http.BaseHttpUtil;
import com.ss.listener.CutFlowListenerImpl;
import com.ss.utils.DeviceUtil;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 离线视频
 *
 * @author chao
 * @create 2020/2/7
 * @email lishuangchao@ss-cas.com
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class OfflineVideoServiceImpl extends BaseServiceImpl implements IOfflineVideoService {
    public static final Log logger = LogFactory.getLog(OfflineVideoServiceImpl.class);

    @Autowired
    private OfflineVideoMapper offlineVideoMapper;

    /**
     * 离线视频分页查询
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public List<OfflineVideo> offlineVideoPage(OfflineVideoVO offlineVideoVO) {
        PageHelper.startPage(offlineVideoVO.getCurrentPage(), offlineVideoVO.getPageSize());
        List<OfflineVideo> internetBars = this.offlineVideoMapper.offlineVideoPage(offlineVideoVO);
        return internetBars;
    }

    /**
     * 添加离线视频
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public int insertOfflineVideo(OfflineVideoVO offlineVideoVO) {
        this.offlineVideoMapper.insertOfflineVideo(offlineVideoVO);
        return offlineVideoVO.getId();
    }

    /**
     * 查询离线视频详情
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public OfflineVideo detail(OfflineVideoVO offlineVideoVO) {
        OfflineVideo detail = this.offlineVideoMapper.detail(offlineVideoVO);
        return detail;
    }

    /**
     * 修改离线视频信息
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public int updateOfflineVideo(OfflineVideoVO offlineVideoVO) {
        int result = this.offlineVideoMapper.updateOfflineVideo(offlineVideoVO);
        return result;
    }

    /**
     * 删除离线视频
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public int deleteOfflineVideo(OfflineVideoVO offlineVideoVO) {

        boolean flag = true;
        int num = 0;
        OfflineVideo detail = this.offlineVideoMapper.detail(offlineVideoVO);
        if (detail.getDeviceId() != null) {
            JSONObject resultJson;
            Map<String, Object> parm = null;
            String postUrl = null;
            String deviceId = null;
            postUrl = PropertiesUtil.getOshttp() + HttpConstant.VIDEO_DELETE;
            // 设置请求参数
            parm = new HashMap<String, Object>();
            parm.put("deviceId", detail.getDeviceId());
            // 发送请求调用欧神接口
            String result = BaseHttpUtil.httpPost(JsonUtils.getFastjsonFromObject(parm), postUrl, null);
            this.logger.info("离线视频删除" + result);

            resultJson = JSONObject.parseObject(result);
            if (resultJson == null || !StringUtils.checkSuccess(resultJson)) {
                flag = false;
            }
        }
        if (flag) {
            num = this.offlineVideoMapper.deleteOfflineVideo(offlineVideoVO);
        }
        return num;
    }

    /**
     * 添加离线视频关联ocean编号
     *
     * @param offlineVideoVO
     * @return
     */
    @Override
    public int insertDeviceId(OfflineVideoVO offlineVideoVO) {

        OfflineVideo detail = this.offlineVideoMapper.detail(offlineVideoVO);
        if (detail == null) {
            return 0;
        }
        JSONObject resultJson;
        Map<String, Object> parm = null;
        String postUrl = null;
        String deviceId = null;
        postUrl = PropertiesUtil.getOshttp() + HttpConstant.VIDEO_ADD;
        // 设置请求参数
        parm = new HashMap<String, Object>();
        parm.put("deviceName", detail.getName());
        parm.put("path", offlineVideoVO.getDepositUrl());
        // 发送请求调用欧神接口
        String result = BaseHttpUtil.httpPost(JsonUtils.getFastjsonFromObject(parm), postUrl, null);
        this.logger.info("离线视频新增" + result);

        resultJson = JSONObject.parseObject(result);
        if (resultJson != null && StringUtils.checkSuccess(resultJson)) {
            deviceId = resultJson.getString("data");
            offlineVideoVO.setDeviceId(deviceId);
        } else {
            offlineVideoVO.setStatus(NumberConstant.FOUR);
        }
        //关联ocean设备编号
        int num = this.offlineVideoMapper.insertDeviceId(offlineVideoVO);
        if(num > 0 && deviceId != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //离线视频抽帧
                    analysisVideo(offlineVideoVO);
                }
            }).start();
        }
        return num;
    }

    /**
     * 修改离线视频状态
     *
     * @param para
     * @return
     */
    @Override
    public int updateStatus(OfflineVideoVO para) {
        return this.offlineVideoMapper.updateStatus(para);
    }

    /**
     * 离线视频抽帧
     * @param offlineVideoVO
     */
    private void analysisVideo(OfflineVideoVO offlineVideoVO) {

        this.logger.info("离线视频分析开始" + offlineVideoVO.getDeviceId());
        //修改离线视频状态
        offlineVideoVO.setStatus(NumberConstant.TWO);
        this.offlineVideoMapper.updateStatus(offlineVideoVO);
        try {
            //ffmpeg存储路径
            String ffmpegCommandPath = DeviceUtil.getFfmpegCommandPath();
            //ffprobe存储路径
            String ffprobeCommandPath = DeviceUtil.getFfprobeCommandPath();
            // 定义监听者
            CustomRunProcessFunc func = new CustomRunProcessFunc();
            // 把监听者绑定到ffmpeg对象
            FFmpegExtender ffmpeg = new FFmpegExtender(ffmpegCommandPath, func);
            FFprobe ffprobe = new FFprobe(ffprobeCommandPath);
            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

            String cutFlowUrl = DeviceUtil.getCutFlowUrl() + "/" + offlineVideoVO.getDeviceId();
            File file = new File(cutFlowUrl);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 输出流，远程视频服务地址
            String rtmpLink = cutFlowUrl + "/" + "%d.jpeg";
            FFmpegProbeResult in = ffprobe.probe( FilePropertiesUtil.getLocation() + offlineVideoVO.getDepositUrl());
            FFmpegBuilder builder = new FFmpegBuilder().addExtraArgs("-an").setInput(in).addOutput(rtmpLink).setFormat("image2").addExtraArgs("-vf").addExtraArgs("fps=fps=1").done();
            FFmpegJob job = executor.createJob(builder, new ProgressListener() {
                @Override
                public void progress(Progress progress) {
                    String progressStatus = progress.status.toString();
                    if ("end".equals(progressStatus)) {
                        offlineVideoVO.setStatus(NumberConstant.THREE);
                        OfflineVideoServiceImpl.this.offlineVideoMapper.updateStatus(offlineVideoVO);
                        OfflineVideoServiceImpl.this.logger.info("离线视频分析结束" + offlineVideoVO.getDeviceId());
                    }
                }
            });
            job.run();

        } catch (Exception e) {
            offlineVideoVO.setStatus(NumberConstant.FIVE);
            this.offlineVideoMapper.updateStatus(offlineVideoVO);
            this.logger.error("离线视频分析异常" + e.toString(), e);
        }
    }

}
