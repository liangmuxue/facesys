package com.ss.isc.sync.data.service.impl;

import com.ss.isc.data.collect.client.IPeopleService;
import com.ss.isc.data.collect.common.web.FacedbfaceVO;
import com.ss.isc.data.resource.client.IBuildingService;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IVillageService;
import com.ss.isc.data.resource.common.model.Building;
import com.ss.isc.data.resource.common.model.Region;
import com.ss.isc.data.resource.common.model.ThirdRegion;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.data.resource.mapper.ThirdRegionMapper;
import com.ss.isc.sync.data.ftp.FtpUtil;
import com.ss.isc.sync.data.http.SyncPropertiesUtil;
import com.ss.isc.sync.data.mapper.SystemMapper;
import com.ss.isc.sync.data.model.Camera;
import com.ss.isc.sync.data.model.House;
import com.ss.isc.sync.data.model.ResultCamera;
import com.ss.isc.sync.data.model.ResultDoorFlow;
import com.ss.isc.sync.data.service.ISyncService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.HttpConstant;
import com.ss.isc.util.coordinate.GetCenterCoordinates;
import com.ss.isc.util.coordinate.IscCoordinate;
import com.ss.isc.util.em.CredentialTypeEnum;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.http.BaseFormatJsonUtil;
import com.ss.isc.util.http.BaseHttpUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.isc.util.thread.SysThreadPool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = {Exception.class})
public class SyncServiceImpl implements ISyncService {

