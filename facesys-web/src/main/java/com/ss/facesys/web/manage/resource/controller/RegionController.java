package com.ss.facesys.web.manage.resource.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IThirdRegionService;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.ThirdRegion;
import com.ss.facesys.data.resource.common.web.RegionVO;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.data.system.common.dto.UserPermission;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.tools.UUIDUtils;
import com.ss.valide.APIListGroup;
import com.ss.valide.APIPageGroup;
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
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 行政区划web请求
 * @author FrancisYs
 * @date 2019/08/08
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/region"})
public class RegionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Resource
    private IRegionService regionService;
    @Resource
    private IThirdRegionService thirdRegionService;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private IOrganizationRegionService organizationRegionService;

    /**
     * 区划四级联动查询
     * @param region
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "四级联动", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<Region>> list(@RequestBody @Validated({APIListGroup.class}) RegionVO region, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Region>> resp = validite(bindingResult);
        try {
            Region paramRegion = new Region();
            paramRegion.setParentId(region.getParentId());
            List<Region> list = this.regionService.list(paramRegion);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("四级联动查询失败");
            this.logger.error("四级联动查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询行政区划分页列表
     * @param region
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/regionInfolist"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询行政区划列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Region>> regionInfolist(@RequestBody @Validated({APIPageGroup.class}) RegionVO region) throws Exception {
        ResponseEntity<PageEntity<Region>> resp = createSuccResponse();
        try {
            Page<Region> data = (Page)this.regionService.regionInfolist(region);
            resp.setData(new PageEntity<>(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("行政区划列表查询失败");
            this.logger.error("行政区划列表查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询行政区划树
     * @param region
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/regionTree"}, method = {RequestMethod.POST})
    public ResponseEntity<List<RegionTree>> regionTree(@RequestBody Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<RegionTree>> resp = createSuccResponse();
        try {
            List<RegionTree> list = this.regionService.regionTree(region);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("行政区划树查询失败");
            this.logger.error("行政区划树查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询行政区划树（包含小区信息）
     * @param region
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/treeData"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "首页行政区划树", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<RegionTree>> treeData(@RequestBody Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<RegionTree>> resp = createSuccResponse();
        try {
            // 参数校验
            if (StringUtils.isBlank(region.getUserIds())) {
                resp = createFailResponse();
                resp.setCode(ResultCode.INVALID_PARAMETER);
                resp.setMessage(ResultCode.INVALID_PARAMETER_MESSAGE);
                return resp;
            }
            // 查询当前用户的权限小区
            User user = new User();
            user.setUserId(region.getUserIds());
            String[] villageCodes = null;
            if (!user.isAdmin()) {
                UserPermission userPermission = this.organizationRegionService.findCurrentPersion(user.getUserId());
                if (userPermission != null) {
                    villageCodes = userPermission.getVillageCodes();
                }
            }
            // 查询行政区划树（包含小区）
            List<RegionTree> list = this.regionService.getTreeData(region, villageCodes);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("行政区划树查询失败");
            this.logger.error("行政区划树查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping(value = {"/updatePinyin"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "更新行政区划", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> updatePinyin(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);

        try {
            List<Region> list = this.regionService.list(region);
            for (Region region2 : list) {
                region2.setPinyin(StringUtils.getPinYinHeadChar(region2.getRegionName()));
                this.regionService.updateRegion(region2);
            }
            resp.setData("success");
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            this.logger.error("更新行政区划失败的原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/findRegion"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Region>> findRegion(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<Region>> resp = validite(bindingResult);
        try {
            List<Region> list = this.regionService.findCurrentRegion(region);
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            resp.setCode("70806005");
            this.logger.error("查找当前节点的详细信息:" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "新增行政区划", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> insertRegion(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);


        try {
            String thirdId = region.getThirdId();
            if (StringUtils.isNotBlank(thirdId)) {
                Integer regionType = region.getRegionType();
                Integer thirdRegionType = this.thirdRegionService.getRegionTypeByThirdId(thirdId);
                if (thirdRegionType.intValue() != 5 && regionType.intValue() == 5) {
                    resp = createFailResponse();
                    resp.setMessage("请选择正确的区域关联！街道只能关联第三方街道！");
                    return resp;
                }
                if (thirdRegionType.intValue() == 5 && regionType.intValue() != 5) {
                    resp = createFailResponse();
                    resp.setMessage("请选择正确的区域关联！省市区只能关联第三方省市区！");
                    return resp;
                }
                String regionThirdId = this.regionService.selectRegionCodeByThirdId(thirdId);
                region.setThirdId(regionThirdId);
            }
            String regionCode = region.getRegionCode();
            Region region1 = this.regionService.isRepeat(regionCode);
            if (region1 == null) {
                Date d = new Date();
                long time = d.getTime();
                BigDecimal b = new BigDecimal(time);
                region.setCreatedTime(b);
                region.setRegionId(UUIDUtils.getRangeString(16));
                region.setRemark(region.getRegionName());
                region.setPinyin(StringUtils.getPinYinHeadChar(region.getRegionName()));
                Integer result = this.regionService.insertRegion(region);
                if (result.intValue() > 0) {

                    this.jedisUtil.del(new String[]{"REGION_TREE"});
                    resp.setData("新增行政区划成功");
                } else {

                    resp = createFailResponse();
                    resp.setCode("70806002");
                    resp.setMessage("操作失败，请联系管理员");
                }
            } else {

                resp = createFailResponse();
                resp.setCode("70806002");
                resp.setMessage("新增区划代码重复！请重新输入");
            }

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            resp.setCode("70806002");
            this.logger.error("新增行政区划失败的原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "删除行政区划当前节点和所有子节点", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Map<String, String>> deleteRegion(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, String>> resp = validite(bindingResult);
        try {
            Map<String, String> resultMap = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

            Integer result1 = this.regionService.deleteChildrenRegion(region);

            Integer result = this.regionService.deleteRegion(region);
            this.jedisUtil.del(new String[]{"REGION_TREE"});
            if (result1.intValue() > 0) {

                this.jedisUtil.del(new String[]{"REGION_TREE"});
                resultMap.put("result", "删除成功！");
                resp.setData(resultMap);
            } else {

                resultMap.put("result", "删除失败！");
                resp.setData(resultMap);
            }

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            resp.setCode("70806003");
            this.logger.error("删除当前节点和子节点失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "更新当前行政区号", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> updateRegion(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) Region region, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);


        try {
            String thirdId = region.getThirdId();
            if (StringUtils.isNotBlank(thirdId)) {
                String regionThirdId = this.regionService.selectRegionCodeByThirdId(thirdId);
                region.setThirdId(regionThirdId);


                Integer thirdRegionType = this.thirdRegionService.getRegionTypeByThirdId(thirdId);

                Integer regionType = region.getRegionType();
                if (thirdRegionType.intValue() == 1 && regionType.intValue() == 5) {
                    resp = createFailResponse();
                    resp.setMessage("请选择正确的区域关联！街道只能关联第三方街道！");
                    return resp;
                }
                if (thirdRegionType.intValue() == 5 && regionType.intValue() != 5) {
                    resp = createFailResponse();
                    resp.setMessage("请选择正确的区域关联！省市区只能关联第三方省市区！");
                    return resp;
                }
            }
            region.setRemark(region.getRegionName());
            region.setPinyin(StringUtils.getPinYinHeadChar(region.getRegionName()));
            Integer result = this.regionService.updateRegion(region);
            if (result.intValue() > 0) {

                this.jedisUtil.del(new String[]{"REGION_TREE"});
                resp.setData("更新行政区划成功");
            } else {

                resp = createFailResponse();
                resp.setCode("70806004");
                resp.setMessage("操作失败，请联系管理员");
            }

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            resp.setCode("70806004");
            this.logger.error("更新当前行政区号失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/match"}, method = {RequestMethod.POST})
    public ResponseEntity<String> matchRegion(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> para) throws Exception {
        ResponseEntity<String> resp = null;
        if (para.containsKey("locRegion") && para.containsKey("thirdRegion") && para.containsKey("regionType")) {
            String locRegion = para.get("locRegion").toString();
            String thirdRegion = para.get("thirdRegion").toString();
            int regionType = Integer.valueOf(para.get("regionType").toString()).intValue();

            Region local = new Region();
            local.setRegionCode(locRegion);
            local = this.regionService.selectOne(local);

            ThirdRegion third = new ThirdRegion();
            third.setRegionCode(thirdRegion);
            third = this.thirdRegionService.selectOne(third);

            int count = this.regionService.matchRegion(local, third, regionType);
            if (count > 0) {
                resp = createSuccResponse();
            } else {

                resp = createFailResponse();
                resp.setMessage("数据库没有记录更新");
            }
        } else {

            resp = createFailResponse();
            resp.setMessage("参数异常");
        }

        return resp;
    }


    @RequestMapping(value = {"/thirdTreeData"}, method = {RequestMethod.POST})
    public ResponseEntity<RegionTree> thirdTreeData(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> para, BindingResult bindingResult) throws Exception {
        ResponseEntity<RegionTree> resp = createSuccResponse();

        try {
            RegionTree root = this.regionService.getThirdTreeData();
            resp.setData(root);

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("操作失败，请联系管理员");
            this.logger.error("查找第三方树结构失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
