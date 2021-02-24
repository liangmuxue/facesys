package com.ss.facesys.web.manage.access.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.service.MonServiceImpl;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIEditGroup;
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
 * MonitorController 布控请求
 * @author zhangao
 * @date 2021/2/24
 * @email zhangao@ss-cas.com
 */
@RestController
@RequestMapping({"/monitor"})
public class MonController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(MonController.class);

    @Resource
    MonServiceImpl monService;

    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "新增布控任务", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertMonitor(@RequestBody @Validated(APIAddGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.monService.insertMonitor(para);
            if ("SUCCESS".equals(message)) {
                resp.setMessage("新增布控成功");
            } else {
                resp.setMessage("新增布控失败");
                resp.setCode(ResultCode.MONITOR_ADD_FAILED_CODE);
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_ADD_FAILED_CODE);
            this.logger.error("新增布控失败，原因：" + e.toString(), e);
            resp.setMessage("新增布控失败" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "编辑布控任务", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateMonitor(@RequestBody @Validated(APIEditGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.monService.updateMonitor(para);
            if ("SUCCESS".equals(message)) {
                resp.setMessage("修改布控成功");
            } else {
                resp.setMessage("修改布控失败");
                resp.setCode(ResultCode.MONITOR_EDIT_FAILED_CODE);
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_EDIT_FAILED_CODE);
            this.logger.error("修改布控失败，原因：" + e.toString(), e);
            resp.setMessage("修改布控失败");
        }
        return resp;
    }
}
