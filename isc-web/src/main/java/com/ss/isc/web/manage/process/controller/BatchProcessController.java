package com.ss.isc.web.manage.process.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.collect.client.IAlarmService;
import com.ss.isc.data.process.client.IVehicleProcessService;
import com.ss.isc.data.process.common.web.PeopleProcessVO;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIOpStatusGroup;
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
 * 预警一键处置
 *
 * @author FrancisYs
 * @date 2019/10/31
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/process/batch"})
public class BatchProcessController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BatchProcessController.class);

    @Resource
    private IAlarmService alarmService;
    @Resource
    private IVehicleProcessService vehicleService;

    /**
     * 预警一键处置
     *
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/handle"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "预警一键处置", type = OperaTypeEnum.OTHER)
    public ResponseEntity<String> handleBatch(@RequestBody @Validated({APIOpStatusGroup.class}) PeopleProcessVO processVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            boolean peopleAlarmProcess = processVO.getOperationType() == NumberConstant.ONE.intValue();
            logger.info("开始执行：{} 一键处置", peopleAlarmProcess ? "人口预警" : "车辆预警");
            resp.setData(peopleAlarmProcess ? alarmService.handleUntreated(processVO) : vehicleService.handleUntreated(processVO));
            return resp;
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("预警一键处置失败");
            this.logger.error("预警一键处置失败，原因：" + e.getMessage(), e);
        }
        return resp;
    }

}