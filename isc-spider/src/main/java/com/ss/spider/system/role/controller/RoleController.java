package com.ss.spider.system.role.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.enums.SystemInitFlagEnum;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.role.form.RoleForm;
import com.ss.spider.system.role.form.RoleQuery;
import com.ss.spider.system.role.model.Role;
import com.ss.spider.system.role.service.RoleService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.github.pagehelper.Page;

import java.util.Arrays;
import java.util.List;
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
* 权限角色
* @author chao
* @create 2019/10/8
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/role"})
public class RoleController extends AbstractController {

    @Autowired
    @Qualifier("roleService")
    private RoleService<Role> roleService;

    /**
     * 权限角色分页查询
     * @param para
     * @return
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "分页查询角色列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Role>> pages(@RequestBody RoleQuery para) {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);
        Role role = new Role();
        BeanUtils.copyProperties(para, role);
        //查询角色
        Page<Role> data = (Page) this.roleService.pages(role, currPage, pageSize);
        ResponseEntity<PageEntity<Role>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询角色
     * @param para
     * @return
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Role>> list(@RequestBody RoleQuery para) {
        Role role = new Role();
        BeanUtils.copyProperties(para, role);
        //查询角色
        List<Role> data = this.roleService.list(role);
        ResponseEntity<List<Role>> resp = createSuccResponse();
        resp.setData(data);
        return resp;
    }

    /**
     * 通过主键获取角色信息
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "获取角色信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Role> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) RoleQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Role> resp = validite(bindingResult);
        resp.setData(this.roleService.get(para.getRoleId()));
        return resp;
    }

    /**
     * 添加权限角色
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "新增角色信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) RoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Role role = new Role();
        BeanUtils.copyProperties(para, role);
        if (role.getStatus() == null) {
            //设置状态为启用
            role.setStatus(StatusEnum.EFFECT.getCode());
        }
        role.setInitFlag(SystemInitFlagEnum.NO.getCode());
        role.setCreatedTime(DateUtils.getCurrentTime());
        role.setUpdatedTime(DateUtils.getCurrentTime());
        try {
            //添加角色
            resp.setData(this.roleService.save(role));
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("新增角色基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("新增角色基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 修改权限角色
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "修改角色信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) RoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Role role = new Role();
        BeanUtils.copyProperties(para, role);
        role.setUpdatedTime(DateUtils.getCurrentTime());
        try {
            //修改角色
            this.roleService.update(role);
        } catch (Exception e) {
            this.logger.error("编辑角色基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }

        return resp;
    }

    /**
     * 切换角色状态
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/opStatus"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "切换角色状态", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> opStatus(@RequestBody @Validated({com.ss.valide.APIOpStatusGroup.class}) RoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> roleIds = ArraysUtils.asList(para.getRoleIds());
        List<String> result = Arrays.asList(para.getRoleIds().split(","));
        for (String roleId: result) {
            if("1".equals(roleId)){
                resp = createFailResponse();
                resp.setMessage("初始角色状态不能修改！");
                return resp;
            }
        }
        try {
            //切换角色状态
            this.roleService.opStatus(roleIds, para.getStatus());
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("角色启用停用失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("角色启用停用失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 删除权限角色
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70006", desc = "删除角色信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) RoleForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> roleIds = ArraysUtils.asList(para.getRoleIds());
        try {
            for (String role: roleIds) {
                if ("1".equals(role)){
                    resp.setMessage("初始角色不能删除！");
                    return resp;
                }
            }
            if (para.getThorough() == 0) {
                //逻辑删除
                this.roleService.discard(roleIds, para.getDeletedUserid());
            } else {
                //物理删除
                this.roleService.delete(roleIds);
            }
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("删除角色基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除角色基本信息失败，原因：", e);
            return createFailResponse();
        }
        return resp;
    }

}
