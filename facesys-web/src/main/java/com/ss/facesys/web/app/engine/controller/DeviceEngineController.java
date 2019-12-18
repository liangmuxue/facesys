package com.ss.facesys.web.app.engine.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.engine.client.IDeviceEngineService;
import com.ss.facesys.data.engine.common.dto.DeviceEngineDTO;
import com.ss.facesys.data.engine.common.model.DeviceEngine;
import com.ss.facesys.data.engine.validate.APIEngineBindGroup;
import com.ss.facesys.web.app.facedb.form.DeviceEngineBindForm;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备绑定引擎关系
 *
 * @author FrancisYs
 * @date 2019/12/17
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/device"})
public class DeviceEngineController extends BaseController {

    private final IDeviceEngineService deviceEngineService;

    @Autowired
    public DeviceEngineController(IDeviceEngineService deviceEngineService) {
        this.deviceEngineService = deviceEngineService;
    }

    /**
     * 设备与引擎绑定关系查询
     *
     * @return
     */
    @PostMapping(value = {"/engine/list"})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询设备绑定引擎列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<DeviceEngineDTO>> engineList(@RequestBody DeviceEngineDTO engineDTO) {
        ResponseEntity<List<DeviceEngineDTO>> resp = createSuccResponse();
        resp.setData(deviceEngineService.engineList(engineDTO));
        return resp;
    }

    /**
     * 设备与引擎绑定关系修改
     *
     * @param bindControlForm
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @PostMapping(value = {"/engine/bind/control"})
    @OpLog(model = ModuleCode.SYSTEM, desc = "设备与引擎绑定关系修改", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> engineBindControl(@RequestBody @Validated({APIEngineBindGroup.class}) DeviceEngineBindForm bindControlForm, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = this.validite(bindingResult);
        try {
            DeviceEngine deviceEngine = new DeviceEngine();
            BeanUtils.copyProperties(bindControlForm, deviceEngine);
            resp.setData(deviceEngineService.bindEngineControl(deviceEngine));
        } catch (ServiceException e) {
            this.logger.error("设备与引擎绑定关系修改失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

}
