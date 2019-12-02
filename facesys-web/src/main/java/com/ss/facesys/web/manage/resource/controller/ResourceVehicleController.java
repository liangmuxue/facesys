package com.ss.facesys.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.IResourceHouseService;
import com.ss.facesys.data.resource.client.IResourcePeopleService;
import com.ss.facesys.data.resource.common.model.People;
import com.ss.facesys.data.resource.common.model.PeopleVehicle;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;;
import java.util.List;

/**
* 房屋增删改
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/resource/vehicle"})
public class ResourceVehicleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ResourceVehicleController.class);

    @Resource
    private IResourceHouseService houseService;
    @Resource
    private IResourcePeopleService peopleService;

    /**
     * 查询人车关系待添加车辆列表
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleVehicleList"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "查询人车关系待添加车辆列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<People>> peopleList(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIListGroup.class}) PeopleVehicle para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<People>> resp = validite(bindingResult);
        try {
            //查询人车关系待添加车辆列表处理
            List<People> data = this.peopleService.peopleVehicleList(para);
            resp.setData(data);
        } catch (Exception e) {
            //查询人车关系待添加车辆列表失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_VEHICLE_PEOPLELIST_FAILED_CODE);
            resp.setMessage("查询人车关系待添加车辆列表失败");
            this.logger.error("查询人车关系待添加车辆列表失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 人车关系编辑
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peopleVehicleRelation"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "人车关系编辑", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Object> savePeopleRelation(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) PeopleVehicle para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Object> resp = validite(bindingResult);
        try {
            //人车关系编辑处理
            Object object = this.houseService.vehiclePeopleRelation(para);
            resp.setData(object);
        } catch (Exception e) {
            //人房关系编辑处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_HOUSE_PEOPLERELATION_FAILED_CODE);
            resp.setMessage("人车关系编辑失败");
            this.logger.error("人车关系编辑失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
