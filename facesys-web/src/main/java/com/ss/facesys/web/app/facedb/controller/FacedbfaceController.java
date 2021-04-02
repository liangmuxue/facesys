package com.ss.facesys.web.app.facedb.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.client.IFacedbfaceService;
import com.ss.facesys.data.collect.common.model.FacedbFace;
import com.ss.facesys.util.DateUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.web.app.facedb.form.FacedbFaceForm;
import com.ss.facesys.web.app.facedb.query.FacedbFaceQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 人像集
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/facedbface"})
public class FacedbfaceController extends BaseController {

    @Resource
    private IFacedbfaceService facedbfaceService;
    @Resource
    private IFacedbService facedbService;


    /**
     * 查询人像集分页列表
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/pages"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像集分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<FacedbFace>> getFacedbfacePages(@RequestBody @Validated({APIPageGroup.class}) FacedbFaceQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<FacedbFace>> resp = validite(bindingResult);
        FacedbFace facedbFace = new FacedbFace();
        BeanUtils.copyProperties(query, facedbFace);
        if (query.getAgeMin() != null && query.getAgeMax() != null) {
            facedbFace.setBirthdayMax(DateUtil.getDateAfterYears(-1 * query.getAgeMin(), null));
            facedbFace.setBirthdayMin(DateUtil.getDateAfterYears(-1 * query.getAgeMax(), null));
        }
        List<Integer> facedbIds = StringUtils.isNotBlank(query.getFacedbId()) ? ArraysUtils.asList(query.getFacedbId()).stream().map(Integer::parseInt).collect(Collectors.toList()) : null;
        facedbFace.setFacedbIds(getAuthResources(query.getUserId(), ResourceType.FACEDB, facedbIds).stream().map(String::valueOf).collect(Collectors.toList()));
        Page<FacedbFace> data = (Page) facedbfaceService.pages(facedbFace, query.getCurrentPage(), query.getPageSize());
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询人像集详细信息
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/detail"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像集详细信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<FacedbFace> getFacedbfaceDetail(@RequestBody @Validated({APIGetsGroup.class}) FacedbFaceQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<FacedbFace> resp = validite(bindingResult);
        FacedbFace facedbFace = new FacedbFace();
        facedbFace.setId(query.getId());
        facedbFace.setStatus(StatusEnum.EFFECT.getCode());
        resp.setData(facedbfaceService.selectOne(facedbFace));
        return resp;
    }

    /**
     * 新增人像集信息
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/insert"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "新增人像集信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertFacedbface(@RequestBody @Validated({APIAddGroup.class}) FacedbFaceForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        FacedbFace facedbFace = new FacedbFace();
        BeanUtils.copyProperties(form, facedbFace);
        facedbFace.setCreateUserId(form.getUserId());
        facedbFace.setCreateTime(ct);
        facedbFace.setUpdateUserId(form.getUserId());
        facedbFace.setUpdateTime(ct);
        try {
            resp.setData(facedbfaceService.insert(facedbFace));
            facedbService.updateFaceCount(facedbFace.getFacedbId(), 1);
        } catch (ServiceException e) {
            this.logger.error("新增人像集失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 修改人像集信息
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/update"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改人像集信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateFacedbface(@RequestBody @Validated({APIEditGroup.class}) FacedbFaceForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        FacedbFace facedbFace = new FacedbFace();
        BeanUtils.copyProperties(form, facedbFace);
        facedbFace.setUpdateUserId(form.getUserId());
        facedbFace.setUpdateTime(ct);
        FacedbFace orgDb = getOriginalDbObj(form.getId());
        facedbFace.setFaceId(orgDb.getFaceId());
        try {
            facedbfaceService.update(facedbFace, orgDb);
        } catch (ServiceException e) {
            this.logger.error("修改人像集失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 删除人像集
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/delete"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除人像集信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteFacedbface(@RequestBody @Validated({APIDeltGroup.class}) FacedbFaceForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        FacedbFace facedbFace = new FacedbFace();
        BeanUtils.copyProperties(form, facedbFace);
        facedbFace.setDeleteUserId(form.getUserId());
        facedbFace.setDeleteTime(ct);
        facedbFace.setStatus(StatusEnum.EXPIRE.getCode());
        FacedbFace orgDb = getOriginalDbObj(form.getId());
        facedbFace.setFaceId(orgDb.getFaceId());
        facedbFace.setFacedbId(orgDb.getFacedbId());
        try {
            facedbfaceService.delete(facedbFace);
            facedbService.updateFaceCount(facedbFace.getFacedbId(), -1);
        } catch (ServiceException e) {
            this.logger.error("删除人像集失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 人像集重提特征
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/reFeature"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "人像集重提特征", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> reFeature(@RequestBody @Validated({APIFeatureExtractionGroup.class}) FacedbFaceForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        FacedbFace orgDb = getOriginalDbObj(form.getId());
        try {
            facedbfaceService.reFeature(orgDb);
        } catch (ServiceException e) {
            this.logger.error("人像集重提特征失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    @PostMapping(value = {"/checkPic"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "校验图片类型", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Integer> checkPic(@RequestBody FacedbFaceForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            Integer integer = facedbfaceService.checkPic(form.getFacePath());
            resp.setData(integer);
        } catch (ServiceException e) {
            this.logger.error("校验图片类型失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    private FacedbFace getOriginalDbObj(Integer id) {
        FacedbFace vp = new FacedbFace();
        vp.setId(id);
        return facedbfaceService.selectOne(vp);
    }

}