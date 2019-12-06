package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FaceDbDTO;
import com.ss.facesys.data.baseinfo.common.util.EntityUtil;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbPeople;
import com.ss.facesys.data.collect.common.web.FacedbPeopleVO;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.collect.mapper.FacedbPeopleMapper;
import com.ss.facesys.data.resource.common.model.People;
import com.ss.facesys.data.resource.common.web.FacedbfaceVO;
import com.ss.facesys.data.resource.mapper.ResourcePeopleMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.file.FileUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * FacedbServiceImpl
 *
 * @author FrancisYs
 * @date 2019/12/5
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
     *
     * @param facedb
     * @return
     */
    @Override
    public List<Facedb> getFacedbList(Facedb facedb) {
        List<Facedb> facedbs = facedbMapper.select(facedb);
        if (CollectionUtils.isNotEmpty(facedbs)) {
            for (Facedb db : facedbs) {
                // 字典值处理：monitorState、type
                EntityUtil.dealDic(db, "monitorState-FACEDB_MONITOR_STATE-monitorStateName", "type-FACEDB_TYPE-typeName");
            }
        }
        return facedbs;
    }

    /**
     * 查询人像库分页列表
     *
     * @param facedb
     * @return
     */
    @Override
    public List<Facedb> getFacedbPage(Facedb facedb, int currentPage, int pageSize) {
        // 初始化分页查询条件
        PageHelper.startPage(currentPage, pageSize);
        Example example = new Example(Facedb.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (StringUtils.isNotBlank(facedb.getName())) {
            example.and().andLike("name", like(facedb.getName()));
        }
        if (StringUtils.isNotBlank(facedb.getOrgId())) {
            example.and().andEqualTo("orgId", facedb.getOrgId());
        }
        if (facedb.getMonitorState() != null) {
            example.and().andEqualTo("monitorState", facedb.getMonitorState());
        }
        List<Facedb> facedbs = facedbMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(facedbs)) {
            for (Facedb db : facedbs) {
                // 字典值处理：monitorState、type
                EntityUtil.dealDic(db, "monitorState-FACEDB_MONITOR_STATE-monitorStateName", "type-FACEDB_TYPE-typeName");
            }
        }
        return facedbs;
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param facedb
     * @return
     */
    @Override
    public Facedb selectOne(Facedb facedb) {
        return facedbMapper.selectOne(facedb);
    }

    private void duplicateCheck(Facedb facedb) throws ServiceException {
        Example example = new Example(Facedb.class);
        example.createCriteria()
                .andEqualTo("status", StatusEnum.EFFECT.getCode())
                .andEqualTo("name", facedb.getName());
        if (facedb.getId() != null) {
            example.and().andNotEqualTo("id", facedb.getId());
        }
        if (CollectionUtils.isNotEmpty(facedbMapper.selectByExample(example))) {
            throw new ServiceException(ResultCode.FACEDB_FACEDBNAME_EXIST);
        }
    }

    /**
     * 新增人像库
     *
     * @param facedb
     * @return
     */
    @Override
    public String insertFacedb(Facedb facedb) throws ServiceException {
        duplicateCheck(facedb);
        // 新增人像系统数据
        facedbMapper.insertSelective(facedb);
        // 新增汇聚平台数据
        String vId = insertVplatFacedb(facedb);
        // 更新人像系统数据
        Facedb update = new Facedb();
        update.setId(facedb.getId());
        update.setFacedbId(vId);
        facedbMapper.updateByPrimaryKeySelective(update);
        return String.valueOf(facedb.getId());
    }

    /**
     * 修改人像库
     *
     * @param facedb
     * @return
     */
    @Override
    public void updateFacedb(Facedb facedb) throws ServiceException {
        duplicateCheck(facedb);
        // 更新人像系统数据
        facedbMapper.updateByPrimaryKeySelective(facedb);
        // 更新汇聚平台数据
        updateVplatFacedb(facedb);
    }

    /**
     * 删除人像库
     *
     * @param facedb
     * @return
     */
    @Override
    public void deleteFacedb(Facedb facedb) throws ServiceException {
        // 校验底库是否布控
        Facedb dbCheck = facedbMapper.selectByPrimaryKey(facedb);
        if (dbCheck.getMonitorState() == CommonConstant.FACEDB_MONITOR_STATE_MONITORED) {
            throw new ServiceException(ResultCode.FACEDB_DELETEFAIL_MONITOR);
        }
        // 删除人像系统数据
        // TODO 先删除人像系统人像集数据

        facedb.setFaceCount(0);
        facedbMapper.updateByPrimaryKeySelective(facedb);

        // 删除汇聚平台数据
        deleteVplatFacedb(dbCheck.getFacedbId());
    }

    private String insertVplatFacedb(Facedb facedb) throws ServiceException {
        FaceDbDTO dto = new FaceDbDTO();
        String vId = null;
        try {
            BeanUtils.copyProperties(facedb, dto);
            JSONObject oceanResult = accessService.facedInsert(JSONObject.toJSONString(dto));
            if (StringUtils.checkSuccess(oceanResult)) {
                vId = oceanResult.getString("data");
            }
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
        if (StringUtils.isBlank(vId)) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
        return vId;
    }

    private void updateVplatFacedb(Facedb facedb) throws ServiceException {
        FaceDbDTO dto = new FaceDbDTO();
        try {
            BeanUtils.copyProperties(facedb, dto);
            dto.setId(facedb.getFacedbId());
            JSONObject oceanResult = accessService.updateFacedb(JSONObject.toJSONString(dto));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    private void deleteVplatFacedb(String facedbId) throws ServiceException {
        try {
            JSONObject oceanResult = accessService.facedDelete(JSONObject.toJSONString(Collections.singletonList(facedbId)));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    /**
     * 人像库重提特征
     *
     * @param facedbId
     * @param faceDBFaceStateInvalid
     * @return
     */
    @Override
    public void reFeature(String facedbId, Integer faceDBFaceStateInvalid) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("id", facedbId);
            param.put("faceDBFaceStateInvalid", faceDBFaceStateInvalid);
            JSONObject oceanResult = accessService.reFeatureFacedb(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
            }
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }



    /**
     * 查询重点人员列表
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