    private static final Log LOG = LogFactory.getLog(SyncServiceImpl.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SystemMapper systemMapper;
    @Autowired
    private IPeopleService peopleService;
    @Autowired
    private IVillageService villageService;
    @Autowired
    private ThirdRegionMapper thirdRegionMapper;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private IBuildingService buildingService;
    @Resource
    public JedisUtil jedisUtil;
    public List<String> tempList = new ArrayList();

    public int synchroThirdRegion(List<String> files, String intranetFilePath) throws IOException {
        List<ThirdRegion> list = new ArrayList<ThirdRegion>();
        ThirdRegion resObj = null;
        int count = 0;
        if (!StringUtils.isEmpty(files)) {
            for (String fn : files) {

                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                if (!StringUtils.isEmpty(objList)) {
                    for (String obj : objList) {
                        if (!StringUtils.isEmpty(obj)) {
                            JSONObject json = JSONObject.parseObject(obj);
                            resObj = new ThirdRegion();
                            resObj.setId(json.getString("id"));
                            resObj.setRegionCode(json.getString("regionCode"));
                            resObj.setRegionName(json.getString("regionName"));
                            resObj.setState(json.getInteger("state").intValue());
                            resObj.setRemark(json.getString("remark"));
                            resObj.setParentId(json.getString("parentId"));
                            resObj.setParentCodeStr(json.getString("parentCodeStr"));
                            resObj.setCoordinates(json.getString("coordinates"));
                            resObj.setSystemNo(json.getString("systemNo"));
                            resObj.setRegionType(Enums.regionType.DISTRICT.getCode());

                            list.add(resObj);
                        }
                    }
                }
            }

            if (null != list && !list.isEmpty()) {
                this.thirdRegionMapper.batchCompareThirdRegion(list);
            }

            FtpUtil.downFiles(files, PropertiesUtil.getFtpFile(), intranetFilePath);
            count = files.size();
        }
        return count;
    }


    public int synchroStreet(List<String> files, String intranetFilePath) throws IOException {
        int count = 0;
        List<ThirdRegion> list = new ArrayList<ThirdRegion>();
        ThirdRegion resObj = null;
        if (!StringUtils.isEmpty(files)) {
            for (String fn : files) {
                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                for (String obj : objList) {
                    JSONObject json = JSONObject.parseObject(obj);
                    resObj = new ThirdRegion();
                    resObj.setId(json.getString("id"));
                    resObj.setRegionCode(json.getString("streetCode"));
                    resObj.setRegionName(json.getString("streetName"));
                    resObj.setState(json.getInteger("state").intValue());
                    resObj.setRemark(json.getString("remark"));
                    resObj.setParentId(json.getString("regionCode"));
                    resObj.setParentCodeStr(json.getString("regionCode"));

                    resObj.setSystemNo(json.getString("systemNo"));
                    resObj.setRegionType(Enums.regionType.STREET.getCode());

                    list.add(resObj);
                }
            }

            if (null != list && !list.isEmpty()) {
                this.thirdRegionMapper.batchCompareThirdRegion(list);
            }

            FtpUtil.downFiles(files, PropertiesUtil.getFtpFile(), intranetFilePath);
            count = files.size();
        }
        return count;
    }


    public int synchroVillage(List<String> files, String intranetFilePath) throws IOException {
        IscCoordinate re = null;
        Village resObj = null;
        Region rg = null;
        int count = 0;

        List<String> okFiles = null;
        if (!StringUtils.isEmpty(files)) {
            for (String fn : files) {

                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                try {
                    List<Village> list = new ArrayList<Village>();

                    for (String obj : objList) {
                        JSONObject json = JSONObject.parseObject(obj);

                        rg = new Region();
                        rg.setThirdId(json.getString("regionCode"));
                        List<Region> rList = this.regionService.getRegion(rg);

                        if (!StringUtils.isEmpty(rList)) {
                            rg = null;

                            for (Region r : rList) {
                                if (r.getRegionType().intValue() == Enums.regionType.STREET.getCode()) {
                                    rg = r;
                                }
                            }
                            if (null == rg) {
                                rg = (Region) rList.get(0);
                            }

                            resObj = new Village();
                            resObj.setVillageName(json.getString("name"));
                            resObj.setThirdId(json.getString("id"));
                            if (null != json.getInteger("state") && json.getInteger("state").intValue() == 1) {
                                resObj.setState(Integer.valueOf(0));
                            } else {
                                resObj.setState(Integer.valueOf(1));
                            }
                            resObj.setDescription(json.getString("remark"));
                            resObj.setOrgCode(rg.getRegionCode());
                            resObj.setThirdRegionCode(json.getString("regionCode"));

                            Village selectByExample = this.villageService.selectBythirdId(resObj.getThirdId());
                            if (null != selectByExample) {
                                resObj.setId(selectByExample.getId());
                            } else {

                                resObj.setGisType(Integer.valueOf(Enums.gisType.WGS.getCode()));
                                resObj.setGisArea(formatGisArea(json.getString("coordinates")));

                                re = getCoordinate(resObj.getGisArea());
                                if (null != re) {
                                    resObj.setLat(Double.valueOf(re.getLatitude()));
                                    resObj.setLon(Double.valueOf(re.getLongitude()));
                                } else {
                                    resObj.setLat(Double.valueOf(0.0D));
                                    resObj.setLon(Double.valueOf(0.0D));
                                }
                                resObj.setGisCenter(resObj.getLat() + "," + resObj.getLon());
                            }

                            List<Region> rgList = new ArrayList<Region>();
                            rgList.add(rg);
                            Region region = null;
                            String pId = rg.getParentId();

                            while (null != pId) {
                                region = this.regionService.getRegionParent(pId);
                                if (null != region && null != region.getParentId() &&
                                        !region.getParentId().equals("0")) {
                                    rgList.add(region);
                                    pId = region.getParentId();
                                    continue;
                                }
                                pId = null;
                            }


                            if (!StringUtils.isEmpty(rgList)) {
                                for (Region r : rgList) {
                                    if (Enums.regionType.PROVINCE.getCode() == r.getRegionType().intValue()) {
                                        resObj.setProvinceCode(r.getRegionCode());
                                        continue;
                                    }
                                    if (Enums.regionType.CITY.getCode() == r.getRegionType().intValue()) {
                                        resObj.setCityCode(r.getRegionCode());
                                        continue;
                                    }
                                    if (Enums.regionType.DISTRICT.getCode() == r.getRegionType().intValue()) {
                                        resObj.setDistrictCode(r.getRegionCode());
                                        continue;
                                    }
                                    if (Enums.regionType.STREET.getCode() == r.getRegionType().intValue()) {
                                        resObj.setStreetCode(r.getRegionCode());
                                    }
                                }
                            }
                            list.add(resObj);
                            continue;
                        }
                        LOG.error("小区同步 - 未找到小区所属区域，regionCode为" + json.getString("regionCode") + "的区域不存在");
                    }


                    if (!StringUtils.isEmpty(list)) {

                        for (int i = 0; i < list.size(); i++) {
                            resObj = (Village) list.get(i);
                            Village selectByExample = this.villageService.selectBythirdId(resObj.getThirdId());
                            if (null != selectByExample) {

                                resObj.setId(selectByExample.getId());
                                resObj.setLat(null);
                                resObj.setLon(null);
                                resObj.setGisArea(null);
                                resObj.setGisCenter(null);
                                this.villageService.updateVillage(resObj);
                            } else {

                                this.villageService.insertVillage(resObj);
                            }
                        }

                        okFiles = StringUtils.pickList(okFiles, fn);
                    }
                } catch (Exception e) {
                    LOG.error("数据同步 - 小区同步异常:", e);
                }
            }


            if (!StringUtils.isEmpty(okFiles)) {

                FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                count = files.size();
            }
        }

        return count;
    }


    public int synchroDoorinfo(List<String> files, String intranetFilePath) throws IOException {
        Building resObj = null;
        Village village = null;
        int count = 0;
        if (!StringUtils.isEmpty(files)) {
            List<String> okFiles = null;
            for (String fn : files) {
                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                try {
                    List<Building> list = new ArrayList<Building>();
                    for (String obj : objList) {
                        JSONObject json = JSONObject.parseObject(obj);
                        if (!StringUtils.isEmpty(json.getString("villageId"))) {

                            village = new Village();
                            village.setThirdId(json.getString("villageId"));
                            village = this.systemMapper.getVillage(village);

                            if (null != village) {

                                resObj = new Building();
                                resObj.setBuildingNo(json.getString("doorCode"));
                                resObj.setBuildingName(json.getString("doorName"));
                                resObj.setGisType(Short.valueOf(Enums.gisType.WGS.getCode()));
                                resObj.setLon(json.getDouble("lng"));
                                resObj.setLat(json.getDouble("lat"));
                                resObj.setDescription(json.getString("remark"));
                                resObj.setState(json.getIntValue("state"));
                                resObj.setThirdStreetCode(json.getString("streetCode"));
                                resObj.setThirdId(json.getString("id"));
                                resObj.setThirdVillageId(json.getString("villageId"));
                                resObj.setVillageCode(village.getVillageCode());
                                list.add(resObj);
                            }
                        }
                    }

                    if (!StringUtils.isEmpty(list)) {

                        Building temp = null;
                        for (Building entity : list) {

                            temp = this.systemMapper.getBuildingByCode(entity.getVillageCode(), entity.getBuildingNo());
                            if (null != temp) {

                                entity.setId(temp.getId());
                                this.systemMapper.updateBuilding(entity);
                                continue;
                            }
                            this.systemMapper.insertBuilding(entity);
                        }

                        okFiles = StringUtils.pickList(okFiles, fn);
                    }
                } catch (Exception e) {
                    LOG.error("数据同步 - 楼栋同步异常:", e);
                }
            }
            if (!StringUtils.isEmpty(okFiles)) {
                FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                count = files.size();
            }
        }

        return count;
    }


    public int synchroHouse(List<String> files, String intranetFilePath) throws IOException {
        int count = 0;
        House entity = null;
        Building temp = null;
        if (!StringUtils.isEmpty(files)) {
            for (String fn : files) {
                List<String> okFiles = null;
                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                try {
                    List<House> list = new ArrayList<House>();
                    for (String obj : objList) {

                        JSONObject json = JSONObject.parseObject(obj);
                        entity = new House();
                        entity.setBuildingNo(json.getString("doorCode"));
                        entity.setGisType(Short.valueOf(Enums.gisType.WGS.getCode()).shortValue());
                        entity.setStatus(json.getIntValue("status"));
                        entity.setThirdRegionCode(json.getString("regionCode"));
                        entity.setThirdStreetCode(json.getString("streetCode"));
                        entity.setRemark(json.getString("remark"));
                        entity.setHouseNo(json.getString("roomCode"));
                        entity.setFloor(json.getString("roomNum"));
                        entity.setUnitNo(json.getString("roomNum"));


                        if (null != temp) {
                            entity.setVillageCode(temp.getVillageCode());
                            entity.setLon(temp.getLon());
                            entity.setLat(temp.getLat());
                        } else {
                            entity.setLon(Double.valueOf(0.0D));
                            entity.setLat(Double.valueOf(0.0D));
                        }
                        list.add(entity);
                    }

                    if (!StringUtils.isEmpty(list)) {
                        House house = null;
                        for (House h : list) {
                            house = this.systemMapper.getHouseByCode(h.getBuildingNo());
                            if (null != house) {
                                this.systemMapper.updateHouse(h);
                                continue;
                            }
                            this.systemMapper.insertHouse(h);
                        }

                        okFiles = StringUtils.pickList(okFiles, fn);
                    }

                } catch (Exception e) {
                    LOG.error("数据同步 - 房屋同步异常:", e);
                }

                if (!StringUtils.isEmpty(okFiles)) {
                    FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                    count = files.size();
                }
            }
        }

        return count;
    }


    public int synchroResident(List<String> files, String publicFilePath, String intranetFilePath, String localFilePath) throws IOException {
        int count = 0;
        FacedbfaceVO entity = null;
        if (!StringUtils.isEmpty(files)) {
            List<String> okFiles = null;
            for (String fn : files) {
                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                try {
                    List<FacedbfaceVO> list = new ArrayList<FacedbfaceVO>();
                    for (String obj : objList) {
                        JSONObject json = JSONObject.parseObject(obj);
                        entity = new FacedbfaceVO();
                        entity.setCardId(json.getString("cardId"));
                        entity.setCardType(String.valueOf(CredentialTypeEnum.ID_CARD.getCode()));
                        entity.setCardImg(json.getString("cardImage"));
                        entity.setGender(json.getInteger("gender"));
                        entity.setFaceImgType(Integer.valueOf(2));
                        entity.setPhoneNo(json.getString("phone"));
                        entity.setName(json.getString("name"));
                        entity.setValidDate(json.getString("validDate"));
                        entity.setFaceImg(json.getString("registerFace"));
                        entity.setPeopleId(json.getString("id"));
                        json.remove("cardImage");
                        json.remove("registerFace");
                        entity.setJsonData(json.toJSONString());
                        list.add(entity);
                    }

                    if (!StringUtils.isEmpty(list)) {
                        this.peopleService.batchInsertFacePeople(list);
                        okFiles = StringUtils.pickList(okFiles, fn);
                    }
                } catch (Exception e) {
                    LOG.error("数据同步 - 人员同步异常:", e);
                }
            }
            if (!StringUtils.isEmpty(okFiles)) {
                FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                count = files.size();
            }
        }
        return count;
    }


    public int synchroDoorFlow(List<String> files, String intranetFilePath) throws IOException {
        int count = 0;
        ResultDoorFlow resObj = null;
        if (!StringUtils.isEmpty(files)) {
            if (this.tempList.size() > 500) {
                this.tempList = (List) this.tempList.stream().skip(400L).collect(Collectors.toList());
            }
            List<String> okFiles = null;
            for (String fn : files) {

                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                try {
                    List<ResultDoorFlow> faceList = null;
                    if (!StringUtils.isEmpty(objList)) {
                        for (String obj : objList) {

                            JSONObject json = JSONObject.parseObject(obj);
                            if (!this.tempList.contains(json.getString("captureId"))) {
                                this.tempList.add(json.getString("captureId"));
                                resObj = (ResultDoorFlow) BaseFormatJsonUtil.formatEntity(json, ResultDoorFlow.class);
                                resObj.setFileName(fn);
                                resObj.setFileValue(obj);
                                faceList = StringUtils.pickList(faceList, resObj);
                            }
                        }
                    }


                    if (!StringUtils.isEmpty(faceList)) {
                        SynchroDoorFlowThread job = new SynchroDoorFlowThread();
                        job.setFaceList(faceList);
                        SysThreadPool.getThread().execute(job);
                    }
                    okFiles = StringUtils.pickList(okFiles, fn);
                } catch (Exception e) {
                    LOG.error("数据同步 - 抓拍记录同步异常:", e);
                }
            }
            if (!StringUtils.isEmpty(okFiles)) {
                FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                count = files.size();
            }
        }
        return count;
    }


    public int synchroDevice(List<String> files, String intranetFilePath, Map<String, Region> regionMap) throws Exception {
        int count = 0;

        List<String> deviceNo = new ArrayList<String>();
        List<ResultCamera> cameraList = new ArrayList<ResultCamera>();
        ResultCamera resObj = null;
        Map<String, List<String>> fileMap = new HashMap<String, List<String>>();
        if (!StringUtils.isEmpty(files)) {
            List<String> okFiles = null;
            for (String fn : files) {

                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);

                try {
                    if (!StringUtils.isEmpty(objList)) {
                        for (String obj : objList) {

                            if (!StringUtils.isEmpty(obj)) {
                                JSONObject json = JSONObject.parseObject(obj);
                                fileMap = addFileMap(fileMap, fn, obj);

                                resObj = (ResultCamera) BaseFormatJsonUtil.formatEntity(json, ResultCamera.class);
                                resObj.setFileName(fn);
                                resObj.setFileValue(obj);
                                if ((resObj.getDeviceType().intValue() == Enums.syncCameraType.FACE.getCode() || resObj
                                        .getDeviceType().intValue() == Enums.syncCameraType.USUAL.getCode() || resObj
                                        .getDeviceType().intValue() == Enums.syncCameraType.TERMINAL.getCode()) &&
                                        !deviceNo.contains(resObj.getDeviceNo())) {

                                    deviceNo.add(resObj.getDeviceNo());
                                    cameraList.add(resObj);
                                }
                            }
                        }
                    }


                    if (!cameraList.isEmpty()) {
                        for (ResultCamera ca : cameraList) {
                            if (null != ca.getDeviceType() && (
                                    Enums.syncCameraType.FACE.getCode() == ca.deviceType.intValue() || Enums.syncCameraType.TERMINAL
                                            .getCode() == ca.deviceType.intValue() || Enums.syncCameraType.USUAL
                                            .getCode() == ca.deviceType.intValue())) {
                                mergeCamera(ca);
                            }
                        }


                        okFiles = StringUtils.pickList(okFiles, fn);
                    }

                } catch (Exception e) {
                    LOG.error("数据同步 - 终端设备同步异常:", e);
                }
            }
            if (!StringUtils.isEmpty(okFiles)) {
                FtpUtil.downFiles(okFiles, PropertiesUtil.getFtpFile(), intranetFilePath);
                count = files.size();
            }
        }

        this.jedisUtil.del(new String[]{"ALL_CAMERA"});

        return count;
    }


