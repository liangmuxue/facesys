package com.ss.spider.system.organization.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.interceptor.cache.beans.CacheUserInfo;
import com.ss.spider.interceptor.service.UserInfoCacheService;
import com.ss.spider.system.organization.form.OrgForm;
import com.ss.spider.system.organization.form.OrgQuery;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.github.pagehelper.Page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping({"/org"})
public class OrganizationController extends AbstractController {

    @Autowired
    @Qualifier("organizationService")
    private OrganizationService<Organization> organizationService;
    @Autowired
    private UserInfoCacheService userInfoCacheService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "分页查询单位列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Organization>> pages(@RequestBody OrgQuery para) throws BindException {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);

        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);

        Page<Organization> data = (Page) this.organizationService.pages(org, currPage, pageSize);

        ResponseEntity<PageEntity<Organization>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));

        return resp;
    }


    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "查询单位信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Organization> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) OrgQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Organization> resp = validite(bindingResult);

        resp.setData(this.organizationService.get(para.getOrgId()));

        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Organization>> list(@RequestBody @Validated({com.ss.valide.APIListGroup.class}) OrgQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<List<Organization>> resp = validite(bindingResult);
        Organization org = new Organization();

        if (para.getDataType().intValue() == 1 &&
                para != null && StringUtils.isNotEmpty(para.getUserId())) {
            CacheUserInfo cacheUserInfo = this.userInfoCacheService.getCacheUserInfo("USERINFO_" + para.getUserId());
            if (!cacheUserInfo.getIsSuperRole()) {
                org.setDeparth(cacheUserInfo.getOrgId());
            }
        }

        BeanUtils.copyProperties(para, org);
        resp.setData(this.organizationService.list(org));

        return resp;
    }


    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "新增单位信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);

        org.setStatus(Integer.valueOf(StatusEnum.EFFECT.getCode()));
        org.setIsLinkage(Short.valueOf((short) StatusEnum.INVALID.getCode()));
        org.setCreateTime(Long.valueOf(DateUtils.getCurrentTime()));
        org.setUpdateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            resp.setData(this.organizationService.save(org));
        } catch (MyBatisSystemException e) {
            this.logger.error("新增组织单位失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("新增组织单位失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("新增组织单位失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "修改单位信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);


        org.setStatus(Integer.valueOf(StatusEnum.EFFECT.getCode()));
        org.setIsLinkage(Short.valueOf((short) StatusEnum.INVALID.getCode()));
        org.setUpdateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.organizationService.update(org);
        } catch (MyBatisSystemException e) {
            this.logger.error("修改组织单位失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("修改组织单位失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("修改组织单位失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "删除单位信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> orgIds = ArraysUtils.asList(para.getOrgIds());
        try {
            //Thorough==0逻辑删除  ==1物理删除
            if (para.getThorough() == 0) {
                this.organizationService.discard(orgIds, para.getDeletedUserid());
            } else {
                this.organizationService.delete(orgIds);
            }
        } catch (MyBatisSystemException e) {
            this.logger.error("删除分组信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除分组信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除分组信息失败，原因：", e);
            return createFailResponse();
        }

        return resp;
    }

    @RequestMapping(value = {"/insertOrgExcel"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "导入组织机构", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertOrgExcel(MultipartFile file,String userId) throws BindException {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            this.organizationService.insertOrg(file,userId);
        } catch (MyBatisSystemException e) {
            this.logger.error("导入组织机构失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("导入组织机构失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("导入组织机构失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }
}
