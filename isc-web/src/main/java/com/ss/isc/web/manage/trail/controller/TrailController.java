package com.ss.isc.web.manage.trail.controller;

import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.resource.client.ICameraService;
import com.ss.isc.data.resource.client.ICommunityResourceService;
import com.ss.isc.data.resource.common.model.Camera;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.trail.client.ITrailService;
import com.ss.isc.data.trail.common.dto.FaceTrackDTO;
import com.ss.isc.data.trail.common.dto.VehicleRecordDTO;
import com.ss.isc.data.trail.common.dto.WifiCollectDataDTO;
import com.ss.isc.data.trail.common.web.FaceTrackVO;
import com.ss.isc.data.trail.common.web.VehicleRecordVO;
import com.ss.isc.data.trail.common.web.WifiCollectDataVO;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/trail"})
public class TrailController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TrailController.class);

    //private static final Integer[] CAMERATYPE = {null, null, null, null, (new Integer[5][3] = (new Integer[5][2] = (new Integer[5][1] = (new Integer[5][0] = Integer.valueOf(1)).valueOf(2)).valueOf(5)).valueOf(6)).valueOf(7)};

    // 相机类型数组
    private static final Integer[] CAMERATYPE = {Enums.cameraType.USUAL.getCode(), Enums.cameraType.FACE.getCode(), Enums.cameraType.DOOR.getCode(), Enums.cameraType.CRED.getCode(), Enums.cameraType.ELEVATOR.getCode()};

    @Resource
    private ITrailService trailService;


    @Resource
    private IAccessService accessService;


    @Resource
    private ICommunityResourceService communityResourceService;


    @Resource
    private ICameraService cameraService;


    @Resource
    private IOrganizationRegionService oRegionService;


    @RequestMapping(value = {"/vehicleTrack"}, method = {RequestMethod.POST})
    public ResponseEntity<List<VehicleRecordDTO>> vehicleTrack(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) VehicleRecordVO vehicleRecordVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<VehicleRecordDTO>> resp = validite(bindingResult);
        try {
            if (StringUtils.isBlank(vehicleRecordVO.getVillageCode())) {
                vehicleRecordVO.setVillageCode(this.oRegionService.dataScopeFilter(vehicleRecordVO.getUserIds()));
            }
            List<VehicleRecordDTO> vehicleTrack = this.trailService.vehicleTrack(vehicleRecordVO);
            resp.setData(vehicleTrack);
        } catch (Exception e) {
            this.logger.error("查询车辆轨迹失败，原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70803001");
            resp.setMessage("查询车辆轨迹失败");
        }
        return resp;
    }


    @RequestMapping(value = {"/macTrack"}, method = {RequestMethod.POST})
    public ResponseEntity<List<WifiCollectDataDTO>> macTrack(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) WifiCollectDataVO wifiCollectDataVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<WifiCollectDataDTO>> resp = validite(bindingResult);
        try {
            if (StringUtils.isBlank(wifiCollectDataVO.getVillageCode())) {
                wifiCollectDataVO.setVillageCode(this.oRegionService.dataScopeFilter(wifiCollectDataVO.getUserIds()));
            }
            List<WifiCollectDataDTO> macTrack = this.trailService.macTrajectory(wifiCollectDataVO);
            resp.setData(macTrack);
        } catch (Exception e) {
            this.logger.error("查询MAC轨迹失败，原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70803002");
            resp.setMessage("查询MAC轨迹失败");
        }
        return resp;
    }


    @RequestMapping(value = {"/faceTrack"}, method = {RequestMethod.POST})
    public ResponseEntity<List<FaceTrackDTO>> faceTrack(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) FaceTrackVO faceTrackVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<FaceTrackDTO>> resp = validite(bindingResult);
        try {
            List<FaceTrackDTO> faceTracks = new ArrayList<FaceTrackDTO>();
            Camera camera = new Camera();
            if (StringUtils.isNotBlank(faceTrackVO.getVillageCode())) {
                camera.setVillageCode(faceTrackVO.getVillageCode());
            } else {

                camera.setVillageCode(this.oRegionService.dataScopeFilter(faceTrackVO.getUserIds()));
            }

            if (CommonConstant.IMGTYPE_BASE64.equals(faceTrackVO.getImgType())) {
                String img = faceTrackVO.getImg();
                img = img.substring(img.indexOf(",") + NumberConstant.ONE.intValue(), img.length());
                faceTrackVO.setImg(img);
            }
            faceTrackVO.setThresholdMin(Float.valueOf(faceTrackVO.getThreshold().floatValue() / NumberConstant.ONE_HUNDRED.intValue()));


            if (PropertiesUtil.getCaptureOcean().intValue() == 1) {
                camera.setCameraType(NumberConstant.ONE);
                List<String> captureDeviceIds = this.cameraService.findCameraIds(camera);
                if (captureDeviceIds.size() > CommonConstant.COMMON_0.intValue()) {
                    faceTrackVO.setDeviceIds((String[]) captureDeviceIds.toArray(new String[captureDeviceIds.size()]));
                    String capaturePara = JsonUtils.getFastjsonFromObject(faceTrackVO);
                    this.logger.info("人脸轨迹抓拍库检索请求参数" + capaturePara);
                    JSONObject capturejsonObject = this.accessService.getRecogTrack(capaturePara);
                    this.logger.info("人脸轨迹抓拍库检索返回结果" + JsonUtils.getFastjsonFromObject(capturejsonObject));
                    if (StringUtils.checkSuccess(capturejsonObject)) {
                        JSONArray data = (JSONArray) capturejsonObject.get("data");
                        faceTracks = JSONArray.parseArray(data.toString(), FaceTrackDTO.class);
                    }
                }
            }
            camera.setCameraList(Arrays.asList(CAMERATYPE));
            camera.setCameraType(null);
            List<String> terminaDeviceIds = this.cameraService.findCameraIds(camera);

            if (terminaDeviceIds.size() > 0) {
                faceTrackVO.setDeviceIds((String[]) terminaDeviceIds.toArray(new String[terminaDeviceIds.size()]));
                String terminalPara = JsonUtils.getFastjsonFromObject(faceTrackVO);
                this.logger.info("人脸轨迹终端库检索请求参数" + terminalPara);
                JSONObject terminalJson = this.accessService.getRecogTerminalDb(terminalPara);
                this.logger.info("人脸轨迹终端库检索返回结果" + terminalJson);
                if (StringUtils.checkSuccess(terminalJson)) {
                    JSONArray data = (JSONArray) terminalJson.get("data");
                    if (data.size() > 0) {
                        List<FaceTrackDTO> terminals = JSONArray.parseArray(data.toString(), FaceTrackDTO.class);
                        for (FaceTrackDTO termin : terminals) {
                            Camera device = this.cameraService.findDevice(termin.getDeviceId());
                            if (device != null) {
                                termin.setLat(String.valueOf(device.getLat()));
                                termin.setLng(String.valueOf(device.getLon()));
                                termin.setDeviceName(device.getCameraName());
                                termin.setDeviceAddress((device.getInstallAdd() == null) ? device.getCameraName() : device
                                        .getInstallAdd());
                            }
                        }
                        faceTracks.addAll(terminals);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(faceTracks)) {
                Collections.sort(faceTracks, new Comparator<FaceTrackDTO>() {
                    public int compare(FaceTrackDTO o1, FaceTrackDTO o2) {
                        if (o1.getCaptureTime().longValue() > o2.getCaptureTime().longValue()) {
                            return -1;
                        }
                        if (o1.getCaptureTime().equals(o2.getCaptureTime())) {
                            return 0;
                        }
                        return 1;
                    }
                });
            }
            resp.setData(faceTracks);
        } catch (Exception e) {
            this.logger.error("查询人脸轨迹失败，原因：" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70803003");
            resp.setMessage("查询人脸轨迹失败");
        }
        return resp;
    }

}
