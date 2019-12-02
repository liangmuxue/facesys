package com.ss.spider.system.department.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.department.form.DepartForm;
import com.ss.spider.system.department.form.DepartQuery;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.department.service.DepartmentService;
import com.ss.spider.system.user.model.User;
import com.ss.spider.system.user.service.UserService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.github.pagehelper.Page;

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


@RestController
@RequestMapping({"/depart"})
public class DepartmentController extends AbstractController {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService<Department> departmentService;
    @Autowired
    @Qualifier("userService")
    private UserService<User> userService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70004", desc = "分页查询部门信息列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Department>> pages(@RequestBody DepartQuery para) throws BindException {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);

        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        depart.setOrgIdList(para.getOrgIds());

        Page<Department> data = (Page) this.departmentService.pages(depart, currPage, pageSize);

        ResponseEntity<PageEntity<Department>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));

        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Department>> list(@RequestBody DepartQuery para) throws BindException {
        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        depart.setOrgIdList(para.getOrgIds());
        List<Department> data = this.departmentService.list(depart);
        ResponseEntity<List<Department>> resp = createSuccResponse();
        resp.setData(data);
        return resp;
    }


    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "70004", desc = "查询部门详情信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Department> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) DepartQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Department> resp = validite(bindingResult);
        resp.setData(this.departmentService.get(para.getDepartId()));

        return resp;
    }


    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "70004", desc = "新增部门信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);

        depart.setStatus(Integer.valueOf(StatusEnum.EFFECT.getCode()));
        depart.setCreatedTime(Long.valueOf(DateUtils.getCurrentTime()));
        depart.setUpdatedTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            resp.setData(this.departmentService.save(depart));
        } catch (MyBatisSystemException e) {
            this.logger.error("保存部门信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("保存部门信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("保存部门信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "70004", desc = "修改部门信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);


        depart.setUpdatedTime(Long.valueOf(DateUtils.getCurrentTime()));
        depart.setStatus(Integer.valueOf(StatusEnum.EFFECT.getCode()));

        try {
            this.departmentService.update(depart);
        } catch (MyBatisSystemException e) {
            this.logger.error("修改部门信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("修改部门信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("修改部门信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70004", desc = "删除部门信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> departIds = ArraysUtils.asList(para.getDepartIds());

        try {
            Integer count = this.userService.getUserCountByDepartIds(departIds);
            if (count.intValue() > 0) {
                throw new ServiceException("当前删除的部门还有人员存在,请先移除人员之后操作.");
            }
            if (para.getThorough() == 0) {
                this.departmentService.discard(departIds, para.getDeletedUserid());
            } else {
                this.departmentService.delete(departIds);
            }
        } catch (MyBatisSystemException e) {
            this.logger.error("删除部门信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除部门信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除部门信息失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

}
