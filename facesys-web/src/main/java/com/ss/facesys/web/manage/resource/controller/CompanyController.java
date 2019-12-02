package com.ss.facesys.web.manage.resource.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.resource.client.ICompanyService;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.common.model.Company;
import com.ss.facesys.data.resource.common.model.CompanyPeople;
import com.ss.facesys.data.resource.common.web.CompanyPeopleVO;
import com.ss.facesys.data.resource.common.web.CompanyQueryVO;
import com.ss.facesys.data.resource.common.web.CompanyVO;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.export.TemplateReflectUtils;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.baseinfo.controller.ResultCode;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
* 实有单位增删改查
* @author chao
* @create 2019/8/16
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/resource/company"})
public class CompanyController extends BaseController {

    public static final Log LOG = LogFactory.getLog(CompanyController.class);

    @Resource
    private ICompanyService companyService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IOrganizationRegionService oRegionService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位信息分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<Company>> pages(HttpServletRequest request, @RequestBody CompanyQueryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Company>> resp = validite(bindingResult);
        try {
            int currPage = getPageIndex(para);
            int pageSize = getPageSize(para);
            if (StringUtils.isBlank(para.getVillageCode()) && StringUtils.isNotBlank(para.getRegionCode())) {
                String villageCodes = this.regionService.getVilllageCodes(para.getRegionCode());
                para.setVillageCodes(villageCodes);
            }
            if (para.getVillageCodes() == null) {
                String villageCodes = this.oRegionService.dataScopeFilter(para.getUserIds());
                para.setVillageCodes(villageCodes);
            }
            Page<Company> data = (Page) this.companyService.pages(para, currPage, pageSize);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806057");
            resp.setMessage("操作失败，请联系管理员");
            LOG.error("实有单位信息分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 实有单位详情信息
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位详情信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Company> get(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) CompanyVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<Company> resp = validite(bindingResult);
        try {
            //实有单位详情信息查询处理
            resp.setData(this.companyService.get(para.getId()));
        } catch (Exception e) {
            //实有单位详情信息查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_INFO_FAILED_CODE);
            resp.setMessage("实有单位详情信息查询失败");
            this.logger.error("实有单位详情信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 新增实有单位
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位新增", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIAddGroup.class}) CompanyVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            Company company = new Company();
            BeanUtils.copyProperties(para, company);
            //新增实有单位处理
            String message = this.companyService.save(company);
            if ("success".equals(message)) {
                resp.setData(message);
            } else {
                //新增实有单位失败处理
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_COMPANY_ADD_FAILED_CODE);
                resp.setMessage("实有单位统一社会信用代码重复，无法添加");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_ADD_FAILED_CODE);
            resp.setMessage("实有单位新增失败");
            LOG.error("实有单位新增失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 实有单位修改
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位修改", type = OperaTypeEnum.SELECT)
    public ResponseEntity<String> edit(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIEditGroup.class}) CompanyVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            Company company = new Company();
            BeanUtils.copyProperties(para, company);
            //实有单位修改处理
            String message = this.companyService.edit(company);
            if ("success".equals(message)) {
                resp.setData(message);
            } else {
                //实有单位修改处理
                resp = createFailResponse();
                resp.setCode(ResultCode.RESOURCE_COMPANY_EDIT_FAILED_CODE);
                resp.setMessage("实有单位统一社会信用代码重复，无法添加");
            }
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_EDIT_FAILED_CODE);
            resp.setMessage("实有单位修改失败");
            LOG.error("实有单位修改失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 实有单位删除
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) CompanyVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            //实有单位删除处理
            String message = this.companyService.delete(para);
            resp.setData(message);
        } catch (Exception e) {
            //实有单位删除失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_DELETE_FAILED_CODE);
            resp.setMessage("实有单位删除失败");
            LOG.error("实有单位删除失败：原因" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 实有单位从业人员分页查询
     * @param request
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/companyPeople/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位从业人员分页查询", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<CompanyPeople>> companyPeoplePages(HttpServletRequest request, @RequestBody CompanyPeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<CompanyPeople>> resp = validite(bindingResult);

        try {
            Integer currPage = para.getCurrentPage();
            Integer pageSize = para.getPageSize();
            //实有单位从业人员分页查询处理
            Page<CompanyPeople> data = (Page) this.companyService.companyPeoplePages(para, currPage.intValue(), pageSize.intValue());
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //实有单位从业人员分页查询失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_PEOPLE_PAGE_FAILED_CODE);
            resp.setMessage("实有单位从业人员分页查询失败");
            LOG.error("实有单位从业人员分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping(value = {"/companyPeople/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位从业人员删除", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> companyPeopleDelete(HttpServletRequest request, @RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) CompanyPeopleVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            //实有单位删除处理
            String message = this.companyService.companyPeopleDelete(para);
            resp.setData(message);
        } catch (Exception e) {
            //实有单位删除失败处理
            resp = createFailResponse();
            resp.setCode(ResultCode.RESOURCE_COMPANY_DELETE_FAILED_CODE);
            resp.setMessage("实有单位从业人员删除失败");
            LOG.error("实有单位从业人员删除失败：原因" + e.toString(), e);
        }
        return resp;
    }

    @RequestMapping({"/importCompany"})
    @OpLog(model = "80006", desc = "实有单位批量导入", type = OperaTypeEnum.OTHER)
    public ResponseEntity<String> importCompany(HttpServletRequest request, MultipartFile file) {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            Map<String, Object> map = TemplateReflectUtils.getDataList(Company.class, "company", file, true);
            List<Company> tempList = (List) map.get("list");
            Map<String, String> imagePaths = (Map) map.get("imagePaths");
            Map<String, String> res = this.companyService.importCompany(tempList, imagePaths);
            if ("success".equals(res.get("result"))) {
                resp.setMessage((String) res.get("message"));
            } else {
                resp = createFailResponse();
                resp.setMessage((String) res.get("message"));
            }

        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806063");
            resp.setMessage("实有单位导入失败");
            LOG.error("实有单位导入失败，原因：" + e.toString(), e);
        }
        return resp;
    }


    @RequestMapping(value = {"/importCompanyPeople"}, method = {RequestMethod.POST})
    @OpLog(model = "80006", desc = "实有单位人员批量导入", type = OperaTypeEnum.OTHER)
    public ResponseEntity<String> importCompanyPeople(HttpServletRequest request, MultipartFile file, Company company) {
        ResponseEntity<String> resp = null;
        try {
            String path = "company_people";
            Map<String, Object> map = TemplateReflectUtils.getDataList(CompanyPeople.class, path, file, true);

            List<CompanyPeople> tempList = (List) map.get("list");

            Map<String, String> imagePaths = (Map) map.get("imagePaths");

            Map<String, String> res = this.companyService.importCompanyPeople(tempList, company, imagePaths);
            if ("success".equals(res.get("result"))) {
                resp = createSuccResponse();
            } else {

                resp = createFailResponse();
            }
            resp.setMessage((String) res.get("message"));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setCode("70806064");
            resp.setMessage("实有单位人员导入失败");
            LOG.error("实有单位人员导入失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
