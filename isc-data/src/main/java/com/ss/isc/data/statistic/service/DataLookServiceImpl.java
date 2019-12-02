package com.ss.isc.data.statistic.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.resource.mapper.CameraMapper;
import com.ss.isc.data.statistic.common.dto.CameraFaceCaptureDTO;
import com.ss.isc.data.statistic.common.dto.CaptureQueryVO;
import com.ss.isc.data.statistic.mapper.StHomeDataMapper;
import com.ss.isc.data.collect.client.ICaptureService;
import com.ss.isc.data.statistic.client.IdataLookService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * DataLookServiceImpl
 *
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class DataLookServiceImpl implements IdataLookService {

    @Autowired
    private StHomeDataMapper stHomeDataMapper;

    @Resource
    private IAccessService accessService;

    @Autowired
    private CameraMapper cameraMapper;


    @Override
    public void selThisThirtyDayPeopel(int dayNum) {
        //获取日的数据
        String previousData = String.valueOf(DateUtils.getPastDate(dayNum+1));
        String currenctDate = String.valueOf(DateUtils.getPastDate(dayNum));
        this.stHomeDataMapper.selDayPeopel(currenctDate,previousData);
    }

    @Override
    public void selThisWeekPeopel(int weekNum) {
        //获取周数据
        this.stHomeDataMapper.selWeekPeopel(weekNum);
    }

    @Override
    public void selthisMouthPeopel(int mouthNum) {
        //获取月的数据
        String currenctDate = String.valueOf(DateUtils.getMouthDay(mouthNum).get("strEndDate"));
        String previousData = String.valueOf(DateUtils.getMouthDay(mouthNum+1).get("strEndDate"));
        this.stHomeDataMapper.selMouthPeopel(currenctDate,previousData);
    }
    /////////////////////////////实有房屋///////////////////////////////////////
    @Override
    public void selThisThirtyDayHouse(int dayNum) {
        //获取日的数据
        String previousData = String.valueOf(DateUtils.getPastDate(dayNum+1));
        String currenctDate = String.valueOf(DateUtils.getPastDate(dayNum));
        this.stHomeDataMapper.selDayHouse(currenctDate,previousData);
    }

    @Override
    public void selThisWeekHouse(int weekNum) {
        //获取周数据
        this.stHomeDataMapper.selWeekHouse(weekNum);
    }

    @Override
    public void selthisMouthHouse(int mouthNum) {
        //获取月的数据
        String currenctDate = String.valueOf(DateUtils.getMouthDay(mouthNum).get("strEndDate"));
        String previousData = String.valueOf(DateUtils.getMouthDay(mouthNum+1).get("strEndDate"));
        this.stHomeDataMapper.selMouthHouse(currenctDate,previousData);
    }
    /////////////////////////////实有单位///////////////////////////////////////
    @Override
    public void selThisThirtyDayCompany(int dayNum) {
        //获取日的数据
        String previousData = String.valueOf(DateUtils.getPastDate(dayNum+1));
        String currenctDate = String.valueOf(DateUtils.getPastDate(dayNum));
        this.stHomeDataMapper.selDayCompany(currenctDate,previousData);
    }

    @Override
    public void selThisWeekCompany(int weekNum) {
        //获取周数据
        this.stHomeDataMapper.selWeekCompany(weekNum);
    }

    @Override
    public void selthisMouthCompany(int mouthNum) {
        //获取月的数据
        String currenctDate = String.valueOf(DateUtils.getMouthDay(mouthNum).get("strEndDate"));
        String previousData = String.valueOf(DateUtils.getMouthDay(mouthNum+1).get("strEndDate"));
        this.stHomeDataMapper.selMouthCompany(currenctDate,previousData);
    }

    /////////////////////////////感知增量///////////////////////////////////////
    @Override
    public void selThisThirtyDayPercept(int dayNum) {
        //获取日的数据
        String currenctDate = String.valueOf(DateUtils.getPastDate(dayNum));
        this.stHomeDataMapper.selDayPercept(currenctDate);
    }

    @Override
    public void selThisWeekPercept(int weekNum) {
        //获取周数据
        this.stHomeDataMapper.selWeekPercept(weekNum);
    }

    @Override
    public void selthisMouthPercept(int mouthNum) {
        //获取月的数据
        String currenctDate = String.valueOf(DateUtils.getMouthDay(mouthNum).get("strEndDate"));
        this.stHomeDataMapper.selMouthPercept(currenctDate);
    }
    //人脸抓拍
    @Override
    public void selThisThirtyDayFace(int dayNum) {
        //获取日的数据
        CaptureQueryVO captureQueryVO=new CaptureQueryVO();
        captureQueryVO.setCaptureTimeStart(DateUtil.getBeginDayOfYesterday(130));
        captureQueryVO.setCaptureTimeEnd(DateUtil.getEndDayOfYesterDay(0));
        // 设备编号数组
        String[] deviceIds = null;
        // 未选择设备时查询小区下的全部设备
        //List<Camera> cameras = this.caputerService.getAllCamera();
        List<CameraFaceCaptureDTO> list = this.cameraMapper.findFaceCapturedeviceIds();
        for (CameraFaceCaptureDTO cameraFaceCaptureDTO : list) {
            int captureCount = NumberConstant.ZERO.intValue();//
            if (StringUtils.isNotBlank(cameraFaceCaptureDTO.getDeviceIds())) {
                captureQueryVO.setDeviceIds(cameraFaceCaptureDTO.getDeviceIds().split(","));
                JSONObject jsonObject = this.accessService.captureStatistics(JsonUtils.getFastjsonFromObject(captureQueryVO));
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                System.out.println("人脸统计"+jsonArray);
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject job = jsonArray.getJSONObject(i);
                        captureCount += job.getInteger("captureCount").intValue();
                    }
                }
                System.out.println("captureCount"+captureCount);
            }
            //this.stHomeDataMapper.selDayPercept(cameraFaceCaptureDTO);
        }


    }

}