    public int synchroVehicle(List<String> files, String intranetFilePath) throws IOException {
        int count = 0;
        LOG.info("内网同步车辆开始" + DateUtil.getCurrentSqlTimestampString());
        if (!StringUtils.isEmpty(files)) {
            for (String fn : files) {
                List<String> objList = FtpUtil.readFileByLine(fn, intranetFilePath);
                if (null != objList && !objList.isEmpty()) {
                    String res = synVehicleRecord(objList);
                    LOG.info("过车同步订阅接口返回" + res);
                }
            }
            FtpUtil.downFiles(files, PropertiesUtil.getFtpFile(), intranetFilePath);
            count = files.size();
        }
        LOG.info("内网同步车辆结束" + DateUtil.getCurrentSqlTimestampString());
        return count;
    }


    private String synVehicleRecord(List<String> list) {
        Map<String, Object> parm = new HashMap<String, Object>();
        String postUrl = SyncPropertiesUtil.getIschttp() + HttpConstant.CW_SUBSCRIBED_VEHICLERECORD;
        JSONArray datas = new JSONArray();
        for (String obj : list) {
            JSONObject objJson = JSONObject.parseObject(obj);
            datas.add(objJson);
        }
        parm.put("vehicleRecords", datas);
        LOG.info("同步过车信息请求数据" + datas.size() + "条");
        return BaseHttpUtil.iscHttpPost(JsonUtils.getFastjsonFromObject(parm), postUrl, null);
    }


