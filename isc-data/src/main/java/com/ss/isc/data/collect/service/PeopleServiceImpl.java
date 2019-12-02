package com.ss.isc.data.collect.service;

import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.baseinfo.common.model.BaseEnums;
import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.collect.client.IPeopleService;
import com.ss.isc.data.collect.common.dto.*;
import com.ss.isc.data.collect.common.model.*;
import com.ss.isc.data.collect.common.web.*;
import com.ss.isc.data.collect.mapper.*;
import com.ss.isc.data.process.client.IPeopleProcessService;
import com.ss.isc.data.process.common.web.PeopleProcessVO;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.file.FileConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.tools.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = {Exception.class})
public class PeopleServiceImpl extends BaseServiceImpl implements IPeopleService {

    public static final Log LOG = LogFactory.getLog(HouseServiceImpl.class);

    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private AddPersonMapper addPersonMapper;
    @Autowired
    private LeavePersonMapper leavePersonMapper;
    @Autowired
    private AddPersonWithMapper addPersonWithMapper;
    @Autowired
    private SpecialPersonMapper specialPersonMapper;
    @Autowired
    private IAccessService accessService;
    @Autowired
    private FrequencyRecordMapper frequencyRecordMapper;
    @Autowired
    private AddPersonCollectMapper addPersonCollectMapper;
    @Autowired
    private IAnalysisMapper analysisMapper;
    @Autowired
    private FrequencyNightPersonMapper frequencyNightPersonMapper;
    @Autowired
    private SensitivePersonMapper sensitivePersonMapper;
    @Autowired
    private LongtimeStayPersonMapper longtimeStayPersonMapper;
    @Resource
    private IPeopleProcessService peopleProcessService;


    @Override
    public AddPerson getAddPersonById(String id) {
        return this.addPersonMapper.getAddPersonById(id);
    }

    @Override
    public LeavePerson getLeavePersonById(String id) {
        return this.leavePersonMapper.getLeavePersonById(id);
    }

    @Override
    public List<AddPerson> queryDayAddList() {
        return this.addPersonMapper.queryDayAddList();
    }

    @Override
    public List<FrequencyNightPerson> queryFrequencyNightList() {
        return this.frequencyNightPersonMapper.queryFrequencyNightList();
    }

    @Override
    public List<SensitivePerson> querySensitiveList() {
        return this.sensitivePersonMapper.querySensitiveList();
    }

    @Override
    public List<AddPersonDetail> queryDayAddDetailList(int state, int days) {
        return this.addPersonMapper.queryDayAddDetailList(state, days);
    }

    @Override
    public int batchCompareAddPerson(List<AddPerson> list, List<AddPersonDetail> detairList) {
        int count = this.addPersonMapper.batchCompareAddPerson(list);
        if (count > 0) {
            count = this.addPersonMapper.batchInsertAddPersonDetail(detairList);
        }
        return count;
    }

    @Override
    public List<LeavePerson> queryLeaveListByState(String villageCode, Integer state) {
        return this.leavePersonMapper.queryLeaveListByState(villageCode, state);
    }

    @Override
    public List<SpecialPerson> querySpecialListByState(Integer state, int specialType) {
        return this.specialPersonMapper.querySpecialListByState(state, specialType);
    }

    @Override
    public int batchCompareLeavePerson(List<LeavePerson> list, List<SpecialPerson> specialList, List<String> delLeaveIdList) {
        int count = 0;
        if (null != list && !list.isEmpty()) {
            count = this.leavePersonMapper.batchCompareLeavePerson(list);
        }
        if (count > 0) {
            List<LeavePersonDetail> detail = new ArrayList<LeavePersonDetail>();
            for (LeavePerson lp : list) {
                if (null != lp.getDetail() && !lp.getDetail().isEmpty()) {
                    detail.add(lp.getDetail().get(0));
                }
            }
            if (!detail.isEmpty()) {
                count = this.leavePersonMapper.batchInsertLeavePersonDetail(detail);
            }
            if (null != specialList && !specialList.isEmpty()) {
                count = this.specialPersonMapper.batchCompareSpecialPerson(specialList);
            }
        }

        if (null != delLeaveIdList && !delLeaveIdList.isEmpty()) {
            for (String pId : delLeaveIdList) {
                this.specialPersonMapper.updateSpecialState(pId, Enums.LeaveState.STATE_3.getCode());
                this.leavePersonMapper.updateLeaveState(pId, Enums.LeaveState.STATE_3.getCode());
            }
        }

        return count;
    }

