package com.ss.facesys.web.app.device.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.client.IDevicePersoncardService;
import com.ss.facesys.data.collect.common.model.DevicePersoncard;
import com.ss.facesys.data.resource.client.ICameraService;
import com.ss.facesys.data.resource.common.web.CameraQueryVO;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.web.app.device.form.DevicePersoncardForm;
import com.ss.facesys.web.app.device.query.DevicePersoncardQuery;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.organization.model.Organization;
import com.ss.tools.DateUtils;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import com.ss.valide.APIPageGroup;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 人证设备
 *
 * @author FrancisYs
 * @date 2020/2/11
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/device/personcard"})
public class DevicePersoncardController extends BaseController {

    @Resource
    private IDevicePersoncardService devicePersoncardService;

    /**
     * 查询人证设备分页列表
     *
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/pages"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人证设备分页列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<PageEntity<DevicePersoncard>> getPersoncardPage(@RequestBody @Validated({APIPageGroup.class}) DevicePersoncardQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<DevicePersoncard>> resp = validite(bindingResult);
        DevicePersoncard devicePersoncard = new DevicePersoncard();
        BeanUtils.copyProperties(query, devicePersoncard);
        devicePersoncard.setIds(getAuthResources(query.getUserId(), ResourceType.PERSONCARD, null));
        if (StringUtils.isBlank(devicePersoncard.getOrgId())) {
            devicePersoncard.setOrgId(getUser(query.getUserId()).getOrgId());
        }
        Page<DevicePersoncard> data = (Page) devicePersoncardService.getPersonCardPage(devicePersoncard, query.getCurrentPage(), query.getPageSize());
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 查询人证设备信息
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/detail"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询人证设备信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<DevicePersoncard> getPersoncardDetail(@RequestBody @Validated({APIGetsGroup.class}) DevicePersoncardQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<DevicePersoncard> resp = validite(bindingResult);
        DevicePersoncard devicePersoncard = new DevicePersoncard();
        devicePersoncard.setId(query.getId());
        devicePersoncard.setStatus(StatusEnum.EFFECT.getCode());
        resp.setData(devicePersoncardService.selectOne(devicePersoncard));
        return resp;
    }

    /**
     * 修改人证设备
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/update"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改人证设备", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updatePersoncard(@RequestBody @Validated({APIEditGroup.class}) DevicePersoncardForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        DevicePersoncard personcard = new DevicePersoncard();
        BeanUtils.copyProperties(form, personcard);
        personcard.setUpdateTime(ct);
        DevicePersoncard orgDb = getOriginalDbObj(form.getId());
        personcard.setDeviceId(orgDb.getDeviceId());
        try {
            devicePersoncardService.updatePersonCard(personcard);
        } catch (ServiceException e) {
            this.logger.error("修改人证设备失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    /**
     * 删除人证设备
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @PostMapping(value = {"/delete"})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除人证设备", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteFacedb(@RequestBody @Validated({APIDeltGroup.class}) DevicePersoncardForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        long ct = DateUtils.getCurrentTime();
        DevicePersoncard personcard = new DevicePersoncard();
        BeanUtils.copyProperties(form, personcard);
        personcard.setDeleteTime(ct);
        personcard.setStatus(StatusEnum.EXPIRE.getCode());
        try {
            devicePersoncardService.deletePersonCard(personcard);
        } catch (ServiceException e) {
            this.logger.error("删除人证设备失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

    private DevicePersoncard getOriginalDbObj(Integer id) {
        DevicePersoncard vp = new DevicePersoncard();
        vp.setId(id);
        return devicePersoncardService.selectOne(vp);
    }

    @RequestMapping(value = {"/tree"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "查询人证设备树", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Organization>> tree(@RequestBody CameraQueryVO queryVO) throws BindException {
        ResponseEntity<List<Organization>> resp = createSuccResponse();
        try {
            List<Integer> resources = getAuthResources(queryVO.getUserId(), ResourceType.PERSONCARD, null);
            queryVO.setResources(resources);
            resp.setData(this.devicePersoncardService.treeData(queryVO));
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("设备树查询失败");
            this.logger.error("设备树查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }
}