package com.ss.isc.web.manage.process.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.process.client.IPeopleProcessService;
import com.ss.isc.data.process.client.IVehicleProcessService;
import com.ss.isc.data.process.common.dto.VehicleDTO;
import com.ss.isc.data.process.common.model.*;
import com.ss.isc.data.process.common.web.VehicleVO;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIListGroup;
import com.ss.valide.APIPageGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * VehicleProcessController 车辆实时预警
 * @author FrancisYs
 * @date 2019/8/26
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/process/vehicle"})
public class VehicleProcessController extends BaseController {

    private static final Log LOG = LogFactory.getLog(VehicleProcessController.class);

    @Resource
    private IVehicleProcessService vehicleService;
    @Resource
    private IPeopleProcessService vehicleProcessService;

    /**
     * 车辆感知发现分页列表
     * @param vehicle
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"discovery/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆感知发现分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<VehicleDiscovery>> discoveryList(@RequestBody @Validated({APIPageGroup.class}) VehicleVO vehicle, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VehicleDiscovery>> resp = validite(bindingResult);
        try {
            LOG.info("/discovery/page--------------------------------" + vehicle.toString());
            vehicle.setVillageCodes(getVillageCodes(vehicle.getVillageCodes()));
            Page<VehicleDiscovery> dPage = (Page) this.vehicleService.discoveryList(vehicle);
            resp.setData(new PageEntity(dPage));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.VEHICLE_PROCESS_DISCOVERY_PAGE_CODE);
            resp.setMessage("车辆感知发现分页查询失败");
            this.logger.error("车辆感知发现分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"discovery/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "感知发现详情", type = OperaTypeEnum.OTHER)
    public ResponseEntity<VehicleDiscovery> discoveryDetail(HttpServletRequest request, @RequestBody VehicleDTO dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<VehicleDiscovery> resp = validite(bindingResult);
        try {
            LOG.info("/discovery/detail--------------------------------" + dto.toString());
            VehicleDiscovery entity = this.vehicleService.discoveryDetail(dto);
            resp.setData(entity);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804018");
            resp.setMessage("感知发现详情查询失败");
            this.logger.error("感知发现详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 车辆感知离开分页列表
     * @param vehicle
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/leave/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆感知离开分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<VehicleLeave>> leavePage(@RequestBody @Validated({APIPageGroup.class}) VehicleVO vehicle, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VehicleLeave>> resp = validite(bindingResult);
        try {
            vehicle.setVillageCodes(getVillageCodes(vehicle.getVillageCodes()));
            Page<VehicleLeave> dPage = (Page) this.vehicleService.leavePage(vehicle);
            resp.setData(new PageEntity(dPage));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.VEHICLE_PROCESS_LEAVE_PAGE_CODE);
            resp.setMessage("车辆感知离开分页查询失败");
            this.logger.error("车辆感知离开分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 车辆滞留分页
     * @param vehicle
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/retation/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆滞留分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<VehicleRetation>> retationPage(@RequestBody @Validated({APIPageGroup.class}) VehicleVO vehicle, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VehicleRetation>> resp = validite(bindingResult);
        try {
            vehicle.setVillageCodes(getVillageCodes(vehicle.getVillageCodes()));
            Page<VehicleRetation> dPage = (Page) this.vehicleService.retationPage(vehicle);
            resp.setData(new PageEntity(dPage));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.VEHICLE_PROCESS_RETATION_PAGE_CODE);
            resp.setMessage("车辆滞留分页查询失败");
            this.logger.error("车辆滞留分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 车辆预警处理
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/handle"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆预警处置", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> discoveryHandle(@RequestBody @Validated({APIAddGroup.class}) Vehicle dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            boolean flag;
            // 校验预警是否已处置
            PeopleProcess process = this.vehicleProcessService.selectPeopleProcess(new PeopleProcess(String.valueOf(dto.getHandleId()), dto.getProcessType()));
            if (process == null) {
                if (CommonConstant.PROCESSTYPE_VEHICLE_DISCOVERY.equals(dto.getProcessType())) {
                    this.vehicleService.discoveryHandle(dto);
                } else if (CommonConstant.PROCESSTYPE_VEHICLE_LEAVE.equals(dto.getProcessType())) {
                    this.vehicleService.leaveHandle(dto);
                } else if (CommonConstant.PROCESSTYPE_VEHICLE_RETENTION.equals(dto.getProcessType())) {
                    this.vehicleService.retationHandle(dto);
                }
                flag = this.vehicleProcessService.vehicleProcess(dto);
                if (flag) {
                    resp.setMessage("处置成功！");
                } else {
                    resp = createFailResponse();
                    resp.setMessage("处置失败！");
                }
            } else {
                resp = createFailResponse();
                resp.setMessage("该数据已处置，请勿重复处置！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.VEHICLE_PROCESS_HANDLE_CODE);
            resp.setMessage("车辆处置失败");
            this.logger.error("车辆处置失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 车辆预警一键处置
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/handleBatch"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆预警一键处置", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> handleBatch(@RequestBody @Validated({APIListGroup.class}) Vehicle dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            int processType = dto.getProcessType();
            // 校验是否存在已处置的预警
            for (String handleId : dto.getHandleIds().split(CommonConstant.SPLIT_COMMA)) {
                PeopleProcess process = this.vehicleProcessService.selectPeopleProcess(new PeopleProcess(handleId, processType));
                if (process != null) {
                    resp = createFailResponse();
                    resp.setMessage("存在已处置数据，请勿重复处置！");
                    return resp;
                }
                if (CommonConstant.PROCESSTYPE_VEHICLE_DISCOVERY.equals(processType)) {
                    this.vehicleService.discoveryHandle(dto);
                } else if (CommonConstant.PROCESSTYPE_VEHICLE_LEAVE.equals(processType)) {
                    this.vehicleService.leaveHandle(dto);
                } else if (CommonConstant.PROCESSTYPE_VEHICLE_RETENTION.equals(processType)) {
                    this.vehicleService.retationHandle(dto);
                }
                if (!(this.vehicleProcessService.vehicleProcess(dto))) {
                    resp = createFailResponse();
                    resp.setMessage("一键处置失败！");
                    return resp;
                }
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.VEHICLE_PROCESS_HANDLE_CODE);
            resp.setMessage("车辆一键处置失败");
            this.logger.error("车辆一键处置失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}