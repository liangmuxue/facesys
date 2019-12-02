package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.baseinfo.common.web.BaseQueryEntity;
import com.ss.facesys.data.collect.client.IPeopleService;
import com.ss.facesys.data.collect.common.dto.*;
import com.ss.facesys.data.collect.common.model.LeavePerson;
import com.ss.facesys.data.collect.common.model.People;
import com.ss.facesys.data.collect.common.web.*;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIPageGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
* 智能分析
* @author chao
* @create 2019/8/22
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/collect/people"})
public class PeopleController extends BaseController {

    public static final Log LOG = LogFactory.getLog(PeopleController.class);

    @Resource
    private IPeopleService peopleService;
    @Resource
    private IOrganizationRegionService oRegionService;

    /**
     * 一社一档-居民详情
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = "80005", desc = "实有人口分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<People>> pages(@RequestBody @Validated({APIPageGroup.class}) PeopleQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<People>> resp = validite(bindingResult);
        try {
            // 查询用户权限小区（避免越权请求）
            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.setVillageCodes(villageCodes);
            Page<People> data = (Page) this.peopleService.page(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.COLLECT_PEOPLE_FAILED_CODE);
            resp.setMessage("实有人口分页查询失败");
            this.logger.error("实有人口分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 疑似新增分页查询
     * @param request
     * @param addPersonQuery
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/addPersonPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "疑似新增分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<AddPersonDTO>> addPersonPage(HttpServletRequest request, @RequestBody AddPersonQuery addPersonQuery, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<AddPersonDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(addPersonQuery);
            int pageSize = getPageSize(addPersonQuery);
            //获取用户操作权限
            String villageCodes = this.oRegionService.dataScopeFilter(addPersonQuery.getUserIds());
            addPersonQuery.getSqlMap().put("dsf", villageCodes);
            //疑似新增查询处理
            List<AddPersonDTO> list = this.peopleService.addPersonPage(addPersonQuery, currentPage, pageSize);
            Page<AddPersonDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //疑似新增查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.ADDPERSON_FAILED_CODE);
            resp.setMessage("疑似新增分页查询失败");
            this.logger.error("疑似新增分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping(value = {"/addPerson/captureDetails"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "疑似新增抓拍详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<AddPersonDetailVO>> addPersonCaptureDetails(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) AddPersonDetailQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<AddPersonDetailVO>> resp = validite(bindingResult);
        try {
            List<AddPersonDetailVO> list = this.peopleService.findAddPersonDetails(para.getAddPersonId());
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804012");
            resp.setMessage("疑似新增抓拍详情查询失败");
            this.logger.error("疑似新增抓拍详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 疑似离开分页查询
     * @param request
     * @param leavePersonQuery
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/leavePersonPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "疑似离开分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<LeavePerson>> leavePersonPage(HttpServletRequest request, @RequestBody LeavePersonQuery leavePersonQuery, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<LeavePerson>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(leavePersonQuery);
            int pageSize = getPageSize(leavePersonQuery);
            //获取用户操作权限
            String villageCodes = this.oRegionService.dataScopeFilter(leavePersonQuery.getUserIds());
            leavePersonQuery.getSqlMap().put("dsf", villageCodes);
            //疑似离开查询处理
            List<LeavePerson> list = this.peopleService.leavePersonPage(leavePersonQuery, currentPage, pageSize);
            Page<LeavePerson> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //疑似离开查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.LEAVEPERSON_FAILED_CODE);
            resp.setMessage("疑似离开分页查询失败");
            this.logger.error("疑似离开分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/top"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "高频top5", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<FrequencyRecordDTO>> frequencyRecordTop(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIListGroup.class}) BaseQueryEntity para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<FrequencyRecordDTO>> resp = validite(bindingResult);
        try {
            List<FrequencyRecordDTO> list = this.peopleService.frequencyRecordTop(para.getVillageCode());
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804013");
            resp.setMessage("高频top5查询失败");
            this.logger.error("高频top5查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/top/details"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "高频top5详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<AddPersonDetailVO>> findAddPersonDetails(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) AddPersonDetailQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<AddPersonDetailVO>> resp = validite(bindingResult);
        try {
            List<AddPersonDetailVO> list = this.peopleService.frequencyRecordTopDetails(para.getAddPersonId());
            resp.setData(list);
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804014");
            resp.setMessage("高频top5详情查询失败");
            this.logger.error("高频top5详情查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/frequencyPersonPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "高频陌生人分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<FrequencyRecordDTO>> frequencyPersonPage(HttpServletRequest request, HttpServletResponse response, @RequestBody FrequencyRecordQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<FrequencyRecordDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(para);
            int pageSize = getPageSize(para);

            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.getSqlMap().put("dsf", villageCodes);

            List<FrequencyRecordDTO> list = this.peopleService.frequencyPersonPage(para, currentPage, pageSize);
            Page<FrequencyRecordDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804010");
            resp.setMessage("高频陌生人分页查询失败");
            this.logger.error("高频陌生人分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/specialPersonPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "高龄老人分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<SpecialPersonDTO>> specialPersonPage(HttpServletRequest request, @RequestBody SpecialPersonQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<SpecialPersonDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(para);
            int pageSize = getPageSize(para);

            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.getSqlMap().put("dsf", villageCodes);

            List<SpecialPersonDTO> list = this.peopleService.specialPersonPage(para, currentPage, pageSize);
            Page<SpecialPersonDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70804011");
            resp.setMessage("高龄老人分页查询失败");
            this.logger.error("高龄老人分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 夜间高频人员查询
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/frequencyNightPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "夜间高频人员分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<FrequencyNightDTO>> frequencyNightPage(HttpServletRequest request, @RequestBody FrequencyNightQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<FrequencyNightDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(para);
            int pageSize = getPageSize(para);
            //获取用户操作权限
            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.getSqlMap().put("dsf", villageCodes);
            //夜间高频人员查询处理
            List<FrequencyNightDTO> list = this.peopleService.frequencyNightPage(para, currentPage, pageSize);
            Page<FrequencyNightDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //夜间高频人员查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.FREQUENCYNIGHT_FAILED_CODE);
            resp.setMessage("夜间高频人员分页查询失败");
            this.logger.error("夜间高频人员分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 长时间逗留人员分页查询
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/longtimeStayPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "长时间逗留人员分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<LongtimeStayDTO>> longtimeStayPage(HttpServletRequest request, @RequestBody LongtimeStayQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<LongtimeStayDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(para);
            int pageSize = getPageSize(para);
            //获取用户操作权限
            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.getSqlMap().put("dsf", villageCodes);
            //长时间逗留人员查询处理
            List<LongtimeStayDTO> list = this.peopleService.longtimeStayPage(para, currentPage, pageSize);
            Page<LongtimeStayDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //长时间逗留人员查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.LONGTIMESTAY_FAILED_CODE);
            resp.setMessage("长时间逗留人员分页查询失败");
            this.logger.error("长时间逗留人员分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 敏感通行人员分页查询
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/sensitiveTrafficPage"}, method = {RequestMethod.POST})
    @OpLog(model = "80004", desc = "敏感通行人员分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<SensitiveTrafficDTO>> sensitiveTrafficPage(HttpServletRequest request, @RequestBody SensitiveTrafficQuery para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<SensitiveTrafficDTO>> resp = validite(bindingResult);
        try {
            int currentPage = getPageIndex(para);
            int pageSize = getPageSize(para);
            //获取用户操作权限
            String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
            para.getSqlMap().put("dsf", villageCodes);
            //敏感通行人员分页查询处理
            List<SensitiveTrafficDTO> list = this.peopleService.sensitiveTrafficPage(para, currentPage, pageSize);
            Page<SensitiveTrafficDTO> data = (Page) list;
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //敏感通行人员分页查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.SENSITIVETRAFFIC_FAILED_CODE);
            resp.setMessage("敏感通行人员分页查询失败");
            this.logger.error("敏感通行人员分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
