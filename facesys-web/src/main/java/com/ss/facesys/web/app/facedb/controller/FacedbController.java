package com.ss.facesys.web.app.facedb.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbPeople;
import com.ss.facesys.data.collect.common.web.FacedbPeopleVO;
import com.ss.facesys.data.resource.client.IResourcePeopleService;
import com.ss.facesys.data.resource.common.model.People;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.web.app.facedb.form.FacedbForm;
import com.ss.facesys.web.app.facedb.query.FacedbQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.tools.DateUtils;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @Resource
    private IResourcePeopleService resourcePeopleService;

    /**
     * 查询人像库列表
     *
     * @return
     */
    @PostMapping(value = {"/list"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人像库列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Facedb>> getFacedblist() {
        ResponseEntity<List<Facedb>> resp = createSuccResponse();
        try {
            Facedb facedb = new Facedb();
            facedb.setStatus(StatusEnum.EFFECT.getCode());
            resp.setData(facedbService.getFacedbList(facedb));
        } catch (Exception e) {
            this.logger.error("查询人像库列表失败，原因：" + e.toString(), e);
            return createFailResponse(ResultCode.FACEDB_LIST_FAILED_CODE, "查询人像库列表失败");
        }
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
    public ResponseEntity<PageEntity<Facedb>> getFacedbPage(@RequestBody @Validated({APIPageGroup.class}) FacedbQuery query, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Facedb>> resp = validite(bindingResult);
        Facedb facedb = new Facedb();
        try {
            BeanUtils.copyProperties(query, facedb);
            Page<Facedb> data = (Page) facedbService.getFacedbPage(facedb, query.getCurrentPage(), query.getPageSize());
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查询人像库分页列表失败，原因：" + e.toString(), e);
            return createFailResponse(ResultCode.FACEDB_PAGE_FAILED_CODE, "查询人像库分页列表失败");
        }
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
    public ResponseEntity<Facedb> getFacedbDetail(@RequestBody @Validated({APIGetsGroup.class}) FacedbQuery query, BindingResult bindingResult) throws Exception {
        ResponseEntity<Facedb> resp = validite(bindingResult);
        try {
            Facedb facedb = new Facedb();
            facedb.setId(query.getId());
            facedb.setStatus(StatusEnum.EFFECT.getCode());
            resp.setData(facedbService.selectOne(facedb));
        } catch (Exception e) {
            this.logger.error("查询人像库信息失败，原因：" + e.toString(), e);
            return createFailResponse(ResultCode.FACEDB_DETAIL_FAILED_CODE, "查询人像库信息失败");
        }
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
    public ResponseEntity<String> insertFacedb(@RequestBody @Validated({APIAddGroup.class}) FacedbForm form, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            long ct = DateUtils.getCurrentTime();
            Facedb facedb = new Facedb();
            BeanUtils.copyProperties(form, facedb);
            facedb.setCreateUserId(form.getUserId());
            facedb.setCreateTime(ct);
            facedb.setUpdateUserId(form.getUserId());
            facedb.setUpdateTime(ct);
            facedb.setFaceCount(0);
            facedb.setMonitorState(CommonConstant.FACEDB_MONITOR_STATE_TBM);
            resp.setData(facedbService.insertFacedb(facedb));
        } catch (Exception e) {
            this.logger.error("新增人像库失败，原因：" + e.getMessage(), e);
            return createFailResponse(ResultCode.FACEDB_ADD_FAILED_CODE, "新增人像库失败");
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
    public ResponseEntity<String> updateFacedb(@RequestBody @Validated({APIEditGroup.class}) FacedbForm form, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            long ct = DateUtils.getCurrentTime();
            Facedb facedb = new Facedb();
            BeanUtils.copyProperties(form, facedb);
            facedb.setUpdateUserId(form.getUserId());
            facedb.setUpdateTime(ct);
            facedbService.updateFacedb(facedb);
        } catch (Exception e) {
            this.logger.error("修改人像库失败，原因：" + e.getMessage(), e);
            return createFailResponse(ResultCode.FACEDB_EDIT_FAILED_CODE, "修改人像库失败");
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
    public ResponseEntity<Map<String, Object>> deleteFacedb(@RequestBody @Validated({APIDeltGroup.class}) FacedbForm form, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            long ct = DateUtils.getCurrentTime();
            Facedb facedb = new Facedb();
            BeanUtils.copyProperties(form, facedb);
            facedb.setDeleteUserId(form.getUserId());
            facedb.setDeleteTime(ct);
            facedb.setStatus(StatusEnum.EXPIRE.getCode());
            facedbService.deleteFacedb(facedb);
        } catch (Exception e) {
            this.logger.error("删除重点人员库失败，原因：" + e.toString(), e);
            return createFailResponse(ResultCode.FACEDB_DELETE_FAILED_CODE, "删除人像库失败");
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
    public ResponseEntity<Map<String, Object>> reFeature(@RequestBody @Validated({APIFeatureExtractionGroup.class}) FacedbForm form, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            facedbService.reFeature(form.getFacedbId(), form.getFaceDBFaceStateInvalid());
        } catch (Exception e) {
            this.logger.error("人像库重提特征失败，原因：" + e.toString(), e);
            return createFailResponse(ResultCode.FACEDB_REFEATURE_FAILED_CODE, "人像库重提特征失败");
        }
        return resp;
    }


    /*  ****************************************弃用**************************************** */

    /**
     * 查询重点人员分页列表
     *
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询重点人员分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<FacedbPeopleVO>> getFacedbPeoplePage(@RequestBody @Validated({APIPageGroup.class}) FacedbPeopleVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<FacedbPeopleVO>> resp = validite(bindingResult);
        try {
            Page<FacedbPeopleVO> data = (Page) facedbService.getFacedbPeoplePage(vo);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_PEOPLE_PAGE_FAILED_CODE);
            this.logger.error("查询重点人员分页列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询重点人员分页列表失败");
        }
        return resp;
    }

    /**
     * 新增重点人员
     *
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/insert"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "新增重点人员", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, Object>> insertFacedbPeople(@RequestBody @Validated({APIAddGroup.class}) FacedbPeopleVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 校验：1.人口是否已经存在于人像库中 2.人口是否维护人像照
            for (String peopleId : vo.getPeopleId().split(CommonConstant.SPLIT_COMMA)) {
                People people = new People();
                people.setPeopleId(peopleId);
                people = resourcePeopleService.selectOne(people);
                // 校验人员是否已经在人像库中
                if (CollectionUtils.isNotEmpty(facedbService.selectFacedbPeopleList(new FacedbPeople(peopleId, vo.getFacedbId())))) {
                    resp = createFailResponse();
                    resp.setCode(ResultCode.FACEDB_PEOPLE_ADD_FAILED_CODE);
                    resp.setMessage("新增重点人员失败：" + people.getPeopleName() + "已经存在于此人员库中！");
                    return resp;
                }
                // 校验人员是否维护人像照
                if (StringUtils.isBlank(people.getFacePic())) {
                    resp = createFailResponse();
                    resp.setCode(ResultCode.FACEDB_PEOPLE_ADD_FAILED_CODE);
                    resp.setMessage("新增重点人员失败：" + people.getPeopleName() + "未维护人像照！");
                    return resp;
                }
            }
            // 新增重点人员
            Map<String, Object> resultMap = facedbService.insertFacedbPeople(new FacedbPeople(vo.getUserIds(), vo.getRemark(), vo.getPeopleId(), vo.getFacedbId()));
            if (!(boolean) (resultMap.get(CommonConstant.SUCCESS_EN_CODE))) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_PEOPLE_ADD_FAILED_CODE);
                resp.setMessage("新增重点人员失败，原因：" + resultMap.get("message"));
                return resp;
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_PEOPLE_ADD_FAILED_CODE);
            this.logger.error("新增重点人员失败，原因：" + e.toString(), e);
            resp.setMessage("新增重点人员失败");
        }
        return resp;
    }

    /**
     * 移除重点人员
     *
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "移除重点人员", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, Object>> deleteFacedbPeople(@RequestBody @Validated({APIDeltGroup.class}) FacedbPeopleVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            FacedbPeople param = facedbService.selectFacedbPeopleList(new FacedbPeople(vo.getId())).get(0);
            param.setUserIds(vo.getUserIds());
            Map<String, Object> resultMap = facedbService.deleteFacedbPeople(param);
            if (!(boolean) (resultMap.get(CommonConstant.SUCCESS_EN_CODE))) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_PEOPLE_DELETE_FAILED_CODE);
                resp.setMessage("移除重点人员失败，原因： " + resultMap.get("message"));
                return resp;
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_PEOPLE_DELETE_FAILED_CODE);
            this.logger.error("移除重点人员失败，原因：" + e.toString(), e);
            resp.setMessage("移除重点人员失败");
        }
        return resp;
    }

}
