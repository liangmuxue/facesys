package com.ss.spider.system.department.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.department.form.DepartForm;
import com.ss.spider.system.department.form.DepartQuery;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.department.service.DepartmentService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.ss.valide.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/depart"})
public class DepartmentController extends AbstractController {

    private final DepartmentService<Department> departmentService;

    @Autowired
    public DepartmentController(@Qualifier("departmentService") DepartmentService<Department> departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 查询部门信息列表
     *
     * @param para
     * @return
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询部门信息列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Department>> list(@RequestBody DepartQuery para) {
        ResponseEntity<List<Department>> resp = createSuccResponse();
        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        depart.setOrgIdList(ArraysUtils.asList(para.getOrgIds()));
        List<Department> data = this.departmentService.list(depart);
        resp.setData(data);
        return resp;
    }

    /**
     * 分页查询部门信息列表
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "分页查询部门信息列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Department>> pages(@RequestBody @Validated({APIPageGroup.class}) DepartQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<Department>> resp = validite(bindingResult);
        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        depart.setOrgIdList(ArraysUtils.asList(para.getOrgIds()));
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);
        Page<Department> data = (Page) this.departmentService.pages(depart, currPage, pageSize);
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询部门详情信息
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询部门详情信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Department> get(@RequestBody @Validated({APIGetsGroup.class}) DepartQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Department> resp = validite(bindingResult);
        resp.setData(this.departmentService.get(para.getDepartId()));
        return resp;
    }

    /**
     * 新增部门
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "新增部门信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({APIAddGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        long currentTime = DateUtils.getCurrentTime();
        depart.setStatus(StatusEnum.EFFECT.getCode());
        depart.setCreateTime(currentTime);
        depart.setUpdateTime(currentTime);
        depart.setCreateUserId(para.getUserId());
        depart.setUpdateUserId(para.getUserId());
        try {
            resp.setData(this.departmentService.save(depart));
        } catch (Exception e) {
            this.logger.error("保存部门信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 修改部门
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改部门信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({APIEditGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        Department depart = new Department();
        BeanUtils.copyProperties(para, depart);
        depart.setStatus(StatusEnum.EFFECT.getCode());
        depart.setUpdateTime(DateUtils.getCurrentTime());
        depart.setUpdateUserId(para.getUserId());
        try {
            this.departmentService.update(depart);
        } catch (Exception e) {
            this.logger.error("修改部门信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 删除部门
     *
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "删除部门信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({APIDeltGroup.class}) DepartForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> departIds = ArraysUtils.asList(para.getDepartIds());
        try {
            if (para.getThorough() == 0) {
                this.departmentService.discard(departIds, para.getUserId());
            } else {
                this.departmentService.delete(departIds);
            }
        } catch (Exception e) {
            this.logger.error("删除部门信息失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

}