    @Override
    public List<FrequencyRecordDTO> frequencyRecordTop(String villageCode) {
        FrequencyRecordQuery frequencyPersonQuery = new FrequencyRecordQuery();

        frequencyPersonQuery.setVillageCode(villageCode);
        return this.frequencyRecordMapper.frequencyRecordTop(frequencyPersonQuery);
    }

    @Override
    public List<AddPersonDetailVO> findAddPersonDetails(String addPersonId) {
        AddPersonCollect addPersonCollect = this.addPersonCollectMapper.findCollect(addPersonId,
                PropertiesUtil.getAddPersonJudgedDays());
        String endDate = DateUtil.addDays(addPersonCollect.getDayEnd(), 1);
        addPersonCollect.setDayEnd(endDate);
        return this.addPersonMapper.findAddPersonDetails(addPersonCollect);
    }

    @Override
    public List<AddPersonDetailVO> frequencyRecordTopDetails(String addPersonId) {
        FrequencyRecord frequencyRecord = this.frequencyRecordMapper
                .getFrequencyRecordById(addPersonId);
        AddPersonCollect addPersonCollect = new AddPersonCollect();
        addPersonCollect.setAddPersonId(addPersonId);
        addPersonCollect.setDayBegin(DateUtil.formatDateDefault(frequencyRecord.getBeginTime()));
        addPersonCollect
                .setDayEnd(DateUtil.addDays(DateUtil.formatDateDefault(frequencyRecord.getEndTime()), 1));
        return this.addPersonMapper.findAddPersonDetails(addPersonCollect);
    }

    @Override
    public int batchInsertAddPersonWith(List<AddPersonWith> list, List<String> detIds) {
        int count = 0;
        if (!StringUtils.isEmpty(list)) {
            this.addPersonWithMapper.batchInsertAddPersonWith(list);
        }

        if (!StringUtils.isEmpty(detIds)) {
            for (String id : detIds) {
                this.addPersonWithMapper.updateWithState(id);
            }
        }
        return count;
    }

    /**
     * 一社一档-居民详情
     *
     * @param peopleQuery
     * @return
     */
    @Override
    public List<People> page(PeopleQuery peopleQuery) {
        int currentPage = peopleQuery.getCurrentPage();
        int pageSize = peopleQuery.getPageSize();
        PageHelper.startPage(currentPage, pageSize);

        People people = new People();
        BeanUtils.copyProperties(peopleQuery, people);

        // 权限小区条件
        people.getSqlMap().put("dsf", peopleQuery.getVillageCodes());
        // 人员标签条件
        if (StringUtils.isNotEmpty(people.getLabel())) {
            StringBuilder sb = new StringBuilder("(");
            String[] labelArray = people.getLabel().split(",");
            for (int i = 0; i < labelArray.length; i++) {
                if (i > CommonConstant.COMMON_0) {
                    sb.append(" OR ");
                }
                sb.append("FIND_IN_SET(").append(labelArray[i]).append(",t1.label)");
            }
            sb.append(")");
            people.getSqlMap().put("labelSql", sb.toString());
        }
        List<People> list = this.peopleMapper.page(people);
        int index = (currentPage - 1) * pageSize + NumberConstant.ONE;
        for (People p : list) {
            List<BaseEnums> labels = new ArrayList<>();
            if (StringUtils.isNotBlank(p.getLabel())) {
                String[] labelArr = p.getLabel().split(",");
                for (String s : labelArr) {
                    labels.add(new BaseEnums(Enums.PeopleLabel.getName(Integer.parseInt(s)), Integer.parseInt(s)));
                }
            }
            p.setLabels(labels);
            p.setRowNum(index);
            if (StringUtils.isNotBlank(p.getIdCardPic()) && !p.getIdCardPic().contains(FileConstant.FILE_HTTPADD)) {
                p.setIdCardPic(FilePropertiesUtil.getHttpUrl() + p.getIdCardPic());
            }
            if (StringUtils.isNotBlank(p.getFacePic()) && !p.getFacePic().contains(FileConstant.FILE_HTTPADD)) {
                p.setFacePic(FilePropertiesUtil.getHttpUrl() + p.getFacePic());
            }
            index++;
        }
        return list;
    }


