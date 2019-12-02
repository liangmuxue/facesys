package com.ss.isc.web.manage.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IResourcePeopleService;
import com.ss.isc.data.resource.common.model.People;
import com.ss.isc.data.resource.common.web.PeopleQueryVO;
import com.ss.isc.data.resource.common.web.PeopleVO;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.util.DateUtil;
import com.ss.isc.util.IDCardUtil;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.em.Enums;
import com.ss.isc.util.export.TemplateReflectUtils;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 实有人口web请求
 * @author FrancisYs
 * @date 2019/08/09
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/people"})
public class ResourcePeopleController extends BaseController {

    private static final Log LOG = LogFactory.getLog(ResourcePeopleController.class);

    @Resource
    private IResourcePeopleService peopleService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IOrganizationRegionService oRegionService;

    /**
     * 实有人口查询分页列表
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口信息分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<People>> page(@RequestBody @Validated({APIPageGroup.class}) PeopleQueryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<People>> resp = validite(bindingResult);
        try {
            para.setVillageCodes(getVillageCodes(para.getVillageCodes()));
            // 未传入小区条件时，默认设置小区条件为当前用户权限下的全部小区
            if (StringUtils.isBlank(para.getVillageCodes())) {
                String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
                para.setVillageCodes(villageCodes);
            }
            // 查询分页列表
            Page<People> data = (Page) this.peopleService.page(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_PAGE_FAILED_CODE);
            resp.setMessage("实有人口分页查询失败");
            LOG.error("人口分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询实有人口详情
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口信息详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<People> get(@RequestBody @Validated({APIGetsGroup.class}) PeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<People> resp = validite(bindingResult);
        try {
            resp.setData(this.peopleService.get(para.getId()));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_INFO_FAILED_CODE);
            resp.setMessage("实有人口信息详情查询失败");
            this.logger.error("实有人口信息详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增实有人口
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口信息新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({APIAddGroup.class}) PeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            // 手机号格式校验
            if (StringUtils.isNotBlank(para.getPhoneNo()) && !Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", para.getPhoneNo())) {
                return createFailResponse(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE, "手机号格式错误!");
            }
            // 证件信息校验：1. 身份证号校验（证件类型为身份证时） 2. 证件唯一校验：[证件类型 + 证件号]
            JSONObject credentialCheck = credentialCheck(para.getCredentialType(), para.getCredentialNo(), null);
            if (credentialCheck != null && !credentialCheck.getBoolean(CommonConstant.PARAM_VALID_KEY)) {
                return createFailResponse(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE, credentialCheck.getString(CommonConstant.PARAM_MSG_KEY));
            }
            /* 校验新增来源 source:疑似新增时校验处置相关主键 */
            if (para.getSource() != null && para.getSource() == Enums.peopleSource.APPERCEIVE_DISCOVER.getCode() && StringUtils.isBlank(para.getRecordId())) {
                return createFailResponse(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE, "疑似新进记录编号不能为空！");
            }
            People people = new People();
            BeanUtils.copyProperties(para, people);
            String message = this.peopleService.save(people);
            if (CommonConstant.SUCCESS_EN_CODE.equals(message)) {
                resp.setData(message);
            } else {
                // 失败的情况分为添加人像集失败或者其他失败原因
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE);
                resp.setMessage(CommonConstant.ERROR_EN_OCEAN_CODE.equals(message) ? "欧神人像集添加失败" : "操作失败，请联系管理员");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE);
            resp.setMessage("新增实有人口失败");
            LOG.error("新增实有人口失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改实有人口信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口信息修改", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({APIEditGroup.class}) PeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            // 手机号格式校验
            if (StringUtils.isNotBlank(para.getPhoneNo()) && !Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", para.getPhoneNo())) {
                return createFailResponse(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE, "手机号格式错误!");
            }
            // 证件信息校验：1. 身份证号校验（证件类型为身份证时） 2. 证件唯一校验：[证件类型 + 证件号]
            JSONObject credentialCheck = credentialCheck(para.getCredentialType(), para.getCredentialNo(), para.getId());
            if (credentialCheck != null && !credentialCheck.getBoolean(CommonConstant.PARAM_VALID_KEY)) {
                return createFailResponse(ResultCode.RESOURCE_PEOPLE_ADD_FAILED_CODE, credentialCheck.getString(CommonConstant.PARAM_MSG_KEY));
            }
            People people = new People();
            BeanUtils.copyProperties(para, people);
            String message = this.peopleService.edit(people);
            if (CommonConstant.SUCCESS_EN_CODE.equals(message)) {
                resp.setData(message);
            } else {
                // 失败的情况分为添加人像集失败或者其他失败原因
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_PEOPLE_EDIT_FAILED_CODE);
                resp.setMessage(CommonConstant.ERROR_EN_OCEAN_CODE.equals(message) ? "欧神人像集修改失败" : "操作失败，请联系管理员");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_EDIT_FAILED_CODE);
            resp.setMessage("修改实有人口失败");
            LOG.error("修改实有人口失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除实有人口（支持批量）
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口信息删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({APIDeltGroup.class}) PeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.peopleService.delete(para);
            resp.setData(message);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_DELETE_FAILED_CODE);
            resp.setMessage("删除实有人口失败");
            LOG.error("删除实有人口失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 收藏/取消收藏实有人口
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/collect"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口收藏/取消收藏", type = OperaTypeEnum.OTHER)
    public ResponseEntity<String> collect(@RequestBody @Validated({APIOpStatusGroup.class}) PeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.peopleService.collect(para.getUserIds(), para.getPeopleIds(), para.getOperationType());
            resp.setData(message);
        } catch (Exception e) {
            String message = para.getOperationType() == CommonConstant.STATUS_COLLECT ? "收藏" : "取消收藏";
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_COLLECT_FAILED_CODE);
            resp.setMessage(message + "实有人口失败");
            LOG.error(message + "实有人口失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/importData"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有人口数据导入", type = OperaTypeEnum.OTHER)
    public ResponseEntity<Object> importData(HttpServletRequest request, MultipartFile file) throws Exception {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            String path = "people";
            this.logger.info("实有人口导入开始" + DateUtil.getCurrentDayTime());
            long startTime = System.currentTimeMillis();
            Map<String, Object> map = TemplateReflectUtils.getDataList(People.class, path, file, false);

            List<People> tempList = (List) map.get("list");

            Map<String, String> imagePaths = (Map) map.get("imagePaths");

            Map<String, String> res = this.peopleService.batchImport(tempList, imagePaths);
            if ("success".equals(res.get("result"))) {
                resp.setMessage((String) res.get("message"));
            } else {

                resp = createFailResponse();
                resp.setMessage((String) res.get("message"));
            }
            this.logger.info("实有人口导入结束" +
                    DateUtil.getCurrentDayTime() + "耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_PEOPLE_IMPORT_FAILED_CODE);
            resp.setMessage("实有人口数据导入失败");
            this.logger.error("实有人口导入失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 证件信息校验：1. 身份证号校验（证件类型为身份证时） 2. 证件唯一校验：[证件类型 + 证件号]
     * @param credentialType 证件类型
     * @param credentialNo 证件号
     * @param id 人口主键
     * @return 返回JSON结果：[valid][msg]
     */
    private JSONObject credentialCheck(Integer credentialType, String credentialNo, Integer id) {
        JSONObject result = new JSONObject();
        result.put(CommonConstant.PARAM_VALID_KEY, true);
        // 身份证时，校验身份证号格式
        if (credentialType != null && credentialType == Enums.credentialType.ID_CARD.getCode() && !IDCardUtil.checkCardId(credentialNo)) {
            result.put(CommonConstant.PARAM_VALID_KEY, false);
            result.put(CommonConstant.PARAM_MSG_KEY, "身份证号格式错误！");
            return result;
        }
        // 证件类型 + 证件号 唯一性校验
        boolean exist = false;
        People people = new People();
        people.setCredentialType(credentialType);
        people.setCredentialNo(credentialNo);
        people.setIsLeave(CommonConstant.DELETE_FLAG_EXIST);
        List<People> checkList = peopleService.selectList(people);
        // 新增时查到不为空即存在重复
        if (id == null && CollectionUtils.isNotEmpty(checkList)) {
            exist = true;
        }
        // 修改时查到数据库中存在非当前主键对应的用户时即存在重复
        if (id != null && CollectionUtils.isNotEmpty(checkList) && !id.equals(checkList.get(0).getId())) {
            exist = true;
        }
        if (exist) {
            result.put(CommonConstant.PARAM_VALID_KEY, false);
            result.put(CommonConstant.PARAM_MSG_KEY, "当前证件号已存在，请勿重复录入！");
            return result;
        }
        return result;
    }

}
