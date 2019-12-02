package com.ss.facesys.web.manage.archives.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.archives.client.IArchiveHouseService;
import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.resource.client.ICommunityResourceService;
import com.ss.facesys.data.resource.client.IResourceHouseService;
import com.ss.facesys.data.resource.common.dto.HouseDTO;
import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.model.House;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.web.HouseQueryVO;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
* 小区楼栋单元房屋信息查询
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/archives/house"})
public class ArchiveHouseController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ArchiveHouseController.class);
    @Resource
    private IArchiveHouseService archivehouseService;
    @Resource
    private ICommunityResourceService communityResourceService;
    @Autowired
    private IResourceHouseService resourceHouseService;
    /**
     * 房屋关联信息查询
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "一屋一档详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> detail(HttpServletRequest request, @RequestBody PersonInfraDTO dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            //房屋关联信息查询处理
            Map<String, Object> returnMap = this.archivehouseService.detail(dto);
            resp.setData(returnMap);
        } catch (Exception e) {
            //房屋关联信息查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_DETAIL_FAILED_CODE);
            resp.setMessage("房屋关联信息详情查询失败");
            this.logger.error("房屋关联信息详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }
    /**
     * 查找所有小区
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"allvillage"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询所有小区", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Village>> allvillage(HttpServletRequest request, @RequestBody Village dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Village>> resp = validite(bindingResult);
        try {
            //查询小区处理
            List<Village> vList = this.communityResourceService.findVillageList(dto);
            resp.setData(vList);
        } catch (Exception e) {
            //查询小区失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_VILLAGE_FAILED_CODE);
            resp.setMessage("查询所有小区,查询失败!");
            this.logger.error("查询所有小区,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }
    /**
     * 查询楼栋
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"allbuilding"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询楼栋", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Building>> allbuilding(HttpServletRequest request, @RequestBody Building dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Building>> resp = validite(bindingResult);
        try {
            //设置楼栋状态为未删除
            dto.setIsDelete(CommonConstant.COMMON_0);
            //查询楼栋处理
            List<Building> bList = this.communityResourceService.findBuildingsByParam(dto);
            resp.setData(bList);
            return resp;
        } catch (Exception e) {
            //查询楼栋失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_BUILDING_FAILED_CODE);
            resp.setMessage("查询楼栋,查询失败!");
            this.logger.error("查询楼栋,查询失败，原因：" + e.toString(), e);
            return resp;
        }
    }
    /**
     * 查询单元
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"allunit"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询单元", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Unit>> allunit(HttpServletRequest request, @RequestBody Unit dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Unit>> resp = validite(bindingResult);
        try {
            //设置单元状态为未删除
            dto.setIsDelete(CommonConstant.COMMON_0);
            //查询单元处理
            List<Unit> vList = this.communityResourceService.select(dto);
            resp.setData(vList);
        } catch (Exception e) {
            //查询单元失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_UNIT_FAILED_CODE);
            resp.setMessage("查询单元,查询失败!");
            this.logger.error("查询单元,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }
    /**
     * 查询房间详情
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"allhouse"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询房间详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<House>> allhouse(HttpServletRequest request, @RequestBody House dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<House>> resp = validite(bindingResult);
        try {
            //设置房间状态为有效
            dto.setStatus(CommonConstant.COMMON_1);
            //查询房间处理
            List<House> vList = this.communityResourceService.findHouseByParam(dto);
            resp.setData(vList);
        } catch (Exception e) {
            //查询房屋失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_HOUSE_FAILED_CODE);
            resp.setMessage("查询房间,查询失败!");
            this.logger.error("查询房间,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 通过id查询房间详情
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"getHouseById"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询房间详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<HouseDTO> getHouseById(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) HouseQueryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<HouseDTO> resp = validite(bindingResult);
        try {
            //查询房间处理
            HouseDTO vList = this.resourceHouseService.get(para.getId());
            resp.setData(vList);
        } catch (Exception e) {
            //查询房屋失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_HOUSE_HOUSE_FAILED_CODE);
            resp.setMessage("查询房间详情,查询失败!");
            this.logger.error("查询房间详情,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