    private People handleRequestPeople(People people) {
        String type = people.getType();

        String value = people.getValue();
        if (StringUtils.isNotBlank(type)) {
            switch (type) {

                case "sex":
                    people.setGenderCode(value);
                    break;

                case "peopleType":
                    people.setPeopleType(Integer.valueOf(value));
                    break;

                case "ageGroup":
                    if (String.valueOf(NumberConstant.ONE).equals(value)) {
                        people.setMinAge(Integer.valueOf(0));
                        people.setMaxAge(Integer.valueOf(17));
                        break;
                    }
                    if (String.valueOf(NumberConstant.TWO).equals(value)) {
                        people.setMinAge(Integer.valueOf(16));
                        people.setMaxAge(Integer.valueOf(41));
                        break;
                    }
                    if (String.valueOf(NumberConstant.THREE).equals(value)) {
                        people.setMinAge(Integer.valueOf(39));
                        people.setMaxAge(Integer.valueOf(61));
                        break;
                    }
                    if (String.valueOf(NumberConstant.FOUR).equals(value)) {
                        people.setMinAge(Integer.valueOf(59));
                        people.setMaxAge(Integer.valueOf(81));
                        break;
                    }
                    people.setMinAge(Integer.valueOf(79));
                    people.setMaxAge(Integer.valueOf(10000));
                    break;

                case "label":
                    if (StringUtils.isNotBlank(value)) {
                        people.setLabelArr(new Integer[]{Integer.valueOf(Integer.parseInt(value))});
                    }
                    break;
            }


        }
        return people;
    }

    /**
     * 疑似新增分页查询
     * @param addPersonQuery
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<AddPersonDTO> addPersonPage(AddPersonQuery addPersonQuery, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(addPersonQuery.getUserIds());
        //获取疑似新增判断天数
        addPersonQuery.setDays(PropertiesUtil.getAddPersonJudgedDays());
//        if (addPersonQuery.getEndTime() != null) {
//            addPersonQuery.setEndTime(DateUtil.getEndDayOfSomeDay(addPersonQuery.getEndTime()));
//        }
        //疑似新增查询
        List<AddPersonDTO> list = this.addPersonMapper.page(addPersonQuery);
        int rowNum = pageSize * (currentPage - 1);
        for (AddPersonDTO addPersonDTO : list) {
            rowNum++;
            addPersonDTO.setRowNum(rowNum);
            addPersonDTO.setThreshold(PropertiesUtil.getThreShold() * NumberConstant.ONE_HUNDRED);
            if (PropertiesUtil.isAddPersonRecogState()) {
                addPersonDTO.setRecogState(NumberConstant.ONE);
            }
        }
        return list;
    }

    /**
     * 疑似离开分页查询
     * @param leavePersonQuery
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<LeavePerson> leavePersonPage(LeavePersonQuery leavePersonQuery, int currentPage,
                                             int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(leavePersonQuery.getUserIds());
        //获取疑似离开判断天数
        leavePersonQuery.setLeaveDays(PropertiesUtil.getLeaveDays());
//        if (leavePersonQuery.getEndTime() != null) {
//            leavePersonQuery.setEndTime(DateUtil.getEndDayOfSomeDay(leavePersonQuery.getEndTime()));
//        }
        //疑似离开查询
        List<LeavePerson> list = this.leavePersonMapper.page(leavePersonQuery);
        int rowNum = pageSize * (currentPage - 1);
        for (LeavePerson leavePerson : list) {
            rowNum++;
            leavePerson.setRowNum(rowNum);
            String idCardPic = leavePerson.getIdCardPic();
            if (StringUtils.isNotBlank(idCardPic) && !idCardPic.contains("http")) {
                leavePerson.setIdCardPic(FilePropertiesUtil.getHttpUrl() + idCardPic);
            }
        }
        return list;
    }

    /**
     * 疑似新增处置
     * @param addPerson
     * @return
     */
    @Override
    public String addPeopleProcess(AddPerson addPerson) {
        boolean flag = (this.addPersonMapper.update(addPerson) > CommonConstant.COMMON_0);
        if (flag  && CommonConstant.COMMON_2 == addPerson.getState()) {
            PeopleProcessVO peopleProcessVO = new PeopleProcessVO();
            peopleProcessVO.setProcessType(CommonConstant.COMMON_1);
            peopleProcessVO.setRecordId(addPerson.getId());
            peopleProcessVO.setState(CommonConstant.COMMON_2);
            peopleProcessVO.setProcessUserId(addPerson.getUserIds());
            //疑似新增人员更新
            peopleProcessService.peopleProcess(peopleProcessVO);
        }
        if (flag) {
            FrequencyRecord frequencyRecord = new FrequencyRecord();
            frequencyRecord.setState(NumberConstant.FOUR);
            frequencyRecord.setAddPersonId(addPerson.getId());
            flag = (this.frequencyRecordMapper.update(frequencyRecord) > CommonConstant.COMMON_0);
        }
        return flag ? "success" : "failed";
    }