    private boolean mergeCamera(ResultCamera resCamera) {
        boolean isSessus = true;

        Building building = this.buildingService.getByThirdId(resCamera.getDoorId());

        Camera camera = this.systemMapper.getCameraByNo(null, resCamera.getDeviceNo());

        String deviceId = addOsCamera(resCamera, camera);

        if (null != camera) {

            camera = resetCamera(resCamera, camera, building);
            if (null != camera) {
                LOG.info("******************" + camera.getCameraName() + "*********************");
                int count = this.systemMapper.updateCamera(camera);
                LOG.info("内网同步 - 本地库同步 更新 大眼睛：" + ((count > 0) ? "成功" : "失败"));
            } else {
                isSessus = false;
                LOG.error("内网同步 - 本地库同步 更新 大眼睛：第三方楼栋信息不存在" + resCamera.getDoorId());
            }

        } else if (!StringUtils.isEmpty(deviceId)) {

            camera = resetCamera(resCamera, null, building);
            if (null != camera) {
                camera.setCameraId(deviceId);
                int count = this.systemMapper.insertCamera(camera);
                LOG.info("内网同步 - 本地库同步 新增 大眼睛：" + ((count > 0) ? "成功" : "失败"));
            } else {
                isSessus = false;
                LOG.error("内网同步 - 本地库同步 新增 大眼睛：第三方楼栋信息不存在" + resCamera.getDoorId());
            }
        } else {
            isSessus = false;
        }


        return isSessus;
    }


