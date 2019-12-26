package com.ss.facesys.data.engine.service;

import com.alibaba.fastjson.JSONObject;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.util.EntityUtil;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.engine.client.IDeviceEngineService;
import com.ss.facesys.data.engine.common.dto.DeviceEngineDTO;
import com.ss.facesys.data.engine.common.model.DeviceEngine;
import com.ss.facesys.data.engine.mapper.DeviceEngineMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResultCode;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import com.ss.tools.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 设备绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/16
 * @email yaoshuai@ss-cas.com
 */
@Service
public class DeviceEngineServiceImpl extends BaseServiceImpl implements IDeviceEngineService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceEngineServiceImpl.class);

    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private DeviceEngineMapper deviceEngineMapper;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private IAccessService accessService;


    /**
     * 查询设备绑定引擎列表
     *
     * @param engineDTO
     * @return
     */
    @Override
    public List<DeviceEngineDTO> engineList(DeviceEngineDTO engineDTO) {
        List<DeviceEngineDTO> resultList = new ArrayList<>();
        // 所属单位、像机名称：转换为设备id条件
        Example example = new Example(DeviceEngine.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isAllBlank(engineDTO.getOrgId(), engineDTO.getCameraName())) {
            Example deviceExp = new Example(Camera.class);
            deviceExp.createCriteria().andEqualTo("state", CommonConstant.DELETE_FLAG_EXIST);
            if (StringUtils.isNotBlank(engineDTO.getOrgId())) {
                deviceExp.and().andEqualTo("orgId", engineDTO.getOrgId());
            }
            if (StringUtils.isNotBlank(engineDTO.getCameraName())) {
                deviceExp.and().andLike("cameraName", like(engineDTO.getCameraName()));
            }
            List<Camera> deviceList = cameraMapper.selectByExample(deviceExp);
            List<Integer> deviceIds = deviceList.stream().map(Camera::getId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(deviceIds)) {
                deviceIds.add(-1);
            }
            criteria.andIn("deviceId", deviceIds);
        }
        List<DeviceEngine> deviceEngines = deviceEngineMapper.selectByExample(example);
        // 返回处理
        if (CollectionUtils.isNotEmpty(deviceEngines)) {
            Map<Integer, List<DeviceEngine>> resMap = deviceEngines.stream().collect(Collectors.groupingBy(DeviceEngine::getDeviceId));
            Example deviceSelExp = new Example(Camera.class);
            deviceSelExp.createCriteria().andEqualTo("state", CommonConstant.DELETE_FLAG_EXIST).andIn("id", resMap.keySet());
            List<Camera> deviceList = cameraMapper.selectByExample(deviceSelExp);
            // 单位信息
            Example orgExp = new Example(Organization.class);
            orgExp.createCriteria().andIn("orgId", deviceList.stream().map(Camera::getOrgId).collect(Collectors.toList()));
            List<Organization> organizations = organizationMapper.selectByExample(orgExp);
            Map<String, String> orgMap = organizations.stream().collect(Collectors.toMap(Organization::getOrgId, Organization::getOrgCname));
            for (Camera device : deviceList) {
                DeviceEngineDTO deviceEngineDTO = new DeviceEngineDTO();
                deviceEngineDTO.setDeviceId(device.getId());
                deviceEngineDTO.setCameraName(device.getCameraName());
                deviceEngineDTO.setOrgId(device.getOrgId());
                deviceEngineDTO.setOrgCname(orgMap.get(device.getOrgId()));
                deviceEngineDTO.setEngineTypeList(resMap.get(device.getId()).stream().map(DeviceEngine::getEngineType).collect(Collectors.toList()));
                resultList.add(deviceEngineDTO);
            }
        }
        return resultList;
    }

    /**
     * 设备绑定引擎关系
     *
     * @param deviceEngine
     * @return
     * @throws ServiceException
     */
    @Override
    public String bindEngineControl(DeviceEngine deviceEngine) throws ServiceException {
        // 查询有效的设备条件
        Example deviceExp = new Example(Camera.class);
        deviceExp.createCriteria().andEqualTo("state", CommonConstant.DELETE_FLAG_EXIST).andIn("id", deviceEngine.getDeviceIds());
        List<Camera> deviceList = cameraMapper.selectByExample(deviceExp);
        if (CollectionUtils.isEmpty(deviceList)) {
            throw new ServiceException(ResultCode.DEVICE_NOTEXIST);
        }
        // 获取操作目标数据集 <设备ID, 汇聚平台人像库ID>
        int engineType, bindStatus, count;
        Map<Integer, String> targetMap = catchBindTarget(deviceList, engineType = deviceEngine.getEngineType(), bindStatus = deviceEngine.getBindStatus());
        Set<Integer> targetIds = targetMap.keySet();
        if (CollectionUtils.isEmpty(targetIds)) {
            throw new ServiceException(ResultCode.DEVICE_ENGINE_BIND_NULLTARGET);
        }
        if (bindStatus == CommonConstant.ENGINE_BIND_STATUS) {
            // 批量绑定
            List<DeviceEngine> bindList = new ArrayList<>();
            targetIds.forEach(deviceId -> {
                DeviceEngine engine = new DeviceEngine();
                engine.setDeviceId(deviceId);
                engine.setEngineType(engineType);
                engine.setCreateTime(DateUtils.getCurrentTime());
                bindList.add(engine);
            });
            try {
                count = deviceEngineMapper.insertList(bindList);
            } catch (Exception e) {
                throw new ServiceException(ResultCode.DEVICE_ENGINE_BIND_FAIL);
            }
        } else {
            // 批量取消绑定
            Example cancelBindExp = new Example(DeviceEngine.class);
            cancelBindExp.createCriteria().andIn("deviceId", targetIds).andEqualTo("engineType", engineType);
            try {
                count = deviceEngineMapper.deleteByExample(cancelBindExp);
            } catch (Exception e) {
                throw new ServiceException(ResultCode.DEVICE_ENGINE_CANCEL_BIND_FAIL);
            }
        }
        // 汇聚平台操作
        vplatEngineControl(new ArrayList<>(targetMap.values()), engineType, bindStatus);
        return "设备与引擎绑定关系：成功将" + count + "条设备数据"
                + (bindStatus == CommonConstant.ENGINE_BIND_STATUS ? "绑定" : "解绑")
                + EntityUtil.getEnumName("ENGINE_TYPE", String.valueOf(engineType));
    }

    /**
     * 获取可做绑定/取消绑定的目标数据
     *
     * @param deviceList
     * @param engineType
     * @param bindStatus
     * @return
     */
    private Map<Integer, String> catchBindTarget(List<Camera> deviceList, Integer engineType, Integer bindStatus) {
        Map<Integer, String> targetMap = new HashMap<>(deviceList.size());
        if (CollectionUtils.isNotEmpty(deviceList)) {
            Map<Integer, String> validMap = deviceList.stream().collect(Collectors.toMap(Camera::getId, Camera::getCameraId));
            Set<Integer> ids = validMap.keySet();
            Example selectBindExp = new Example(DeviceEngine.class);
            selectBindExp.createCriteria().andIn("deviceId", ids).andEqualTo("engineType", engineType);
            List<DeviceEngine> bindList = deviceEngineMapper.selectByExample(selectBindExp);
            Set<Integer> bindIds = bindList.stream().map(DeviceEngine::getDeviceId).collect(Collectors.toSet());
            Set<Integer> notBindIds = ids.stream().filter(deviceId -> !bindIds.contains(deviceId)).collect(Collectors.toSet());
            Set<Integer> targetIds = bindStatus == CommonConstant.ENGINE_BIND_STATUS ? notBindIds : bindIds;
            targetIds.forEach(targetId -> {
                targetMap.put(targetId, validMap.get(targetId));
            });
        }
        return targetMap;
    }

    /**
     * 汇聚平台引擎绑定调用
     *
     * @param vplatDeviceIds
     * @param engineType
     * @param bindStatus
     * @throws ServiceException
     */
    private void vplatEngineControl(List<String> vplatDeviceIds, Integer engineType, Integer bindStatus) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("deviceIds", vplatDeviceIds);
            param.put("engineType", engineType);
            param.put("bindStatus", bindStatus);
            JSONObject oceanResult = accessService.deviceEngineControl(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.DEVICE_VPLAT_ENGINE_CONTROL_FAIL);
        }
    }

}
