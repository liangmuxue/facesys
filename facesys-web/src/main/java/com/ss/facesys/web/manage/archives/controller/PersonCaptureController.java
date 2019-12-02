package com.ss.facesys.web.manage.archives.controller;

import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.archives.common.web.PersonCaptureVO;
import com.ss.facesys.data.baseinfo.common.dto.PersonCaptureDTO;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.http.BaseFormatJsonUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * PersonCaptureController 查询人脸抓拍库相关请求
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/archives/personcapture"})
public class PersonCaptureController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private IAccessService accessService;
    @Resource
    private ICameraService cameraService;

    /**
     * 一人一档：人脸抓拍[requestType=1]、开门记录[requestType=2]
     * @param personCaptureDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "通过人脸查询抓拍库", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> list(@RequestBody PersonCaptureVO personCaptureDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 获取配置的默认最低阈值
            float threShold = PropertiesUtil.getThreShold();
            List<PersonCaptureDTO> pList = new ArrayList<>();
            List<String> cameraIdList;

            if (StringUtils.isNoneBlank(personCaptureDTO.getImg())) {
                personCaptureDTO.setImgType(CommonConstant.IMGTYPE_FULLURL);
                personCaptureDTO.setCaptureTimeE(new Date());
                personCaptureDTO.setThresholdMin(threShold);
                Camera camera = new Camera();
                camera.setUserIds(personCaptureDTO.getUserIds());
                JSONObject jsobj = null;
                if (CommonConstant.COMMON_1.equals(personCaptureDTO.getRequestType())) {
                    // requestType=1 人脸抓拍
                    personCaptureDTO.setCaptureTimeB(DateUtils.addDays(new Date(), PropertiesUtil.getCaptureDays()));
                    Integer[] cameraType = {Enums.cameraType.USUAL.getCode(), Enums.cameraType.FACE.getCode(), Enums.cameraType.DOOR.getCode(), Enums.cameraType.CRED.getCode(), Enums.cameraType.ELEVATOR.getCode()};
                    camera.setCameraTypeList(Arrays.asList(cameraType));
                } else if (CommonConstant.COMMON_2.equals(personCaptureDTO.getRequestType())) {
                    // requestType=2 开门记录
                    personCaptureDTO.setCaptureTimeB(DateUtils.addDays(new Date(), PropertiesUtil.getPersonDoorDays()));
                    camera.setCameraType(Enums.cameraType.DOOR.getCode());
                }
                // 查询需要检索的像机集合
                cameraIdList = this.cameraService.cameraIdList(camera);
                if (CollectionUtils.isNotEmpty(cameraIdList)) {
                    String[] deviceIds = cameraIdList.toArray(new String[0]);
                    personCaptureDTO.setDeviceIds(deviceIds);
                    String paraJsonOpen = JsonUtils.getFastjsonFromObject(personCaptureDTO);
                    this.logger.info("终端库1：N人脸检索参数：" + paraJsonOpen);
                    //jsobj = this.accessService.getRecogTerminalDb(paraJsonOpen);
                    jsobj = this.accessService.getRecogCameraDb(paraJsonOpen);
                    this.logger.info("终端库1：N人脸检索返回值：" + jsobj);
                }
                // 封装检索结果
                if (jsobj != null && StringUtils.checkSuccess(jsobj)) {
                    pList = BaseFormatJsonUtil.formatList(jsobj.get("data"), PersonCaptureDTO.class);
                }
                if (CollectionUtils.isNotEmpty(pList)) {
                    // 添加设备信息
                    for (PersonCaptureDTO dto : pList) {
                        Camera entity = this.cameraService.findDevice(dto.getDeviceId());
                        if (entity != null) {
                            dto.setDeviceName(entity.getCameraName());
                            dto.setDeviceAddress((entity.getInstallAdd() == null) ? entity.getCameraName() : entity.getInstallAdd());
                        }
                    }
                    // 使用lambda表达式对结果进行排序：将最近抓拍的排到最前
                    pList.sort((o1, o2) -> {
                        if (o1.getCaptureTime() > o2.getCaptureTime()) {
                            return -1;
                        }
                        if (o1.getCaptureTime().equals(o2.getCaptureTime())) {
                            return 0;
                        }
                        return 1;
                    });
                }
            }
            Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            map.put("pList", pList);
            map.put("threShold", threShold);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_CAPTURE_FAILED_CODE);
            resp.setMessage("一人一档通过人脸查询抓拍库,查询失败!");
            this.logger.error("一人一档通过人脸查询抓拍库,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