    private Camera resetCamera(ResultCamera resCamera, Camera camera, Building building) {
        if (null == camera) {
            camera = new Camera();
        }
        camera.setCameraName(resCamera.getDeviceName());
        if (!StringUtils.isEmpty(resCamera.getLat())) {
            camera.setLat(Double.valueOf(resCamera.getLat()));
        }
        if (!StringUtils.isEmpty(resCamera.getLng())) {
            camera.setLon(Double.valueOf(resCamera.getLng()));
        }
        camera.setCameraIp(resCamera.getIpAddr());
        camera.setCameraState(resCamera.getDeviceStatus().intValue());
        camera.setCameraType(convertDeviceType(resCamera.getDeviceType().intValue()));
        camera.setProductCode(resCamera.getDeviceNo());
        if (!StringUtils.isEmpty(resCamera.getVillageId())) {
            camera.setVillageCode(resCamera.getVillageId());
        } else if (!StringUtils.isEmpty(resCamera.getDoorId()) &&
                null != building) {
            camera.setVillageCode(building.getVillageCode());
        }


        return camera;
    }


    private int convertDeviceType(int type) {
        if (type == Enums.syncCameraType.FACE.getCode())
            return Enums.cameraType.DOOR.getCode();
        if (type == Enums.syncCameraType.USUAL.getCode()) {

            return Enums.cameraType.DOOR.getCode();
        }
        if (type == Enums.syncCameraType.TERMINAL.getCode()) {
            return Enums.cameraType.CRED.getCode();
        }
        return 0;
    }


