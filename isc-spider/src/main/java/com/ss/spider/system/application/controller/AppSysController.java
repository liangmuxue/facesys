package com.ss.spider.system.application.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.application.form.AppSysForm;
import com.ss.spider.system.application.form.AppSysQuery;
import com.ss.spider.system.application.model.AppSystem;
import com.ss.spider.system.application.service.AppSystemService;
import com.ss.tools.ArraysUtils;
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
@RequestMapping({"/app"})
public class AppSysController extends AbstractController {

    @Autowired
    @Qualifier("appSystemService")
    private AppSystemService<AppSystem> appSystemService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "分页查询应用列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AppSystem>> pages(@RequestBody AppSysQuery para) throws Exception {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);

        AppSystem appSys = new AppSystem();
        BeanUtils.copyProperties(para, appSys);

        Page<AppSystem> data = (Page) this.appSystemService.pages(appSys, currPage, pageSize);

        ResponseEntity<PageEntity<AppSystem>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));

        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<AppSystem>> list(@RequestBody AppSysQuery para) throws Exception {
        AppSystem app = new AppSystem();
        BeanUtils.copyProperties(para, app);

        ResponseEntity<List<AppSystem>> resp = createSuccResponse();
        resp.setData(this.appSystemService.list(app));

        return resp;
    }


    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "查询应用信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<AppSystem> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) AppSysQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<AppSystem> resp = validite(bindingResult);

        resp.setData(this.appSystemService.get(para.getAppId()));

        return resp;
    }


    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "新增应用信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) AppSysForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);

        AppSystem app = new AppSystem();
        BeanUtils.copyProperties(para, app);

        try {
            resp.setData(this.appSystemService.save(app));
        } catch (MyBatisSystemException e) {
            this.logger.error("查询应用详情信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("查询应用详情信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("查询应用详情信息失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70003", desc = "删除应用信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) AppSysQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> appIds = ArraysUtils.asList(para.getAppId());

        try {
            this.appSystemService.remove(appIds);
        } catch (MyBatisSystemException e) {
            this.logger.error("删除应用详情信息失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除应用详情信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除应用详情信息失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

}
