package com.ss.isc.web.manage.collect.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.collect.client.IHouseService;
import com.ss.isc.data.collect.common.model.Building;
import com.ss.isc.data.collect.common.model.Company;
import com.ss.isc.data.collect.common.model.Employee;
import com.ss.isc.data.collect.common.model.House;
import com.ss.isc.data.collect.common.model.Vehicle;
import com.ss.isc.data.collect.common.web.CompanyListVO;
import com.ss.isc.data.resource.common.web.HouseListVO;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* 房屋和单位列表查询
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
@RestController
@CrossOrigin
@RequestMapping({"/house"})
public class HouseController extends BaseController {

    public static final Log LOG = LogFactory.getLog(HouseController.class);

    @Resource
    private IHouseService houseService;

    /**
     * 实有房屋分页查询
     * @param request
     * @param response
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getHouse"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有房屋分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<House>> getHouse(HttpServletRequest request, HttpServletResponse response, @RequestBody HouseListVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<House>> resp = validite(bindingResult);

        try {
            Integer currPage = para.getCurrentPage();
            Integer pageSize = para.getPageSize();
            //查询房屋处理
            Page<House> data = (Page) this.houseService.getHouse(para, currPage, pageSize);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //查询房屋失败处理
            this.logger.error("查找实有房屋失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.GETHOUSE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 实有单位分页查询
     * @param request
     * @param response
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getCompany"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<House>> getCompany(HttpServletRequest request, HttpServletResponse response, @RequestBody CompanyListVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<House>> resp = validite(bindingResult);

        try {
            para.setVillageCodes(getVillageCodes(para.getVillageCodes()));
            Integer currPage = para.getCurrentPage();
            Integer pageSize = para.getPageSize();
            //查询房屋处理
            Page<Company> data = (Page) this.houseService.getCompany(para, currPage.intValue(), pageSize.intValue());
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //查询单位失败处理
            this.logger.error("查找实有单位失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.GETCOMPANY_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    @RequestMapping(value = {"/getEmployee"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Employee>> getEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Employee>> resp = validite(bindingResult);

        try {
            int pageNum = NumberUtils.toInt((String) map.get("pageNum"));
            int pageSize = NumberUtils.toInt((String) map.get("pageSize"));

            Page data = (Page) this.houseService.getEmployee(map, pageNum, pageSize);

            resp.setData(new PageEntity(data));

        } catch (Exception e) {
            this.logger.error("查找实有单位人员 失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805002");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    @RequestMapping(value = {"/searchEmployee"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Employee>> searchEmployee(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Employee>> resp = validite(bindingResult);

        try {
            int pageNum = NumberUtils.toInt((String) map.get("pageNum"));
            int pageSize = NumberUtils.toInt((String) map.get("pageSize"));
            Page data = (Page) this.houseService.searchEmployee(map, pageNum, pageSize);
            resp.setData(new PageEntity(data));

        } catch (Exception e) {
            this.logger.error("查找实有单位人员 失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805002");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    @RequestMapping(value = {"getBuildHouses"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, Object>> getBuildHouses(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Building building, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            Map<String, Object> map = this.houseService.getBuildHouses(building);
            resp.setData(map);
        } catch (Exception e) {
            this.logger.error("查找首页房屋楼栋 失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    @RequestMapping(value = {"findList"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Vehicle>> findList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> map, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Vehicle>> resp = validite(bindingResult);

        try {
            Page data = (Page) this.houseService.findList(map);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查找过车记录失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805009");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

}