    private String addOsCamera(ResultCamera resCamera, Camera camera) {
        JSONObject resultJson = null;
        Map<String, Object> parm = null;

        String postUrl = SyncPropertiesUtil.getTerminalhttp() + HttpConstant.TERMINAL_REG_DEVICE;
        String deviceId = null;
        if (resCamera.getDeviceType().intValue() == Enums.syncCameraType.FACE.getCode() || resCamera
                .getDeviceType().intValue() == Enums.syncCameraType.TERMINAL.getCode() || resCamera
                .getDeviceType().intValue() == Enums.syncCameraType.USUAL.getCode()) {

            parm = new HashMap<String, Object>();
            parm.put("deviceNo", resCamera.getDeviceNo());


            parm.put("deviceType", resCamera.getDeviceType());
            parm.put("sysVer", resCamera.getSysVer());
            parm.put("sysIccid", resCamera.getSysIccid());
            parm.put("ipAddr", resCamera.getIpAddr());
            parm.put("model", resCamera.getModel());
            parm.put("system", resCamera.getSystem());
            parm.put("firm", resCamera.getFirm());
        }

        LOG.info("内网同步 - 新增修改设备：" + JSONObject.toJSON(parm).toString());

        String result = BaseHttpUtil.httpPost(JSONObject.toJSON(parm).toString(), postUrl, null);
        resultJson = JSONObject.parseObject(result);
        LOG.info("内网同步 - 新增 设备返回：" + result);

        if (null == camera) {
            if (StringUtils.checkSuccess(resultJson)) {

                parm = new HashMap<String, Object>();
                parm.put("engineType", Integer.valueOf(1));

                resultJson = resultJson.getJSONObject("data");
                deviceId = resultJson.getString("deviceId");

                engineBind(deviceId, 2);
            } else {
                LOG.error("内网同步 - 新增 设备失败：" + resultJson.getString("message"));
            }
        }
        return deviceId;
    }


