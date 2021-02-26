package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.util.EntityUtil;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IDevicePersoncardService;
import com.ss.facesys.data.collect.common.model.DevicePersoncard;
import com.ss.facesys.data.collect.common.model.Hotel;
import com.ss.facesys.data.collect.common.model.InternetBar;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.HotelMapper;
import com.ss.facesys.data.collect.mapper.InternetBarMapper;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.PersoncardMainEnum;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.em.ResultCode;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.UserResource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DevicePersoncardServiceImpl
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class DevicePersoncardServiceImpl extends BaseServiceImpl implements IDevicePersoncardService {

    @Resource
    private DevicePersoncardMapper devicePersoncardMapper;
    @Resource
    private HotelMapper hotelMapper;
    @Resource
    private InternetBarMapper internetBarMapper;
    @Resource
    private IAccessService accessService;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private UserResourceMapper userResourceMapper;


    /**
     * 查询人证设备分页列表
     *
     * @param devicePersoncard
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<DevicePersoncard> getPersonCardPage(DevicePersoncard devicePersoncard, int currentPage, int pageSize) {
        Example example = new Example(DevicePersoncard.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (StringUtils.isNotBlank(devicePersoncard.getDeviceName())) {
            example.and().andLike("deviceName", like(devicePersoncard.getDeviceName()));
        }
        if (StringUtils.isNotBlank(devicePersoncard.getDeviceCode())) {
            example.and().andLike("deviceCode", like(devicePersoncard.getDeviceCode()));
        }
        if (StringUtils.isNotBlank(devicePersoncard.getOrgId())) {
            example.and().andIn("orgId", getAllOrgNodes(devicePersoncard.getOrgId()));
        }
        if (CollectionUtils.isNotEmpty(devicePersoncard.getIds())) {
            example.and().andIn("id", devicePersoncard.getIds());
        }
        // 初始化分页查询条件
        PageHelper.startPage(currentPage, pageSize);
        List<DevicePersoncard> personcards = devicePersoncardMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(personcards)) {
            Map<String, Organization> orgMap = getOrgMapByIds(personcards.stream().map(DevicePersoncard::getOrgId).collect(Collectors.toList()));
            Map<Integer, Hotel> hotelMap = getHotelMapByIds(personcards.stream()
                    .filter(personcard -> PersoncardMainEnum.HOTEL.getKey().equals(personcard.getRelAddressType()))
                    .map(DevicePersoncard::getRelAddressId).collect(Collectors.toList()));
            Map<Integer, InternetBar> internetBarMap = getInternetBarMapByIds(personcards.stream()
                    .filter(personcard -> PersoncardMainEnum.INTERNET_BAR.getKey().equals(personcard.getRelAddressType()))
                    .map(DevicePersoncard::getRelAddressId).collect(Collectors.toList()));
            for (DevicePersoncard personcard : personcards) {
                Object relAddressObj = PersoncardMainEnum.HOTEL.getKey().equals(personcard.getRelAddressType()) ? hotelMap.get(personcard.getRelAddressId()) : internetBarMap.get(personcard.getRelAddressId());
                JSONObject relAddress = JSONObject.parseObject(JSON.toJSONString(relAddressObj));
                // 酒店网吧信息
                personcard.setRelAddressTypeName(PersoncardMainEnum.get(personcard.getRelAddressType()));
                personcard.setRelAddressName(relAddress == null ? null : relAddress.getString("name"));
                // 单位名称
                personcard.setOrgCname(orgMap.get(personcard.getOrgId()).getOrgCname());
                // 字典值处理： onlineState、monitorState
                EntityUtil.dealDic(personcard, "onlineState-CAMERA_STATE-onlineStateName", "monitorState-FACEDB_MONITOR_STATE-monitorStateName");
            }
        }
        return personcards;
    }

    private Map<String, Organization> getOrgMapByIds(List<String> orgIds) {
        Map<String, Organization> orgMap = new HashMap<>();
        if (CollectionUtils.isEmpty(orgIds)) {
            return orgMap;
        }
        Example example = new Example(Organization.class);
        example.createCriteria().andIn("orgId", orgIds);
        List<Organization> orgList = organizationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(orgList)) {
            orgMap = orgList.stream().collect(Collectors.toMap(Organization::getOrgId, Function.identity()));
        }
        return orgMap;
    }

    private Map<Integer, Hotel> getHotelMapByIds(List<Integer> ids) {
        Map<Integer, Hotel> hotelMap = new HashMap<>();
        if (CollectionUtils.isEmpty(ids)) {
            return hotelMap;
        }
        Example example = new Example(Hotel.class);
        example.createCriteria().andIn("id", ids);
        List<Hotel> hotels = hotelMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(hotels)) {
            hotelMap = hotels.stream().collect(Collectors.toMap(Hotel::getId, Function.identity()));
        }
        return hotelMap;
    }

    private Map<Integer, InternetBar> getInternetBarMapByIds(List<Integer> ids) {
        Map<Integer, InternetBar> internetBarMap = new HashMap<>();
        if (CollectionUtils.isEmpty(ids)) {
            return internetBarMap;
        }
        Example example = new Example(InternetBar.class);
        example.createCriteria().andIn("id", ids);
        List<InternetBar> bars = internetBarMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(bars)) {
            internetBarMap = bars.stream().collect(Collectors.toMap(InternetBar::getId, Function.identity()));
        }
        return internetBarMap;
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param devicePersoncard
     * @return
     */
    @Override
    public DevicePersoncard selectOne(DevicePersoncard devicePersoncard) {
        devicePersoncard = devicePersoncardMapper.selectOne(devicePersoncard);
        if (devicePersoncard != null) {
            Map<String, Organization> orgMap = getOrgMapByIds(Collections.singletonList(devicePersoncard.getOrgId()));
            Object relAddressObj = PersoncardMainEnum.HOTEL.getKey().equals(devicePersoncard.getRelAddressType()) ?
                    getHotelMapByIds(Collections.singletonList(devicePersoncard.getRelAddressId())).get(devicePersoncard.getRelAddressId()) :
                    getInternetBarMapByIds(Collections.singletonList(devicePersoncard.getRelAddressId())).get(devicePersoncard.getRelAddressId());
            JSONObject relAddress = JSONObject.parseObject(JSON.toJSONString(relAddressObj));
            // 酒店网吧信息
            devicePersoncard.setRelAddressTypeName(PersoncardMainEnum.get(devicePersoncard.getRelAddressType()));
            devicePersoncard.setRelAddressName(relAddress == null ? null : relAddress.getString("name"));
            // 单位名称
            devicePersoncard.setOrgCname(orgMap.get(devicePersoncard.getOrgId()).getOrgCname());
            // 字典值处理： onlineState、monitorState
            EntityUtil.dealDic(devicePersoncard, "onlineState-CAMERA_STATE-onlineStateName", "monitorState-FACEDB_MONITOR_STATE-monitorStateName");
        }
        return devicePersoncard;
    }

    private void duplicateCheck(DevicePersoncard devicePersoncard) throws ServiceException {
        if (StringUtils.isNotBlank(devicePersoncard.getIp())) {
            Example example = new Example(DevicePersoncard.class);
            example.createCriteria()
                    .andEqualTo("status", StatusEnum.EFFECT.getCode())
                    .andEqualTo("ip", devicePersoncard.getIp());
            if (devicePersoncard.getId() != null) {
                example.and().andNotEqualTo("id", devicePersoncard.getId());
            }
            if (CollectionUtils.isNotEmpty(devicePersoncardMapper.selectByExample(example))) {
                throw new ServiceException(ResultCode.PERSONCARD_IP_EXIST);
            }
        }
    }

    /**
     * 修改人证设备
     *
     * @param devicePersoncard
     * @return
     */
    @Override
    public void updatePersonCard(DevicePersoncard devicePersoncard) throws ServiceException {
        duplicateCheck(devicePersoncard);
        // 更新人像系统数据
        try {
            devicePersoncardMapper.updateByPrimaryKeySelective(devicePersoncard);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.PERSONCARD_FACESYS_UPDATE_FAIL);
        }
        // 更新汇聚平台数据
//        updateVplatPersonCard(devicePersoncard);
    }

    /**
     * 删除人证设备
     *
     * @param devicePersoncard
     * @return
     */
    @Override
    public void deletePersonCard(DevicePersoncard devicePersoncard) throws ServiceException {
        // 校验底库是否布控
        DevicePersoncard dbCheck = devicePersoncardMapper.selectByPrimaryKey(devicePersoncard);
        if (dbCheck.getMonitorState() == CommonConstant.FACEDB_MONITOR_STATE_MONITORED) {
            throw new ServiceException(ResultCode.PERSONCARD_DELETEFAIL_MONITOR);
        }
        try {
            devicePersoncardMapper.updateByPrimaryKeySelective(devicePersoncard);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.PERSONCARD_FACESYS_DELETE_FAIL);
        }
        // 删除赋权数据
        UserResource userResource = new UserResource();
        userResource.setResourceId(devicePersoncard.getId());
        userResource.setType(ResourceType.PERSONCARD.getValue());
        userResourceMapper.delete(userResource);
        // 删除汇聚平台数据
