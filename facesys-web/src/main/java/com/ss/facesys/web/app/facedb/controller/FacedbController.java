package com.ss.facesys.web.app.facedb.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.engine.common.dto.FacedbEngineDTO;
import com.ss.facesys.data.engine.common.model.FacedbEngine;
import com.ss.facesys.data.engine.validate.APIEngineBindGroup;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.web.app.facedb.form.FacedbEngineBindForm;
import com.ss.facesys.web.app.facedb.form.FacedbForm;
import com.ss.facesys.web.app.facedb.query.FacedbQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.tools.DateUtils;
import com.ss.valide.*;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 人像库
 *
 * @author FrancisYs
 * @date 2019/12/5
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/facedb"})
public class FacedbController extends BaseController {

    @Resource
    private IFacedbService facedbService;

    /**
     * 查询人像库列表
     *
     * @return
     */
    @PostMapping(value = {"/list"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像库列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Facedb>> getFacedblist(@RequestBody FacedbQuery query) {
        ResponseEntity<List<Facedb>> resp = createSuccResponse();
        Facedb facedb = new Facedb();
        facedb.setStatus(StatusEnum.EFFECT.getCode());
        facedb.setIds(getAuthResources(query.getUserId(), ResourceType.FACEDB, null));
        resp.setData(facedbService.getFacedbList(facedb));
        return resp;
    }

    /**
     * 查询人像库分页列表
     *
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/pages"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像库分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Facedb>> getFacedbPage(@RequestBody @Validated({APIPageGroup.class}) FacedbQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<Facedb>> resp = validite(bindingResult);
        Facedb facedb = new Facedb();
        BeanUtils.copyProperties(query, facedb);
        facedb.setIds(getAuthResources(query.getUserId(), ResourceType.FACEDB, null));
        Page<Facedb> data = (Page) facedbService.getFacedbPage(facedb, query.getCurrentPage(), query.getPageSize());
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询人像库信息
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/detail"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像库信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Facedb> getFacedbDetail(@RequestBody @Validated({APIGetsGroup.class}) FacedbQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<Facedb> resp = validite(bindingResult);
        Facedb facedb = new Facedb();
        facedb.setId(query.getId());
        facedb.setStatus(StatusEnum.EFFECT.getCode());
        resp.setData(facedbService.selectOne(facedb));
        return resp;
    }

    /**
     * 新增人像库
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/insert"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "新增人像库", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertFacedb(@RequestBody @Validated({APIAddGroup.class}) FacedbForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        Facedb facedb = new Facedb();
        BeanUtils.copyProperties(form, facedb);
        facedb.setCreateUserId(form.getUserId());
        facedb.setCreateTime(ct);
        facedb.setUpdateUserId(form.getUserId());
        facedb.setUpdateTime(ct);
        facedb.setFaceCount(0);
        facedb.setMonitorState(CommonConstant.FACEDB_MONITOR_STATE_TBM);
        try {
            resp.setData(facedbService.insertFacedb(facedb));
        } catch (ServiceException e) {
            this.logger.error("新增人像库失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 修改人像库
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/update"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改人像库", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateFacedb(@RequestBody @Validated({APIEditGroup.class}) FacedbForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        Facedb facedb = new Facedb();
        BeanUtils.copyProperties(form, facedb);
        facedb.setUpdateUserId(form.getUserId());
        facedb.setUpdateTime(ct);
        Facedb orgDb = getOriginalDbObj(form.getId());
        facedb.setFacedbId(orgDb.getFacedbId());
        try {
            facedbService.updateFacedb(facedb);
        } catch (ServiceException e) {
            this.logger.error("修改人像库失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 删除人像库
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/delete"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除人像库", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteFacedb(@RequestBody @Validated({APIDeltGroup.class}) FacedbForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        Facedb facedb = new Facedb();
        BeanUtils.copyProperties(form, facedb);
        facedb.setFaceCount(0);
        facedb.setDeleteUserId(form.getUserId());
        facedb.setDeleteTime(ct);
        facedb.setStatus(StatusEnum.EXPIRE.getCode());
        try {
            facedbService.deleteFacedb(facedb);
        } catch (ServiceException e) {
            this.logger.error("删除人像库失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 人像库重提特征
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/reFeature"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "人像库重提特征", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> reFeature(@RequestBody @Validated({APIFeatureExtractionGroup.class}) FacedbForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Facedb orgDb = getOriginalDbObj(form.getId());
        try {
            facedbService.reFeature(form.getId(), orgDb.getFacedbId(), form.getFaceDBFaceStateInvalid());
        } catch (ServiceException e) {
            this.logger.error("人像库重提特征失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    private Facedb getOriginalDbObj(Integer id) {
        Facedb vp = new Facedb();
        vp.setId(id);
        return facedbService.selectOne(vp);
    }

    /**
     * 人像库与引擎绑定关系查询
     *
     * @return
     */
    @PostMapping(value = {"/engine/list"})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询人像库绑定引擎列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<FacedbEngineDTO>> engineList(@RequestBody FacedbEngineDTO engineDTO) {
        ResponseEntity<List<FacedbEngineDTO>> resp = createSuccResponse();
        List<FacedbEngineDTO> facedbEngineDTOS = facedbService.engineList(engineDTO);
        List<Integer> facedbIds = facedbEngineDTOS.stream().map(FacedbEngineDTO::getFacedbId).collect(Collectors.toList());
        List<Integer> authFacedbIds = getAuthResources(engineDTO.getUserId(), ResourceType.FACEDB, facedbIds);
        List<FacedbEngineDTO> resultList = facedbEngineDTOS.stream().filter(facedbEngineDTO -> authFacedbIds.contains(facedbEngineDTO.getFacedbId())).collect(Collectors.toList());
        resp.setData(resultList);
        return resp;
    }

    /**
     * 人像库与引擎绑定关系修改
     *
     * @param bindControlForm
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @PostMapping(value = {"/engine/bind/control"})
    @OpLog(model = ModuleCode.SYSTEM, desc = "人像库与引擎绑定关系修改", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> engineBindControl(@RequestBody @Validated({APIEngineBindGroup.class}) FacedbEngineBindForm bindControlForm, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = this.validite(bindingResult);
        try {
            bindControlForm.setFacedbIds(getAuthResources(bindControlForm.getUserId(), ResourceType.FACEDB, bindControlForm.getFacedbIds()));
            FacedbEngine facedbEngine = new FacedbEngine();
            BeanUtils.copyProperties(bindControlForm, facedbEngine);
            resp.setData(facedbService.bindEngineControl(facedbEngine));
        } catch (ServiceException e) {
            this.logger.error("人像库与引擎绑定关系修改失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

}