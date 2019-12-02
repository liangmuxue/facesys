package com.ss.spider.system.role.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.model.ResourceTree;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.spider.system.role.form.RoleResourceForm;
import com.ss.spider.system.role.form.RoleResourceQuery;
import com.ss.spider.system.role.model.RoleResource;
import com.ss.spider.system.role.service.RoleResourceService;
import com.ss.tools.ArraysUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

/**
* 角色权限操作
* @author chao
* @create 2019/10/9
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/role/resource"})
public class RoleResourceController extends AbstractController {

    @Autowired
    @Qualifier("roleResourceService")
    private RoleResourceService<RoleResource> roleResourceService;
    @Autowired
    @Qualifier("resourceService")
    private ResourceService<Resource> resourceService;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "查询角色权限列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Resource>> list(@RequestBody RoleResourceQuery para) throws Exception {
        RoleResource roleResource = new RoleResource();
        BeanUtils.copyProperties(para, roleResource);
        ResponseEntity<List<Resource>> resp = createSuccResponse();
        List<RoleResource> roleResourceList = this.roleResourceService.list(roleResource);
        List<String> resIds = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(roleResourceList)) {
            for (RoleResource rr : roleResourceList) {
                resIds.add(rr.getResourceId());
            }
            resp.setData(this.resourceService.gets(resIds));
        }
        return resp;
    }

    /**
     * 查询角色资源列表
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/tree"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "查询角色资源列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<ResourceTree>> tree(@RequestBody RoleResourceQuery para) throws Exception {
        ResponseEntity<List<ResourceTree>> resp = createSuccResponse();
        //查询角色资源列表
        List<ResourceTree> roleResourceList = this.roleResourceService.tree(para);
        resp.setData(roleResourceList);
        return resp;
    }


    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "新增角色权限", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> add(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) RoleResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> resourceIds = ArraysUtils.asList(para.getResourceIds());

        String roleId = para.getRoleId();
        String createdUId = para.getCreatedUserid();
        List<RoleResource> list = new ArrayList<RoleResource>();
        for (String resourceId : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(resourceId);
            roleResource.setRoleId(roleId);

            list.add(roleResource);
        }

        try {
            this.roleResourceService.batchSave(list, resourceIds);
        } catch (MyBatisSystemException e) {
            this.logger.error("给角色添加资源失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("给角色添加资源失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("给角色添加资源失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

    /**
     * 删除角色权限
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "删除角色权限", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) RoleResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> resourceIds = ArraysUtils.asList(para.getResourceIds());
        List<RoleResource> list = new ArrayList<RoleResource>();
        for (String resourceId : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(para.getRoleId());
            roleResource.setResourceId(resourceId);
            list.add(roleResource);
        }
        try {
            this.roleResourceService.delete(list);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("从角色中删除资源失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("从角色中删除资源失败,原因：", e);
            return createFailResponse();
        }

        return resp;
    }

    /**
     * 修改角色权限
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "修改角色权限", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) RoleResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        if ("1".equals(para.getRoleId())){
            resp = createFailResponse();
            resp.setMessage("初始角色权限不能修改！");
            return resp;
        }
        //得到要添加的权限
        List<String> resourceIds = ArraysUtils.asList(para.getResourceIds());
        //确定修改角色
        String roleId = para.getRoleId();
        List<RoleResource> list = new ArrayList<RoleResource>();
        for (String resourceId : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(resourceId);
            roleResource.setRoleId(roleId);
            list.add(roleResource);
        }
        try {
            //删除权限
            this.roleResourceService.delete(list);
            //添加权限
            this.roleResourceService.batchSave(list, resourceIds);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("角色修改资源失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("角色修改资源失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }
}