//        deleteVplatPersonCard(dbCheck.getDeviceId());
    }

    private void updateVplatPersonCard(DevicePersoncard devicePersoncard) throws ServiceException {
        try {
            Object relAddressObj = PersoncardMainEnum.HOTEL.getKey().equals(devicePersoncard.getRelAddressType()) ?
                    getHotelMapByIds(Collections.singletonList(devicePersoncard.getRelAddressId())).get(devicePersoncard.getRelAddressId()) :
                    getInternetBarMapByIds(Collections.singletonList(devicePersoncard.getRelAddressId())).get(devicePersoncard.getRelAddressId());
            JSONObject relAddress = JSONObject.parseObject(JSON.toJSONString(relAddressObj));
            JSONObject param = new JSONObject();
            param.put("deviceId", devicePersoncard.getDeviceId());
            param.put("lng", devicePersoncard.getLon());
            param.put("lat", devicePersoncard.getLat());
            param.put("address", relAddress == null ? null : relAddress.getString("name"));
            JSONObject oceanResult = accessService.updatePersoncard(JSONObject.toJSONString(param));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            this.logger.error("修改汇聚平台人证设备失败，错误码: {},错误描述：{}", e.getCode(), e.getMessage(), e);
//            throw e;
        } catch (Exception e) {
            this.logger.error(ResultCode.PERSONCARD_VPLAT_FAIL.getDesc(), e);
//            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    private void deleteVplatPersonCard(String deviceId) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("deviceIds", Collections.singletonList(deviceId));
            JSONObject oceanResult = accessService.deletePersoncard(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            this.logger.error("删除汇聚平台人证设备失败，错误码: {},错误描述：{}", e.getCode(), e.getMessage(), e);
//            throw e;
        } catch (Exception e) {
            this.logger.error(ResultCode.PERSONCARD_VPLAT_FAIL.getDesc(), e);
//            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }


    @Override
    public List<Organization> treeData(CameraQueryVO queryVO) {
        Example example = new Example(Organization.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        example.orderBy("seq").asc();
        List<Organization> organizations = organizationMapper.selectByExample(example);
        Map<String, Organization> dataMap = new HashMap<>(16);
        for (Organization organization : organizations) {
            dataMap.put(organization.getOrgId(), organization);
        }
        List<Organization> cameras = this.devicePersoncardMapper.findTreeCameras(queryVO);
        List<Organization> temp = new ArrayList<>();
        for (Organization o: cameras) {
            Organization organization = dataMap.get(o.getParentId());
            while (true) {
                if (temp.contains(organization)) {
                    break;
                } else {
                    temp.add(organization);
                    if (StringUtils.isEmpty(organization.getParentId()) || "0".equals(organization.getParentId())) {
                        break;
                    } else {
                        organization = dataMap.get(organization.getParentId());
                    }
                }
            }
        }
        temp.addAll(cameras);
        return createOrgTree(temp);
    }

    private List<Organization> createOrgTree(List<Organization> organizationList) {
        if (CollectionUtils.isEmpty(organizationList)) {
            return Collections.emptyList();
        }
        // 创建根节点
        Organization root = new Organization();
        // 组装Map数据
        Map<String, Organization> dataMap = new HashMap<>(16);
        for (Organization organization : organizationList) {
            dataMap.put(organization.getOrgId(), organization);
        }
        // 组装树形结构
        Set<Map.Entry<String, Organization>> entrySet = dataMap.entrySet();
        for (Map.Entry<String, Organization> entry : entrySet) {
            Organization currentNode = entry.getValue();
            if (StringUtils.isEmpty(currentNode.getParentId()) || "0".equals(currentNode.getParentId())) {
                root.getChildren().add(currentNode);
            } else {
                dataMap.get(currentNode.getParentId()).getChildren().add(currentNode);
            }
        }
        return root.getChildren();
    }
}