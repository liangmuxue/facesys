package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbPeople;
import com.ss.facesys.data.collect.common.web.FacedbPeopleVO;
import com.ss.facesys.data.collect.common.web.FacedbVO;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.collect.mapper.FacedbPeopleMapper;
import com.ss.facesys.data.resource.common.model.People;
import com.ss.facesys.data.resource.common.web.FacedbfaceVO;
import com.ss.facesys.data.resource.mapper.ResourcePeopleMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.file.FileUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FacedbServiceImpl
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class FacedbServiceImpl extends BaseServiceImpl implements IFacedbService {

    private Logger logger = LoggerFactory.getLogger(FacedbServiceImpl.class);

    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private FacedbPeopleMapper facedbPeopleMapper;
    @Resource
    private ResourcePeopleMapper resourcePeopleMapper;
    @Resource
    private IAccessService accessService;

    /**
     * 查询人像库列表
     * @param facedb
     * @return
     */
    @Override
    public List<Facedb> getFacedbList(Facedb facedb) {
        return facedbMapper.getFacedbList(facedb);
    }

    /**
     * 查询重点人员库分页列表
     * @param vo
     * @return
     */
    @Override
    public List<Facedb> getFacedbPage(FacedbVO vo) {
        // 初始化分页查询条件
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        Facedb facedb = new Facedb();
        BeanUtils.copyProperties(vo, facedb);
        return facedbMapper.getFacedbList(facedb);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     * @param facedb
     * @return
     */
    @Override
    public Facedb selectOne(Facedb facedb) {
        facedb.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        return facedbMapper.selectOne(facedb);
    }

    /**
     * 新增重点人员库
     * @param facedb
     * @return
     */
    @Override
    public Map<String, Object> insertFacedb(Facedb facedb) {
        Map<String, Object> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        resultMap.put(CommonConstant.SUCCESS_EN_CODE, true);
        // 新增欧神人像库
        this.logger.info("新增重点人员库：新增欧神人像库");
        JSONObject oceanResult = accessService.facedInsert(JSONObject.toJSONString(facedb));
        if (!StringUtils.checkSuccess(oceanResult)) {
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", oceanResult.getString("message"));
            this.logger.info("新增欧神人像库失败，失败信息：" + oceanResult.getString("message"));
            return resultMap;
        }
        // 新增社区人像库
        this.logger.info("新增重点人员库：新增社区人像库");
        Map<String, Object> idMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        String oceanId = oceanResult.getString("data");
        facedb.setFacedbId(oceanId);
        facedb.setCreateTime(System.currentTimeMillis());
        facedb.setCreateUser(facedb.getUserIds());
        try {
            facedbMapper.insertSelective(facedb);
        } catch (Exception e) {
            this.logger.error("新增重点人员库：新增社区人像库失败", e);
            JSONObject paramJson = new JSONObject();
            JSONArray facedbIds = new JSONArray();
            facedbIds.add(oceanId);
            paramJson.put("facedbIds", facedbIds);
            accessService.facedDelete(paramJson.toString());
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", "新增重点人员库：新增社区人像库失败：" + e.getMessage());
            return resultMap;
        }
        this.logger.info("新增重点人员库成功");
        // 获取社区人像库主键
        Facedb insert = new Facedb();
        insert.setName(facedb.getName());
        insert = facedbMapper.selectOne(insert);
        idMap.put("id", insert.getId());
        idMap.put("oceanId", oceanId);
        resultMap.put("data", idMap);
        return resultMap;
    }

    /**
     * 修改重点人员库信息
     * @param facedb
     * @return
     */
    @Override
    public Map<String, Object> updateFacedb(Facedb facedb) {
        Map<String, Object> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        resultMap.put(CommonConstant.SUCCESS_EN_CODE, true);
        Facedb original = facedbMapper.selectByPrimaryKey(facedb);
        // 修改欧神人像库
        this.logger.info("修改重点人员库：修改欧神人像库");
        JSONObject oceanParam = JSONObject.parseObject(JSONObject.toJSONString(facedb));
        oceanParam.put("id", original.getFacedbId());
        JSONObject oceanResult = accessService.updateFacedb(oceanParam.toJSONString());
        if (!StringUtils.checkSuccess(oceanResult)) {
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", oceanResult.getString("message"));
            this.logger.info("修改欧神人像库失败，失败信息：" + oceanResult.getString("message"));
            return resultMap;
        }
        // 修改社区人像库
        this.logger.info("修改重点人员库：修改社区人像库");
        facedb.setUpdateTime(System.currentTimeMillis());
        facedb.setUpdateUser(facedb.getUserIds());
        try {
            facedbMapper.updateByPrimaryKeySelective(facedb);
        } catch (Exception e) {
            this.logger.error("修改重点人员库：修改社区人像库失败", e);
            oceanParam = JSONObject.parseObject(JSON.toJSONString(original));
            oceanParam.put("id", original.getFacedbId());
            accessService.updateFacedb(oceanParam.toJSONString());
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", "修改重点人员库：修改社区人像库失败：" + e.getMessage());
            return resultMap;
        }
        this.logger.info("修改重点人员库成功");
        // 返回人像库编号信息
        Map<String, Object> idMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        idMap.put("id", facedb.getId());
        idMap.put("oceanId", original.getFacedbId());
        resultMap.put("data", idMap);
        return resultMap;
    }

    /**
     * 删除重点人员库
     * @param facedb
     * @return
     */
    @Override
    public void deleteFacedb(Facedb facedb) throws Exception {
        Facedb original = facedbMapper.selectByPrimaryKey(facedb);
        // 删除社区人像库
        this.logger.info("删除重点人员库：删除社区人像库");
        facedb.setDeleteTime(System.currentTimeMillis());
        facedb.setDeleteUser(facedb.getUserIds());
        facedb.setDeleteFlag(CommonConstant.DELETE_FLAG_DELETE);
        facedbMapper.updateByPrimaryKeySelective(facedb);
        // 删除欧神人像库
        this.logger.info("删除重点人员库：删除欧神人像库");
        JSONObject paramJson = new JSONObject();
        JSONArray facedbIds = new JSONArray();
        facedbIds.add(original.getFacedbId());
        paramJson.put("facedbIds", facedbIds);
        JSONObject oceanResult = accessService.facedDelete(paramJson.toString());
        if (!StringUtils.checkSuccess(oceanResult)) {
            throw new Exception("删除欧神人像库失败");
        }
    }

    /**
     * 查询重点人员列表
     * @param facedbPeople
     * @return
     */
    @Override
    public List<FacedbPeople> selectFacedbPeopleList(FacedbPeople facedbPeople) {
        facedbPeople.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
        return facedbPeopleMapper.select(facedbPeople);
    }

    /**
     * 查询重点人员分页列表
     * @param vo
     * @return
     */
    @Override
    public List<FacedbPeopleVO> getFacedbPeoplePage(FacedbPeopleVO vo) {
        // 初始化分页查询条件
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());
        List<FacedbPeopleVO> facedbPeopleList = facedbPeopleMapper.getFacedbPeopleList(vo);
        if (CollectionUtils.isNotEmpty(facedbPeopleList)) {
            for (FacedbPeopleVO facedbPeopleVO : facedbPeopleList) {
                if (StringUtils.isNotBlank(facedbPeopleVO.getFacePic()) && !facedbPeopleVO.getFacePic().contains(FileConstant.FILE_HTTPADD)) {
                    facedbPeopleVO.setFacePic(FilePropertiesUtil.getHttpUrl() + facedbPeopleVO.getFacePic());
                }
                List<String> labels = new ArrayList<>();
                if (StringUtils.isNotBlank(facedbPeopleVO.getLabel())) {
                    String[] labelArr = facedbPeopleVO.getLabel().split(CommonConstant.SPLIT_COMMA);
                    for (String label : labelArr) {
                        labels.add(Enums.PeopleLabel.getName(Integer.parseInt(label)));
                    }
                }
                if (CollectionUtils.isNotEmpty(labels)) {
                    facedbPeopleVO.setLabel(String.join(CommonConstant.SPLIT_COMMA, labels));
                }
            }
        }
        return facedbPeopleList;
    }

    /**
     * 新增重点人员
     * @param facedbPeople
     * @return
     */
    @Override
    public Map<String, Object> insertFacedbPeople(FacedbPeople facedbPeople) throws Exception {
        Map<String, Object> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        resultMap.put(CommonConstant.SUCCESS_EN_CODE, true);
        // 社区人像库编号主键id
        int ssFacedbId = facedbPeople.getFacedbId();
        // 欧神人像库编号
        Facedb facedb = new Facedb();
        facedb.setId(ssFacedbId);
        facedb = facedbMapper.selectByPrimaryKey(facedb);
        String oceanFacedbId = facedb.getFacedbId();
        // 新增欧神人像集并保存人像集id
        boolean oceanFlag = true;
        String errorMessage = "";
        List<String> facedbFaceIds = new ArrayList<>();
        List<FacedbPeople> facedbPeopleList = new ArrayList<>();
        Map<String, Object> oceanResult;
        for (String peopleId : facedbPeople.getPeopleId().split(CommonConstant.SPLIT_COMMA)) {
            People people = new People();
            people.setPeopleId(peopleId);
            people.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
            people = resourcePeopleMapper.selectOne(people);
            oceanResult = insertFacedbface(people, oceanFacedbId);
            if (!(boolean) oceanResult.get(CommonConstant.SUCCESS_EN_CODE)) {
                oceanFlag = false;
                errorMessage = String.valueOf(oceanResult.get("message"));
                break;
            }
            String facedbfaceId = String.valueOf(oceanResult.get("facedbfaceId"));
            facedbFaceIds.add(facedbfaceId);
            FacedbPeople insertObj = new FacedbPeople(peopleId, ssFacedbId, facedbfaceId, facedbPeople.getRemark());
            insertObj.setCreateTime(System.currentTimeMillis());
            insertObj.setCreateUser(facedbPeople.getUserIds());
            insertObj.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
            facedbPeopleList.add(insertObj);
        }
        // 欧神人像集新增失败处理：删除已新增的人像集数据
        if (!oceanFlag) {
            if (CollectionUtils.isNotEmpty(facedbFaceIds)) {
                deleteFacedbface(facedbFaceIds);
            }
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", errorMessage);
            return resultMap;
        }
        try {
            // 批量新增社区人口-人像库关联数据[ss_facedb_people]
            facedbPeopleMapper.insertList(facedbPeopleList);
        } catch (Exception e) {
            if (CollectionUtils.isNotEmpty(facedbFaceIds)) {
                deleteFacedbface(facedbFaceIds);
            }
            throw new Exception("新增重点人员失败：" + e.getMessage());
        }
        return resultMap;
    }

    /**
     * 移除重点人员
     * @param facedbPeople
     * @return
     */
    @Override
    public Map<String, Object> deleteFacedbPeople(FacedbPeople facedbPeople) {
        Map<String, Object> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        resultMap.put(CommonConstant.SUCCESS_EN_CODE, true);
        // 删除欧神人像集
        List<String> facedbFaceIds = new ArrayList<>();
        facedbFaceIds.add(facedbPeople.getFacedbFaceId());
        JSONObject oceanResult = deleteFacedbface(facedbFaceIds);
        if (!StringUtils.checkSuccess(oceanResult)) {
            resultMap.put(CommonConstant.SUCCESS_EN_CODE, false);
            resultMap.put("message", oceanResult.getString("message"));
            return resultMap;
        }
        // 删除社区人口人像库关联数据
        facedbPeople.setDeleteTime(System.currentTimeMillis());
        facedbPeople.setDeleteUser(facedbPeople.getUserIds());
        facedbPeople.setDeleteFlag(CommonConstant.DELETE_FLAG_DELETE);
        facedbPeopleMapper.updateByPrimaryKeySelective(facedbPeople);
        return resultMap;
    }

    /**
     * 新增欧神人像集
     * @param people
     * @param oceanFacedbId
     * @return
     */
    private Map<String, Object> insertFacedbface(People people, String oceanFacedbId) {
        Map<String, Object> oceanResult = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        // 封装人像集视图对象
        FacedbfaceVO facedbfaceVO = new FacedbfaceVO();
        facedbfaceVO.setCardId(people.getCredentialNo());
        facedbfaceVO.setCardType(CommonConstant.OCEAN_CARD_TYPE_ID);
        facedbfaceVO.setFacedbId(oceanFacedbId);
        facedbfaceVO.setName(people.getPeopleName());
        facedbfaceVO.setGender(people.getGenderCode());
        facedbfaceVO.setBirthday(people.getBirthDate().replaceAll("-", ""));
        facedbfaceVO.setNation(people.getNation());
        facedbfaceVO.setPhoneNo(people.getPhoneNo());
        String facePic = people.getFacePic();
        String cardPic = people.getIdCardPic();
        if (StringUtils.isNotBlank(facePic) && !facePic.contains(FileConstant.FILE_HTTPADD)) {
            facePic = FilePropertiesUtil.getHttpUrl() + facePic;
        }
        if (StringUtils.isNotBlank(cardPic) && !cardPic.contains(FileConstant.FILE_HTTPADD)) {
            cardPic = FilePropertiesUtil.getHttpUrl() + cardPic;
        }
        // 图片参数处理为base64格式
        try {
            facedbfaceVO.setFaceImg(FileUtil.getBase64ByUrl(facePic));
            if (StringUtils.isNotBlank(cardPic)) {
                facedbfaceVO.setCardImg(FileUtil.getBase64ByUrl(cardPic));
            }
        } catch (Exception e) {
            String errorMessage = "新增欧神人像集时图片URL转BSAE64异常";
            logger.error(errorMessage, e);
            oceanResult.put(CommonConstant.SUCCESS_EN_CODE, false);
            oceanResult.put("message", errorMessage);
            return oceanResult;
        }
        JSONObject jsonObject = this.accessService.facedbfaceInsert(JsonUtils.getFastjsonFromObject(facedbfaceVO));
        if (!StringUtils.checkSuccess(jsonObject)) {
            oceanResult.put(CommonConstant.SUCCESS_EN_CODE, false);
            oceanResult.put("message", jsonObject.getString("message"));
            return oceanResult;
        }
        // 成功返回欧神人像集id
        oceanResult.put(CommonConstant.SUCCESS_EN_CODE, true);
        oceanResult.put("facedbfaceId", jsonObject.getString("data"));
        return oceanResult;
    }

    /**
     * 删除欧神人像集
     * @param facedbFaceIds
     * @return
     */
    private JSONObject deleteFacedbface(List<String> facedbFaceIds) {
        JSONObject paramJson = new JSONObject();
        JSONArray facedbFaceIdsArray = new JSONArray();
        facedbFaceIdsArray.addAll(facedbFaceIds);
        paramJson.put("facedbFaceIds", facedbFaceIdsArray);
        return this.accessService.facedbfaceDelete(paramJson.toString());
    }

}
