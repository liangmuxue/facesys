package com.ss.isc.web.manage.archives.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.archives.client.IPersonVehicleService;
import com.ss.isc.data.archives.common.model.Vehicle;
import com.ss.isc.data.archives.common.model.VehicleRecord;
import com.ss.isc.data.archives.common.model.VehicleVO;
import com.ss.isc.data.collect.common.model.House;
import com.ss.isc.data.resource.common.web.HouseListVO;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
* 车辆信息增删改查
* @author chao
* @create 2019/8/19
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/archives/personvehicle"})
public class PersonVehicleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PersonVehicleController.class);

    @Resource
    private IPersonVehicleService personVehicleService;

    /**
     * 查询车辆详情
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/selectById"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "车辆信息通过ID查询详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Vehicle>> selectById(HttpServletRequest request, @RequestBody Vehicle para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Vehicle>> resp = validite(bindingResult);
        try {
            //查询车辆详情处理
            List<Vehicle> vList =this.personVehicleService.selectById(para);
            resp.setData(vList);
        } catch (Exception e) {
            //查询车辆详情失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_VEHICLE_DETAIL_FAILED_CODE);
            resp.setMessage("车辆信息通过ID查询详情,查询失败!");
            this.logger.error("车辆信息通过ID查询详情,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 过车记录查询
     * @param request
     * @param vehicleRecordDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "过车记录", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<VehicleRecord>> list(HttpServletRequest request, @RequestBody VehicleRecord vehicleRecordDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<VehicleRecord>> resp = validite(bindingResult);
        try {
            //过车记录查询处理
            List<VehicleRecord> vList = this.personVehicleService.list(vehicleRecordDTO);
            resp.setData(vList);
        } catch (Exception e) {
            //过车记录查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_VEHICLE_LIST_FAILED_CODE);
            resp.setMessage("一车一档过车记录,查询失败!");
            this.logger.error("一车一档过车记录,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增车辆
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) VehicleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(para, vehicle);
            //新增车辆处理
            String message = this.personVehicleService.save(vehicle);
            //新增车辆存在处理
            if ("ishave".equals(message)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.ARCHIVES_PERSON_ADD_FAILED_CODE);
                resp.setMessage("车牌号已经存在");
            } else {
                resp.setMessage("新增车辆信息成功");
            }
        } catch (Exception e) {
            //新增车辆失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_ADD_FAILED_CODE);
            resp.setMessage("车辆新增失败");
            this.logger.error("车辆新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改车辆信息
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆信息修改", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> edit(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) VehicleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(para, vehicle);
            //修改车辆处理
            String message = this.personVehicleService.edit(vehicle);
            //修改车辆存在处理
            if ("ishave".equals(message)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.ARCHIVES_PERSON_EDIT_FAILED_CODE);
                resp.setMessage("车牌号已经存在");
            } else {
                resp.setMessage("修改车辆信息成功");
            }
        } catch (Exception e) {
            //修改车辆失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_EDIT_FAILED_CODE);
            resp.setMessage("修改车辆信息失败");
            this.logger.error("修改车辆信息失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除车辆
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) VehicleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(para, vehicle);
            //删除车辆处理
            String message = this.personVehicleService.delete(vehicle);
            if ("success" == message){
                resp.setMessage("车辆删除成功");
            } else {
                resp = createFailResponse();
                resp.setCode(ResultCode.ARCHIVES_PERSON_DELETE_FAILED_CODE);
                resp.setMessage("车辆删除失败");
            }
        } catch (Exception e) {
            //删除车辆失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_DELETE_FAILED_CODE);
            resp.setMessage("车辆删除失败");
            this.logger.error("车辆删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 通过证件号查询车辆那个信息
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "车辆信息查询详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Vehicle>> detail(HttpServletRequest request, @RequestBody Vehicle para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Vehicle>> resp = validite(bindingResult);
        try {
            //查询车辆详情处理
            List<Vehicle> vList =this.personVehicleService.detail(para);
            resp.setData(vList);
        } catch (Exception e) {
            //查询车辆详情失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_VEHICLE_DETAIL_FAILED_CODE);
            resp.setMessage("车辆信息查询详情,查询失败!");
            this.logger.error("车辆信息查询详情,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }
}
