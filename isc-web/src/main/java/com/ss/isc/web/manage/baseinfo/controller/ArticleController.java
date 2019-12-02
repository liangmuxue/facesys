package com.ss.isc.web.manage.baseinfo.controller;

import com.github.pagehelper.Page;
import com.ss.isc.data.baseinfo.common.web.TemplateVO;
import com.ss.isc.data.collect.common.model.Employee;
import com.ss.isc.data.collect.common.model.People;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.export.TemplateUtils;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author: liangmuxue
 * @desc: 临时测试支持
 */

@RestController
@RequestMapping({"/article"})
public class ArticleController extends BaseController {

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Employee>> searchEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Employee>> resp = validite(bindingResult);
        int pageNum = NumberUtils.toInt((String) map.get("pageNum"));
        int pageSize = NumberUtils.toInt((String) map.get("pageSize"));
        Page<People> data = new Page<People>();
        data.add(new People());
        data.setTotal(1L);
        data.setPageNum(1);
        data.setPageSize(10);
        data.setReasonable(true);
        resp.setData(new PageEntity(data));
        return resp;
    }

}
