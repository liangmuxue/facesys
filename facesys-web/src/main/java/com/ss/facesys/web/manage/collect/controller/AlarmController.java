package com.ss.facesys.web.manage.collect.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FaceDbDTO;
import com.ss.facesys.data.collect.client.IAlarmService;
import com.ss.facesys.data.collect.client.ICaptureService;
import com.ss.facesys.data.collect.common.web.AlarmRecordVO;
import com.ss.facesys.data.collect.common.web.AlarmVO;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIKeyStateGroup;
import com.ss.valide.APIPageGroup;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AlarmController 人口预警请求
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/collect/alarm"})
public class AlarmController extends BaseController {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");

    private Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Resource
    private IAlarmService alarmService;
    @Resource
    private IAccessService accessService;
    @Resource
    private ICommunityResourceService communityResourceService;
    @Resource
    private ICaptureService caputerService;
    @Resource
    private IOrganizationRegionService oRegionService;

    /**
     * 查询未处理预警数据统计条数
     * @param alarm
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/untreatedCount"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "查询未处理预警数据统计条数", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Integer>> untreatedCount(@RequestBody @Validated({APIKeyStateGroup.class}) AlarmVO alarm, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Integer>> resp = validite(bindingResult);
        Map<String, Integer> result = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        try {
            alarm.setVillageCodes(getVillageCodes(alarm.getVillageCodes()));
            String userIds = alarm.getUserIds();
            // 查询用户权限下的全部像机
            List<String> allList = new ArrayList<>();
            Camera camera = new Camera();
            camera.setUserIds(userIds);
            Integer[] typeList = {Enums.cameraType.USUAL.getCode(), Enums.cameraType.FACE.getCode(), Enums.cameraType.DOOR.getCode(), Enums.cameraType.CRED.getCode(), Enums.cameraType.ELEVATOR.getCode()};
            camera.setCameraList(Arrays.asList(typeList));
            camera.setVillageCode(alarm.getVillageCodes());
            List<Camera> cList = this.communityResourceService.findCameras(camera);
            for (Camera camera1 : cList) {
                allList.add(camera1.getCameraId());
            }
            alarm.setCameraIdsString(StringUtils.join(allList.toArray(), ","));
            // 查询用户权限小区
            if (StringUtils.isBlank(alarm.getVillageCodes())) {
                String villageCodes = this.oRegionService.dataScopeFilter(userIds);
                alarm.setVillageCodes(villageCodes);
            }
            // 查询数据条数
            List<Integer> countList = alarmService.getUntreatedCount(alarm);
            result.put("people", countList.get(0));
            result.put("vehicleDiscovery", countList.get(1));
            result.put("vehicleLeave", countList.get(2));
            result.put("vehicleRetention", countList.get(3));
            result.put("vehicleAll", (countList.get(1) + countList.get(2) + countList.get(3)));
            resp.setData(result);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("查询未处理预警数据统计条数失败");
            this.logger.error("查询未处理预警数据统计条数失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询人口预警分页列表
     * @param alarm
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "布控告警列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<AlarmRecordVO>> list(@RequestBody @Validated({APIPageGroup.class}) AlarmVO alarm, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AlarmRecordVO>> resp = validite(bindingResult);
        try {
            // 布控类型默认黑名单报警
            alarm.setMonitorType(Enums.AlarmMonitorType.BLACK.getCode());
            alarm.setTopSeq(0);
            // 相似度范围条件
            if (StringUtils.isNotBlank(alarm.getMinSim())) {
                float f = Float.parseFloat(alarm.getMinSim());
                f /= 100.0F;
                alarm.setMinSim(String.valueOf(f));
            }
            if (StringUtils.isNotBlank(alarm.getMaxSim())) {
                float f = Float.parseFloat(alarm.getMaxSim());
                f /= 100.0F;
                alarm.setMaxSim(String.valueOf(f));
            }
            // 像机条件
            List<String> allList = new ArrayList<>();
            if (CollectionUtils.isEmpty(alarm.getCameraIds()) && StringUtils.isBlank(alarm.getVillageCodes())) {
                // 未选择时小区且未选择像机时查询用户权限下的全部像机
                Camera camera = new Camera();
                camera.setUserIds(alarm.getUserIds());
                camera.setVillageCode(alarm.getVillageCode());
                Integer[] typeList = {Enums.cameraType.USUAL.getCode(), Enums.cameraType.FACE.getCode(), Enums.cameraType.DOOR.getCode(), Enums.cameraType.CRED.getCode(), Enums.cameraType.ELEVATOR.getCode()};
                camera.setCameraList(Arrays.asList(typeList));
                List<Camera> cList = this.communityResourceService.findCameras(camera);
                for (Camera camera1 : cList) {
                    allList.add(camera1.getCameraId());
                }
            } else {
                // 汇总已选择小区下的像机和已选择像机的集合
                String[] villages;
                alarm.setVillageCodes(getVillageCodes(alarm.getVillageCodes()));
                if (StringUtils.isNotBlank(alarm.getVillageCodes()) && (villages = alarm.getVillageCodes().split(CommonConstant.SPLIT_COMMA)).length > 0) {
                    List<com.ss.facesys.data.collect.common.model.Camera> cList = this.caputerService.getAllCamera(villages);
                    if (CollectionUtils.isNotEmpty(cList)) {
                        for (com.ss.facesys.data.collect.common.model.Camera camera : cList) {
                            allList.add(camera.getCameraId());
                        }
                    }
                }
                if (alarm.getCameraIds() != null) {
                    allList.addAll(alarm.getCameraIds());
                }
            }
            alarm.setCameraIdsString(StringUtils.join(allList.toArray(), ","));
            // 底库条件：未选择底库时，查询全部底库条件
            if (CollectionUtils.isEmpty(alarm.getLibraryIds())) {
                JSONObject jsonobj = this.accessService.facedblist();
                if (!StringUtils.checkSuccess(jsonobj)) {
                    resp = createFailResponse();
                    resp.setCode(ResultCode.OCEAN_FACEDB_CODE);
                    resp.setMessage("查询欧神人像库失败");
                }
                this.logger.info("facedblist ----------------- 人脸底库列表 result-------------:" + jsonobj);
                List<FaceDbDTO> faceList = BaseFormatJsonUtil.formatList(jsonobj.get("data"), FaceDbDTO.class);
                List<String> addList = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(faceList)) {
                    for (FaceDbDTO faceDbDTO : faceList) {
                        if (faceDbDTO.getType() != null) {
                            addList.add(faceDbDTO.getId());
                        }
                    }
                }
                alarm.setLibraryIdsString(StringUtils.join(addList.toArray(), ","));
            } else {
                alarm.setLibraryIdsString(StringUtils.join(alarm.getLibraryIds().toArray(), ","));
            }
            // 查询预警分页列表
            Page<AlarmRecordVO> data = (Page) this.alarmService.findListByPage(alarm);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_ALARM_CODE);
            resp.setMessage("布控告警查询失败");
            this.logger.error("布控告警查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/facedblist"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "人脸底库列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<FaceDbDTO>> facedblist(HttpServletRequest request, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<List<FaceDbDTO>> resp = validite(bindingResult);


            JSONObject jsonobj = this.accessService.facedblist();
            if (!StringUtils.checkSuccess(jsonobj)) {
                resp = createFailResponse();
                resp.setCode("70804005");
                resp.setMessage("查询欧神人像库失败");
            }
            this.logger.info("facedblist ----------------- 人脸底库列表 result-------------:" + jsonobj);


            List<FaceDbDTO> faceList = BaseFormatJsonUtil.formatList(jsonobj.get("data"), FaceDbDTO.class);
            List<FaceDbDTO> addList = new ArrayList<FaceDbDTO>();
            for (FaceDbDTO faceDbDTO : faceList) {
                if (faceDbDTO.getType() != null) {
                    addList.add(faceDbDTO);
                }
            }

            resp.setData(addList);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
