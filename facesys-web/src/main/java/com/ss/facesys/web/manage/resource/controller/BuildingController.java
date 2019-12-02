package com.ss.facesys.web.manage.resource.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.IBuildingService;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IUnitService;
import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.web.BuildingQueryVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * BuildingController 楼栋web请求
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/building"})
public class BuildingController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BuildingController.class);

    @Resource
    private IBuildingService buildingService;
    @Resource
    private IUnitService unitService;
    @Resource
    private IRegionService regionService;

    /**
     * 查询楼栋分页列表
     * @param buildingQueryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "楼栋分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Building>> page(@RequestBody @Validated({APIPageGroup.class}) BuildingQueryVO buildingQueryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Building>> resp = validite(bindingResult);
        try {
            buildingQueryVO.setVillageCodes(getVillageCodes(buildingQueryVO.getVillageCodes()));
            Page<Building> data = (Page) this.buildingService.pages(buildingQueryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_BUILDING_PAGE_CODE);
            resp.setMessage("楼栋分页查询失败");
            this.logger.error("楼栋分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增楼栋
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "楼栋新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, String>> add(@RequestBody @Validated({APIAddGroup.class}) Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            this.buildingService.add(dto);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_BUILDING_ADD_CODE);
            resp.setMessage("楼栋新增失败");
            this.logger.error("楼栋新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询楼栋详情
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/selectOne"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询楼栋详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Building> selectOne(@RequestBody @Validated({APIGetsGroup.class}) Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Building> resp = validite(bindingResult);
        try {
            Building entity = this.buildingService.selectOne(dto);
            resp.setData(entity);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_BUILDING_DETAIL_CODE);
            resp.setMessage("楼栋详情查询失败");
            this.logger.error("楼栋详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改楼栋
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "楼栋修改", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> update(@RequestBody @Validated({APIEditGroup.class}) Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Building entity = this.buildingService.selectOne(dto);
            if (entity != null) {
                this.buildingService.update(dto);
            } else {
                resp = createFailResponse();
                resp.setMessage("该楼栋不存在，不能修改！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_BUILDING_UPDATE_CODE);
            resp.setMessage("楼栋修改失败");
            this.logger.error("楼栋修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除楼栋
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "楼栋删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Validated({APIDeltGroup.class}) Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            // 删除前校验楼栋下是否有关联单元
            Unit rDTO = new Unit();
            rDTO.setVillageCode(dto.getVillageCode());
            rDTO.setBuildingNo(String.valueOf(dto.getBuildingNo()));
            List<Unit> uList = this.unitService.list(rDTO);
            if (CollectionUtils.isEmpty(uList)) {
                Building entity = this.buildingService.selectOne(dto);
                if (entity != null) {
                    this.buildingService.delete(dto);
                } else {
                    resp = createFailResponse();
                    resp.setMessage("该楼栋不存在，不能删除！");
                }
            } else {
                resp = createFailResponse();
                resp.setMessage("该楼栋下存在单元，不可删除！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_BUILDING_DELETE_CODE);
            resp.setMessage("楼栋删除失败");
            this.logger.error("楼栋删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "列表查询", type = OperaTypeEnum.DELETE)
    public ResponseEntity<List<Building>> list(HttpServletRequest request, @RequestBody Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Building>> resp = validite(bindingResult);
        try {
            List<Building> list = this.buildingService.list(dto);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806015");
            resp.setMessage("楼栋列表查询失败");
            this.logger.error("楼栋列表查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
