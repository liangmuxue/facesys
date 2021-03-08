package com.ss.facesys.web.manage.access.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.common.web.AlarmRecordsVO;
import com.ss.facesys.data.access.common.web.AnalysisCountVO;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.mapper.AlarmRecordMapper;
import com.ss.facesys.data.access.service.AnalysisCountServiceImpl;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.data.statistic.common.dto.AlarmHour;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * AnalysisCountController 分析统计
 * @author zhangao
 * @date 2021/3/4
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/analysis"})
public class AnalysisCountController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AnalysisCountController.class);

    @Resource
    private AnalysisCountServiceImpl analysisCountService;

    @RequestMapping(value = {"/alarmRecord"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "分析报警统计", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String,Object>> alarmRecord(@RequestBody @Validated(APIAddGroup.class) AnalysisCountVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String,Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = this.analysisCountService.selAlarmCount(para);
            resp.setData(map);
            resp.setMessage("查询成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ANALYSISCOUNT_SEL_FAILED_CODE);
            this.logger.error("查询失败，原因：" + e.toString(), e);
            resp.setMessage("查询失败" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping(value = {"/snapRecord"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "分析抓拍统计", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String,Object>> snapRecord(@RequestBody @Validated(APIAddGroup.class) AnalysisCountVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String,Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = this.analysisCountService.selSnapCount(para);
            resp.setData(map);
            resp.setMessage("查询成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ANALYSISCOUNT_SEL_FAILED_CODE);
            this.logger.error("查询失败，原因：" + e.toString(), e);
            resp.setMessage("查询失败" + e.getMessage());
        }
        return resp;
    }
}
