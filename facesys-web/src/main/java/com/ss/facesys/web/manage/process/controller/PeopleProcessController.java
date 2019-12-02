package com.ss.facesys.web.manage.process.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.j7cai.common.util.JsonUtils;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.client.MediaInfoService;
import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.facesys.data.collect.client.IAlarmService;
import com.ss.facesys.data.collect.client.IPeopleService;
import com.ss.facesys.data.collect.common.dto.RegisterDTO;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.process.client.IPeopleProcessService;
import com.ss.facesys.data.process.common.model.AddPersonRecog;
import com.ss.facesys.data.process.common.model.PeopleProcess;
import com.ss.facesys.data.process.common.web.AddPersonRecogVO;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.em.CredentialTypeEnum;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIGetsGroup;
import com.ss.valide.APIListGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * PeopleProcessController 人员预警处理
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/process"})
public class PeopleProcessController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PeopleProcessController.class);

    @Resource
    private IPeopleService peopleService;
    @Resource
    private MediaInfoService mediaInfoService;
    @Resource
    private IPeopleProcessService peopleProcessService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IAlarmService alarmService;
    @Resource
    private IAccessService accessService;
    @Resource
    private IVillageService villageService;

    /**
     * 人口预警处理
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleProcess"}, method = {RequestMethod.POST})
    public ResponseEntity<String> peopleProcess(@RequestBody @Validated({APIAddGroup.class}) PeopleProcessVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = "";
            // 处置类型
            int proceeType = para.getProcessType();
            // 校验预警是否处置
            PeopleProcess peopleProcess = this.peopleProcessService.selectPeopleProcess(new PeopleProcess(para.getRecordId(), para.getProcessType()));
            if (peopleProcess != null) {
                resp = createFailResponse();
                resp.setMessage("该记录已经处置");
                return resp;
            }
            // 根据处置类型进行处置（同时更新预警记录状态）
            if (CommonConstant.PROCESSTYPE_ADDPERSON == proceeType) {
                this.logger.info("疑似新增处置");
                AddPerson addPerson = new AddPerson(para.getRecordId(), para.getState());
                this.peopleService.addPeopleProcess(addPerson);
            } else if (CommonConstant.PROCESSTYPE_LEAVEPERSON == proceeType) {
                this.logger.info("疑似离开处置");
                LeavePerson leavePerson = new LeavePerson(para.getRecordId(), para.getState());
                leavePerson.setRemark(para.getRemark());
                this.peopleService.leavePeopleProcess(leavePerson);
            } else if (CommonConstant.PROCESSTYPE_FREQUENCY == proceeType) {
                this.logger.info("高频陌生人处置");
                FrequencyRecord frequencyRecord = new FrequencyRecord();
                if (para.getLabelValue() != null) {
                    frequencyRecord.setLabel(StringUtils.arrToString(para.getLabelValue()));
                }
                frequencyRecord.setAddPersonId(para.getRecordId());
                frequencyRecord.setState(para.getState());
                this.peopleService.frequencyPersonProcess(frequencyRecord);
            } else if (CommonConstant.PROCESSTYPE_OLDMAN == proceeType) {
                this.logger.info("高龄老人处置");
                this.peopleService.specialPersonProcess(new SpecialPerson(para.getRecordId(), para.getState()));
            } else if (CommonConstant.PROCESSTYPE_ALARM == proceeType) {
                this.logger.info("预警人员处置");
                this.alarmService.alarmProcess(para.getRecordId(), para.getState());
            } else if (CommonConstant.PROCESSTYPE_FREQUENCYNIGHT == proceeType) {
                this.logger.info("夜间高频处置");
                FrequencyNightPerson frequencyNightPerson = new FrequencyNightPerson(para.getRecordId(), para.getState());
                frequencyNightPerson.setRemark(para.getRemark());
                frequencyNightPerson.setLabel(para.getLabel());
                this.peopleService.frequencyNightPersonProcess(frequencyNightPerson);
            } else if (CommonConstant.PROCESSTYPE_SENSITIVETRAFFIC == proceeType) {
                this.logger.info("敏感通行处置");
                SensitivePerson sensitivePerson = new SensitivePerson(para.getRecordId(), para.getState());
                sensitivePerson.setRemark(para.getRemark());
                sensitivePerson.setLabel(para.getLabel());
                this.peopleService.sensitivePersonProcess(sensitivePerson);
            } else if (CommonConstant.PROCESSTYPE_LONGTIMESTAY == proceeType) {
                this.logger.info("长时间逗留处置");
                LongtimeStayPerson longtimeStayPerson = new LongtimeStayPerson(para.getRecordId(), para.getState());
                longtimeStayPerson.setRemark(para.getRemark());
                longtimeStayPerson.setLabel(para.getLabel());
                this.peopleService.longtimeStayPersonProcess(longtimeStayPerson);
            }
            // 新增处置信息记录
            message = this.peopleProcessService.peopleProcess(para);
            resp.setData(message);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.PEOPLEPROCESS_FAILED_CODE);
            resp.setMessage("人员处置失败");
            this.logger.error("人员处置失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 一键处置人口预警
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/warningProcessBatch"}, method = {RequestMethod.POST})
    public ResponseEntity<String> warningProcessBatch(@RequestBody @Validated({APIListGroup.class}) PeopleProcessVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            // 处置类型默认设置为布控报警处置
            para.setProcessType(CommonConstant.PROCESSTYPE_ALARM);
            // 校验是否存在已处置的预警
            for (String recordId : para.getRecordIds().split(CommonConstant.SPLIT_COMMA)) {
                PeopleProcess peopleProcess = this.peopleProcessService.selectPeopleProcess(new PeopleProcess(recordId, para.getProcessType()));
                if (peopleProcess != null) {
                    resp = createFailResponse();
                    resp.setMessage("存在已处置数据，请勿重复处置！");
                    return resp;
                }
            }
            this.logger.info("预警人员一键处置");
            String message = alarmService.warningProcessBatch(para);
            resp.setData(message);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.PEOPLEPROCESS_FAILED_CODE);
            resp.setMessage("人员一键处置失败");
            this.logger.error("人员一键处置失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/peopleProcessView"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> peopleProcessView(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({APIGetsGroup.class}) PeopleProcessVO peopleProcessVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Object> resp = validite(bindingResult);
        try {
            PeopleProcess para = new PeopleProcess();
            para.setRecordId(peopleProcessVO.getRecordId());
            para.setProcessType(peopleProcessVO.getProcessType());

            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

            PeopleProcess peopleProcess = this.peopleProcessService.selectPeopleProcess(para);
            if (peopleProcess != null) {
                map.put("peopleProcess", peopleProcess);
                if (!peopleProcessVO.getProcessType().equals(CommonConstant.PROCESSTYPE_ADDPERSON)) {

                    MediaInfo mediaInfo = new MediaInfo();
                    mediaInfo.setRecordId(peopleProcess.getId());
                    List<MediaInfo> list = this.mediaInfoService.findMediaInfos(mediaInfo);
                    map.put("mediaInfos", list);
                } else {

                    People people = this.peopleService.findPeopleInfo(peopleProcess.getRecordId());
                    if (people != null) {
                        if (StringUtils.isNotBlank(people.getOriginCode())) {

                            Region province = this.regionService.selectRegionByCode(people.getOriginCode().substring(0, 2) + "0000");
                            if (province != null) {
                                people.setProvince(province.getRegionName());
                                people.setProvinceCode(province.getRegionCode());
                            }
                        }
                        if (people.getStreetCode() != null) {
                            Region street = this.regionService.selectRegionByCode(String.valueOf(people.getStreetCode()));
                            if (street != null) {
                                people.setStreetName(street.getRegionName());
                            }
                        }
                        people.setPeopleTypeName(Enums.PeopleType.getName(people.getPeopleType()));
                        people.setCredentialTypeName(CredentialTypeEnum.getName(people.getCredentialType()));
                    }
                    map.put("people", people);
                }
            }
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804002");
            resp.setMessage("人员处置信息查询失败");
            this.logger.error("人员处置信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/registerRecog"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "实有人口库对比", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> addPersonRecog(HttpServletRequest request, @RequestBody @Validated({APIGetsGroup.class}) AddPersonRecogVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            List<RegisterDTO> registerDTOs = new ArrayList<RegisterDTO>();

            String fadbId = this.villageService.getVillageFacedbId(PropertiesUtil.getRegionCode());
            this.logger.info("底库信息" + fadbId);
            if (StringUtils.isNotBlank(fadbId)) {

                String img = para.getImg().replace("http://", "");
                para.setTopN(NumberConstant.FIVE);
                para.setImgType(CommonConstant.IMGTYPE_URL);
                para.setImg(img.substring(img.indexOf("/", 1), img.length()));
                para.setThresholdMin(Float.valueOf(PropertiesUtil.getThreShold()));
                para.setFacedbIds(new String[]{fadbId});

                String parmJson = JsonUtils.getFastjsonFromObject(para);
                this.logger.info("底库检索请求参数" + parmJson);
                JSONObject jsonObject = this.accessService.getRecogRegisterDb(parmJson);
                this.logger.info("底库检索返回结果" + jsonObject.toString());

                if (StringUtils.checkSuccess(jsonObject)) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    registerDTOs = JSONArray.parseArray(jsonArray.toString(), RegisterDTO.class);
                }
            }


            AddPersonRecog personRecog = this.peopleProcessService.selectAddPersonRecogMapper(para.getRecordId());
            if (personRecog != null) {
                personRecog.setStatus(CommonConstant.COMMON_1.intValue());
                RegisterDTO registerDTO = new RegisterDTO();
                BeanUtils.copyProperties(personRecog, registerDTO);

                boolean containState = false;
                for (RegisterDTO re : registerDTOs) {
                    if (registerDTO.getCardId().equals(re.getCardId())) {
                        containState = true;
                        re.setStatus(CommonConstant.COMMON_1.intValue());
                        break;
                    }
                }
                if (!containState) {
                    registerDTOs.add(registerDTO);
                }
            }


            Collections.sort(registerDTOs, new Comparator<RegisterDTO>() {
                public int compare(RegisterDTO arg0, RegisterDTO arg1) {
                    float a0 = arg0.getRecogScore().floatValue();
                    float a1 = arg1.getRecogScore().floatValue();
                    if (a1 > a0) {
                        return 1;
                    }
                    if (a1 == a0) {
                        return 0;
                    }

                    return -1;
                }
            });

            if (registerDTOs.size() > CommonConstant.COMMON_5.intValue()) {
                registerDTOs.remove(registerDTOs.size() - 1);
            }
            map.put("registerRecogs", registerDTOs);
            map.put("personRecog", personRecog);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804003");
            this.logger.error("底库对比失败，原因：" + e.toString(), e);
            resp.setMessage("底库对比失败");
        }
        return resp;
    }


    @RequestMapping(value = {"/saveRecogInfo"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "实有人口库检索信息保存", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> saveRecogInfo(HttpServletRequest request, @RequestBody @Validated({APIGetsGroup.class}) AddPersonRecog addPersonRecog, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            if (StringUtils.isBlank(addPersonRecog.getCardPathFull()) || StringUtils.isBlank(addPersonRecog.getCardId()) ||
                    StringUtils.isBlank(addPersonRecog.getRecordId())) {
                resp = createFailResponse();
                resp.setCode("70804004");
                resp.setMessage("必填字段不能为空");
            } else {

                String personRecog = this.peopleProcessService.saveRecogInfo(addPersonRecog);
                resp.setData(personRecog);
            }

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804004");
            this.logger.error("实有人口库检索信息保存失败，原因：" + e.toString(), e);
            resp.setMessage("实有人口库检索信息保存失败");
        }
        return resp;
    }

    /**
     * 人员预警结果查询
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleProcessResult"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> peopleProcessResult(@RequestBody @Validated({APIGetsGroup.class}) PeopleProcessVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Object> resp = validite(bindingResult);
        try {
            // 处置类型
            int proceeType = para.getProcessType();
            if (CommonConstant.PROCESSTYPE_LEAVEPERSON == proceeType) {
                this.logger.info("疑似离开处置结果查询");
                LeavePerson leavePerson = this.peopleService.findLeavePersonResult(para.getRecordId());
                resp.setData(leavePerson);
            } else if (CommonConstant.PROCESSTYPE_FREQUENCYNIGHT == proceeType) {
                this.logger.info("夜间高频处置结果查询");
                FrequencyNightPerson frequencyNightPerson = this.peopleService.findFrequencyNightPersonResult(para.getRecordId());
                resp.setData(frequencyNightPerson);
            } else if (CommonConstant.PROCESSTYPE_SENSITIVETRAFFIC == proceeType) {
                this.logger.info("敏感通行处置结果查询");
                SensitivePerson sensitivePerson = this.peopleService.findSensitivePersonResult(para.getRecordId());
                resp.setData(sensitivePerson);
            } else if (CommonConstant.PROCESSTYPE_LONGTIMESTAY == proceeType) {
                this.logger.info("长时间逗留处置结果查询");
                LongtimeStayPerson longtimeStayPerson = this.peopleService.findLongtimeStayPersonResult(para.getRecordId());
                resp.setData(longtimeStayPerson);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.PEOPLEPROCESS_FAILED_CODE);
            resp.setMessage("人员处置结果查询失败");
            this.logger.error("人员处置结果查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