    /**
     * 疑似离开处置
     * @param leavePerson
     * @return
     */
    @Override
    public boolean leavePeopleProcess(LeavePerson leavePerson) {
        boolean flag = true;
        LeavePerson lp = this.leavePersonMapper.getLeavePersonById(leavePerson.getId());
        //疑似离开人员更新
        flag = (this.leavePersonMapper.update(leavePerson) > CommonConstant.COMMON_0);

        if (CommonConstant.COMMON_2 == leavePerson.getState()) {
            People people = new People();
            people.setPeopleId(lp.getPeopleId());
            people.setIsLeave(NumberConstant.ONE);
            people.setLeaveTime(System.currentTimeMillis());
            flag = (this.peopleMapper.updateByPeopleId(people) > CommonConstant.COMMON_0);
        }

        SpecialPerson specialPerson = new SpecialPerson();
        specialPerson.setRecordId(leavePerson.getId());
        specialPerson.setState(NumberConstant.FOUR);
        return (this.specialPersonMapper.updateByRecordId(specialPerson) > CommonConstant.COMMON_0);
    }

    @Override
    public People findPeopleInfo(String recordId) {
        People people = null;
        AddPerson addPerson = this.addPersonMapper.getAddPersonById(recordId);
        if (addPerson != null && StringUtils.isNotBlank(addPerson.getPeopleId())) {
            People para = new People();
            para.setPeopleId(addPerson.getPeopleId());
            people = (People) this.peopleMapper.selectOne(para);
            if (StringUtils.isNotBlank(people.getFacePic()) &&
                    !people.getFacePic().contains("http")) {
                people.setFacePic(FilePropertiesUtil.getHttpUrl() + people.getFacePic());
            }
            if (StringUtils.isNotBlank(people.getIdCardPic()) &&
                    !people.getIdCardPic().contains("http")) {
                people.setIdCardPic(FilePropertiesUtil.getHttpUrl() + people.getIdCardPic());
            }
        }
        return people;
    }

    @Override
    public List<FrequencyRecordDTO> frequencyPersonPage(FrequencyRecordQuery para, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(para.getUserIds());
        para.setAmount(PropertiesUtil.getFrequencyPersonAmount());
        if (para.getEndTime() != null) {
            para.setEndTime(DateUtil.getEndDayOfSomeDay(para.getEndTime()));
        }
        List<FrequencyRecordDTO> list = this.frequencyRecordMapper.pages(para);
        int rowNum = pageSize * (currentPage - 1);
        for (FrequencyRecordDTO fDto : list) {
            rowNum++;
            fDto.setRowNum(Integer.valueOf(rowNum));
        }
        return list;
    }

    /**
     * 高频陌生人处置
     * @param frequencyRecord
     * @return
     */
    @Override
    public boolean frequencyPersonProcess(FrequencyRecord frequencyRecord) {
        boolean flag = false;

        flag = (this.frequencyRecordMapper.update(frequencyRecord) > 0);
        AddPerson addPerson = new AddPerson();
        addPerson.setId(frequencyRecord.getAddPersonId());
        addPerson.setState(NumberConstant.FOUR);
        addPerson.setLabel(frequencyRecord.getLabel());

        return (this.addPersonMapper.update(addPerson) > 0);
    }

