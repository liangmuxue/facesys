package com.ss.spider.system.user.controller;

import com.ss.annotation.OpLog;
import com.ss.common.Constants;
import com.ss.controller.AbstractController;
import com.ss.enums.CommonEnumClass;
import com.ss.enums.OperaTypeEnum;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.interceptor.cache.beans.CacheUserInfo;
import com.ss.spider.interceptor.service.UserInfoCacheService;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.spider.system.user.form.PasswordForm;
import com.ss.spider.system.user.form.ResetPasswordForm;
import com.ss.spider.system.user.form.UpdateMyUserForm;
import com.ss.spider.system.user.form.UserForm;
import com.ss.spider.system.user.form.UserQuery;
import com.ss.spider.system.user.model.User;
import com.ss.spider.system.user.service.UserService;
import com.ss.spider.system.user.service.vo.UserFuzzyMatchVO;
import com.ss.tools.ArraysUtils;
import com.ss.tools.Base64ImageUtils;
import com.ss.tools.DateUtils;
import com.github.pagehelper.Page;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.tools.FileUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
* 账户相关操作
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/user"})
public class UserController extends AbstractController {

    @Autowired
    @Qualifier("userService")
    private UserService<User> userService;
    @Autowired
    private UserInfoCacheService userInfoCacheService;
    @Autowired
    private OrganizationService organizationService;
    //private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    /**
     * 分页查询账户列表
     * @param para
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "分页查询账户列表", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<User>> pages(@RequestBody UserQuery para) throws Exception {
        int currPage = getPageIndex(para);
        int pageSize = getPageSize(para);
        User user = new User();
        BeanUtils.copyProperties(para, user);
        Page<User> data = (Page) this.userService.pages(user, currPage, pageSize);
        ResponseEntity<PageEntity<User>> resp = createSuccResponse();
        resp.setData(new PageEntity(data));
        return resp;
    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<UserFuzzyMatchVO>> list(@RequestBody UserQuery para) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(para, user);

        if ((para.getDataType() == null || 1 == para.getDataType().shortValue()) &&
                para != null && !StringUtils.isEmpty(para.getUserId())) {
            CacheUserInfo cacheUserInfo = this.userInfoCacheService
                    .getCacheUserInfo("USERINFO_" + para.getUserId());
            if (!cacheUserInfo.getIsSuperRole()) {
                List<String> lowerIds = this.organizationService.getLowerIds(cacheUserInfo.getOrgId());
                user.setOrgIds(lowerIds);
            }
        }

        user.setUserId(para.getOpUserId());
        ResponseEntity<List<UserFuzzyMatchVO>> resp = createSuccResponse();
        resp.setData(this.userService.userFuzzyMatch(user));
        return resp;
    }

