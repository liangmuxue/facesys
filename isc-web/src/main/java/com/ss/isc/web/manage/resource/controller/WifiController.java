package com.ss.isc.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IWifiService;
import com.ss.isc.data.resource.common.model.Wifi;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.export.TemplateReflectUtils;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping({"/resource/wifi"})
public class WifiController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CameraController.class);


    @Resource
    private IRegionService regionService;


    @Resource
    private IWifiService wifiService;


    @Resource
    private IOrganizationRegionService organizationRegionService;


    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "新增一个wifi设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> insertWifi(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Wifi wifi, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            int num = this.wifiService.insertWifi(wifi);
            if (num > 0) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("result", "添加成功");
                resp.setData(map);
            }
        } catch (Exception e) {
            this.logger.error("新增wifi设备失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70806041");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "更新一个wifi设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> updateWifi(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Wifi wifi, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            int num = this.wifiService.updateWifi(wifi);
            Map<String, String> map = new HashMap<String, String>();
            if (num > 0) {
                map.put("result", "更新成功");
                resp.setData(map);
            } else {
                map.put("result", "更新失败");
                resp.setData(map);
            }
        } catch (Exception e) {
            this.logger.error("更新wifi设备失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70806043");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "删除一个wifi设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> deleteWifi(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Wifi wifi, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            int num = this.wifiService.deleteWifi(wifi);
            Map<String, String> map = new HashMap<String, String>();
            if (num > 0) {
                map.put("result", "删除成功");
                resp.setData(map);
            } else {
                map.put("result", "删除失败");
                resp.setData(map);
            }
        } catch (Exception e) {
            this.logger.error("删除wifi设备失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70806042");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/findAllWifis"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查找wifi设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Wifi>> findAllWifis(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Wifi wifi, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Wifi>> resp = validite(bindingResult);

        try {
            if (StringUtils.isBlank(wifi.getVillageCode()) && StringUtils.isNotBlank(wifi.getRegionCode())) {
                String villageCodes = this.regionService.getVilllageCodes(wifi.getRegionCode());
                wifi.setVillageCodes(villageCodes);
            }
            Page<Wifi> data = (Page) this.wifiService.findAllWifis(wifi);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查找wifi设备失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805010");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "wifi设备采集", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Wifi>> pages(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Wifi wifi, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Wifi>> resp = validite(bindingResult);

        try {
            if (null == wifi.getCurrentPage() || null == wifi.getPageSize()) {
                wifi.setCurrentPage(Integer.valueOf(1));
                wifi.setPageSize(Integer.valueOf(10));
            }

            List<String> villages = wifi.getVillages();

            if (null == wifi.getVillages() || villages.size() == 0) {
                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(wifi.getUserIds());
                if (userPermission != null) {
                    String[] villageCodes = userPermission.getVillageCodes();
                    if (null != villageCodes && villageCodes.length > 0) {
                        List<String> asList = Arrays.asList(villageCodes);
                        wifi.setVillages(asList);
                    } else {

                        resp = createFailResponse();
                        resp.setMessage("您没有任何小区的权限，请联系管理员！");
                    }
                }
            }
            Page<Wifi> data = (Page) this.wifiService.pages(wifi);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查找wifi采集记录失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805004");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/importWifiData"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "导入wifi设备", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Object> importWifiData(HttpServletRequest request, MultipartFile file) throws Exception {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            Map<String, Object> map = TemplateReflectUtils.getDataList(Wifi.class, "wifi", file, true);


            List<Wifi> tempList = (List) map.get("list");

            Map<String, String> imagePaths = (Map) map.get("imagePaths");

            String message = this.wifiService.batchImport(tempList, imagePaths);
            resp.setData(message);
        } catch (Exception e) {
            this.logger.error("导入wifi记录失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70806045");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

}
