package com.ss.spider.system.user.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.role.model.Role;
import com.ss.spider.system.role.service.RoleService;
import com.ss.spider.system.user.form.UserRoleForm;
import com.ss.spider.system.user.form.UserRoleQuery;
import com.ss.spider.system.user.model.UserRole;
import com.ss.spider.system.user.service.UserRoleService;
import com.ss.tools.ArraysUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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


@RestController
@RequestMapping({"/user/role"})
public class UserRoleController extends AbstractController {

    @Autowired
    @Qualifier("userRoleService")
    private UserRoleService<UserRole> userRoleService;
    @Autowired
    private RoleService<Role> roleService;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Role>> list(@RequestBody UserRoleQuery para) throws Exception {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(para, userRole);
        if (StringUtils.isNotBlank(para.getOpUserId())) {
            userRole.setUserId(para.getOpUserId());
        }
        ResponseEntity<List<Role>> resp = createSuccResponse();

        List<UserRole> userRoleList = this.userRoleService.list(userRole);
        List<String> roleIds = new ArrayList<String>();

        if (!CollectionUtils.isEmpty(userRoleList)) {

            for (UserRole ur : userRoleList) {
                roleIds.add(ur.getRoleId());
            }
            resp.setData(this.roleService.gets(roleIds));
        }

        return resp;
    }


    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "添加用户角色", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> add(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) UserRoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> roleIds = ArraysUtils.asList(para.getRoleIds());


        String createdUid = para.getCreatedUserid();

        List<UserRole> list = new ArrayList<UserRole>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();

            userRole.setRoleId(roleId);
            userRole.setUserId(para.getOpUserId());

            list.add(userRole);
        }

        try {
            this.userRoleService.batchSave(list, roleIds);
        } catch (MyBatisSystemException e) {
            this.logger.error("添加用户角色失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("添加用户角色失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("添加用户角色失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70001", desc = "删除用户角色", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) UserRoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> roleIdList = ArraysUtils.asList(para.getRoleIds());

        List<UserRole> list = new ArrayList<UserRole>();
        for (String roleId : roleIdList) {
            UserRole userRole = new UserRole();
            userRole.setUserId(para.getOpUserId());
            userRole.setRoleId(roleId);

            list.add(userRole);
        }

        try {
            this.userRoleService.delete(list);
        } catch (MyBatisSystemException e) {
            this.logger.error("删除用户角色失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除用户角色失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除用户角色失败,原因：", e);
            return createFailResponse();
        }

        return resp;
    }

}
