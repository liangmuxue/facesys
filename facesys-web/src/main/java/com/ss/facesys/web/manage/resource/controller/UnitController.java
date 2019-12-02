package com.ss.facesys.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IResourceHouseService;
import com.ss.facesys.data.resource.client.IUnitService;
import com.ss.facesys.data.resource.common.model.House;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.web.UnitQueryVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

/**
 * UnitController 单元web请求
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/unit"})
public class UnitController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UnitController.class);

    @Resource
    private IUnitService unitService;
    @Resource
    private IResourceHouseService houseService;
    @Resource
    private IRegionService regionService;

    /**
     * 查询单元分页列表
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "单元分页查询 ", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Unit>> page(@RequestBody @Validated({APIPageGroup.class}) UnitQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Unit>> resp = validite(bindingResult);
        try {
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            Page<Unit> data = (Page) this.unitService.pages(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_UNIT_PAGE_CODE);
            resp.setMessage("单元分页查询失败");
            this.logger.error("单元分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "单元列表查询 ", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Unit>> list(HttpServletRequest request, @RequestBody Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Unit>> resp = validite(bindingResult);
        try {
            List<Unit> data = this.unitService.list(dto);
            resp.setData(data);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806021");
            resp.setMessage("单元列表查询失败");
            this.logger.error("单元列表查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增单元
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "单元新增 ", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, String>> add(@RequestBody @Validated({APIAddGroup.class}) Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            this.unitService.add(dto);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_UNIT_ADD_CODE);
            resp.setMessage("单元新增失败");
            this.logger.error("单元新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询单元详情
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/selectOne"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询单元详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Unit> selectOne(@RequestBody @Validated({APIGetsGroup.class}) Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Unit> resp = validite(bindingResult);
        try {
            Unit entity = this.unitService.selectOne(dto);
            resp.setData(entity);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_UNIT_DETAIL_CODE);
            resp.setMessage("单元详情查询失败");
            this.logger.error("单元详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改单元
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "单元修改 ", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> update(@RequestBody @Validated({APIEditGroup.class}) Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Unit entity = this.unitService.selectOne(dto);
            if (entity != null) {
                this.unitService.update(dto);
            } else {
                resp = createFailResponse();
                resp.setMessage("该单元不存在，不能修改！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_UNIT_UPDATE_CODE);
            resp.setMessage("单元修改失败");
            this.logger.error("单元修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除单元
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "单元刪除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Validated({APIDeltGroup.class}) Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            dto = unitService.selectOne(dto);
            // 删除单元前校验是否有关联房屋
            House hDTO = new House();
            hDTO.setVillageCode(dto.getVillageCode());
            hDTO.setBuildingNo(dto.getBuildingNo());
            hDTO.setUnitNo(dto.getUnitNo());
            List<House> hList = this.houseService.list(hDTO);
            if (CollectionUtils.isEmpty(hList)) {
                Unit entity = this.unitService.selectOne(dto);
                if (entity != null) {
                    this.unitService.delete(dto);
                } else {
                    resp = createFailResponse();
                    resp.setMessage("该单元不存在，不能删除！");
                }
            } else {
                resp = createFailResponse();
                resp.setMessage("该单元下存在房屋，不可删除！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_UNIT_DELETE_CODE);
            resp.setMessage("单元删除失败");
            this.logger.error("单元删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