    /**
     * 获取用户详情信息及权限列表
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/get"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "获取用户详情信息及权限列表", type = OperaTypeEnum.SELECT)
    public ResponseEntity<User> get(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) UserQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<User> resp = validite(bindingResult);
        resp.setData(this.userService.get(para.getOpUserId()));
        return resp;
    }

    /**
     * 获取用户详情信息
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/getById"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "获取账户详情信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<User> getById(@RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) UserQuery para, BindingResult bindingResult) throws BindException {
        ResponseEntity<User> resp = validite(bindingResult);
        resp.setData(this.userService.getById(para.getOpUserId()));
        return resp;
    }

    /**
     * 新增账户信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "新增账户信息", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> save(@RequestBody @Validated({com.ss.valide.APIAddGroup.class}) UserForm para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        User user = new User();
        BeanUtils.copyProperties(para, user);
        user.setStatus(StatusEnum.EFFECT.getCode());
        user.setCreateTime(DateUtils.getCurrentTime());
        user.setUpdateTime(DateUtils.getCurrentTime());
        try {
            if (!StringUtils.isEmpty(para.getPictureUrl())) {
                boolean checkBase64 = Base64ImageUtils.isCheckBase64(para.getPictureUrl(), Constants.IMAGE_TYPE.split(","));
                if (!checkBase64) {
                    this.logger.error("新增账户信息失败,图片base64数据上传错误!错误码：{},错误描述：{},图片Base64：{}",
                            CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR.getKey(),
                            CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR.getValue(),
                            para.getPictureUrl());
                    throw new ServiceException(CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR);
                }
            }
            //新增用户
            resp.setData(this.userService.save(user, ArraysUtils.asList(para.getRoleIds())));
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("新增账户基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("新增账户基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }
        return resp;
    }

    /**
     * 修改个人密码
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/password"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "修改用户密码", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> password(@RequestBody @Validated final PasswordForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        List<User> list = this.userService.gets(new HashMap<String, Object>(1) {
            {
                put("userId", para.getOpUserId());
            }
        });
        if (CollectionUtils.isEmpty(list)) {
            resp = createFailResponse();
            resp.setMessage("用户不存在，请检查用户");
            return resp;
        }
        String password = para.getOldPassword();
        User user = (User) list.get(0);
//        if (StringUtils.hasText(user.getSalt())) {
//            password = para.getOldPassword() + user.getSalt();
//            para.setNewPassword(this.bcrypt.encode(para.getNewPassword() + user.getSalt()));
//        } else {
//            para.setNewPassword(this.bcrypt.encode(para.getNewPassword()));
//        }
        if (!password.equals(user.getPassword())) {
            resp = createFailResponse();
            resp.setData("原密码输入错误");
            return resp;
        }
        user = new User();
        user.setUserId(para.getOpUserId());
        user.setPassword(para.getNewPassword());
        user.setUpdateUserId(para.getUpdatedUserid());
        user.setUpdateTime(DateUtils.getCurrentTime());
        try {
            this.userService.updateNotNull(user);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("修改用户密码失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("修改用户密码失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

    /**
     * 重置账户密码
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/resetpassword"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "重置账户密码", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> resetpassword(@RequestBody @Validated final ResetPasswordForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        //查询用户信息
        List<User> list = this.userService.gets(new HashMap<String, Object>(CommonConstant.COMMON_1) {
            {
                put("userId", para.getOpUserId());
            }
        });
        if (CollectionUtils.isEmpty(list)) {
            resp = createFailResponse();
            resp.setMessage("用户不存在，请检查用户");
            return resp;
        }
//        User user = (User) list.get(0);
//        if (StringUtils.hasText(user.getSalt())) {
//            para.setNewPassword(this.bcrypt.encode(para.getNewPassword() + user.getSalt()));
//        } else {
//            para.setNewPassword(this.bcrypt.encode(para.getNewPassword()));
//        }
        User user = new User();
        user.setUserId(para.getOpUserId());
        user.setPassword(para.getNewPassword());
        user.setUpdateUserId(para.getUpdateUserId());
        user.setUpdateTime(DateUtils.getCurrentTime());
        try {
            //修改密码
            this.userService.updateNotNull(user);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("重置账户密码失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("重置账户密码失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());

            return resp;
        }

        return resp;
    }

    /**
     * 修改账户信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "修改账户信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> edit(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) UserForm para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        if ("1000".equals(para.getOpUserId())){
            resp = createFailResponse();
            resp.setMessage("系统账户不能修改！");
            return resp;
        }
        User user = new User();
        BeanUtils.copyProperties(para, user);
        user.setUpdateTime(DateUtils.getCurrentTime());
        user.setUserId(para.getOpUserId());
        try {
            if (!StringUtils.isEmpty(para.getPictureUrl())) {
                boolean checkBase64 = Base64ImageUtils.isCheckBase64(para.getPictureUrl(), Constants.IMAGE_TYPE.split(","));
                if (!checkBase64) {
                    this.logger.error("修改账户信息失败,图片base64数据上传错误!错误码：{},错误描述：{},图片Base64：{}",
                            CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR.getKey(),
                            CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR.getValue(),
                            para.getPictureUrl());
                    throw new ServiceException(CommonEnumClass.CommonInterfaceEnum.USER_IMG_DATA_ERROR);
                }
            }
            //修改账户信息
            this.userService.update(user, ArraysUtils.asList(para.getRoleIds()));
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("修改账户基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("修改账户基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
            return resp;
        }

        return resp;
    }

    /**
     * 删除用户信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "删除用户信息", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> delete(@RequestBody @Validated({com.ss.valide.APIDeltGroup.class}) UserForm para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> userIds = ArraysUtils.asList(para.getUserIds());
        List<String> result = Arrays.asList(para.getUserIds().split(","));
        for (String userId: result) {
            if ("1000".equals(userId)){
                resp.setMessage("初始用户不能删除！");
                return resp;
            }
        }
        try {
            if (para.getThorough() == 0) {
                this.userService.discard(userIds, para.getDeletedUserid());
            } else {
                this.userService.delete(userIds);
            }
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("删除用户基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("删除用户基本信息失败，原因：", e);
            return createFailResponse();
        }
        return resp;
    }

    /**
     * 账户启用停用
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/opStatus"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.SYSTEM, desc = "切换账户状态", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> opStatus(@RequestBody @Validated({com.ss.valide.APIOpStatusGroup.class}) UserForm para, BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        List<String> userIds = ArraysUtils.asList(para.getUserIds());
        try {
            this.userService.opStatus(userIds, para.getStatus());
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("账户启用停用失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("账户启用停用失败，原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }


    @RequestMapping(value = {"/searchKey/list"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "模糊匹配用户信息", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<User>> searchKeyList(@RequestBody UserQuery para) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("searchKey", para.getSearchKey());

        ResponseEntity<List<User>> resp = createSuccResponse();
        resp.setData(this.userService.gets(map));

        return resp;
    }

    /**
     * 修改个人用户信息
     * @param para
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/editMyUserInfo"}, method = {RequestMethod.POST})
    @OpLog(model = "70005", desc = "修改个人用户信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> editMyUserInfo(@RequestBody @Validated({com.ss.valide.APIEditGroup.class}) UpdateMyUserForm para, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        User user = new User();
        BeanUtils.copyProperties(para, user);
        user.setUserId(para.getOpUserId());
        user.setUpdateTime(DateUtils.getCurrentTime());
        user.setUpdateUserId(user.getUserId());
        try {
            //修改个人信息
            this.userService.editMyUserInfo(user);
        } catch (MyBatisSystemException | DataAccessResourceFailureException e) {
            this.logger.error("修改用户基本信息失败,原因：", e);
            throw e;
        } catch (Exception e) {
            this.logger.error("修改用户基本信息失败,原因：", e);
            resp = createFailResponse();
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

}
