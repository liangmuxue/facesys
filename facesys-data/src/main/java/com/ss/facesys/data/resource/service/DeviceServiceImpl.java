package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.collect.common.dto.AlarmDTO;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.resource.client.IDeviceService;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.mapper.DeviceMapper;
import com.ss.facesys.util.autoconfigure.DeviceProperties;
import com.ss.facesys.util.em.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时监控
 * @author 李爽超 chao
 * @create 2019/08/26
 * @email lishuangchao@ss-cas.com
 **/
@Service
@Transactional(rollbackFor = {Exception.class})
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 查询设备列表
     * @param region
     * @param villageCodes
     * @return
     */
    @Override
    public List<RegionTree> getTreeData(Region region, String[] villageCodes) {
        List<RegionTree> allList;
        List<RegionTree> regionList;
        List<RegionTree> villageList;
        List<RegionTree> deviceList;
        int temp = 1;
        //查询存在设备的小区
        String deviceVillageCodes = this.deviceMapper.findVillageCodes();
        //查询存在设备的区划
        String orgCodes =  this.deviceMapper.findOrgCodes(deviceVillageCodes);
        //查询存在设备的市
        String cityCodes = this.deviceMapper.findCityCodes(orgCodes);
        //查询市信息
        allList = this.deviceMapper.findCity(cityCodes);
        //查询行政区划树信息
        regionList = this.deviceMapper.findRegion(orgCodes);
        allList.addAll(regionList);
        //查询小区信息
        villageList = this.deviceMapper.findVillageRegion(deviceVillageCodes);
        // 将属于用户权限区划且包含小区的区划加入结果集合中
        if (villageCodes != null) {
            for (String villageCode : villageCodes) {
                for (RegionTree v : villageList) {
                    if (villageCode.equals(v.getRegionCode())) {
                        allList.add(v);
                    }
                }
            }
        } else {
            allList.addAll(villageList);
        }
        //查询设备信息
        deviceList = this.deviceMapper.findDeviceRegion();
        for (RegionTree regiontree: deviceList) {
            regiontree.setRemark(DeviceProperties.getSocketServerUri() + regiontree.getRegionId());
            regiontree.setRegionCode(regiontree.getParentId() + temp);
            temp++;
        }
        allList.addAll(deviceList);
        allList.sort((o1, o2) -> Integer.compare(o1.getRegionCode().compareTo(o2.getRegionCode()), 0));
        return buildRegionTree(allList);
    }

    /**
     * 查询视频监控信息
     *
     * @param cameras
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> cameraPreview(List<Camera> cameras) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Camera> allCamera = new ArrayList<>();
        String message = "";
        //查询视频监控信息
        for (Camera camera : cameras) {
            Camera cameraUrl = deviceMapper.findCameraUrl(camera.getCameraId());
            if (cameraUrl != null) {
                cameraUrl.setStreamSource(DeviceProperties.getSocketServerUri() + camera.getCameraId());
                cameraUrl.setCameraId(camera.getCameraId());
                cameraUrl.setCameraName(camera.getCameraName());
                allCamera.add(cameraUrl);
            } else {
                message += camera.getCameraName() + " ";
            }
        }
        if ("" != message) {
            message += "设备不存在！";
        }
        if (allCamera != null) {
        }
        map.put("cameras", allCamera);
        map.put("message", message);
        return map;
    }

    /**
     * 查询布控预警信息
     *
     * @param dto
     * @return
     */
    @Override
    public List<AlarmRecordVO> findAlarm(AlarmDTO dto) {
        return this.deviceMapper.findAlarm(dto);
    }

    /**
     * 创建区划树数据
     *
     * @param allList
     * @return
     */
    private List<RegionTree> buildRegionTree(List<RegionTree> allList) {
        List<RegionTree> treeData = new ArrayList<>();
        RegionTree currentCity = new RegionTree();
        RegionTree currentArea = new RegionTree();
        RegionTree currentStreet = new RegionTree();
        RegionTree currentVillage = new RegionTree();
        RegionTree currentCamera;

        for (RegionTree regionTree : allList) {
            // 市级别
            if (regionTree.getRegionType() == Enums.regionType.CITY.getCode()) {
                currentCity = regionTree;
                if (currentCity.getChildren() == null) {
                    currentCity.setChildren(new ArrayList<RegionTree>());
                }
                treeData.add(currentCity);
                continue;
            }
            // 区级别
            if (regionTree.getRegionType() == Enums.regionType.DISTRICT.getCode()) {
                currentArea = regionTree;
                if (currentArea.getChildren() == null) {
                    currentArea.setChildren(new ArrayList<RegionTree>());
                }
                currentCity.getChildren().add(currentArea);
                continue;
            }
            // 街道级别
            if (regionTree.getRegionType() == Enums.regionType.STREET.getCode()) {
                currentStreet = regionTree;
                if (currentStreet.getChildren() == null) {
                    currentStreet.setChildren(new ArrayList<RegionTree>());
                }
                currentArea.getChildren().add(currentStreet);
                continue;
            }
            // 小区级别
            if (regionTree.getRegionType() == Enums.regionType.VILLAGE.getCode()) {
                currentVillage = regionTree;
                if (currentVillage.getParentId().equals(currentArea.getRegionId())) {
                    currentArea.getChildren().add(currentVillage);
                } else if (currentVillage.getParentId().equals(currentStreet.getRegionId())) {
                    currentStreet.getChildren().add(currentVillage);
                }
                continue;
            }
            // 设备级别
            if (regionTree.getRegionType() == Enums.regionType.CAMERA.getCode()) {
                currentCamera = regionTree;
                if (currentVillage.getChildren() == null){
                    currentVillage.setChildren(new ArrayList<RegionTree>());
                }
                currentVillage.getChildren().add(currentCamera);
            }
        }
        return treeData;
    }
}
