package com.ss.facesys.web.manage.access.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.common.web.AlarmRecordsVO;
import com.ss.facesys.data.access.service.AlarmRecordServiceImpl;
import com.ss.facesys.data.collect.common.model.AlarmRecord;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIGetsGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 报警记录
 * @author zhangao
 * @date 2021/3/2
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/alarmRecord"})
public class AlarmRecordController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    @Resource
    private AlarmRecordServiceImpl alarmRecordService;

    @RequestMapping(value = {"/selBlackRecord"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询黑名单报警信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AlarmRecord>> selBlackRecord(@RequestBody @Validated(APIGetsGroup.class) AlarmRecordsVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AlarmRecord>> resp = validite(bindingResult);
        try {
            Page<AlarmRecord> data =  (Page) this.alarmRecordService.selBlackRecord(para);
            resp.setData(new PageEntity<>(data));
            resp.setMessage("查询报警信息成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMRECORD_FAILED_CODE);
            this.logger.error("查询报警信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询报警信息失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/selStrangerRecord"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询陌生人报警信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AlarmRecord>> selStrangerRecord(@RequestBody @Validated(APIGetsGroup.class) AlarmRecordsVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AlarmRecord>> resp = validite(bindingResult);
        try {
            Page<AlarmRecord> data =  (Page) this.alarmRecordService.selStrangerRecord(para);
            resp.setData(new PageEntity<>(data));
            resp.setMessage("查询报警信息成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMRECORD_FAILED_CODE);
            this.logger.error("查询报警信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询报警信息失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/selInconformityRecord"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询人证不符报警信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AlarmRecord>> selInconformityRecord(@RequestBody @Validated(APIGetsGroup.class) AlarmRecordsVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AlarmRecord>> resp = validite(bindingResult);
        try {
            Page<AlarmRecord> data =  (Page) this.alarmRecordService.selInconformityRecord(para);
            resp.setData(new PageEntity<>(data));
            resp.setMessage("查询报警信息成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMRECORD_FAILED_CODE);
            this.logger.error("查询报警信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询报警信息失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/updateState"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改报警记录状态", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> updateState(@RequestBody @Validated(APIGetsGroup.class) AlarmRecordsVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.alarmRecordService.updateState(para);
            if("SUCCESS".equals(message)) {
                resp.setMessage("修改报警记录状态成功");
            }else{
                resp.setMessage("修改报警记录状态失败");
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ALARMRECORD_EDIT_FAILED_CODE);
            this.logger.error("修改报警记录状态失败，原因：" + e.toString(), e);
            resp.setMessage("修改报警记录状态失败");
        }
        return resp;
    }


}
