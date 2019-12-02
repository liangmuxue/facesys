package com.ss.facesys.web.manage.archives.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.archives.client.IPersonService;
import com.ss.facesys.data.archives.common.dto.PersonHouseDTO;
import com.ss.facesys.data.archives.common.dto.PersonHousePeopleDTO;
import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.archives.common.dto.VillageCodeDTO;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.archives.common.model.WifiCollect;
import com.ss.facesys.data.archives.common.web.HouseVO;
import com.ss.facesys.data.archives.common.web.InfrastructureVO;
import com.ss.facesys.data.archives.common.web.PeopleVO;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ss.valide.APIGetsGroup;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 实有人口详细信息请求
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/archives/person"})
public class PersonController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private IPersonService personService;
    @Resource
    private IOrganizationRegionService oRegionService;

    /**
     * 一人一档-查询实有人口详情
     * @param peopleVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "获取人员信息详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> detail(@RequestBody @Validated({APIGetsGroup.class}) PeopleVO peopleVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            People peopleDTO = new People();
            peopleDTO.setUserIds(peopleVO.getUserIds());
            peopleDTO.setId(peopleVO.getId());
            Map<String, Object> map = this.personService.detail(peopleDTO);
            resp.setData(map);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_DETAIL_FAILED_CODE);
            resp.setMessage("一人一档根据身份证号获取人员信息详情,查询失败!");
            this.logger.error("一人一档根据身份证号获取人员信息详情,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 房屋详情查询
     * @param request
     * @param personHouseDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/house"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "房屋详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<HouseVO>> house(HttpServletRequest request, @RequestBody PersonHouseDTO personHouseDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<HouseVO>> resp = validite(bindingResult);
        try {
            //房屋查询处理
            List<HouseVO> houseList = this.personService.house(personHouseDTO);
            resp.setData(houseList);
        } catch (Exception e) {
            //房屋查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_HOUSE_FAILED_CODE);
            resp.setMessage("一人一档获取房屋详情,查询失败!");
            this.logger.error("一人一档获取房屋详情,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/infrastructure"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "查询水电煤", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<InfrastructureVO> infrastructure(HttpServletRequest request, @RequestBody PersonInfraDTO personInfraDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<InfrastructureVO> resp = validite(bindingResult);


        try {
            InfrastructureVO ifra = this.personService.infrastructure(personInfraDTO);

            resp.setData(ifra);
        } catch (Exception e) {

            resp = createFailResponse();
            resp.setCode("70802015");
            resp.setMessage("一人一档查询水电煤,查询失败!");
            this.logger.error("一人一档查询水电煤,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 房屋居住人员信息
     * @param request
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/housePeople"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "房屋居住人员信息", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> housePeople(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class})PersonHousePeopleDTO dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            //房屋居住人员信息查询处理
            Map<String, Object> housePeopleMap = this.personService.getPeopleByHouseId(dto);
            resp.setData(housePeopleMap);
        } catch (Exception e) {
            //房屋居住人员信息查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_HOUSEPEOPLE_FAILED_CODE);
            resp.setMessage("一人一档房屋居住人员信息,查询失败!");
            this.logger.error("一人一档房屋居住人员信息,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 一人一档-预警信息
     * @param dto
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/warning"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "预警信息", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<Map<String, Object>> warning(@RequestBody VillageCodeDTO dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<Map<String, Object>> resp = validite(bindingResult);
        try {
            if (StringUtils.isBlank(dto.getCredentialNo()) || StringUtils.isBlank(dto.getUserIds())) {
                resp = createFailResponse();
                resp.setMessage("一人一档预警信息查询失败：身份证号和当前用户编号不能为空");
                return resp;
            }
            dto.setBeginTime(CommonConstant.DATE_PATTERN.format(DateUtils.addDays(new Date(), PropertiesUtil.getWarningDays())));
            dto.setEndTime(CommonConstant.DATE_PATTERN.format(new Date()));
            String villageCodes = this.oRegionService.dataScopeFilter(dto.getUserIds());
            dto.setSqlString(villageCodes);
            Map<String, Object> warningMap = this.personService.warning(dto);
            resp.setData(warningMap);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.ARCHIVES_PERSON_WARNING_FAILED_CODE);
            resp.setMessage("一人一档预警信息,查询失败!");
            this.logger.error("一人一档预警信息,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/mac"}, method = {RequestMethod.POST})
    @OpLog(model = "80002", desc = "mac记录列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<WifiCollect>> mac(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIListGroup.class}) VillageCodeDTO dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<WifiCollect>> resp = validite(bindingResult);

        try {
            dto.setBeginTime(CommonConstant.DATE_PATTERN.format(DateUtils.addDays(new Date(), PropertiesUtil.getMacRrcordDays())));
            dto.setEndTime(CommonConstant.DATE_PATTERN.format(new Date()));

            List<WifiCollect> housePeopleMap = this.personService.mac(dto);


            resp.setData(housePeopleMap);
        } catch (Exception e) {

            resp = createFailResponse();
            resp.setCode("70802018");
            resp.setMessage("一人一档mac记录列表,查询失败!");
            this.logger.error("一人一档mac记录列表,查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