    public void engineBind(String deviceId, int engineType) {
        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("engineType", Integer.valueOf(engineType));
        parm.put("deviceId", deviceId);

        String result = BaseHttpUtil.httpPost(JSONObject.toJSON(parm).toString(),
                SyncPropertiesUtil.getOshttp() + HttpConstant.ENGINE_BIND, null);
        JSONObject engineJson = JSONObject.parseObject(result);
        if (!StringUtils.checkSuccess(engineJson)) {
            LOG.error("内网同步 - 新增 设备绑定引擎失败：" + engineJson.getString("message"));
        }
    }


    private IscCoordinate getCoordinate(String coordinate) {
        List<IscCoordinate> coordinateList = null;
        IscCoordinate cooObj = null;
        if (null != coordinate) {
            coordinateList = new ArrayList<IscCoordinate>();
            String[] coo = coordinate.split(";");
            String[] atom = null;
            if (null != coo && coo.length > 0) {
                for (int i = 0; i < coo.length; i++) {
                    atom = coo[i].split(",");
                    cooObj = new IscCoordinate(Double.valueOf(atom[0]).doubleValue(), Double.valueOf(atom[1]).doubleValue());
                    coordinateList = StringUtils.pickList(coordinateList, cooObj);
                }
            }

            return GetCenterCoordinates.getCenterPoint(coordinateList);
        }

        return null;
    }


    private String formatGisArea(String gisArea) {
        String area = null;
        if (!StringUtils.isEmpty(gisArea)) {

            area = gisArea.replaceAll("@", ";");
        } else {
            area = "";
        }
        return area;
    }


    private Map<String, List<String>> addFileMap(Map<String, List<String>> fileMap, String key, String value) {
        if (!fileMap.containsKey(key)) {
            List<String> list = new ArrayList<String>();
            list.add(value);
            fileMap.put(key, list);
        } else {
            ((List) fileMap.get(key)).add(value);
        }
        return fileMap;
    }

    public class SynchroDoorFlowThread
            implements Runnable {

        private List<ResultDoorFlow> faceList;

        public List<ResultDoorFlow> getFaceList() {
            return this.faceList;
        }


        public void setFaceList(List<ResultDoorFlow> faceList) {
            this.faceList = faceList;
        }


        public void run() {
            LOG.info("SynchroDoorFlowThread set ");
            if (!StringUtils.isEmpty(this.faceList)) {
                LOG.info("SynchroDoorFlowThread faceList " + this.faceList.size());
                JSONObject resultJson = null;
                Map<String, Object> parm = null;
                String cardId = null;
                for (ResultDoorFlow door : this.faceList) {
                    LOG.info("流水同步  CaptureId：" + door.getCaptureId() + " captureTime:" + DateUtil.formatDate(door.getTime()));
                    if (!StringUtils.isEmpty(door.getIdNumber())) {
                        cardId = SyncServiceImpl.this.systemMapper.getFacedbfaceId(door.getIdNumber());
                    }


                    Camera camera = SyncServiceImpl.this.systemMapper.getCameraByNo(null, door.getDeviceNo());
                    parm = new HashMap<String, Object>();
                    if (null != camera) {
                        parm.put("deviceNo", camera.getProductCode());
                        parm.put("personId", cardId);
                        parm.put("spotImgPath", door.getFace());
                        parm.put("panoramaPath", door.getPanorama());
                        parm.put("compareDate", SDF.format(door.getTime()));
                        parm.put("type", door.getCheckModel());
                        LOG.info("========camera========" + JSONObject.toJSONString(camera));
                        if (camera.getCameraType() != Enums.cameraType.USUAL.getCode()) {
                            LOG.info("========getCameraType========" + camera.getCameraType());
                            parm.put("threshold", door.getCompareScore());
                            parm.put("score", door.getCompareScore());
                            parm.put("result", door.getResult());
                        } else {

                            parm.put("threshold", "0.85");
                            parm.put("score", "0.5");
                            parm.put("result", "2");
                        }
                        LOG.info("=======用ocean流水信息上传接口======" + JSONObject.toJSON(parm).toString());

                        String result = BaseHttpUtil.httpPost(JSONObject.toJSON(parm).toString(),
                                SyncPropertiesUtil.getTerminalhttp() + HttpConstant.TERMINAL_UPLOAD_RECORD, null);

                        resultJson = JSONObject.parseObject(result);
                        if (StringUtils.checkSuccess(resultJson)) {
                            continue;
                        }
                        LOG.error("同步抓拍数据 - 流水同步失败" + resultJson.toJSONString());
                    }
                }
            }
        }

    }

}