    @Override
    public List<SpecialPersonDTO> specialPersonPage(SpecialPersonQuery para, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(para.getUserIds());
        para.setDays(PropertiesUtil.getOldManDays());
        if (para.getEndTime() != null) {
            para.setEndTime(DateUtil.getEndDayOfSomeDay(para.getEndTime()));
        }
        List<SpecialPersonDTO> list = this.specialPersonMapper.pages(para);
        int rowNum = pageSize * (currentPage - 1);
        for (SpecialPersonDTO fDto : list) {
            rowNum++;
            fDto.setRowNum(Integer.valueOf(rowNum));
            String idCardPic = fDto.getIdCardPic();
            if (StringUtils.isNotBlank(idCardPic) && !idCardPic.contains("http")) {
                fDto.setIdCardPic(FilePropertiesUtil.getHttpUrl() + idCardPic);
            }
        }
        return list;
    }

    /**
     * 高龄老人处置
     * @param specialPerson
     * @return
     * @throws Exception
     */
    @Override
    public boolean specialPersonProcess(SpecialPerson specialPerson) throws Exception {
        boolean flag = false;
        SpecialPerson sp = this.specialPersonMapper.selectById(specialPerson.getId());

        flag = (this.specialPersonMapper.update(specialPerson) > CommonConstant.COMMON_0);

        LeavePerson leavePerson = new LeavePerson();
        leavePerson.setId(sp.getRecordId());
        leavePerson.setState(NumberConstant.FOUR);
        flag = (this.leavePersonMapper.update(leavePerson) > CommonConstant.COMMON_0);

        if (CommonConstant.COMMON_2 == specialPerson.getState()) {
            People people = new People();
            people.setPeopleId(sp.getPeopleId());
            people.setIsLeave(NumberConstant.ONE);
            people.setLeaveTime(System.currentTimeMillis());
            flag = (this.peopleMapper.updateByPeopleId(people).intValue() > CommonConstant.COMMON_0
                    .intValue());
        }
        return flag;
    }

    @Override
    public boolean batchInsertFacePeople(List<FacedbfaceVO> facedbfaceVOs) {
        boolean flag = false;
        String villageCode = UUIDUtils.getRangeString(20);
        People people = null;
        long current = System.currentTimeMillis();
        for (FacedbfaceVO facedbfaceVO : facedbfaceVOs) {

            facedbfaceVO.setFacedbId(PropertiesUtil.getFacedbId());
            String facedbfaceId = facedbfaceInsert(facedbfaceVO);
            if (!StringUtils.isBlank(facedbfaceId)) {
                people = new People();
                people.setPeopleId(facedbfaceVO.getPeopleId());
                people.setVillageCode(villageCode);
                people.setPeopleType(Enums.PeopleType.PEOPLETYPE_RESIDENCE.getCode());
                people.setCredentialType(Integer.valueOf(facedbfaceVO.getCardType()));
                people.setCredentialNo(facedbfaceVO.getCardId());
                people.setPeopleName(facedbfaceVO.getName());
                people.setPhoneNo(facedbfaceVO.getPhoneNo());
                people.setNation(facedbfaceVO.getNation());
                people.setSource(Enums.peopleSource.OTHER.getCode());
                people.setJsonData(facedbfaceVO.getJsonData());
                people.setUpdateTime(current);
                people.setCreateTime(current);
                flag = (this.peopleMapper.insertSelective(people) > CommonConstant.COMMON_0.intValue());
                if (flag) {
                    flag = (this.peopleMapper.insertFacedbfaceInfo(facedbfaceVO.getPeopleId(), facedbfaceId) > CommonConstant.COMMON_0);
                }
                continue;
            }
            LOG.error(facedbfaceVO.getName() + facedbfaceVO.getCardId() + "入库失败");
        }

        return flag;
    }

    @Override
    public String facedbfaceInsert(FacedbfaceVO facedbfaceVO) {
        String facedbfaceId = "";

        facedbfaceVO.setCardType(String.valueOf(NumberConstant.ONE_HUNDRED_AND_FIFTY_NINE));
        JSONObject jsonObject = this.accessService
                .facedbfaceInsert(JsonUtils.getFastjsonFromObject(facedbfaceVO));
        String code = jsonObject.getString("code");
        if ("00000000".equals(code)) {
            facedbfaceId = jsonObject.getString("data");
        }
        return facedbfaceId;
    }

