package com.ss.isc.data.collect.service;

import com.ss.isc.data.collect.client.ICaptureService;
import com.ss.isc.data.collect.common.dto.CaptureSumDTO;
import com.ss.isc.data.collect.common.dto.RegisterStatisticsDTO;
import com.ss.isc.data.collect.common.model.Camera;
import com.ss.isc.data.collect.common.model.CaptureCount;
import com.ss.isc.data.collect.mapper.CaptureMapper;
import com.ss.isc.util.jedis.JedisUtil;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CaptureServiceImpl
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CaptureServiceImpl implements ICaptureService {

    public static final Log LOG = LogFactory.getLog(HouseServiceImpl.class);

    @Autowired
    private CaptureMapper captureMapper;
    @Resource
    private JedisUtil jedisUtil;


    @Override
    public boolean batchInsertCaptureCount(List<CaptureCount> list) {
        boolean isSuccess = false;
        int count = -1;
        if (list.size() > 0) {
            count = this.captureMapper.insertCaptureCounts(list);
        }
        if (count > -1) {
            isSuccess = true;
        }
        return isSuccess;
    }


    @Override
    public int todayCount() {
        return this.captureMapper.todayCount();
    }


    @Override
    public List<CaptureSumDTO> getCaptureSum() {
        return this.captureMapper.getCaptureSum();
    }


    @Override
    public List<CaptureSumDTO> findVillageCaptureTotal() {
        List<CaptureSumDTO> list = null;
        if (this.jedisUtil.hasKey("VILLAGES_CAPTURETOTAL")) {
            Object object = this.jedisUtil.get("VILLAGES_CAPTURETOTAL");
            if (object == null) {
                list = this.captureMapper.findVillageCaptureTotal();
                this.jedisUtil.set("VILLAGES_CAPTURETOTAL", list);
            } else {

                list = (List) object;
            }
        } else {

            list = this.captureMapper.findVillageCaptureTotal();
            this.jedisUtil.set("VILLAGES_CAPTURETOTAL", list);
        }
        return list;
    }


    @Override
    public List<RegisterStatisticsDTO> registerStatistics() {
        return this.captureMapper.registerStatistics();
    }


    @Override
    public List<Camera> getCamera(String villageCode) {
        return this.captureMapper.getCamera(villageCode);
    }


    @Override
    public List<Camera> getCamera1(String villageCode) {
        return this.captureMapper.getCamera1(villageCode);
    }

    /**
     * 查询指定全部小区下的设备列表
     * @param villages
     * @return
     */
    @Override
    public List<Camera> getAllCamera(String[] villages) {
        return this.captureMapper.getAllCamera(villages);
    }
    /**
     * 查询全部小区下的全部设备列表
     * @return
     */
    @Override
    public List<Camera> getAllCamera() {
        return this.captureMapper.getAllCameras();
    }

    @Override
    public List<Camera> getAllCamera1(String[] villages) {
        return this.captureMapper.getAllCamera1(villages);
    }


    @Override
    public List<CaptureSumDTO> getCaptureAllSum() {
        return this.captureMapper.getCaptureAllSum();
    }

}
