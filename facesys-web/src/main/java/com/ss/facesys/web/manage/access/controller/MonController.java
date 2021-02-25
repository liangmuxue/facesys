package com.ss.facesys.web.manage.access.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.access.common.dto.MonitorTask;
import com.ss.facesys.data.access.common.web.MonVO;
import com.ss.facesys.data.access.service.MonServiceImpl;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.*;
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

    @RequestMapping(value = {"/updateStatus"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "修改布控启用/停用状态", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateMonitorStatus(@RequestBody @Validated(APIKeyStateGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.monService.updateMonitorStatus(para);
            if ("SUCCESS".equals(message)) {
                resp.setMessage("成功" + (para.getState() == 1 ? "启用" : "停用") + "id为：" + para.getId() + "的布控任务");
            } else {
                resp.setMessage("修改布控状态失败");
                resp.setCode(ResultCode.MONITOR_STATUS_EDIT_FAILED_CODE);
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_STATUS_EDIT_FAILED_CODE);
            this.logger.error("修改布控失败，原因：" + e.toString(), e);
            resp.setMessage("修改布控失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "删除布控任务", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteMonitor(@RequestBody @Validated(APIDeltGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.monService.deleteMonitor(para);
            if ("SUCCESS".equals(message)) {
                resp.setMessage("成功删除id为" +  para.getId() + "的布控任务");
            } else {
                resp.setMessage("删除布控失败");
                resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
            }
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
            this.logger.error("删除布控失败，原因：" + e.toString(), e);
            resp.setMessage("删除布控失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/select"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询布控任务", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<MonitorTask>> selectMonitor(@RequestBody @Validated(APIPageGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<MonitorTask>> resp = validite(bindingResult);
        try {
            Page<MonitorTask> data =  (Page)this.monService.selectMonitor(para);
            resp.setData(new PageEntity<>(data));
            resp.setMessage("查询布控成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
            this.logger.error("查询布控失败，原因：" + e.toString(), e);
            resp.setMessage("查询布控失败");
        }
        return resp;
    }

    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80007", desc = "查询布控任务详细信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<MonitorTask> selectMonitorDetail(@RequestBody @Validated(APIGetsGroup.class) MonVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<MonitorTask> resp = validite(bindingResult);
        try {
            MonitorTask monitorTask = this.monService.selectMonitorDetail(para);
            resp.setData(monitorTask);
            resp.setMessage("查询布控任务详细信息成功");
        }catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.MONITOR_DELETE_FAILED_CODE);
            this.logger.error("查询布控任务详细信息失败，原因：" + e.toString(), e);
            resp.setMessage("查询布控任务详细信息失败");
        }
        return resp;
    }
}
