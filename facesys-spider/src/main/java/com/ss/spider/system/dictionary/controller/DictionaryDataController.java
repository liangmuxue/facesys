package com.ss.spider.system.dictionary.controller;

import com.ss.controller.AbstractController;
import com.ss.exception.ServiceException;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.dictionary.form.DictionaryDataForm;
import com.ss.spider.system.dictionary.form.DictionaryDataQuery;
import com.ss.spider.system.dictionary.model.DictionaryData;
import com.ss.spider.system.dictionary.service.DictionaryDataService;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"dic/data"})
public class DictionaryDataController extends AbstractController {

    @Autowired
    private DictionaryDataService<DictionaryData> dictionaryDataService;

    @RequestMapping({"pages"})
    public ResponseEntity<PageEntity<DictionaryData>> pages(@RequestBody DictionaryDataQuery query) {
        ResponseEntity<PageEntity<DictionaryData>> resp = new ResponseEntity<PageEntity<DictionaryData>>();

        int pageIndex = getPageIndex(query);
        int pageSize = getPageSize(query);

        DictionaryData dictionary = new DictionaryData();
        BeanUtils.copyProperties(query, dictionary);

        List<DictionaryData> pages = this.dictionaryDataService.pages(dictionary, pageSize, pageIndex);
        resp.setData(new PageEntity((Page) pages));

        return resp;
    }


    @RequestMapping({"list"})
    public ResponseEntity<List<DictionaryData>> list(@RequestBody DictionaryDataQuery query) {
        ResponseEntity<List<DictionaryData>> resp = new ResponseEntity<List<DictionaryData>>();

        DictionaryData dictionary = new DictionaryData();
        BeanUtils.copyProperties(query, dictionary);

        List<DictionaryData> list = this.dictionaryDataService.list(dictionary);
        resp.setData(list);

        return resp;
    }


    @RequestMapping({"gets"})
    public ResponseEntity<List<DictionaryData>> gets(
            @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) final DictionaryDataForm form,
            BindingResult result) throws BindException {
        ResponseEntity<List<DictionaryData>> resp = validite(result);

        resp.setData(this.dictionaryDataService.gets(new HashMap<String, Object>(1) {

        }));
        return resp;
    }


    @RequestMapping({"save"})
    public ResponseEntity<String> save(
            @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) DictionaryDataForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        DictionaryData dictionary = new DictionaryData();
        BeanUtils.copyProperties(form, dictionary);

        dictionary.setId(UUIDUtils.getUUID());
        dictionary.setCreatedTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.dictionaryDataService.save(dictionary);
        } catch (MyBatisSystemException e) {
            this.logger.error("新增字典内容失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("新增字典内容失败,原因：", e);
            throw e;
        } catch (ServiceException e) {
            this.logger.error("新增字典内容失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping({"update"})
    public ResponseEntity<String> update(
            @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) DictionaryDataForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        DictionaryData dictionary = new DictionaryData();
        BeanUtils.copyProperties(form, dictionary);
        dictionary.setUpdatedTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.dictionaryDataService.update(dictionary);
        } catch (MyBatisSystemException e) {
            this.logger.error("编辑字典内容失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("编辑字典内容失败,原因：", e);
            throw e;
        } catch (ServiceException e) {
            this.logger.error("编辑字典内容失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }


    @RequestMapping({"remove"})
    public ResponseEntity<String> remove(
            @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) final DictionaryDataForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        try {
            this.dictionaryDataService.remove(new HashMap<String, Object>(1) {

            });
        } catch (MyBatisSystemException e) {
            this.logger.error("删除字典内容失败,原因：", e);
            throw e;
        } catch (DataAccessResourceFailureException e) {
            this.logger.error("删除字典内容失败,原因：", e);
            throw e;
        } catch (ServiceException e) {
            this.logger.error("删除字典内容失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

}