    @Override
    public List<FrequencyRecord> analysisFrequeryRecord(AddPersonDetailQuery addPersonDetailQuery) {
        return this.frequencyRecordMapper.analysisFrequeryRecord(addPersonDetailQuery);
    }

    @Override
    public FrequencyRecord findFrequencyRecordById(String addPersonId) {
        return this.frequencyRecordMapper.getFrequencyRecordById(addPersonId);
    }

    @Override
    public int batchCompareFrequencyRecord(List<FrequencyRecord> frequencyRecords) {
        return this.frequencyRecordMapper.batchCompareFrequencyRecord(frequencyRecords);
    }

    /**
     * 疑似新增人员对用设备查询
     * @param addPersonId
     * @return
     */
    @Override
    public List<String> queryAddPersonDetCaptureIds(String addPersonId) {
        return this.addPersonMapper.queryAddPersonDetCaptureIds(addPersonId);
    }

    /**
     * 夜间高频人员对应设备查询
     * @param frequencyNightPersonId
     * @return
     */
    @Override
    public List<String> queryFrequencyNightPersonDetCaptureIds(String frequencyNightPersonId) {
        return this.frequencyNightPersonMapper.queryFrequencyNightPersonDetCaptureIds(frequencyNightPersonId);
    }

    /**
     * 敏感通行人员对应设备查询
     * @param sensitivePersonId
     * @return
     */
    @Override
    public List<String> querySensitivePersonDetCaptureIds(String sensitivePersonId) {
        return this.sensitivePersonMapper.querySensitivePersonDetCaptureIds(sensitivePersonId);
    }

    /**
     * 夜间高频人员分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<FrequencyNightDTO> frequencyNightPage(FrequencyNightQuery param, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(param.getUserIds());
        //获取夜间高频人员判断次数
        param.setAmount(PropertiesUtil.getFrequencyNightAmount());
//        if (param.getEndTime() != null) {
//            param.setEndTime(DateUtil.getEndDayOfSomeDay(param.getEndTime()));
//        }
        //夜间高频人员查询
        List<FrequencyNightDTO> list = this.analysisMapper.frequencyNightPage(param);
        int rowNum = pageSize * (currentPage - 1);
        for (FrequencyNightDTO frequencyNightDTO : list) {
            rowNum++;
            frequencyNightDTO.setRowNum(rowNum);
            String capturePath = frequencyNightDTO.getCapturePath();
            if (StringUtils.isNotBlank(capturePath) && !capturePath.contains("http")) {
                frequencyNightDTO.setCapturePath(FilePropertiesUtil.getHttpUrl() + capturePath);
            }
        }
        return list;
    }

    /**
     * 长时间逗留人员分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<LongtimeStayDTO> longtimeStayPage(LongtimeStayQuery param, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(param.getUserIds());
        //获取长时间逗留人员判断时间
        param.setStayTime(PropertiesUtil.getLongtimeStayTime());
//        if (param.getEndTime() != null) {
//            param.setEndTime(DateUtil.getEndDayOfSomeDay(param.getEndTime()));
//        }
        //长时间逗留人员查询
        List<LongtimeStayDTO> list = this.analysisMapper.longtimeStayPage(param);
        int rowNum = pageSize * (currentPage - 1);
        for (LongtimeStayDTO longtimeStayDTO : list) {
            rowNum++;
            longtimeStayDTO.setRowNum(rowNum);
            String capturePath = longtimeStayDTO.getCapturePath();
            if (StringUtils.isNotBlank(capturePath) && !capturePath.contains("http")) {
                longtimeStayDTO.setCapturePath(FilePropertiesUtil.getHttpUrl() + capturePath);
            }
        }
        return list;
    }

    /**
     * 敏感通行人员分页查询
     * @param param
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<SensitiveTrafficDTO> sensitiveTrafficPage(SensitiveTrafficQuery param, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        User user = new User();
        user.setUserId(param.getUserIds());
        //获取敏感通行人员判断天数
        param.setAmount(PropertiesUtil.getSensitiveTraffic());
//        if (param.getEndTime() != null) {
//            param.setEndTime(DateUtil.getEndDayOfSomeDay(param.getEndTime()));
//        }
        //敏感通行人员查询
        List<SensitiveTrafficDTO> list = this.analysisMapper.sensitiveTrafficPage(param);
        int rowNum = pageSize * (currentPage - 1);
        for (SensitiveTrafficDTO sensitiveTrafficDTO : list) {
            rowNum++;
            sensitiveTrafficDTO.setRowNum(rowNum);
            String capturePath = sensitiveTrafficDTO.getCapturePath();
            if (StringUtils.isNotBlank(capturePath) && !capturePath.contains("http")) {
                sensitiveTrafficDTO.setCapturePath(FilePropertiesUtil.getHttpUrl() + capturePath);
            }
        }
        return list;
    }

    /**
     * 夜间高频处置
     * @param frequencyNightPerson
     * @return
     */
    @Override
    public boolean frequencyNightPersonProcess(FrequencyNightPerson frequencyNightPerson) {
        //夜间高频人员更新
        return (this.frequencyNightPersonMapper.update(frequencyNightPerson) > 0);
    }

