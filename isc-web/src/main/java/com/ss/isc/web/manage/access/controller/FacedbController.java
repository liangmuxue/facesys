package com.ss.isc.web.manage.access.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.collect.client.IFacedbService;
import com.ss.isc.data.collect.common.model.Facedb;
import com.ss.isc.data.collect.common.model.FacedbPeople;
import com.ss.isc.data.collect.common.web.FacedbPeopleVO;
import com.ss.isc.data.collect.common.web.FacedbVO;
import com.ss.isc.data.resource.client.IResourcePeopleService;
import com.ss.isc.data.resource.common.model.People;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * FacedbController 人像库请求
 * @author FrancisYs
 * @date 2019/8/06
 * @update 2019/8/22
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/facedb"})
public class FacedbController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FacedbController.class);

    @Resource
    private IFacedbService facedbService;
    @Resource
    private IResourcePeopleService resourcePeopleService;

    /**
     * 查询重点人像库列表
     * @return
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询重点人像库列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Facedb>> getFacedblist() {
        ResponseEntity<List<Facedb>> resp = createSuccResponse();
        try {
            Facedb facedb = new Facedb();
            facedb.setModel(CommonConstant.FACEDB_MODEL_SPECIAL);
            List<Facedb> facedbList = facedbService.getFacedbList(facedb);
            resp.setData(facedbList);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_LIST_FAILED_CODE);
            this.logger.error("查询重点人像库列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询重点人像库列表失败");
        }
        return resp;
    }

    /**
     * 查询重点人员库分页列表
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询重点人员库分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Facedb>> getFacedbPage(@RequestBody @Validated({APIPageGroup.class}) FacedbVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Facedb>> resp = validite(bindingResult);
        try {
            vo.setModel(CommonConstant.FACEDB_MODEL_SPECIAL);
            Page<Facedb> data = (Page) facedbService.getFacedbPage(vo);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_PAGE_FAILED_CODE);
            this.logger.error("查询重点人员库分页列表失败，原因：" + e.toString(), e);
            resp.setMessage("查询重点人员库分页列表失败");
        }
        return resp;
    }

    /**
     * 新增重点人员库
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "新增重点人员库", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, Object>> insertFacedb(@RequestBody @Validated({APIAddGroup.class}) FacedbVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 校验是否存在同名人像库
            Facedb facedb = new Facedb();
            facedb.setName(vo.getName());
            facedb = facedbService.selectOne(facedb);
            if (facedb != null) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_ADD_FAILED_CODE);
                resp.setMessage("新增重点人员库失败，已存在此名称人员库： " + vo.getName());
                return resp;
            }
            // 新增人像库
            facedb = new Facedb();
            vo.setModel(CommonConstant.FACEDB_MODEL_SPECIAL);
            BeanUtils.copyProperties(vo, facedb);
            Map<String, Object> map = facedbService.insertFacedb(facedb);
            if (!(boolean)(map.get(CommonConstant.SUCCESS_EN_CODE))) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_ADD_FAILED_CODE);
                resp.setMessage("新增重点人员库失败，原因： " + map.get("message"));
                return resp;
            }
            // 成功新增获取返回的id
            resp.setData((Map<String, Object>)map.get("data"));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_ADD_FAILED_CODE);
            this.logger.error("新增重点人员库失败，原因：" + e.toString(), e);
            resp.setMessage("新增重点人员库失败");
        }
        return resp;
    }

    /**
     * 查询重点人员库信息
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询重点人员库信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Facedb> getFacedbDetail(@RequestBody @Validated({APIGetsGroup.class}) FacedbVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Facedb> resp = validite(bindingResult);
        try {
            Facedb facedb = new Facedb();
            facedb.setId(vo.getId());
            facedb = facedbService.selectOne(facedb);
            resp.setData(facedb);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_DETAIL_FAILED_CODE);
            this.logger.error("查询重点人员库信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询重点人员库信息失败");
        }
        return resp;
    }

    /**
     * 修改重点人员库信息
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改重点人员库信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, Object>> updateFacedb(@RequestBody @Validated({APIEditGroup.class}) FacedbVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 校验是否存在同名人像库
            Facedb facedb = new Facedb();
            facedb.setName(vo.getName());
            facedb = facedbService.selectOne(facedb);
            if (facedb != null && !facedb.getId().equals(vo.getId())) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_EDIT_FAILED_CODE);
                resp.setMessage("修改重点人员库失败，已存在此名称人员库： " + vo.getName());
                return resp;
            }
            // 修改人像库
            facedb = new Facedb();
            vo.setModel(CommonConstant.FACEDB_MODEL_SPECIAL);
            BeanUtils.copyProperties(vo, facedb);
            Map<String, Object> map = facedbService.updateFacedb(facedb);
            if (!(boolean)(map.get(CommonConstant.SUCCESS_EN_CODE))) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_ADD_FAILED_CODE);
                resp.setMessage("修改重点人员库失败，原因： " + map.get("message"));
                return resp;
            }
            // 成功修改获取返回的id
            resp.setData((Map<String, Object>)map.get("data"));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_EDIT_FAILED_CODE);
            this.logger.error("修改重点人员库失败，原因：" + e.toString(), e);
            resp.setMessage("修改重点人员库失败");
        }
        return resp;
    }

    /**
     * 删除重点人员库
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "删除重点人员库", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, Object>> deleteFacedb(@RequestBody @Validated({APIDeltGroup.class}) FacedbVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            // 校验人员库下有无关联人口数据
            FacedbPeople facedbPeople = new FacedbPeople();
            facedbPeople.setFacedbId(vo.getId());
            List<FacedbPeople> facedbPeopleList = facedbService.selectFacedbPeopleList(facedbPeople);
            if (CollectionUtils.isNotEmpty(facedbPeopleList)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.FACEDB_DELETE_FAILED_CODE);
                resp.setMessage("删除重点人员库失败：此重点人员库下有关联人员数据");
                return resp;
            }
            // 删除人像库
            Facedb facedb = new Facedb();
            BeanUtils.copyProperties(vo, facedb);
            facedbService.deleteFacedb(facedb);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.FACEDB_DELETE_FAILED_CODE);
            this.logger.error("删除重点人员库失败，原因：" + e.toString(), e);
            resp.setMessage("删除重点人员库失败");
        }
        return resp;
    }

    /**
     * 查询重点人员分页列表
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询重点人员分页列表", type = OperaTypeEnum.SELECT)
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
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "新增重点人员", type = OperaTypeEnum.ADD)
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
            if (!(boolean)(resultMap.get(CommonConstant.SUCCESS_EN_CODE))) {
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
     * @param vo
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/people/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "移除重点人员", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, Object>> deleteFacedbPeople(@RequestBody @Validated({APIDeltGroup.class}) FacedbPeopleVO vo, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            FacedbPeople param = facedbService.selectFacedbPeopleList(new FacedbPeople(vo.getId())).get(0);
            param.setUserIds(vo.getUserIds());
            Map<String, Object> resultMap = facedbService.deleteFacedbPeople(param);
            if (!(boolean)(resultMap.get(CommonConstant.SUCCESS_EN_CODE))) {
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
