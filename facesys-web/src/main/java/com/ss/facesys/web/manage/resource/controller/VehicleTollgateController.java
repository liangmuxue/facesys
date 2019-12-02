package com.ss.facesys.web.manage.resource.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IVehicleTollgateService;
import com.ss.facesys.data.resource.common.model.Tollgate;
import com.ss.facesys.data.resource.common.web.TollgateQueryVO;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.export.TemplateReflectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * VehicleTollgateController 车辆卡口web请求
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/vehicle"})
public class VehicleTollgateController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CameraController.class);

    @Resource
    private IVehicleTollgateService vehicleService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IOrganizationRegionService organizationRegionService;


    @RequestMapping(value = {"/findAllVehicleTollgates"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "根据区域树上的某个节点查找所有的车辆卡口", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Tollgate>> findAllVehicleTollgates(HttpServletRequest request, @RequestBody Tollgate tollgate, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Tollgate>> resp = createSuccResponse();


        try {
            if (StringUtils.isBlank(tollgate.getVillageCode()) && StringUtils.isNotBlank(tollgate.getRegionCode())) {
                String villageCodes = this.regionService.getVilllageCodes(tollgate.getRegionCode());
                tollgate.setVillageCodes(villageCodes);
            }
            Page<Tollgate> data = (Page) this.vehicleService.findAllVehicleTollgates(tollgate);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查找所有的车辆卡口失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805005");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询车辆卡口分页列表
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/tollgate/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆卡口分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Tollgate>> page(@RequestBody @Validated({APIPageGroup.class}) TollgateQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Tollgate>> resp = validite(bindingResult);
        try {
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            Page<Tollgate> data = (Page) this.vehicleService.pages(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VEHICLETOLLGATE_PAGE_CODE);
            resp.setMessage("车辆卡口分页查询失败");
            this.logger.error("车辆卡口分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增车辆卡口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/tollgate/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆卡口新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, Object>> add(@RequestBody @Validated({APIAddGroup.class}) Tollgate dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Tollgate entity = null;
            if (StringUtils.isNotBlank(dto.getTollgateID())) {
                Tollgate tolDTO = new Tollgate();
                tolDTO.setTollgateID(dto.getTollgateID());
                tolDTO.setIsDelete(NumberConstant.ZERO);
                entity = this.vehicleService.selectOne(tolDTO);
            }
            if (entity != null) {
                resp = createFailResponse();
                resp.setMessage("该卡口已存在，请勿重复添加！");
            } else {
                this.vehicleService.add(dto);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VEHICLETOLLGATE_ADD_CODE);
            resp.setMessage("车辆卡口新增失败");
            this.logger.error("车辆卡口新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询车辆卡口详情
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/tollgate/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询车辆卡口详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Tollgate> selectOne(@RequestBody @Validated({APIGetsGroup.class}) Tollgate dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Tollgate> resp = validite(bindingResult);
        try {
            Tollgate entity = this.vehicleService.selectOne(dto);
            resp.setData(entity);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VEHICLETOLLGATE_DETAIL_CODE);
            resp.setMessage("楼栋详情查询失败");
            this.logger.error("楼栋详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改车辆卡口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆卡口更新", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> update(@RequestBody @Validated({APIEditGroup.class}) Tollgate dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Tollgate tolDTO = new Tollgate();
            tolDTO.setId(dto.getId());
            tolDTO.setIsDelete(NumberConstant.ZERO);
            Tollgate entity = this.vehicleService.selectOne(tolDTO);
            if (entity == null) {
                resp = createFailResponse();
                resp.setMessage("该车辆卡口不存在，不能修改！");
            } else {
                this.vehicleService.update(dto);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VEHICLETOLLGATE_UPDATE_CODE);
            resp.setMessage("车辆卡口修改失败");
            this.logger.error("车辆卡口修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除车辆卡口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "车辆卡口删除", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Validated({APIDeltGroup.class}) Tollgate dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Tollgate entity = this.vehicleService.selectOne(dto);
            if (entity != null) {
                this.vehicleService.delete(dto);
            } else {
                resp = createFailResponse();
                resp.setMessage("该车辆卡口不存在，不能删除！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VEHICLETOLLGATE_DELETE_CODE);
            resp.setMessage("车辆卡口删除失败");
            this.logger.error("车辆卡口删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/importVehicleTollgate"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> importVehicleTollgate(MultipartFile file) {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            Map<String, Object> map1 = TemplateReflectUtils.getDataList(Tollgate.class, "vehicle_tollgate", file, true);


            List<Tollgate> tempList = (List) map1.get("list");

            Map<String, String> imagePaths = (Map) map1.get("imagePaths");

            Map<String, String> map = this.vehicleService.batchImport(tempList, imagePaths);
            if ("success".equals(map.get("result"))) {
                resp = createSuccResponse();
            } else {
                resp = createFailResponse();
            }
            resp.setMessage((String) map.get("message"));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806050");
            resp.setMessage("车辆卡口导入失败");
            this.logger.error("车辆卡口导入失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
