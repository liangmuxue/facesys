package com.ss.spider.system.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.resource.form.ResourceForm;
import com.ss.spider.system.resource.form.ResourceQuery;
import com.ss.spider.system.resource.form.UserResourceQuery;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.tools.ArraysUtils;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.Page;
import java.util.HashMap;
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
@RequestMapping({"/resource"})
public class ResourceController extends AbstractController {

    @Autowired
    @Qualifier("resourceService")
    private ResourceService<Resource> resourceService;

    /**
     * 查询用户资源列表
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/query"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "查询用户资源列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Resource>> query(@RequestBody @Validated final UserResourceQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<List<Resource>> resp = validite(bindingResult);
        resp.setData(this.resourceService.query(new HashMap<String, Object>() {
            {
                put("userId", para.getUserId());
                put("appId", para.getAppId());
            }
        }));
        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "查询资源列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Resource>> list(@RequestBody ResourceQuery para) throws BindException {
        Resource res = new Resource();
        BeanUtils.copyProperties(para, res);

        ResponseEntity<List<Resource>> resp = createSuccResponse();
        resp.setData(this.resourceService.list(res));

        return resp;
    }


    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "分页查询资源列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Resource>> pages(@RequestBody ResourceQuery para) throws BindException {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);

        Resource res = new Resource();
        BeanUtils.copyProperties(para, res);

        Page<Resource> data = (Page) this.resourceService.pages(res, currPage, pageSize);

        ResponseEntity<PageEntity<Resource>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));

        return resp;
    }


    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "获取资源信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Resource> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) ResourceQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<Resource> resp = validite(bindingResult);

        resp.setData(this.resourceService.get(para.getResourceId()));

        return resp;
    }


    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "新增资源信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) ResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        Resource res = new Resource();
        BeanUtils.copyProperties(para, res);


        res.setResourceId(UUIDUtils.getUUID());
        res.setCreateTime(Long.valueOf(DateUtils.getCurrentTime()));
        res.setUpdateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            resp.setData(this.resourceService.save(res));
        } catch (MyBatisSystemException e) {
            this.logger.error("新增资源基本信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("新增资源基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("新增资源基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "修改资源信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) ResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);


        Resource res = new Resource();
        BeanUtils.copyProperties(para, res);
        res.setUpdateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.resourceService.update(res);
        } catch (MyBatisSystemException e) {
            this.logger.error("编辑资源基本信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("编辑资源基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("编辑资源基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70007", desc = "删除资源信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) ResourceForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> resIds = ArraysUtils.asList(para.getResourceIds());


        try {
            if (para.getThorough().intValue() == 0) {
                this.resourceService.discard(resIds, para.getDeletedUserid());
            } else {
                this.resourceService.delete(resIds);
            }
        } catch (MyBatisSystemException e) {
            this.logger.error("删除资源基本信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除资源基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除资源基本信息失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

}
