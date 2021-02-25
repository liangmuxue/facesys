package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FacedbfaceDTO;
import com.ss.facesys.data.baseinfo.common.util.EntityUtil;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IFacedbfaceService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.data.collect.mapper.FacedbFaceMapper;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.util.AgeUtil;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResultCode;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.file.FileUtil;
import com.ss.tools.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * FacedbServiceImpl
 *
 * @author FrancisYs
 * @date 2019/12/5
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class FacedbfaceServiceImpl extends BaseServiceImpl implements IFacedbfaceService {

    @Resource
    private FacedbFaceMapper facedbFaceMapper;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private IAccessService accessService;


    /**
     * 查询人像集分页列表
     *
     * @param facedbFace
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<FacedbFace> pages(FacedbFace facedbFace, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        // 初始化分页查询条件
        Example example = new Example(FacedbFace.class);
        example.selectProperties("id", "facePath", "name", "gender", "birthday", "cardType", "cardId", "facedbId", "state", "nation", "phoneNo");
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (CollectionUtils.isNotEmpty(facedbFace.getFacedbIds())) {
            example.and().andIn("facedbId", facedbFace.getFacedbIds());
        }
        if (StringUtils.isNotBlank(facedbFace.getName())) {
            example.and().andLike("name", like(facedbFace.getName()));
        }
        if (StringUtils.isNotBlank(facedbFace.getCardId())) {
            example.and().andLike("cardId", like(facedbFace.getCardId()));
        }
        if (StringUtils.isNotBlank(facedbFace.getPhoneNo())) {
            example.and().andLike("phoneNo", like(facedbFace.getPhoneNo()));
        }
        if (facedbFace.getGender() != null) {
            example.and().andEqualTo("gender", facedbFace.getGender());
        }
        if (facedbFace.getState() != null) {
            example.and().andEqualTo("state", facedbFace.getState());
        }
        if (StringUtils.isNoneBlank(facedbFace.getBirthdayMin(), facedbFace.getBirthdayMax())) {
            example.and().andBetween("birthday", facedbFace.getBirthdayMin(), facedbFace.getBirthdayMax());
        }
        example.orderBy("createTime").desc();
        List<FacedbFace> faces = facedbFaceMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(faces)) {
            List<Integer> facedbIdList = faces.stream().map(FacedbFace::getFacedbId).collect(Collectors.toList());
            Map<Integer, String> facedbMap = getFacedbMap(facedbIdList);
            faces.forEach(face -> {
                dealFaceQueryResult(face, facedbMap);
            });
        }
        return faces;
    }

    private Map<Integer, String> getFacedbMap(List<Integer> facedbIdList) {
        Map<Integer, String> facedbMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(facedbIdList)) {
            Example example = new Example(Facedb.class);
            example.createCriteria().andIn("id", facedbIdList).andEqualTo("status", StatusEnum.EFFECT.getCode());
            List<Facedb> facedbs = facedbMapper.selectByExample(example);
            facedbMap = facedbs.stream().collect(Collectors.toMap(Facedb::getId, Facedb::getName));
        }
        return facedbMap;
    }

    private void dealFaceQueryResult(FacedbFace facedbFace, Map<Integer, String> facedbMap) {
        if (facedbFace != null) {
            // 字典值处理：gender、cardType、state、nation
            EntityUtil.dealDic(facedbFace, "gender-GENDER-genderName", "cardType-ID_TYPE-cardTypeName", "state-FACE_FEATURE_STATE-stateName", "nation-NATION-nationName");
            // 生日计算年龄：birthday
            try {
                facedbFace.setAge(AgeUtil.getAge(DateUtil.formatString(facedbFace.getBirthday(), DateUtil.DATE_FORMATE_YYYYMMDD)));
            } catch (Exception e) {
                facedbFace.setAge(0);
            }
            // 人像库名称：facedbId
            facedbFace.setFacedbName(facedbMap.get(facedbFace.getFacedbId()));
        }
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param facedbFace
     * @return
     */
    @Override
    public FacedbFace selectOne(FacedbFace facedbFace) {
        FacedbFace face = facedbFaceMapper.selectOne(facedbFace);
        if (face != null) {
            Map<Integer, String> facedbMap = getFacedbMap(Collections.singletonList(face.getFacedbId()));
            dealFaceQueryResult(face, facedbMap);
        }
        return face;
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param facedbFace
     * @return
     */
    @Override
    public List<FacedbFace> selectList(FacedbFace facedbFace) {
        return facedbFaceMapper.select(facedbFace);
    }

    private void duplicateCheck(FacedbFace facedbFace) throws ServiceException {
        Example example = new Example(FacedbFace.class);
        example.createCriteria()
                .andEqualTo("status", StatusEnum.EFFECT.getCode())
                .andEqualTo("cardType", facedbFace.getCardType())
                .andEqualTo("cardId", facedbFace.getCardId());
        if (facedbFace.getId() != null) {
            example.and().andNotEqualTo("id", facedbFace.getId());
        }
        if (CollectionUtils.isNotEmpty(facedbFaceMapper.selectByExample(example))) {
            throw new ServiceException(ResultCode.FACEDBFACE_CARDID_EXIST);
        }
    }

    private void recentImgCheck(FacedbFace facedbFace) throws ServiceException {
        if (StringUtils.isBlank(facedbFace.getRecentPath())) {
            return;
        }
        faceDetectByUrl(facedbFace.getRecentPath().contains(FileConstant.FILE_HTTPADD) ? facedbFace.getRecentPath() : FilePropertiesUtil.getHttpUrl() + facedbFace.getRecentPath());
    }

    /**
     * 新增人像集
     *
     * @param facedbFace
     * @return
     */
    @Override
    public String insert(FacedbFace facedbFace) throws ServiceException {
//        duplicateCheck(facedbFace);
        recentImgCheck(facedbFace);
        // 新增人像系统数据
        try {
            facedbFaceMapper.insertSelective(facedbFace);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_FACESYS_INSERT_FAIL);
        }
        // 新增汇聚平台数据
        String faceId = String.valueOf(insertVplatFacedbface(facedbFace));
        if(faceId==null){
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
        Integer state = StatusEnum.EFFECT.getCode();
        // 更新人像系统数据
        FacedbFace update = new FacedbFace();
        update.setId(facedbFace.getId());
        update.setFaceId(faceId);
//        update.setFacePath(facedbfaceDTO.getFacePath());
        update.setState(state);
        try {
            facedbFaceMapper.updateByPrimaryKeySelective(update);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_FACESYS_UPDATE_FAIL);
        }
        return String.valueOf(facedbFace.getId());
    }

    /**
     * 修改人像集
     *
     * @param facedbFace
     * @param orginal
     * @return
     */
    @Override
    public void update(FacedbFace facedbFace, FacedbFace orginal) throws ServiceException {
//        duplicateCheck(facedbFace);
        recentImgCheck(facedbFace);
        Long faceId = insertVplatFacedbface(facedbFace);
        if(faceId==null){
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
        facedbFace.setFaceId(String.valueOf(faceId));
        facedbFace.setState(StatusEnum.EFFECT.getCode());
        // 更新人像系统数据
        try {
            facedbFaceMapper.updateByPrimaryKeySelective(facedbFace);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_FACESYS_UPDATE_FAIL);
        }
    }

    /**
     * 删除人像集
     *
     * @param facedbFace
     * @return
     */
    @Override
    public void delete(FacedbFace facedbFace) throws ServiceException {
        // 校验人像集是否布控
//        Facedb dbCheck = new Facedb();
//        dbCheck.setId(facedbFace.getFacedbId());
//        dbCheck = facedbMapper.selectByPrimaryKey(dbCheck);
//        if (dbCheck.getMonitorState() == CommonConstant.FACEDB_MONITOR_STATE_MONITORED) {
//            throw new ServiceException(ResultCode.FACEDB_DELETEFAIL_MONITOR);
//        }
        // 删除人像系统数据
        try {
            facedbFaceMapper.updateByPrimaryKeySelective(facedbFace);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_FACESYS_DELETE_FAIL);
        }
//        // 删除汇聚平台数据
//        deleteVplatFacedbFace(Collections.singletonList(facedbFace.getFaceId()));
    }

    /**
     * 批量删除人像集
     *
     * @param facedbFaceIds
     * @param deleteUserId
     * @return
     */
    @Override
    public void deleteBatch(List<Integer> facedbFaceIds, String deleteUserId) throws ServiceException {
        if (CollectionUtils.isNotEmpty(facedbFaceIds)) {
            Example example = new Example(FacedbFace.class);
            example.createCriteria().andIn("id", facedbFaceIds);
            List<FacedbFace> deleteList = facedbFaceMapper.selectByExample(example);
            // 校验人像集是否布控
            Set<Integer> facedbIdSet = deleteList.stream().map(FacedbFace::getFacedbId).collect(Collectors.toSet());
            Example exp = new Example(Facedb.class);
            exp.createCriteria().andIn("id", facedbIdSet);
            List<Facedb> facedbList = facedbMapper.selectByExample(exp);
            List<Facedb> monitorList = facedbList.stream().filter(facedb -> facedb.getMonitorState() == CommonConstant.FACEDB_MONITOR_STATE_MONITORED).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(monitorList)) {
                throw new ServiceException(ResultCode.FACEDBFACE_DELETEFAIL_MONITOR);
            }
            // 删除人像系统数据
            FacedbFace facedbFace = new FacedbFace();
            facedbFace.setDeleteUserId(deleteUserId);
            facedbFace.setDeleteTime(DateUtils.getCurrentTime());
            facedbFace.setStatus(StatusEnum.EXPIRE.getCode());
            try {
                facedbFaceMapper.updateByExampleSelective(facedbFace, example);
            } catch (Exception e) {
                throw new ServiceException(ResultCode.FACEDBFACE_FACESYS_DELETE_FAIL);
            }
            // 删除汇聚平台数据
            List<String> faceIdList = deleteList.stream().map(FacedbFace::getFaceId).collect(Collectors.toList());
            deleteVplatFacedbFace(faceIdList);
        }
    }

    private void faceDetectByUrl(String url) throws ServiceException {
        String img;
        try {
            img = FileUtil.getBase64ByUrl(url);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.IMG_TO_BASE64_FAIL);
        }
        faceDetect(img);
    }

    private void faceDetect(String img) throws ServiceException {
        JSONObject detectParam = new JSONObject() {{
            put("img", img);
        }};
        JSONObject detect = accessService.faceDetect(detectParam.toJSONString());
        if (!StringUtils.checkSuccess(detect) || CollectionUtils.isEmpty(detect.getJSONArray("data"))) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL.getCode(), "图片中未检测到人脸");
        }
    }

    /**
     * 新增汇聚平台人像集
     *
     * @param facedbFace
     * @return
     */
    private Long insertVplatFacedbface(FacedbFace facedbFace) throws ServiceException {
        FacedbfaceDTO dto = new FacedbfaceDTO();
        Long faceId =null;
        try {
            BeanUtils.copyProperties(facedbFace, dto, "facedbId");
            Facedb facedb = new Facedb();
            facedb.setId(facedbFace.getFacedbId());
            facedb.setStatus(StatusEnum.EFFECT.getCode());
            facedb = facedbMapper.selectOne(facedb);
            dto.setFacedbId(facedb.getFacedbId());
            String fullPath = !facedbFace.getFacePath().contains(FileConstant.FILE_HTTPADD) ? FilePropertiesUtil.getHttpUrl() + facedbFace.getFacePath() : facedbFace.getFacePath();
            try {
                dto.setFaceImg(FileUtil.getBase64ByUrl(fullPath));
            } catch (Exception e) {
                throw new ServiceException(ResultCode.IMG_TO_BASE64_FAIL);
            }
            dto.setFaceImgType(CommonConstant.IMGTYPE_BASE64);
            // 如果使用SFGO引擎，则需要生成对应引擎规则的ID
            String engineId = getFaceDbEngineId(dto.getFacedbId());
            faceId = com.ss.tools.UUIDUtils.encodeToFaceId(Integer.parseInt(engineId), (int) System.currentTimeMillis() / 1000, (int) (Math.random() * 10000));
            faceDetect(dto.getFaceImg());
            JSONObject params = new JSONObject();
            params.put("img", dto.getFaceImg());
            params.put("groupId", dto.getFacedbId());
            params.put("userId", faceId);
            params.put("feature", null);
            params.put("datetime", facedbFace.getUpdateTime() / 1000);
            JSONObject oceanResult = accessService.facedbfaceInsert(params.toJSONString());
            if (!"0".equals(oceanResult.getString("result"))) {
                throw new ServiceException(oceanResult.getString("result"), oceanResult.getString("info"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
        return faceId;
    }

    /**
     * 查询汇聚平台人像集信息
     *
     * @param faceId
     * @return
     * @throws ServiceException
     */
    private FacedbfaceDTO getVplatFacedbFace(String faceId) throws ServiceException {
        FacedbfaceDTO facedbfaceDTO;
        try {
            JSONObject param = new JSONObject();
            param.put("id", faceId);
            JSONObject oceanResult = this.accessService.facedbfaceGet(JsonUtils.getFastjsonFromObject(param));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
            facedbfaceDTO = JSONObject.parseObject(oceanResult.getString("data"), FacedbfaceDTO.class);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
        if (facedbfaceDTO == null) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
        return facedbfaceDTO;
    }

    /**
     * 更新汇聚平台人像集信息
     *
     * @param facedbFace
     * @param orginal
     * @throws ServiceException
     */
    private void updateVplatFacedbFace(FacedbFace facedbFace, FacedbFace orginal) throws ServiceException {
        FacedbfaceDTO dto = new FacedbfaceDTO();
        try {
            BeanUtils.copyProperties(facedbFace, dto);
            dto.setId(facedbFace.getFaceId());
            // 获取汇聚平台人像库ID
            Facedb facedb = new Facedb();
            facedb.setId(facedbFace.getFacedbId());
            facedb = facedbMapper.selectByPrimaryKey(facedb);
            dto.setFacedbId(facedb.getFacedbId());
            // 若更新了照片则传入注册照参数
            if (StringUtils.isNotBlank(facedbFace.getFacePath()) && !orginal.getFacePath().equals(facedbFace.getFacePath()) && !facedbFace.getFacePath().contains(FileConstant.FILE_HTTPADD)) {
                String fullPath = FilePropertiesUtil.getHttpUrl() + facedbFace.getFacePath();
                try {
                    dto.setFaceImg(FileUtil.getBase64ByUrl(fullPath));
                } catch (Exception e) {
                    throw new ServiceException(ResultCode.IMG_TO_BASE64_FAIL);
                }
                dto.setFaceImgType(CommonConstant.IMGTYPE_BASE64);
                faceDetect(dto.getFaceImg());
            }
            JSONObject oceanResult = accessService.facedbfaceUpdate(JsonUtils.getFastjsonFromObject(dto));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
    }

    /**
     * 删除汇聚平台人像集信息
     *
     * @param faceIdList
     * @throws ServiceException
     */
    private void deleteVplatFacedbFace(List<String> faceIdList) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("facedbFaceIds", faceIdList);
            JSONObject oceanResult = accessService.facedbfaceDelete(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDBFACE_VPLAT_FAIL);
        }
    }

    /**
     * 重提人像集特征
     *
     * @param orgDb
     * @return
     * @throws ServiceException
     */
    @Override
    public void reFeature(FacedbFace orgDb) throws ServiceException {
        try {
            update(orgDb,null);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    /**
     * 根据Example条件更新人像集信息 facedbFace包含的不是null的属性值
     *
     * @param facedbFace
     * @param example
     */
    @Override
    public void updateByExampleSelective(FacedbFace facedbFace, Example example) {
        facedbFaceMapper.updateByExampleSelective(facedbFace, example);
    }

    /**
     * 获取人像库序列值作为引擎底库id
     *
     * @param faceDbId
     * @return
     */
    public String getFaceDbEngineId(String faceDbId){
        return faceDbId.substring(faceDbId.length() - 6);
    }
}