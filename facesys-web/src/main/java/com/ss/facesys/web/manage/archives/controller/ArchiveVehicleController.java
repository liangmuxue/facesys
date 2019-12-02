package com.ss.facesys.web.manage.archives.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.archives.client.IArchiveVehicleService;
import com.ss.facesys.data.archives.client.IPersonVehicleService;
import com.ss.facesys.data.archives.client.IVehicleDiscoveryService;
import com.ss.facesys.data.archives.client.IVehicleRetentionService;
import com.ss.facesys.data.archives.common.model.*;
import com.ss.facesys.data.process.client.IVehicleProcessService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
* 车辆列表及感知信息查询
* @author chao
* @create 2019/8/19
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/archives/vehicle"})
public class ArchiveVehicleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ArchiveVehicleController.class);
    @Resource
    private IPersonVehicleService personVehicleService;
    @Resource
    private IArchiveVehicleService vehicleService;
    @Resource
    private IVillageService villageService;
    @Resource
    private IVehicleDiscoveryService discoveryService;
    @Resource
    private IArchiveVehicleService archiveVehicleService;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private IVehicleRetentionService vehicleRetentionService;
    @Resource
    private IVehicleProcessService vehicleProcessService;

    /**
     * 查询车辆列表信息
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "一车一档信息分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Vehicle>> detail(HttpServletRequest request, @RequestBody Vehicle para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Vehicle>> resp = validite(bindingResult);

        try {
            para.setVillageCodes(getVillageCodes(para.getVillageCodes()));
            Integer currPage = para.getCurrentPage();
            Integer pageSize = para.getPageSize();
            //查询车辆信息处理
            Page<Vehicle> vList = (Page) this.personVehicleService.select(para, currPage, pageSize);
            resp.setData(new PageEntity(vList));
        } catch (Exception e) {
            //查询车辆信息失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_VEHICLE_DETAIL_FAILED_CODE);
            resp.setMessage("一车一档信息分页查询,查询失败!");
            this.logger.error("一车一档信息分页查询,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 感知发现查询
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/discovery"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "一车一档感知发现", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<VehicleDiscovery>> discovery(HttpServletRequest request, @RequestBody VehicleDiscovery dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<VehicleDiscovery>> resp = validite(bindingResult);
        try {
            //感知发现查询处理
            List<VehicleDiscovery> vList = this.vehicleService.getDiscovery(dto);
            /*for (VehicleDiscovery vehicleDiscovery : vList) {
                VehicleDTO dayDTO = new VehicleDTO();
                dayDTO.setVillageCode(vehicleDiscovery.getVillageCode());
                dayDTO.setPlateNumber(vehicleDiscovery.getPlateNumber());
                dayDTO.setBeginTime(vehicleDiscovery.getDayBegin());
                dayDTO.setEndTime(CommonConstant.DATE_PATTERN.format(DateUtil.getEndDayOfSomeDay(CommonConstant.SHORT_DATE_PATTERN.parse(vehicleDiscovery.getDayEnd()))));
                int totalDays = this.vehicleProcessService.discoveryDays(dayDTO);
                vehicleDiscovery.setDays(Integer.valueOf(totalDays));
            }*/
            resp.setData(vList);
        } catch (Exception e) {
            //感知发现查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_VEHICLE_DISCOVERY_FAILED_CODE);
            resp.setMessage("一车一档感知发现,查询失败!");
            this.logger.error("一车一档感知发现,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 感知离开查询
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/leave"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "一车一档感知离开", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<VehicleLeave>> leave(HttpServletRequest request, @RequestBody VehicleLeave dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<VehicleLeave>> resp = validite(bindingResult);
        try {
            //感知离开查询处理
            List<VehicleLeave> data = this.vehicleService.getLeave(dto);
            resp.setData(data);
        } catch (Exception e) {
            //感知离开查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_VEHICLE_LEAVE_FAILED_CODE);
            resp.setMessage("一车一档感知离开,查询失败!");
            this.logger.error("一车一档感知离开,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 车辆滞留查询
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/retention"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "一车一档车辆滞留", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<VehicleRetation>> retention(HttpServletRequest request, @RequestBody VehicleRetation dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<VehicleRetation>> resp = validite(bindingResult);
        try {
            //车辆滞留查询处理
            List<VehicleRetation> data = this.vehicleService.getRetation(dto);
            resp.setData(data);
        } catch (Exception e) {
            //车辆滞留查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_VEHICLE_RETENTION_FAILED_CODE);
            resp.setMessage("一车一档车辆滞留,查询失败!");
            this.logger.error("一车一档车辆滞留,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
