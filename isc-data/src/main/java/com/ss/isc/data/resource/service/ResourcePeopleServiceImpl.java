package com.ss.isc.data.resource.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.collect.client.IPeopleService;
import com.ss.isc.data.collect.common.model.AddPerson;
import com.ss.isc.data.collect.common.model.Facedb;
import com.ss.isc.data.collect.common.model.FacedbPeople;
import com.ss.isc.data.collect.mapper.FacedbMapper;
import com.ss.isc.data.collect.mapper.FacedbPeopleMapper;
import com.ss.isc.data.resource.client.IResourcePeopleService;
import com.ss.isc.data.resource.common.model.People;
import com.ss.isc.data.resource.common.model.PeopleCollection;
import com.ss.isc.data.resource.common.model.PeopleFacedbFace;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.isc.data.resource.common.web.FacedbfaceVO;
import com.ss.isc.data.resource.common.web.PeopleHouseVO;
import com.ss.isc.data.resource.common.web.PeopleQueryVO;
import com.ss.isc.data.resource.common.web.PeopleVO;
import com.ss.isc.data.resource.mapper.PeopleCollectionMapper;
import com.ss.isc.data.resource.mapper.ResourcePeopleMapper;
import com.ss.isc.data.resource.mapper.VillageMapper;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.IDCardUtil;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.file.FileConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.isc.util.file.FileUtil;
import com.ss.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ResourcePeopleServiceImpl：实有人口资源service接口实现
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ResourcePeopleServiceImpl extends BaseServiceImpl implements IResourcePeopleService {

    public static final Log LOG = LogFactory.getLog(ResourcePeopleServiceImpl.class);

    @Autowired
    private ResourcePeopleMapper peopleMapper;
    @Resource
    private IAccessService accessService;
    @Autowired
    private PeopleCollectionMapper peopleCollectionMapper;
    @Resource
    private FacedbPeopleMapper facedbPeopleMapper;
    @Resource
    private VillageMapper villageMapper;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private IPeopleService peopleService;

    /**
     * 查询实有人口分页列表
     * @param para
     * @return
     */
    @Override
    public List<People> page(PeopleQueryVO para) {
        // 初始化分页条件
        PageHelper.startPage(para.getCurrentPage(), para.getPageSize());
        // 人员标签条件
        if (StringUtils.isNotEmpty(para.getLabelStr())) {
            para.setLabelArr(para.getLabelStr().split(CommonConstant.SPLIT_COMMA));
        }
        // 查询列表
        List<People> list = this.peopleMapper.page(para);
        // 查询用户收藏列表
        PeopleCollection peopleCollection = new PeopleCollection();
        peopleCollection.setUserId(para.getUserIds());
        List<PeopleCollection> peopleCollections = peopleCollectionMapper.peopleCollectionList(peopleCollection);
        String collectPeople = null;
        if (CollectionUtils.isNotEmpty(peopleCollections)) {
            collectPeople = StringUtils.join(peopleCollections.stream().map(PeopleCollection::getPeopleId).collect(Collectors.toList()), CommonConstant.SPLIT_COMMA);
        }
        // 结果处理
        for (People entity : list) {
            // 照片路径处理
            entity.setIdCardPicFull(dealPicUrl(entity.getIdCardPic()));
            entity.setFacePicFull(dealPicUrl(entity.getFacePic()));
            // 人员标签处理
            entity.setLabel(Enums.PeopleLabel.getName(entity.getLabel()));
            // 收藏状态
            entity.setCollectStatus((StringUtils.isBlank(collectPeople) || !collectPeople.contains(entity.getPeopleId())) ? CommonConstant.STATUS_CANCEL_COLLECT : CommonConstant.STATUS_COLLECT);
        }
        return list;
    }

    /**
     * 新增实有人口
     * @param people
     * @return
     */
    @Override
    public String save(People people) throws Exception {

        // 查询小区的欧神人像库ID
        String facedbId = this.peopleMapper.selectFacedbId(people.getVillageCode());

        // 欧神人像集数据处理
        String facedbfaceId = "";
        String facePic = StringUtils.isBlank(people.getFacePic()) ? people.getIdCardPic() : people.getFacePic();
        if (StringUtils.isNotBlank(facedbId) && StringUtils.isNotBlank(facePic)) {
            FacedbfaceVO facedbfaceVO = new FacedbfaceVO();
            facedbfaceVO.setCardId(people.getCredentialNo());
            facedbfaceVO.setCardType(CommonConstant.OCEAN_CARD_TYPE_ID);
            facedbfaceVO.setName(people.getPeopleName());
            facedbfaceVO.setGender(people.getGenderCode());
            facedbfaceVO.setAddress(people.getResidenceAddress());
            facedbfaceVO.setNation(people.getNation());
            facedbfaceVO.setBirthday(people.getBirthDate().replaceAll("-", ""));
            facedbfaceVO.setPhoneNo(people.getPhoneNo());
            facedbfaceVO.setFacedbId(facedbId);
            facedbfaceVO.setFaceImg(FileUtil.getBase64ByUrl(dealPicUrl(facePic)));
            if (StringUtils.isNotBlank(people.getIdCardPic())) {
                facedbfaceVO.setCardImg(FileUtil.getBase64ByUrl(dealPicUrl(people.getIdCardPic())));
            }
            // 调用欧神新增人像集
            facedbfaceId = facedbfaceInsert(facedbfaceVO);
            if (StringUtils.isBlank(facedbfaceId)) {
                return CommonConstant.ERROR_EN_OCEAN_CODE;
            }
        }

        // 社区数据处理
        String peopleId = UUIDUtils.getUUID();
        long currentTime = System.currentTimeMillis();
        if (people.getSource() == null || Enums.peopleSource.getName((short)people.getSource().intValue()) == null) {
            // 默认人口库数据
            people.setSource((int) Enums.peopleSource.PEOPLE_LIBRARY.getCode());
        }
        people.setCreateTime(currentTime);
        people.setUpdateTime(currentTime);
        people.setIsLeave(NumberConstant.ZERO);
        people.setPeopleId(peopleId);
        // 人口数据/人口-人像库关联数据
        Facedb ssFacedb = new Facedb();
        ssFacedb.setFacedbId(facedbId);
        ssFacedb.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        ssFacedb = facedbMapper.selectOne(ssFacedb);
        boolean facedbRes = facedbPeopleMapper.insertSelective(new FacedbPeople(peopleId, ssFacedb.getId(), facedbfaceId, currentTime, people.getUserIds(), true)) > 0,
                peopleRes = peopleMapper.insertSelective(people) > 0,
                addPerRes = true;
        if (Enums.peopleSource.APPERCEIVE_DISCOVER.getCode() == people.getSource()) {
            // 疑似新增处置
            People insertPeople = new People();
            insertPeople.setPeopleId(peopleId);
            insertPeople = this.selectOne(insertPeople);
            AddPerson addPerson = new AddPerson(people.getRecordId(), CommonConstant.COMMON_2);
            addPerson.setPeopleId(String.valueOf(insertPeople.getId()));
            addPerson.setUserIds(people.getUserIds());
            addPerRes = CommonConstant.SUCCESS_EN_CODE.equals(peopleService.addPeopleProcess(addPerson));
        }
        if (!(facedbRes && addPerRes && peopleRes)) {
            // 欧神回滚
            facedbfaceDelete(new String[]{facedbfaceId});
            // 社区回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonConstant.ERROR_EN_CODE;
        }
        return CommonConstant.SUCCESS_EN_CODE;
    }

    /**
     * 修改实有人口
     * @param people
     * @return
     */
    @Override
    public String edit(People people) throws Exception {

        String oceanOpr = "";

        // 查询修改前数据
        People dbPeople = peopleMapper.selectById(people.getId());
        String facedbfaceId = dbPeople.getFacedbfaceId();
        String orgFacePic = StringUtils.isBlank(dbPeople.getFacePic()) ? dbPeople.getIdCardPic() : dbPeople.getFacePic();
        String facedbId = peopleMapper.selectFacedbId(people.getVillageCode());

        // 欧神人像集数据处理
        String facePic = StringUtils.isBlank(people.getFacePic()) ? people.getIdCardPic() : people.getFacePic();
        if (StringUtils.isBlank(facePic) && StringUtils.isNotBlank(facedbfaceId)) {
            oceanOpr = "delete";
            // 未上传人像但是原先有人像集信息：即删除人像集
            if (!facedbfaceDelete(new String[]{facedbfaceId})) {
                return CommonConstant.ERROR_EN_OCEAN_CODE;
            }
        }
        if (StringUtils.isNotBlank(facePic) && StringUtils.isNotBlank(facedbId)) {
            // 上传了人像：具备新增人像集/更新人像集的条件，封装共有参数
            FacedbfaceVO facedbfaceVO = new FacedbfaceVO();
            facedbfaceVO.setCardId(people.getCredentialNo());
            facedbfaceVO.setCardType(CommonConstant.OCEAN_CARD_TYPE_ID);
            facedbfaceVO.setName(people.getPeopleName());
            facedbfaceVO.setGender(people.getGenderCode());
            facedbfaceVO.setAddress(people.getResidenceAddress());
            facedbfaceVO.setNation(people.getNation());
            facedbfaceVO.setBirthday(people.getBirthDate().replaceAll("-", ""));
            facedbfaceVO.setPhoneNo(people.getPhoneNo());
            facedbfaceVO.setFacedbId(facedbId);
            facedbfaceVO.setFaceImg(FileUtil.getBase64ByUrl(dealPicUrl(facePic)));
            if (StringUtils.isNotBlank(people.getIdCardPic())) {
                facedbfaceVO.setCardImg(FileUtil.getBase64ByUrl(dealPicUrl(people.getIdCardPic())));
            }
            if (StringUtils.isBlank(facedbfaceId)) {
                oceanOpr = "insert";
                // 新增欧神人像集
                facedbfaceId = facedbfaceInsert(facedbfaceVO);
                if (StringUtils.isBlank(facedbfaceId)) {
                    return CommonConstant.ERROR_EN_OCEAN_CODE;
                }
            } else if (!orgFacePic.equals(facePic)) {
                oceanOpr = "update";
                // 修改欧神人像集
                facedbfaceVO.setId(facedbfaceId);
                if (!facedbfaceUpdate(facedbfaceVO)) {
                    return CommonConstant.ERROR_EN_OCEAN_CODE;
                }
            }
        }

        // 社区数据处理
        long currentTime = System.currentTimeMillis();
        people.setUpdateTime(currentTime);
        people.setIsLeave(NumberConstant.ZERO);
        // 人口数据/人口-人像库关联数据
        Facedb ssFacedb = new Facedb();
        ssFacedb.setFacedbId(facedbId);
        ssFacedb.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        ssFacedb = facedbMapper.selectOne(ssFacedb);
        boolean facedbRes = facedbPeopleMapper.updateByPrimaryKeySelective(new FacedbPeople(dbPeople.getSsFacedbPeopleId(), ssFacedb.getId(), facedbfaceId, currentTime, people.getUserIds(), false)) > 0,
                peopleRes = peopleMapper.updateByPrimaryKeySelective(people) > 0;

        if (!(facedbRes && peopleRes)) {
            // 欧神回滚
            if ("insert".equals(oceanOpr)) {
                facedbfaceDelete(new String[]{facedbfaceId});
            }
            // 社区回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonConstant.ERROR_EN_CODE;
        }
        return CommonConstant.SUCCESS_EN_CODE;
    }

    /**
     * 查询实有人口详情
     * @param id
     * @return
     */
    @Override
    public People get(Integer id) {
        People people = this.peopleMapper.selectById(id);
        if (people != null) {
            // 照片路径处理
            people.setIdCardPicFull(dealPicUrl(people.getIdCardPic()));
            people.setFacePicFull(dealPicUrl(people.getFacePic()));
        }
        return people;
    }

    /**
     * 删除实有人口
     * @param param 批量删除时多个id以","分隔
     * @return
     */
    @Override
    public String delete(PeopleVO param) {
        String idArrStr = param.getIdArr();
        // 删除人口的同时删除欧神人像集
        List<String> facedbfaceIds = new ArrayList<>();
        List<String> ssFacedbPeopleIds = new ArrayList<>();
        List<People> peopleList = this.peopleMapper.selectByIdArrStr(idArrStr);

        if (CollectionUtils.isNotEmpty(peopleList)) {
            for (People people : peopleList) {
                if (StringUtils.isNotBlank(people.getFacedbfaceId())) {
                    facedbfaceIds.add(people.getFacedbfaceId());
                }
                if (StringUtils.isNotBlank(people.getSsFacedbPeopleId())) {
                    ssFacedbPeopleIds.add(people.getSsFacedbPeopleId());
                }
            }
            if (CollectionUtils.isNotEmpty(facedbfaceIds)) {
                if(!facedbfaceDelete(facedbfaceIds.toArray(new String[]{}))){
                    return CommonConstant.ERROR_EN_CODE;
                }
            }
        }
        // 人像集成功删除后再删除人口信息（更新删除标识）
        FacedbPeople facedbPeople = new FacedbPeople(String.join(CommonConstant.SPLIT_COMMA, ssFacedbPeopleIds));
        facedbPeople.setDeleteTime(System.currentTimeMillis());
        facedbPeople.setDeleteUser(param.getUserIds());
        boolean facedbRes = facedbPeopleMapper.deleteByIdBatch(facedbPeople) == peopleList.size(),
                peopleRes = peopleMapper.deleteById(idArrStr) == peopleList.size();
        if (!(facedbRes && peopleRes)) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return CommonConstant.ERROR_EN_CODE;
        }
        return CommonConstant.SUCCESS_EN_CODE;
    }

    /**
     * 收藏/取消收藏实有人口
     * @param userIds 当前用户编号
     * @param peopleIds 实有人口编号
     * @param operationType 操作类型
     * @return 操作结果描述
     */
    @Override
    public String collect(String userIds, String peopleIds, Integer operationType) {
        try {
            // 判断操作类型
            if (operationType == CommonConstant.STATUS_COLLECT) {
                List<PeopleCollection> insertList = new ArrayList<>();
                PeopleCollection peopleCollection;
                // 收藏操作
                for (String peopleId : peopleIds.split(CommonConstant.SPLIT_COMMA)) {
                    peopleCollection = new PeopleCollection();
                    peopleCollection.setUserId(userIds);
                    peopleCollection.setPeopleId(peopleId);
                    // 若已在收藏中则不再新增
                    if (peopleCollectionMapper.selectByUserIdAndPeopleId(peopleCollection) != null) {
                        continue;
                    }
                    insertList.add(peopleCollection);
                }
                peopleCollectionMapper.insertList(insertList);
            } else {
                // 取消收藏
                PeopleCollection peopleCollection = new PeopleCollection();
                peopleCollection.setUserId(userIds);
                peopleCollection.setPeopleId(peopleIds);
                peopleCollectionMapper.batchCancelCollect(peopleCollection);
            }
        } catch (Exception e) {
            String operation = operationType == CommonConstant.STATUS_COLLECT ? "收藏" : "取消收藏";
            LOG.error(e.toString(), e);
            LOG.error(operation + "失败");
            return CommonConstant.ERROR_EN_CODE;
        }
        return CommonConstant.SUCCESS_EN_CODE;
    }

    /**
     * 根据属性参数查询唯一人口对象
     * @param people
     * @return
     */
    @Override
    public People selectOne(People people) {
        people.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        return peopleMapper.selectOne(people);
    }

    /**
     * 根据属性参数查询人口列表
     * @param people
     * @return
     */
    @Override
    public List<People> selectList(People people) {
        people.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        return peopleMapper.select(people);
    }

    @Override
    public Map<String, String> batchImport(List<People> tempList, Map<String, String> imagePaths) {
        Map<String, String> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        boolean ckeckFlag = true;
        StringBuilder failureMsg = new StringBuilder();
        int index = 2;
        List<People> insertList = new ArrayList<People>();
        List<People> updateList = new ArrayList<People>();
        List<PeopleFacedbFace> facedbFaces = new ArrayList<PeopleFacedbFace>();

        String facedbId = "";

        String villageCode = "";
        long currentTime = System.currentTimeMillis();
        for (People people : tempList) {
            index++;
            people.setCreateTime(currentTime);
            people.setUpdateTime(currentTime);
            people.setIsLeave(CommonConstant.COMMON_0);

            if (StringUtils.isBlank(people.getVillageCode()) && people.getPeopleType() == null && people
                    .getCredentialType() == null && StringUtils.isBlank(people.getCredentialNo())) {
                break;
            }
            if (StringUtils.isBlank(people.getVillageCode())) {
                failureMsg.append(index + "行“小区编号”列数据格式非法。");
                ckeckFlag = false;
                break;
            }
            if (people.getPeopleType() == null) {
                failureMsg.append(index + "行“户籍类别”列数据格式非法。");
                ckeckFlag = false;
                break;
            }
            if (people.getCredentialType() == null) {
                failureMsg.append(index + "行“证件类型”列数据格式非法。");
                ckeckFlag = false;
                break;
            }
            if (StringUtils.isBlank(people.getCredentialNo())) {
                failureMsg.append(index + "行“证件号码”列数据格式非法。");
                ckeckFlag = false;

                break;
            }
            String streeCode = people.getStreetCode();
            String domicileCode = people.getDomicileCode();
            if (StringUtils.isNotBlank(streeCode) && streeCode.length() > 9) {
                people.setStreetCode(
                        streeCode.substring(NumberConstant.ZERO.intValue(), NumberConstant.NINE.intValue()));
            }
            if (StringUtils.isNotBlank(domicileCode) && domicileCode.length() > 6) {
                people.setDomicileCode(
                        domicileCode.substring(NumberConstant.ZERO.intValue(), NumberConstant.SIX.intValue()));
            }

            people.setBirthDate(IDCardUtil.parseBirthDay(people.getCredentialNo()));

            People entity = this.peopleMapper.check(people);

            if (entity == null) {
                String peopleId = UUIDUtils.getUUID();
                people.setPeopleId(peopleId);
                if (imagePaths.containsKey(people.getCredentialNo())) {

                    String cardImageBase64 = FileUtil
                            .encryptToBase64((String) imagePaths.get(people.getCredentialNo()));
                    if (StringUtils.isNotBlank(cardImageBase64)) {
                        people.setIdCardPicBaseString(cardImageBase64);
                        people.setFacePicBaseString(cardImageBase64);
                        if (!villageCode.equals(people.getVillageCode())) {
                            villageCode = people.getVillageCode();
                            String peopleFacedbId = this.peopleMapper.selectFacedbId(villageCode);
                            if (StringUtils.isNotBlank(peopleFacedbId)) {
                                facedbId = peopleFacedbId;
                                people.setFacedbId(facedbId);
                            }
                        } else {

                            people.setFacedbId(facedbId);
                        }

                        if (StringUtils.isNotBlank(people.getFacedbId())) {
                            PeopleFacedbFace peopleFacedbFace = peopleImportFaceInsert(people);
                            if (peopleFacedbFace != null) {
                                facedbFaces.add(peopleFacedbFace);
                                people.setIdCardPic(peopleFacedbFace.getIdCardPic());
                                people.setFacePic(peopleFacedbFace.getFacePic());
                            } else {

                                ckeckFlag = false;
                                failureMsg.append("欧神人像集接口调用失败");
                                break;
                            }
                        }
                    }
                }
                insertList.add(people);
                continue;
            }
            updateList.add(people);
        }

        if (ckeckFlag) {
            if (insertList.size() > 0) {
                batchInsertPeople(insertList);
            }
            if (updateList.size() > 0) {
                batchUpdatePeople(updateList);
            }
            if (facedbFaces.size() > 0) {
                this.peopleMapper.batchInsertFaceInfo(facedbFaces);
            }
            map.put("result", "success");
            map.put("message",
                    "成功导入" + (insertList.size() + updateList.size()) + "条数据，新增" + insertList.size() + "条，更新"
                            + updateList
                            .size() + "条");
        } else {

            map.put("result", "failed");
            map.put("message", "导入失败：失败原因" + failureMsg.toString());
        }
        return map;
    }


    private boolean batchInsertPeople(List<People> list) {
        int count = NumberConstant.ZERO.intValue();

        int batchCount = 1500;
        int batchLastIndex = batchCount;
        List<List<People>> shareList = new ArrayList<List<People>>();
        for (int index = 0; index < list.size(); ) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                shareList.add(list.subList(index, batchLastIndex));

                break;
            }
            shareList.add(list.subList(index, batchLastIndex));

            index = batchLastIndex;
            batchLastIndex = index + batchCount - 1;
        }

        if (CollectionUtils.isNotEmpty(shareList)) {
            for (List<People> subList : shareList) {

                count = this.peopleMapper.insertList(subList);
                if (count == CommonConstant.COMMON_0.intValue()) {
                    break;
                }
            }
        }
        return (count > CommonConstant.COMMON_0.intValue());
    }


    private boolean batchUpdatePeople(List<People> list) {
        int count = NumberConstant.ZERO.intValue();

        int batchCount = 1500;
        int batchLastIndex = batchCount;
        List<List<People>> shareList = new ArrayList<List<People>>();
        for (int index = 0; index < list.size(); ) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                shareList.add(list.subList(index, batchLastIndex));

                break;
            }
            shareList.add(list.subList(index, batchLastIndex));

            index = batchLastIndex;
            batchLastIndex = index + batchCount - 1;
        }

        if (CollectionUtils.isNotEmpty(shareList)) {
            for (List<People> subList : shareList) {

                count = this.peopleMapper.updateBatch(subList);
                if (count == CommonConstant.COMMON_0.intValue()) {
                    break;
                }
            }
        }
        return (count > CommonConstant.COMMON_0.intValue());
    }


    public PeopleFacedbFace peopleImportFaceInsert(People people) {
        LOG.info("欧神导入请求接口开始" + DateUtil.getCurrentSqlTimestampString());
        PeopleFacedbFace peopleFacedbFace = null;
        FacedbfaceVO facedbfaceVO = new FacedbfaceVO();
        facedbfaceVO.setCardId(people.getCredentialNo());
        facedbfaceVO.setName(people.getPeopleName());
        facedbfaceVO.setGender(Integer.valueOf(people.getGenderCode().intValue()));
        facedbfaceVO.setNation(people.getNation());
        facedbfaceVO.setBirthday(people.getBirthDate().replaceAll("-", ""));
        facedbfaceVO.setPhoneNo(people.getPhoneNo());
        facedbfaceVO.setFacedbId(people.getFacedbId());
        facedbfaceVO.setFaceImg(people.getFacePicBaseString());
        facedbfaceVO.setCardImg(people.getIdCardPicBaseString());

        String faceId = facedbfaceInsert(facedbfaceVO);
        if (StringUtils.isNotBlank(faceId)) {

            JSONObject jsonObject = facedbfaceGet(faceId);
            if (jsonObject != null) {
                peopleFacedbFace = new PeopleFacedbFace();
                peopleFacedbFace.setPeopleId(people.getPeopleId());
                peopleFacedbFace.setFacedbfaceId(faceId);
                peopleFacedbFace.setFacePic(jsonObject.getString("facePathFull"));
                peopleFacedbFace.setIdCardPic(jsonObject.getString("cardPathFull"));
            }
        }
        LOG.info("欧神导入请求接口结束" + DateUtil.getCurrentSqlTimestampString());
        return peopleFacedbFace;
    }

    /**
     * 调用欧神接口新增人像集信息
     * @param facedbfaceVO
     * @return 新增的人像集ID
     */
    public String facedbfaceInsert(FacedbfaceVO facedbfaceVO) {
        String paraJson = JsonUtils.getFastjsonFromObject(facedbfaceVO);
        LOG.info("欧神人像集新增请求开始" + DateUtil.getCurrentSqlTimestampString());
        JSONObject jsonObject = this.accessService.facedbfaceInsert(paraJson);
        LOG.info("欧神人像集新增返回结果" + jsonObject + "时间" + DateUtil.getCurrentSqlTimestampString());
        if (!StringUtils.checkSuccess(jsonObject)) {
            return "";
        }
        return jsonObject.getString("data");
    }

    /**
     * 调用欧神接口查询人像集信息
     * @param facedbfaceId
     * @return
     */
    public JSONObject facedbfaceGet(String facedbfaceId) {
        JSONObject returnObj = null;
        Map<String, String> para = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        para.put("id", facedbfaceId);
        String paraJson = JsonUtils.getFastjsonFromObject(para);
        LOG.info("欧神人像集信息查看请求参数" + paraJson);
        JSONObject jsonObject = this.accessService.facedbfaceGet(paraJson);
        LOG.info("欧神人像集信息查询返回结果" + jsonObject);
        if (StringUtils.checkSuccess(jsonObject)) {
            returnObj = jsonObject.getJSONObject("data");
        }
        return returnObj;
    }

    /**
     * 调用欧神接口更新人像集信息
     * @param facedbfaceVO
     * @return
     */
    public boolean facedbfaceUpdate(FacedbfaceVO facedbfaceVO) {
        String paraJson = JsonUtils.getFastjsonFromObject(facedbfaceVO);
        LOG.info("欧神人像集修改请求开始" + DateUtil.getCurrentSqlTimestampString());
        JSONObject jsonObject = this.accessService.facedbfaceUpdate(paraJson);
        LOG.info("欧神人像集修改返回结果" + jsonObject);
        return StringUtils.checkSuccess(jsonObject);
    }

    /**
     * 调用欧神接口删除人像集信息
     * @param facedbFaceIds
     * @return
     */
    public boolean facedbfaceDelete(String[] facedbFaceIds) {
        Map<String, String[]> para = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        para.put("facedbFaceIds", facedbFaceIds);
        String paraJson = JsonUtils.getFastjsonFromObject(para);
        LOG.info("欧神人像集删除请求参数" + paraJson);
        JSONObject jsonObject = this.accessService.facedbfaceDelete(paraJson);
        LOG.info("欧神人像集删除返回结果" + jsonObject);
        return StringUtils.checkSuccess(jsonObject);
    }

    /**
     * 查询人房关系列表
     * @param para
     * @return
     */
    @Override
    public List<People> peopleList(PeopleHouseVO para) {
        if (PropertiesUtil.getHousePeoplePageSize() != null) {
            para.setPageSize(PropertiesUtil.getHousePeoplePageSize());
        } else {
            para.setPageSize(NumberConstant.ONE_HUNDRED);
        }
        return this.peopleMapper.peopleList(para);
    }

    /**
     * 查询人车关系列表
     * @param para
     * @return
     */
    @Override
    public List<People> peopleVehicleList(PeopleVehicle para) {
        para.setPageSize(NumberConstant.ONE_HUNDRED);
        return this.peopleMapper.peopleVehicleList(para);
    }

    /**
     * 处理图片地址
     * @param origin 原始地址
     * @return 完整访问路径
     */
    private String dealPicUrl(String origin) {
        if (StringUtils.isBlank(origin)) {
            return "";
        }
        if (!origin.contains(FileConstant.FILE_HTTPADD)) {
            return FilePropertiesUtil.getHttpUrl() + origin;
        }
        return origin;
    }

}
