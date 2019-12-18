package com.ss.spider.system.organization.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.facesys.util.coordinate.GetCenterCoordinates;
import com.ss.facesys.util.coordinate.IscCoordinate;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.organization.form.OrgForm;
import com.ss.spider.system.organization.form.OrgQuery;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
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

import java.util.List;

/**
 * 单位管理
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/org"})
public class OrganizationController extends AbstractController {

    private final OrganizationService<Organization> organizationService;

    @Autowired
    public OrganizationController(@Qualifier("organizationService") OrganizationService<Organization> organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * 查询单位列表
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询单位列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Organization>> list(@RequestBody @Validated({APIListGroup.class}) OrgQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<List<Organization>> resp = validite(bindingResult);
        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);
        resp.setData(this.organizationService.list(org));
        return resp;
    }

    /**
     * 查询单位分页列表
     *
     * @param para
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询单位分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Organization>> pages(@RequestBody @Validated({APIPageGroup.class}) OrgQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<Organization>> resp = validite(bindingResult);
        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);
        Page<Organization> data = (Page) this.organizationService.pages(org, currPage, pageSize);
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询单位树
     *
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/tree"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询单位树", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Organization>> tree() throws BindException {
        ResponseEntity<List<Organization>> resp = createSuccResponse();
        try {
            resp.setData(this.organizationService.treeData());
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("单位树查询失败");
            this.logger.error("单位树查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询单位信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询单位信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Organization> get(@RequestBody @Validated({APIGetsGroup.class}) OrgQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Organization> resp = validite(bindingResult);
        resp.setData(this.organizationService.get(para.getOrgId()));
        return resp;
    }

    /**
     * 新增单位信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "新增单位信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({APIAddGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);
        long currentTime = DateUtils.getCurrentTime();
        org.setStatus(StatusEnum.EFFECT.getCode());
        org.setIsLinkage((short) StatusEnum.INVALID.getCode());
        org.setCreateTime(currentTime);
        org.setUpdateTime(currentTime);
        org.setCreateUserId(para.getUserId());
        org.setUpdateUserId(para.getUserId());
        // 坐标信息
        List<IscCoordinate> gisList;
        if (CollectionUtils.isNotEmpty(gisList = para.getGisList())) {
            IscCoordinate centerGis = GetCenterCoordinates.getCenterPoint(gisList);
            org.setLon(centerGis.getLongitude());
            org.setLat(centerGis.getLatitude());
            org.setGisArea(gisList);
        }
        try {
            resp.setData(this.organizationService.save(org));
        } catch (Exception e) {
            this.logger.error("新增单位失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 修改单位信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改单位信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({APIEditGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Organization org = new Organization();
        BeanUtils.copyProperties(para, org);
        org.setStatus(StatusEnum.EFFECT.getCode());
        org.setIsLinkage((short) StatusEnum.INVALID.getCode());
        org.setUpdateTime(DateUtils.getCurrentTime());
        org.setUpdateUserId(para.getUserId());
        // 坐标信息
        List<IscCoordinate> gisList;
        if (CollectionUtils.isNotEmpty(gisList = para.getGisList())) {
            IscCoordinate centerGis = GetCenterCoordinates.getCenterPoint(gisList);
            org.setLon(centerGis.getLongitude());
            org.setLat(centerGis.getLatitude());
            org.setGisArea(gisList);
        }
        try {
            this.organizationService.update(org);
        } catch (Exception e) {
            this.logger.error("修改组织单位失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 删除单位信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "删除单位信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({APIDeltGroup.class}) OrgForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> orgIds = ArraysUtils.asList(para.getOrgIds());
        try {
            // Thorough == 0 逻辑删除  == 1 物理删除
            if (para.getThorough() == 0) {
                this.organizationService.discard(orgIds, para.getUserId());
            } else {
                this.organizationService.delete(orgIds);
            }
        } catch (Exception e) {
            this.logger.error("删除分组信息失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    @RequestMapping(value = {"/insertOrgExcel"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "导入组织单位", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertOrgExcel(MultipartFile file, String userId) throws BindException {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            this.organizationService.insertOrg(file, userId);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("导入组织单位失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("导入组织单位失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

}