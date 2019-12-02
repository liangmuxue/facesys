package com.ss.isc.web.manage.collect.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.collect.client.IPopuStatisticsService;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/collect/popustatistics"})
public class PopuStatisticsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PopuStatisticsController.class);


    @Resource
    private IPopuStatisticsService popuStatisticsService;


    @RequestMapping(value = {"all"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "实有人口统计 ", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> all(HttpServletRequest request, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            String villageCode = null;
            if (StringUtils.isNotBlank((CharSequence) map.get("villageCode"))) {
                villageCode = (String) map.get("villageCode");
            }


            Map<String, Object> statisticsmap = this.popuStatisticsService.getPopulationData(villageCode);
            resp.setData(statisticsmap);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804016");
            resp.setMessage("实有人口统计查询失败");
            this.logger.error("实有人口统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
