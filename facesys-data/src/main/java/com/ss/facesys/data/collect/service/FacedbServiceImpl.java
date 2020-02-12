package com.ss.facesys.data.collect.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.access.common.dto.FaceDbDTO;
import com.ss.facesys.data.baseinfo.common.util.EntityUtil;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.client.IFacedbfaceService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.engine.common.dto.FacedbEngineDTO;
import com.ss.facesys.data.engine.common.model.FacedbEngine;
import com.ss.facesys.data.engine.mapper.FacedbEngineMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.em.ResultCode;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.UserResource;
import com.ss.tools.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
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
public class FacedbServiceImpl extends BaseServiceImpl implements IFacedbService {

    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private IFacedbfaceService facedbfaceService;
    @Resource
    private IAccessService accessService;
    @Resource
    private FacedbEngineMapper facedbEngineMapper;
    @Resource
    private OrganizationMapper organizationMapper;
    @Resource
    private UserResourceMapper userResourceMapper;


    /**
     * 查询人像库列表
     *
     * @param facedb
     * @return
     */
    @Override
    public List<Facedb> getFacedbList(Facedb facedb) {
        Example example = this.entityToExample(facedb);
        if (CollectionUtils.isNotEmpty(facedb.getIds())) {
            example.and().andIn("id", facedb.getIds());
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
     * 查询人像库分页列表
     *
     * @param facedb
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<Facedb> getFacedbPage(Facedb facedb, int currentPage, int pageSize) {
        // 初始化分页查询条件
        PageHelper.startPage(currentPage, pageSize);
        Example example = new Example(Facedb.class);
        example.selectProperties("id", "name", "faceCount", "monitorState", "type", "remark", "orgId", "updateTime");
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (StringUtils.isNotBlank(facedb.getName())) {
            example.and().andLike("name", like(facedb.getName()));
        }
        if (StringUtils.isNotBlank(facedb.getOrgId())) {
            example.and().andIn("orgId", getAllOrgNodes(facedb.getOrgId()));
        }
        if (facedb.getMonitorState() != null) {
            example.and().andEqualTo("monitorState", facedb.getMonitorState());
        }
        if (CollectionUtils.isNotEmpty(facedb.getIds())) {
            example.and().andIn("id", facedb.getIds());
        }
        List<Facedb> facedbs = facedbMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(facedbs)) {
            Map<String, Organization> orgMap = getOrgMapByIds(facedbs.stream().map(Facedb::getOrgId).collect(Collectors.toList()));
            for (Facedb db : facedbs) {
                db.setOrgCname(orgMap.get(db.getOrgId()).getOrgCname());
                // 字典值处理：monitorState、type
                EntityUtil.dealDic(db, "monitorState-FACEDB_MONITOR_STATE-monitorStateName", "type-FACEDB_TYPE-typeName");
            }
        }
        return facedbs;
    }

    private Map<String, Organization> getOrgMapByIds(List<String> orgIds) {
        Map<String, Organization> orgMap = new HashMap<>();
        if (CollectionUtils.isEmpty(orgIds)) {
            return orgMap;
        }
        Example example = new Example(Organization.class);
        example.createCriteria().andIn("orgId", orgIds);
        List<Organization> orgList = organizationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(orgList)) {
            orgMap = orgList.stream().collect(Collectors.toMap(Organization::getOrgId, Function.identity()));
        }
        return orgMap;
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param facedb
     * @return
     */
    @Override
    public Facedb selectOne(Facedb facedb) {
        facedb = facedbMapper.selectOne(facedb);
        if (facedb != null) {
            Map<String, Organization> orgMap = getOrgMapByIds(Collections.singletonList(facedb.getOrgId()));
            facedb.setOrgCname(orgMap.get(facedb.getOrgId()).getOrgCname());
            EntityUtil.dealDic(facedb, "monitorState-FACEDB_MONITOR_STATE-monitorStateName", "type-FACEDB_TYPE-typeName");
        }
        return facedb;
    }

    /**
     * 根据example查询人像库列表
     *
     * @param example
     * @return
     */
    @Override
    public List<Facedb> selectByExample(Example example) {
        return facedbMapper.selectByExample(example);
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
        try {
            facedbMapper.insertSelective(facedb);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_FACESYS_INSERT_FAIL);
        }
        // 新增权限数据
        UserResource userResource = new UserResource();
        userResource.setUserId(facedb.getCreateUserId());
        userResource.setResourceId(facedb.getId());
        userResource.setType(ResourceType.FACEDB.getValue());
        try {
            userResourceMapper.insertSelective(userResource);
        } catch (Exception e) {
            System.err.println("新增人像库默认赋权当前用户失败：" + e.getMessage());
        }
        // 绑定引擎数据
        FacedbEngine facedbEngine = new FacedbEngine();
        facedbEngine.setFacedbId(facedb.getId());
        facedbEngine.setEngineType(CommonConstant.ENGINE_TYPE_FACE);
        facedbEngine.setCreateTime(DateUtils.getCurrentTime());
        try {
            facedbEngineMapper.insertSelective(facedbEngine);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_ENGINE_BIND_FAIL);
        }
        // 新增汇聚平台数据
        String vId = insertVplatFacedb(facedb);
        // 更新人像系统数据
        Facedb update = new Facedb();
        update.setId(facedb.getId());
        update.setFacedbId(vId);
        try {
            facedbMapper.updateByPrimaryKeySelective(update);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_FACESYS_UPDATE_FAIL);
        }
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
        try {
            facedbMapper.updateByPrimaryKeySelective(facedb);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_FACESYS_UPDATE_FAIL);
        }
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
        FacedbFace facedbFace = new FacedbFace();
        facedbFace.setFacedbId(facedb.getId());
        facedbFace.setStatus(StatusEnum.EFFECT.getCode());
        List<FacedbFace> deleteList = facedbfaceService.selectList(facedbFace);
        List<Integer> facedbFaceIds = deleteList.stream().map(FacedbFace::getId).collect(Collectors.toList());
        facedbfaceService.deleteBatch(facedbFaceIds, facedb.getDeleteUserId());
        try {
            facedbMapper.updateByPrimaryKeySelective(facedb);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_FACESYS_DELETE_FAIL);
        }
        // 删除赋权数据
        UserResource userResource = new UserResource();
        userResource.setResourceId(facedb.getId());
        userResource.setType(ResourceType.FACEDB.getValue());
        userResourceMapper.delete(userResource);
        // 删除绑定引擎数据
        FacedbEngine facedbEngine = new FacedbEngine();
        facedbEngine.setFacedbId(facedb.getId());
        facedbEngineMapper.delete(facedbEngine);
        // 删除汇聚平台数据
        deleteVplatFacedb(dbCheck.getFacedbId());
    }

    private String insertVplatFacedb(Facedb facedb) throws ServiceException {
        FaceDbDTO dto = new FaceDbDTO();
        String vId;
        try {
            BeanUtils.copyProperties(facedb, dto);
            JSONObject oceanResult = accessService.facedInsert(JSONObject.toJSONString(dto));
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
            vId = oceanResult.getString("data");
        } catch (ServiceException e) {
            throw e;
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
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    private void deleteVplatFacedb(String facedbId) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("facedbIds", Collections.singletonList(facedbId));
            JSONObject oceanResult = accessService.facedDelete(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    /**
     * 人像库重提特征
     *
     * @param id
     * @param facedbId
     * @param faceDBFaceStateInvalid
     * @return
     */
    @Override
    public void reFeature(Integer id, String facedbId, Integer faceDBFaceStateInvalid) throws ServiceException {
        try {
            // 校验人像库是否绑定人脸引擎
            FacedbEngine facedbEngine = new FacedbEngine();
            facedbEngine.setFacedbId(id);
            facedbEngine.setEngineType(CommonConstant.ENGINE_TYPE_FACE);
            List<FacedbEngine> checkList = facedbEngineMapper.select(facedbEngine);
            if (CollectionUtils.isEmpty(checkList)) {
                throw new ServiceException(ResultCode.FACEDB_ENGINE_BIND_REF_NOT_EXIST);
            }
            JSONObject param = new JSONObject();
            param.put("id", facedbId);
            param.put("faceDBFaceStateInvalid", faceDBFaceStateInvalid);
            JSONObject oceanResult = accessService.reFeatureFacedb(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_FAIL);
        }
    }

    /**
     * 更新人像库人脸数量
     *
     * @param facedbId
     * @param num
     */
    @Override
    public void updateFaceCount(Integer facedbId, Integer num) {
        if (facedbId != null && num != null) {
            Facedb facedb = new Facedb();
            facedb.setId(facedbId);
            facedb.setStatus(StatusEnum.EFFECT.getCode());
            facedb = facedbMapper.selectOne(facedb);
            if (facedb != null) {
                facedb.setFaceCount(facedb.getFaceCount() + num);
                facedbMapper.updateByPrimaryKeySelective(facedb);
            }
        }
    }

    /**
     * 查询人像库绑定引擎列表
     *
     * @param engineDTO
     * @return
     */
    @Override
    public List<FacedbEngineDTO> engineList(FacedbEngineDTO engineDTO) {
        List<FacedbEngineDTO> resultList = new ArrayList<>();
        // 所属单位、人像库名称：转换为人像库id条件
        Example example = new Example(FacedbEngine.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isAllBlank(engineDTO.getOrgId(), engineDTO.getName())) {
            Example facedbExp = new Example(Facedb.class);
            facedbExp.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
            if (StringUtils.isNotBlank(engineDTO.getOrgId())) {
                facedbExp.and().andEqualTo("orgId", engineDTO.getOrgId());
            }
            if (StringUtils.isNotBlank(engineDTO.getName())) {
                facedbExp.and().andLike("name", like(engineDTO.getName()));
            }
            List<Facedb> facedbList = facedbMapper.selectByExample(facedbExp);
            List<Integer> facedbIds = facedbList.stream().map(Facedb::getId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(facedbIds)) {
                facedbIds.add(-1);
            }
            criteria.andIn("facedbId", facedbIds);
        }
        List<FacedbEngine> facedbEngines = facedbEngineMapper.selectByExample(example);
        // 返回处理
        if (CollectionUtils.isNotEmpty(facedbEngines)) {
            Map<Integer, List<FacedbEngine>> resMap = facedbEngines.stream().collect(Collectors.groupingBy(FacedbEngine::getFacedbId));
            Example facedbSelExp = new Example(Facedb.class);
            facedbSelExp.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode()).andIn("id", resMap.keySet());
            List<Facedb> facedbList = facedbMapper.selectByExample(facedbSelExp);
            // 单位信息
            Example orgExp = new Example(Organization.class);
            orgExp.createCriteria().andIn("orgId", facedbList.stream().map(Facedb::getOrgId).collect(Collectors.toList()));
            List<Organization> organizations = organizationMapper.selectByExample(orgExp);
            Map<String, String> orgMap = organizations.stream().collect(Collectors.toMap(Organization::getOrgId, Organization::getOrgCname));
            for (Facedb facedb : facedbList) {
                FacedbEngineDTO facedbEngineDTO = new FacedbEngineDTO();
                facedbEngineDTO.setFacedbId(facedb.getId());
                facedbEngineDTO.setName(facedb.getName());
                facedbEngineDTO.setOrgId(facedb.getOrgId());
                facedbEngineDTO.setOrgCname(orgMap.get(facedb.getOrgId()));
                facedbEngineDTO.setEngineTypeList(resMap.get(facedb.getId()).stream().map(FacedbEngine::getEngineType).collect(Collectors.toList()));
                resultList.add(facedbEngineDTO);
            }
        }
        return resultList;
    }

    /**
     * 人像库绑定引擎关系
     *
     * @param facedbEngine
     * @return
     * @throws ServiceException
     */
    @Override
    public String bindEngineControl(FacedbEngine facedbEngine) throws ServiceException {
        // 查询有效的人像库条件
        Example dbe = new Example(Facedb.class);
        dbe.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode()).andIn("id", facedbEngine.getFacedbIds());
        List<Facedb> facedbs = facedbMapper.selectByExample(dbe);
        if (CollectionUtils.isEmpty(facedbs)) {
            throw new ServiceException(ResultCode.FACEDB_NOTEXIST);
        }
        // 获取操作目标数据集  人像库ID, 汇聚平台人像库ID
        int engineType, bindStatus, count;
        Map<Integer, String> targetMap = catchBindTarget(facedbs, engineType = facedbEngine.getEngineType(), bindStatus = facedbEngine.getBindStatus());
        Set<Integer> targetIds = targetMap.keySet();
        if (CollectionUtils.isEmpty(targetIds)) {
            throw new ServiceException(ResultCode.FACEDB_ENGINE_BIND_NULLTARGET);
        }
        if (bindStatus == CommonConstant.ENGINE_BIND_STATUS) {
            // 批量绑定
            List<FacedbEngine> bindList = new ArrayList<>();
            targetIds.forEach(facedbId -> {
                FacedbEngine engine = new FacedbEngine();
                engine.setFacedbId(facedbId);
                engine.setEngineType(engineType);
                engine.setCreateTime(DateUtils.getCurrentTime());
                bindList.add(engine);
            });
            try {
                count = facedbEngineMapper.insertList(bindList);
            } catch (Exception e) {
                throw new ServiceException(ResultCode.FACEDB_ENGINE_BIND_FAIL);
            }
        } else {
            // 批量取消绑定
            Example cancelBindExp = new Example(FacedbEngine.class);
            cancelBindExp.createCriteria().andIn("facedbId", targetIds).andEqualTo("engineType", engineType);
            try {
                count = facedbEngineMapper.deleteByExample(cancelBindExp);
                // 人像库下的人像集特征值状态更新为无效
                Example example = new Example(FacedbFace.class);
                example.createCriteria().andIn("facedbId", targetIds);
                FacedbFace facedbFace = new FacedbFace();
                facedbFace.setState(StatusEnum.INVALID.getCode());
                this.facedbfaceService.updateByExampleSelective(facedbFace, example);
            } catch (Exception e) {
                throw new ServiceException(ResultCode.FACEDB_ENGINE_CANCEL_BIND_FAIL);
            }
        }
        // 汇聚平台操作
        vplatEngineControl(new ArrayList<>(targetMap.values()), engineType, bindStatus);
        return "人像库与引擎绑定关系：成功将" + count + "条人像库数据"
                + (bindStatus == CommonConstant.ENGINE_BIND_STATUS ? "绑定" : "解绑")
                + EntityUtil.getEnumName("ENGINE_TYPE", String.valueOf(engineType));
    }

    /**
     * 获取可做绑定/取消绑定的目标数据
     *
     * @param facedbList
     * @param engineType
     * @param bindStatus
     * @return
     */
    private Map<Integer, String> catchBindTarget(List<Facedb> facedbList, Integer engineType, Integer bindStatus) {
        Map<Integer, String> targetMap = new HashMap<>(facedbList.size());
        if (CollectionUtils.isNotEmpty(facedbList)) {
            Map<Integer, String> validMap = facedbList.stream().collect(Collectors.toMap(Facedb::getId, Facedb::getFacedbId));
            Set<Integer> ids = validMap.keySet();
            Example selectBindExp = new Example(FacedbEngine.class);
            selectBindExp.createCriteria().andIn("facedbId", ids).andEqualTo("engineType", engineType);
            List<FacedbEngine> bindList = facedbEngineMapper.selectByExample(selectBindExp);
            Set<Integer> bindIds = bindList.stream().map(FacedbEngine::getFacedbId).collect(Collectors.toSet());
            Set<Integer> notBindIds = ids.stream().filter(facedbId -> !bindIds.contains(facedbId)).collect(Collectors.toSet());
            Set<Integer> targetIds = bindStatus == CommonConstant.ENGINE_BIND_STATUS ? notBindIds : bindIds;
            targetIds.forEach(targetId -> {
                targetMap.put(targetId, validMap.get(targetId));
            });
        }
        return targetMap;
    }

    /**
     * 汇聚平台引擎绑定调用
     *
     * @param facedbIds
     * @param engineType
     * @param bindStatus
     * @throws ServiceException
     */
    private void vplatEngineControl(List<String> facedbIds, Integer engineType, Integer bindStatus) throws ServiceException {
        try {
            JSONObject param = new JSONObject();
            param.put("facedbIds", facedbIds);
            param.put("engineType", engineType);
            param.put("bindStatus", bindStatus);
            JSONObject oceanResult = accessService.facedbEngineControl(param.toJSONString());
            if (!StringUtils.checkSuccess(oceanResult)) {
                throw new ServiceException(oceanResult.getString("code"), oceanResult.getString("message"));
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.FACEDB_VPLAT_ENGINE_CONTROL_FAIL);
        }
    }

}