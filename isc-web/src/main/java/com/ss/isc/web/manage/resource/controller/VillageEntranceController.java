package com.ss.isc.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IVillageEntranceService;
import com.ss.isc.data.resource.common.model.CameraRef;
import com.ss.isc.data.resource.common.model.VillageEntrance;
import com.ss.isc.data.resource.common.web.VillageEntranceQueryVO;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

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
 * VillageEntranceController 小区出入口web请求
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/villageEntrance"})
public class VillageEntranceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(VillageEntranceController.class);

    @Resource
    private IVillageEntranceService vEntranceService;
    @Resource
    private IRegionService regionService;

    /**
     * 查询出入口分页列表
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<VillageEntrance>> page(@RequestBody @Validated({APIPageGroup.class}) VillageEntranceQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VillageEntrance>> resp = validite(bindingResult);
        try {
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            Page<VillageEntrance> data = (Page) this.vEntranceService.pages(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_PAGE_CODE);
            resp.setMessage("小区出入口分页查询失败");
            this.logger.error("小区出入口分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增小区出入口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<Map<String, String>> add(@RequestBody @Validated({APIAddGroup.class}) VillageEntrance dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            this.vEntranceService.add(dto);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_ADD_CODE);
            resp.setMessage("小区出入口新增失败");
            this.logger.error("小区出入口新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询小区出入口详情
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询小区出入口详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<VillageEntrance> selectOne(@RequestBody @Validated({APIGetsGroup.class}) VillageEntrance dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<VillageEntrance> resp = validite(bindingResult);
        try {
            VillageEntrance entity = this.vEntranceService.selectOne(dto);
            resp.setData(entity);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_DETAIL_CODE);
            resp.setMessage("楼栋详情查询失败");
            this.logger.error("楼栋详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改小区出入口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口编辑", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> update(@RequestBody @Validated({APIEditGroup.class}) VillageEntrance dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            VillageEntrance entity = this.vEntranceService.selectOne(dto);
            if (entity != null) {
                this.vEntranceService.update(dto);
            } else {
                resp = createFailResponse();
                resp.setMessage("该小区出入口不存在，不能修改！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_UPDATE_CODE);
            resp.setMessage("小区出入口修改失败");
            this.logger.error("小区出入口修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除小区出入口
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<Map<String, String>> delete(@RequestBody @Validated({APIDeltGroup.class}) VillageEntrance dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            // 删除前查询出入口是否有关联的摄像头
            List<CameraRef> cameraRefList = this.vEntranceService.selectCameraRef(dto);
            if (CollectionUtils.isEmpty(cameraRefList)) {
                VillageEntrance entity = this.vEntranceService.selectOne(dto);
                if (entity != null) {
                    this.vEntranceService.delete(dto);
                } else {
                    resp = createFailResponse();
                    resp.setMessage("该小区出入口不存在，不能删除！");
                }
            } else {
                resp = createFailResponse();
                resp.setMessage("该小区出入口下存在关联摄像头，不能删除！");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_DELETE_CODE);
            resp.setMessage("小区出入口删除失败");
            this.logger.error("小区出入口删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 出入口关联设备
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/ref"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口关联摄像头操作", type = OperaTypeEnum.EDIT)
    public ResponseEntity<Map<String, String>> ref(@RequestBody @Validated({APIAddGroup.class}) CameraRef dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            // 关联表当前只存了出入口关联数据，类型可默认为1
            if (dto.getType() == null) {
                dto.setType(NumberConstant.ONE);
            }
            this.vEntranceService.ref(dto);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_REF_CODE);
            resp.setMessage("小区出入口关联摄像头失败");
            this.logger.error("小区出入口关联摄像头失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询出入口关联设备列表
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/reflist"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "小区出入口关联摄像头列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<CameraRef>> refList(@RequestBody @Validated({APIListGroup.class}) CameraRef dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<CameraRef>> resp = validite(bindingResult);
        try {
            // 关联表当前只存了出入口关联数据，类型可默认为1
            if (dto.getType() == null) {
                dto.setType(NumberConstant.ONE);
            }
            List<CameraRef> refList = this.vEntranceService.refList(dto);
            resp.setData(refList);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.NO_VILLAGEENTRANCE_REFLIST_CODE);
            resp.setMessage("小区出入口关联摄像头列表查询失败");
            this.logger.error("小区出入口关联摄像头列表查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
