package com.ss.facesys.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IResourceHouseService;
import com.ss.facesys.data.resource.client.IResourcePeopleService;
import com.ss.facesys.data.resource.common.dto.HouseDTO;
import com.ss.facesys.data.resource.common.model.House;
import com.ss.facesys.data.resource.common.model.People;
import com.ss.facesys.data.resource.common.model.PeopleHouse;
import com.ss.facesys.data.resource.common.web.HouseEditVO;
import com.ss.facesys.data.resource.common.web.HouseQueryVO;
import com.ss.facesys.data.resource.common.web.PeopleHouseVO;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.export.TemplateReflectUtils;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
* 房屋增删改
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/resource/house"})
public class ResourceHouseController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ResourceHouseController.class);

    @Resource
    private IResourceHouseService houseService;
    @Resource
    private IResourcePeopleService peopleService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IOrganizationRegionService oRegionService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋信息分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<HouseDTO>> pages(HttpServletRequest request, @RequestBody HouseQueryVO para) throws Exception {
        ResponseEntity<PageEntity<HouseDTO>> resp = createSuccResponse();
        try {
            para.setCurrentPage(Integer.valueOf(getPageIndex(para)));
            para.setPageSize(Integer.valueOf(getPageSize(para)));
            if (StringUtils.isBlank(para.getVillageCode()) && StringUtils.isNotBlank(para.getRegionCode())) {
                String villageCodes = this.regionService.getVilllageCodes(para.getRegionCode());
                para.setVillageCodes(villageCodes);
            }
            if (para.getVillageCodes() == null) {
                String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
                para.setVillageCodes(villageCodes);
            }
            Page<HouseDTO> data = (Page) this.houseService.pages(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806022");
            resp.setMessage("房屋信息分页查询失败");
            this.logger.error("房屋信息分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋信息详情信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<HouseDTO> get(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) HouseQueryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<HouseDTO> resp = validite(bindingResult);
        try {
            resp.setData(this.houseService.get(para.getId()));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806023");
            resp.setMessage("房屋信息详情信息查询失败");
            this.logger.error("房屋信息详情信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增房屋
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) HouseEditVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            House house = new House();
            BeanUtils.copyProperties(para, house);
            //新增房屋处理
            String message = this.houseService.save(house);
            //新增房屋存在处理
            if ("ishave".equals(message)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_HOUSE_ADD_FAILED_CODE);
                resp.setMessage("房屋编号已经存在");
            } else {
                resp.setMessage("新增房屋信息成功");
            }
        } catch (Exception e) {
            //新增房屋失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_ADD_FAILED_CODE);
            resp.setMessage("房屋新增失败");
            this.logger.error("房屋新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 修改房屋
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋修改", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) HouseEditVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            House house = new House();
            BeanUtils.copyProperties(para, house);
            //修改房屋信息处理
            String message = this.houseService.edit(house);
            if ("ishave".equals(message)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_HOUSE_EDIT_FAILED_CODE);
                resp.setMessage("房屋编号已经存在");
            } else {
                resp.setMessage("修改房屋信息成功");
            }
        } catch (Exception e) {
            //修改房屋信息失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_EDIT_FAILED_CODE);
            resp.setMessage("房屋修改失败");
            this.logger.error("房屋修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 删除房屋
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) HouseEditVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.houseService.delete(para.getId());
            if ("havePeople".equals(message)) {
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_HOUSE_DELETE_FAILED_CODE);
                resp.setMessage("请先清除人房关系");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_DELETE_FAILED_CODE);
            resp.setMessage("房屋删除失败");
            this.logger.error("房屋删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 批量删除房屋
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/batchDelete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "资源房屋批量删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> batchDelete(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) HouseEditVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.houseService.batchDelete(para.getIds());
            if (!"".equals(message)) {
                resp = createFailResponse();
                resp.setCode("70806026");
                resp.setMessage(message);
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806026");
            resp.setMessage("房屋删除失败");
            this.logger.error("房屋删除失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 查询人房关系待添加人员列表
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleList"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询人房关系待添加人员列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<People>> peopleList(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIListGroup.class}) PeopleHouseVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<People>> resp = validite(bindingResult);
        try {
            //查询人房关系待添加人员列表处理
            List<People> data = this.peopleService.peopleList(para);
            resp.setData(data);
        } catch (Exception e) {
            //查询人房关系待添加人员列表失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_PEOPLELIST_FAILED_CODE);
            resp.setMessage("查询人房关系待添加人员列表失败");
            this.logger.error("查询人房关系待添加人员列表列表失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 人房关系编辑
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleRelation"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "人房关系编辑", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Object> savePeopleRelation(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) PeopleHouseVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Object> resp = validite(bindingResult);
        try {
            //人房关系编辑处理
            Object object = this.houseService.housePeopleRelation(para);
            resp.setData(object);
        } catch (Exception e) {
            //人房关系编辑失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_PEOPLERELATION_FAILED_CODE);
            resp.setMessage("人房关系编辑失败");
            this.logger.error("人房关系编辑失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping(value = {"/importHouseData"}, method = {RequestMethod.POST})
    public ResponseEntity<Object> importHouseData(HttpServletRequest request, MultipartFile file) {
        ResponseEntity<Object> resp = createSuccResponse();
        try {
            Map<String, String> res = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
            String path = "house";
            String type = request.getParameter("type");

            if ("1".equals(type)) {
                Map<String, Object> map = TemplateReflectUtils.getDataList(House.class, path, file, true);

                List<House> tempList = (List) map.get("list");
                res = this.houseService.batchImport(tempList);
            } else {
                List<PeopleHouse> tempList = TemplateReflectUtils.getDataList(PeopleHouse.class, file);
                res = this.houseService.importPeopleRelation(tempList);
            }
            if ("failed".equals(res.get("result"))) {
                resp = createFailResponse();
                resp.setCode("70806029");
            }
            resp.setMessage((String) res.get("message"));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806029");
            resp.setMessage("房屋信息导入失败");
            this.logger.error("房屋信息导入失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
