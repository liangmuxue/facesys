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
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResultCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
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
        facedb = facedbMapper.selectOne(facedb);
        if (facedb != null) {
            EntityUtil.dealDic(facedb, "monitorState-FACEDB_MONITOR_STATE-monitorStateName", "type-FACEDB_TYPE-typeName");
        }
        return facedb;
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

}