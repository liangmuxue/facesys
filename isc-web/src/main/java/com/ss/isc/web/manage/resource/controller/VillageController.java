package com.ss.isc.web.manage.resource.controller;

import com.github.pagehelper.Page;
import com.ss.isc.data.access.client.IAccessService;
import com.ss.isc.data.baseinfo.common.web.BaseQueryEntity;
import com.ss.isc.data.resource.client.ICommunityResourceService;
import com.ss.isc.data.resource.client.IHomePageResourceService;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IVillageService;
import com.ss.isc.data.resource.common.model.*;
import com.ss.isc.data.resource.common.web.VillageQueryVO;
import com.ss.isc.data.resource.mapper.VillageMapper;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.file.FileConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.isc.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIPageGroup;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VillageController：小区web请求
 * @author FrancisYs
 * @date 2019/8/15
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/resource/village"})
public class VillageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(VillageController.class);

    @Resource
    private IRegionService regionService;
    @Resource
    private IVillageService villageService;
    @Resource
    private IAccessService accessService;
    @Resource
    private IOrganizationRegionService organizationRegionService;
    @Resource
    private ICommunityResourceService communityResourceService;
    @Autowired
    private IHomePageResourceService homePageResourceService;
    @Autowired
    private VillageMapper villageMapper;

    /**
     * 查询小区分页数据
     * @param queryVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/findAllVillage"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<Village>> findAllVillage(@RequestBody @Validated({APIPageGroup.class}) VillageQueryVO queryVO) throws Exception {
        ResponseEntity<PageEntity<Village>> resp = createSuccResponse();
        try {
            // 查询区划下的全部小区
            if (StringUtils.isNotBlank(queryVO.getRegionCodes())) {
                List<String> villageCodeList = new ArrayList<>();
                for (String regionCode : queryVO.getRegionCodes().split(CommonConstant.SPLIT_COMMA)) {
                    String villageCodes = this.regionService.getVilllageCodes(regionCode);
                    if (StringUtils.isNotBlank(villageCodes)) {
                        villageCodeList.add(villageCodes);
                    }
                }
                if (CollectionUtils.isNotEmpty(villageCodeList)) {
                    queryVO.setVillageCodes(String.join(",", villageCodeList.toArray(new String[]{})));
                }
            }
            // 查询小区分页列表
            Page<Village> data = (Page) this.villageService.findAllVillage(queryVO);
            if (null != data) {
                resp.setData(new PageEntity(data));
            }
        } catch (Exception e) {
            this.logger.error("查找所有的小区失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.FINDALLVILLAGES_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 新增小区
     * @param village
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    public ResponseEntity<Integer> insertVillage(@RequestBody @Validated({APIAddGroup.class}) Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<Integer> resp = validite(bindingResult);
        try {
            int count = this.villageService.insertVillage(village);
            resp.setData(count);
        } catch (Exception e) {
            this.logger.error("新增小区失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.VILLAGEINSERT_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 删除小区
     * @param village
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> deleteVillage(@RequestBody @Validated({APIDeltGroup.class}) Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Map<String, String> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);

            String villageCode = village.getVillageCode();
            /*
             * 此处将userIds参数设为1000（管理员）未知原因
             */
            if (StringUtils.isNotBlank(village.getUserIds())) {
                village.setUserIds("1000");
            }

            // 删除前校验小区下是否关联出入口资源
            List<VillageEntrance> findVillageEntrance = this.communityResourceService.findVillageEntrance(village);
            if (findVillageEntrance != null && findVillageEntrance.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区出入口资源！");
                return resp;
            }
            // 删除前校验小区下是否关联楼栋资源
            List<Building> buildings = this.communityResourceService.findBuildings(village);
            if (buildings != null && buildings.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区楼栋资源！");
                return resp;
            }
            // 删除前校验小区下是否关联单位资源
            List<Company> companys = this.homePageResourceService.findCompany(village);
            if (companys != null && companys.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区单位资源！");
                return resp;
            }
            // 删除前校验小区下是否关联wifi资源
            List<Wifi> wifis = this.homePageResourceService.findWifis(village);
            if (wifis != null && wifis.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区wifi资源！");
                return resp;
            }
            // 删除前校验小区下是否关联车辆卡口资源
            List<Tollgate> tollgates = this.homePageResourceService.findTollgates(village);
            if (tollgates != null && tollgates.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区车辆卡口资源！");
                return resp;
            }
            // 删除前校验小区下是否关联门禁资源
            List<AccessDevice> accessDevices = this.homePageResourceService.findAccessDevice(village);
            if (accessDevices != null && accessDevices.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区门禁资源！");
                return resp;
            }
            // 删除前校验小区下是否关联门禁资源
            Camera camera = new Camera();
            if (null != villageCode) {
                camera.setVillageCode(villageCode);
            }
            camera.setUserIds(village.getUserIds());
            List<Camera> cameras = this.communityResourceService.findCameras(camera);
            if (cameras != null && cameras.size() != 0) {
                resp = createFailResponse();
                resp.setMessage("请先删除与该小区关联的小区相机资源！");
                return resp;
            }

            // 查询小区关联人像库数据（删除小区时会同时删除关联人像库数据）
            Facedb facedb = this.villageService.findFaceDbByCode(village.getVillageCode());
            // 删除小区
            Integer result1 = this.villageService.deleteVillage(village, facedb.getFacedbId());

            if (result1 > 0) {
                // 成功删除小区后删除redis小区数据缓存
                this.jedisUtil.del(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
                this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
                this.jedisUtil.del(CacheConstant.REDIS_KEY_SUPERHOME_SEVEN);
                resp.setMessage("删除成功！");
            } else {
                resp = createFailResponse();
                resp.setCode(ResultCode.VILLAGEDELETE_FAILED_CODE);
                resultMap.put("result", "删除失败！");
                resp.setData(resultMap);
            }
        } catch (Exception e) {
            this.logger.error("删除小区失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.VILLAGEDELETE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 修改小区
     * @param village
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> updateRegion(@RequestBody @Validated({APIEditGroup.class}) Village village, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Map<String, String> resultMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            village.setPinyin(StringUtils.getPinYinHeadChar(village.getVillageName()));
            Integer result = this.villageService.updateVillage(village);
            if (result > 0) {
                // 删除redis中与小区相关的数据
                this.jedisUtil.del(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
                this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
                this.jedisUtil.del(CacheConstant.REDIS_KEY_ALL_VILLAGE);
                this.jedisUtil.del(CacheConstant.REDIS_KEY_SUPERHOME_SEVEN);
                resultMap.put("result", "更新成功");
                resp.setData(resultMap);
            } else {
                resp = createFailResponse();
                resp.setCode(ResultCode.VILLAGEUPDATE_FAILED_CODE);
                resultMap.put("result", "更新失败，请重新添加！");
                resp.setData(resultMap);
            }
            resp.setData(resultMap);
        } catch (Exception e) {
            this.logger.error("更新小区失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode(ResultCode.VILLAGEUPDATE_FAILED_CODE);
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询小区详情
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/findVillage"}, method = {RequestMethod.POST})
    public ResponseEntity<Village> findVillage(@RequestBody Map<String, String> map) throws Exception {
        ResponseEntity<Village> resp = createSuccResponse();
        try {
            if (!map.containsKey("id") || StringUtils.isBlank(map.get("id"))) {
                resp = createFailResponse();
                resp.setMessage("查询失败：请传入主键参数");
                return resp;
            }
            List<Village> data = this.villageService.findVillage(map);
            if (CollectionUtils.isNotEmpty(data)) {
                // 小区照片调整为完整路径
                for (Village village : data) {
                    if (StringUtils.isNotBlank(village.getVillagePicUrl()) && !village.getVillagePicUrl().contains(FileConstant.FILE_HTTPADD)) {
                        village.setVillagePicFullUrl(FilePropertiesUtil.getHttpUrl() + village.getVillagePicUrl());
                    }
                }
                resp.setData(data.get(0));
            }
        } catch (Exception e) {
            this.logger.error("查询小区失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/findAllVillageEntrances"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<VillageEntrance>> findAllVillageEntrances(HttpServletRequest request, HttpServletResponse response, @RequestBody VillageEntrance villageEntrance, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<VillageEntrance>> resp = createSuccResponse();


        try {
            if (StringUtils.isBlank(villageEntrance.getVillageCode()) &&
                    StringUtils.isNotBlank(villageEntrance.getRegionCode())) {
                String villageCodes = this.regionService.getVilllageCodes(villageEntrance.getRegionCode());
                villageEntrance.setVillageCodes(villageCodes);
            }

            Page<VillageEntrance> data = (Page) this.villageService.findAllVillageEntrances(villageEntrance);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            this.logger.error("查询小区出路口失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setCode("70805006");
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }


    @RequestMapping(value = {"/findVillageByCode"}, method = {RequestMethod.POST})
    public ResponseEntity<Village> findVillageByCode(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Village> resp = validite(bindingResult);
        resp.setData(this.villageService.findVillageByCode(para.getVillageCode()));
        return resp;
    }

}
