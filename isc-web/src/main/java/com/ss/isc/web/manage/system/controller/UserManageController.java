package com.ss.isc.web.manage.system.controller;

import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.isc.data.system.client.IUserManageService;
import com.ss.isc.data.system.common.model.User;
import com.ss.isc.util.StringUtils;
import com.ss.isc.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.github.pagehelper.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 用户分页列表
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/system/userManage"})
public class UserManageController extends BaseController {

    @Resource
    private IUserManageService userManageService;

    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "用户管理分页", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<User>> pages(HttpServletRequest request, @RequestBody User dto, BindingResult bindingResult) throws Exception {
        try {
            //查询用户列表
            Page<User> data = (Page) this.userManageService.pages(dto);
            ResponseEntity<PageEntity<User>> resp = validite(bindingResult);
            resp.setData(new PageEntity(data));
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "用户详情", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<User> detail(HttpServletRequest request, @RequestBody User dto, BindingResult bindingResult) throws Exception {
        ResponseEntity<User> resp = validite(bindingResult);
        try {
            if (StringUtils.isBlank(dto.getOpUserId())){
                resp = createFailResponse();
                resp.setMessage("用户编号不能为空！");
                return resp;
            }
            dto.setUserId(dto.getOpUserId());
            //查询用户详情
            User user = this.userManageService.detail(dto);
            resp.setData(user);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

}
