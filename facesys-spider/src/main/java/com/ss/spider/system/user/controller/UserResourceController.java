package com.ss.spider.system.user.controller;

import com.ss.annotation.OpLog;
import com.ss.controller.AbstractController;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.util.StringUtils;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.user.form.UserResourceForm;
import com.ss.spider.system.user.form.UserResourceQuery;
import com.ss.spider.system.user.model.UserResource;
import com.ss.spider.system.user.service.UserResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 账户权限关联操作
 * @author chao
 * @create 2019/12/23
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/user/resource"})
public class UserResourceController extends AbstractController {

    @Autowired
    @Qualifier("userResourceService")
    private UserResourceService userResourceService;

    /**
     * 查询账户像机关联列表
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/cameraList"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询账户像机关联列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<UserResource>> cameraList(@RequestBody @Validated UserResourceQuery para) throws Exception {
        ResponseEntity<List<UserResource>> resp = createSuccResponse();
        try {
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceId(para.getResourceId());
            userResource.setResourceName(para.getResourceName());
            userResource.setOrgId(para.getOrgId());
            List<UserResource> userResourceList = this.userResourceService.cameraList(userResource);
            resp.setData(userResourceList);
        } catch (Exception e) {
            this.logger.error("查询账户像机关联列表失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 修改账户像机关联信息
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/cameraEdit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改账户像机关联信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> cameraEdit(@RequestBody @Validated UserResourceForm para) throws Exception {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            List<String> cameraIds = null;
            if (StringUtils.isNotBlank(para.getResourceIds())){
                cameraIds = Arrays.asList(para.getResourceIds().split(","));
            }
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceIds(cameraIds);
            String result = this.userResourceService.cameraEdit(userResource);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("修改账户像机关联信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 查询账户人像库关联列表
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/facedbList"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询账户人像库关联列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<UserResource>> facedbList(@RequestBody @Validated UserResourceQuery para) throws Exception {
        ResponseEntity<List<UserResource>> resp = createSuccResponse();
        try {
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceId(para.getResourceId());
            userResource.setResourceName(para.getResourceName());
            userResource.setOrgId(para.getOrgId());
            List<UserResource> userResourceList = this.userResourceService.facedbList(userResource);
            resp.setData(userResourceList);
        } catch (Exception e) {
            this.logger.error("查询账户人像库关联列表失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 修改账户人像库关联信息
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/facedbEdit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改账户人像库关联信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> facedbEdit(@RequestBody @Validated UserResourceForm para) throws Exception {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            List<String> facedbIds = null;
            if (StringUtils.isNotBlank(para.getResourceIds())){
                facedbIds = Arrays.asList(para.getResourceIds().split(","));
            }
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceIds(facedbIds);
            String result = this.userResourceService.facedbEdit(userResource);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("修改账户人像库关联信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 查询账户人证设备关联列表
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/deviceList"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "查询账户人证设备关联列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<List<UserResource>> deviceList(@RequestBody @Validated UserResourceQuery para) throws Exception {
        ResponseEntity<List<UserResource>> resp = createSuccResponse();
        try {
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceId(para.getResourceId());
            userResource.setResourceName(para.getResourceName());
            userResource.setOrgId(para.getOrgId());
            List<UserResource> userResourceList = this.userResourceService.deviceList(userResource);
            resp.setData(userResourceList);
        } catch (Exception e) {
            this.logger.error("查询账户人证设备关联列表失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    /**
     * 修改账户人证设备关联信息
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/deviceEdit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改账户人证设备关联信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> deviceEdit(@RequestBody @Validated UserResourceForm para) throws Exception {
        ResponseEntity<String> resp = createSuccResponse();
        try {
            List<String> deviceIds = null;
            if (StringUtils.isNotBlank(para.getResourceIds())){
                deviceIds = Arrays.asList(para.getResourceIds().split(","));
            }
            UserResource userResource = new UserResource();
            userResource.setUserId(para.getUserIds());
            userResource.setResourceIds(deviceIds);
            String result = this.userResourceService.deviceEdit(userResource);
            resp.setData(result);
        } catch (Exception e) {
            this.logger.error("修改账户人证设备关联信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}
