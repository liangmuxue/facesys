package com.ss.facesys.data.statistic.service;

import com.ss.facesys.data.collect.common.model.SnapRecord;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.data.statistic.client.SnapHourService;
import com.ss.facesys.data.statistic.common.dto.SnapHour;
import com.ss.facesys.data.statistic.mapper.SnapHourMapper;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.FaceOrBodyEnum;
import com.ss.facesys.util.em.ResourceType;
import com.ss.tools.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SnapHourServiceImpl
 *
 * @author zhangao
 * @date 2021/3/5
 * @email zhangao@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SnapHourServiceImpl implements SnapHourService {

    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private SnapHourMapper snapHourMapper;
    
    @Override
    public void selSnapHourJob(int dayNum) {
        //获取起始时间和今天的日期，小时
        Long endTime = System.currentTimeMillis();
        Long startTime = endTime - 3600000;
        Date dates = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(dates);
        String date = times.replaceAll("[[\\s-:punct:]]","").substring(0,8);
        String hour = times.replaceAll("[[\\s-:punct:]]","").substring(8,10);
        String zero = "00";
        //零时转成24时，日期为昨天
        if(zero.equals(hour)){
            hour = "24";
            date = DateUtils.getPastDate(1).replaceAll("[[\\s-:punct:]]","");
        }
        //插零
        Camera camera = new Camera();
        camera.setState(0);
        List<Camera> select = cameraMapper.select(camera);
        List<SnapHour> snapHourList = new ArrayList<>();
        List<Integer> cameraIdList = new ArrayList();
        for (Camera cam : select) {
            SnapHour snapHour = new SnapHour();
            snapHour.setDeviceId(cam.getId());
            snapHour.setCount(CommonConstant.COMMON_0);
            snapHour.setHour(Integer.valueOf(hour));
            snapHour.setDate(Integer.valueOf(date));
            snapHour.setCreateTime(System.currentTimeMillis());
            snapHourList.add(snapHour);
            //保存设备id
            cameraIdList.add(cam.getId());
        }
        snapHourMapper.insertList(snapHourList);
        //查询数据
        for (Integer cameraId : cameraIdList) {
            SnapRecord snapRecord = new SnapRecord();
            snapRecord.setDeviceId(cameraId);
            snapRecord.setStartTime(startTime);
            snapRecord.setEndTime(endTime);
            snapRecord.setCaptureType(FaceOrBodyEnum.FACE.getCode());
            snapRecord.setDeviceType(ResourceType.CAMERA.getValue());
            Integer cnt = snapHourMapper.selSnapRecord(snapRecord);
            //修改数据
            Example example = new Example(SnapHour.class);
            example.createCriteria().andEqualTo("hour",hour)
                    .andEqualTo("date",date)
                    .andEqualTo("deviceId",cameraId);
            SnapHour snap = new SnapHour();
            snap.setCount(cnt);
            snapHourMapper.updateByExampleSelective(snap,example);
        }
    }
}
