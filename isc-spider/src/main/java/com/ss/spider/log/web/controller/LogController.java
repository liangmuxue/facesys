package com.ss.spider.log.web.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.CommonEnumClass;
import com.ss.enums.OperaTypeEnum;
import com.ss.exception.ServiceException;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.bean.ExcelData;
import com.ss.spider.log.bean.dto.QueryDTO;
import com.ss.spider.log.bean.vo.AppLogVO;
import com.ss.spider.log.enums.OperaStateEnum;
import com.ss.spider.log.model.AppLog;
import com.ss.spider.log.model.AppLogOpModel;
import com.ss.spider.log.service.AppLogService;
import com.ss.spider.log.utils.ExcelUtils;
import com.ss.spider.log.web.form.LogQuery;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/log"})
public class LogController extends AbstractController {

    @Autowired
    private AppLogService appLogService;

    private void checkTime(LogQuery para, BindingResult bindingResult) {
        if (para.getOperTimeB() != null && para.getOperTimeE() != null && para.getOperTimeE().longValue() < para.getOperTimeB().longValue()) {
            addBindingError("operTimeB", "操作开始时间大于操作结束时间", bindingResult);
        }
    }


    @PostMapping({"/pages"})
    public ResponseEntity<PageEntity<AppLogVO>> pages(@RequestBody @Validated LogQuery para, BindingResult bindingResult) throws BindException, ServiceException {
        this.logger.info("操作日志分页查询-开始。请求参数:{}", JSON.toJSONString(para));
        checkTime(para, bindingResult);
        ResponseEntity<PageEntity<AppLogVO>> res = validite(bindingResult);
        QueryDTO queryDTO = new QueryDTO();
        BeanUtils.copyProperties(para, queryDTO, new String[]{"userId"});
        queryDTO.setUserId(para.getOpUserId());
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);
        List<AppLogVO> pages = this.appLogService.pages(queryDTO, currPage, pageSize);
        res.setData(new PageEntity((Page) pages));
        this.logger.info("操作日志分页查询-结束。返回数据:{}", JSON.toJSONString(res));
        return res;
    }


    @PostMapping({"/export/excel"})
    public ResponseEntity<String> exportExcel(@Validated LogQuery para, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws BindException, ServiceException, UnsupportedEncodingException {
        this.logger.info("操作日志导出excel-开始。请求参数:{}", JSON.toJSONString(para));
        checkTime(para, bindingResult);
        ResponseEntity<String> res = validite(bindingResult);
        if (StringUtils.isBlank(para.getUserId())) {
            res.setCode(CommonEnumClass.CommonInterfaceEnum.ACCOUNT_IS_LANDED_ELSEWHERE.getKey());
            res.setMessage(CommonEnumClass.CommonInterfaceEnum.ACCOUNT_IS_LANDED_ELSEWHERE.getValue());
            return res;
        }
        QueryDTO queryDTO = new QueryDTO();
        BeanUtils.copyProperties(para, queryDTO, new String[]{"userId"});
        queryDTO.setUserId(para.getOpUserId());
        List<AppLog> logs = this.appLogService.list(queryDTO);
        ExcelData excelData = new ExcelData();
        excelData.setName("操作日志记录");
        List<String> titles = new ArrayList<String>();
        titles.add("操作员ID");
        titles.add("警员编号");
        titles.add("姓名");
        titles.add("日志类型");
        titles.add("操作时间");
        titles.add("操作模块");
        titles.add("操作类型");
        titles.add("操作结果");
        titles.add("操作员IP");
        titles.add("耗时（ms）");
        titles.add("描述");
        excelData.setTitles(titles);
        List<List<Object>> rows = new ArrayList<List<Object>>();
        List<AppLogOpModel> modelList = this.appLogService.getModelList();
        Map<String, String> modelMap = new HashMap<String, String>(10);
        if (!CollectionUtils.isEmpty(modelList)) {
            for (AppLogOpModel model : modelList) {
                modelMap.put(model.getDataValue(), model.getDataName());
            }
        }

        if (CollectionUtils.isNotEmpty(logs)) {
            for (AppLog log : logs) {
                List<Object> row = new ArrayList<Object>();
                row.add((log.getUserId() == null) ? "" : log.getUserId());
                row.add((log.getWorkCode() == null) ? "" : log.getWorkCode());
                row.add((log.getName() == null) ? "" : log.getName());

                row.add((log.getLogType() == null) ? "" : OpLog.LogType.getValue(log.getLogType().shortValue()));
                row.add(DateFormatUtils.format(log.getOperTime().longValue(), "yyyy-MM-dd HH:mm:ss"));

                row.add((modelMap.get(log.getModuleCode()) == null) ? log.getModuleCode() : modelMap.get(log.getModuleCode()));

                row.add(OperaTypeEnum.get(log.getType()).getValue());

                row.add((log.getIsSuccess() == null) ? "" : OperaStateEnum.getValue(log.getIsSuccess().shortValue()));
                row.add((log.getIp() == null) ? "" : log.getIp());
                row.add(log.getTimeConsum());
                row.add((StringUtils.isNotBlank(log.getDesc()) && log.getDesc().length() > 255) ? StringUtils.left(log.getDesc(), 255) : log.getDesc());
                rows.add(row);
            }
        } else {
            List<Object> row = new ArrayList<Object>();
            row.add("没有数据");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            row.add("");
            rows.add(row);
        }
        excelData.setRows(rows);
        String fileName = "操作日志.xlsx";
        try {
            ExcelUtils.exportExcel(request, response, fileName, excelData);
        } catch (Exception e) {
            this.logger.error("操作日志导出Excel失败，param={}", para, e);
            throw new ServiceException(CommonEnumClass.CommonInterfaceEnum.UNKNOWN_FAIL);
        }
        return res;
    }

}
