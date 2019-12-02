package com.ss.spider.system.dictionary.ext.controller;

import com.ss.controller.AbstractController;
import com.ss.exception.ServiceException;
import com.ss.response.ResponseEntity;
import com.ss.spider.system.dictionary.ext.form.DictionaryDataExtQuery;
import com.ss.spider.system.dictionary.model.Dictionary;
import com.ss.spider.system.dictionary.model.DictionaryData;
import com.ss.spider.system.dictionary.service.DictionaryDataService;
import com.ss.spider.system.dictionary.service.DictionaryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"dic/data/ext"})
public class DictionaryDataExtController extends AbstractController {

    @Autowired
    private DictionaryService<Dictionary> dictionaryService;
    @Autowired
    private DictionaryDataService dictionaryDataService;

    @PostMapping({"/list"})
    public ResponseEntity<List<DictionaryData>> list(@RequestBody @Validated({com.ss.valide.APIPageGroup.class}) DictionaryDataExtQuery para, BindingResult bindingResult) throws BindException, ServiceException {
        ResponseEntity<List<DictionaryData>> resp = validite(bindingResult);
        Dictionary dictionary = new Dictionary();
        dictionary.setDicTypeCode(para.getDicTypeCode());
        List<Dictionary> dictionaryList = this.dictionaryService.list(dictionary);
        if (CollectionUtils.isEmpty(dictionaryList)) {
            throw new ServiceException("字典类型编码不存在");
        }
        Map<String, Object> getPara = new HashMap<String, Object>(1);
        getPara.put("dicId", ((Dictionary) dictionaryList.get(0)).getDicId());
        List<DictionaryData> datas = this.dictionaryDataService.gets(getPara);
        resp.setData(datas);
        return resp;
    }

}