    /**
     * 敏感通行处置
     * @param sensitivePerson
     * @return
     */
    @Override
    public boolean sensitivePersonProcess(SensitivePerson sensitivePerson) {
        //敏感通行人员跟信息
        return (this.sensitivePersonMapper.update(sensitivePerson) > 0);
    }

    /**
     * 长时间逗留处置
     * @param longtimeStayPerson
     * @return
     */
    @Override
    public boolean longtimeStayPersonProcess(LongtimeStayPerson longtimeStayPerson) {
        //长时间逗留人员更新
        return (this.longtimeStayPersonMapper.update(longtimeStayPerson) > 0);
    }

    /**
     * 疑似离开处置结果查询
     * @param id
     * @return
     */
    @Override
    public LeavePerson findLeavePersonResult(String id) {
        LeavePerson leavePerson = this.leavePersonMapper.findLeavePersonResult(id);
        return leavePerson;
    }

    /**
     * 夜间高频处置结果查询
     * @param id
     * @return
     */
    @Override
    public FrequencyNightPerson findFrequencyNightPersonResult(String id) {
        FrequencyNightPerson frequencyNightPerson = this.frequencyNightPersonMapper.findFrequencyNightPersonResult(id);
        if (frequencyNightPerson != null){
            String label = frequencyNightPerson.getLabel();
            //取得人员标签
            String labels = this.queryLabel(label);
            frequencyNightPerson.setLabel(labels);
        }
        return frequencyNightPerson;
    }

    /**
     * 敏感通行处置结果查询
     * @param id
     * @return
     */
    @Override
    public SensitivePerson findSensitivePersonResult(String id) {
        SensitivePerson sensitivePerson = this.sensitivePersonMapper.findSensitivePersonResult(id);
        if (sensitivePerson != null) {
            String label = sensitivePerson.getLabel();
            //取得人员标签
            String labels = this.queryLabel(label);
            sensitivePerson.setLabel(labels);
        }
        return sensitivePerson;
    }

    /**
     * 长时间逗留处置结果查询
     * @param id
     * @return
     */
    @Override
    public LongtimeStayPerson findLongtimeStayPersonResult(String id) {
        LongtimeStayPerson longtimeStayPerson = this.longtimeStayPersonMapper.findLongtimeStayPersonResult(id);
        if (longtimeStayPerson != null){
            String label = longtimeStayPerson.getLabel();
            //取得人员标签
            String labels = this.queryLabel(label);
            longtimeStayPerson.setLabel(labels);
        }
        return longtimeStayPerson;
    }

    /**
     * 人员标签查询
     * @param label
     * @return
     */
    public String queryLabel(String label){
        String tempAll = "";
        if (StringUtils.isNotBlank(label)) {
            String[] labels = label.trim().split(",");
            if (labels.length > 0) {
                for (int i = 0; i < labels.length; i++) {
                    //取得人员标签内容
                    String temp = this.frequencyNightPersonMapper.getLabel(labels[i]);
                    tempAll += temp + ",";
                }
            }
            if ("".equals(tempAll)) {
                tempAll.substring(0,tempAll.length() - 1);
            }
        }
        return tempAll;
    }
}
