package com.ss.isc.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.isc.data.baseinfo.common.web.BaseQueryEntity;
import com.ss.isc.data.collect.client.IVehicleService;
import com.ss.isc.data.collect.common.dto.VehicleDTO;
import com.ss.isc.data.collect.common.web.VehicleQueryVO;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.StringUtils;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIPageGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * VehicleController
 * @author FrancisYs
 * @date 2019/8/20
 * @email yaoshuai@ss-cas.com
 */
@RestController
@CrossOrigin
@RequestMapping({"/collect/vehicle"})
public class VehicleController extends BaseController {

    public static final Log LOG = LogFactory.getLog(VehicleController.class);

    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Resource
    private IVehicleService vehicleService;


    @RequestMapping(value = {"/vehicleStatistics"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Integer>> findVehicleStatistics(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Integer>> resp = validite(bindingResult);
        try {
            Map<String, Integer> map = this.vehicleService.findVehicleStatistics(para.getVillageCode());
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("车辆统计查询失败");
            this.logger.error("车辆统计查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询过车信息分页列表
     * @param queryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/findList"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<VehicleDTO>> findList(@RequestBody @Validated({APIPageGroup.class}) VehicleQueryVO queryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VehicleDTO>> resp = validite(bindingResult);
        try {
            queryVO.setVillageCodes(getVillageCodes(queryVO.getVillageCodes()));
            // 未选择社区默认查询当前用户权限下的全部社区
            if (StringUtils.isBlank(queryVO.getVillageCodes())) {
                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(queryVO.getUserIds());
                if (userPermission != null) {
                    String[] villageCodes = userPermission.getVillageCodes();
                    if (null != villageCodes && villageCodes.length > 0) {
                        queryVO.setVillages(villageCodes);
                        queryVO.setVillageCodes(StringUtils.join(villageCodes, ","));
                    } else {
                        resp = createFailResponse();
                        resp.setMessage("您没有任何小区的权限，请联系管理员！");
                        return resp;
                    }
                } else {
                    resp = createFailResponse();
                    resp.setMessage("您没有任何小区的权限，请联系管理员！");
                    return resp;
                }
            }
            Page<VehicleDTO> data = (Page) this.vehicleService.findCarList(queryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("过车信息查询失败");
            this.logger.error("过车信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
