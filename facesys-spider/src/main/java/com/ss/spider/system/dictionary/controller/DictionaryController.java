package com.ss.spider.system.dictionary.controller;

import com.ss.controller.AbstractController;
import com.ss.exception.ServiceException;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.dictionary.form.DictionaryForm;
import com.ss.spider.system.dictionary.form.DictionaryQuery;
import com.ss.spider.system.dictionary.model.Dictionary;
import com.ss.spider.system.dictionary.service.DictionaryService;
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
@RequestMapping({"dic"})
public class DictionaryController extends AbstractController {

    @Autowired
    private DictionaryService<Dictionary> dictionaryService;

    @RequestMapping({"pages"})
    public ResponseEntity<PageEntity<Dictionary>> pages(@RequestBody DictionaryQuery query) {
        ResponseEntity<PageEntity<Dictionary>> resp = createSuccResponse();

        int pageIndex = getPageIndex(query);
        int pageSize = getPageSize(query);
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(query, dictionary);

        List<Dictionary> pages = this.dictionaryService.pages(dictionary, pageSize, pageIndex);
        resp.setData(new PageEntity((Page) pages));

        return resp;
    }


    @RequestMapping({"list"})
    public ResponseEntity<List<Dictionary>> list(@RequestBody DictionaryQuery query) {
        ResponseEntity<List<Dictionary>> resp = createSuccResponse();

        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(query, dictionary);

        List<Dictionary> list = this.dictionaryService.list(dictionary);
        resp.setData(list);

        return resp;
    }


    @RequestMapping({"gets"})
    public ResponseEntity<List<Dictionary>> gets(
            @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) final DictionaryForm form,
            BindingResult result) throws BindException {
        ResponseEntity<List<Dictionary>> resp = validite(result);

        resp.setData(this.dictionaryService.gets(new HashMap<String, Object>(1) {

        }));
        return resp;
    }


    @RequestMapping({"save"})
    public ResponseEntity<String> save(
            @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) DictionaryForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(form, dictionary);

        dictionary.setDicId(UUIDUtils.getUUID());
        dictionary.setCreateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.dictionaryService.save(dictionary);
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
            @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) DictionaryForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(form, dictionary);
        dictionary.setUpdateTime(Long.valueOf(DateUtils.getCurrentTime()));

        try {
            this.dictionaryService.update(dictionary);
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
            @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) final DictionaryForm form,
            BindingResult result) throws BindException {
        ResponseEntity<String> resp = validite(result);

        try {
            this.dictionaryService.remove(new HashMap<String, Object>(1) {

            });
        } catch (ServiceException e) {
            this.logger.error("删除字典内容失败,原因:", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

}
