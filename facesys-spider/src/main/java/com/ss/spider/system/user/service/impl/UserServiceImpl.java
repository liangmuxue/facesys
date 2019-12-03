package com.ss.spider.system.user.service.impl;

import com.ss.exception.ServiceException;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.spider.system.role.model.RoleResource;
import com.ss.spider.system.role.service.RoleResourceService;
import com.ss.spider.system.sequence.model.AppSequence;
import com.ss.spider.system.sequence.model.SysSeqEnum;
import com.ss.spider.system.sequence.service.AppSequenceService;
import com.ss.spider.system.user.mapper.UserMapper;
import com.ss.spider.system.user.mapper.UserRoleMapper;
import com.ss.spider.system.user.model.User;
import com.ss.spider.system.user.model.UserRole;
import com.ss.spider.system.user.service.UserService;
import com.ss.spider.system.user.service.vo.UserFuzzyMatchVO;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.ss.util.nasstorage.config.properties.NasstorageProperties;
import com.ss.util.nasstorage.file.NasstorageFile;
import com.ss.util.nasstorage.util.NasstorageUtil;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
* 用户相关操作
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@Service("userService")
public class UserServiceImpl extends AbstractSsServiceImpl<User> implements UserService<User> {

    private static final String USR_SEQ_CODE = SysSeqEnum.USR_ID_SEQ.getCode();


    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    @Qualifier("appSequenceService")
    private AppSequenceService<AppSequence> appSequenceService;

    @Autowired
    private NasstorageProperties nasproperties;

    @Autowired
    @Qualifier("roleResourceService")
    private RoleResourceService<RoleResource> roleResourceService;

    @Autowired
    @Qualifier("resourceService")
    private ResourceService<Resource> resourceService;

    /**
     * 生成用户编号
     * @return
     * @throws ServiceException
     */
    @Override
    public String getNewUsrId() throws ServiceException {
        String nextVal = this.appSequenceService.getNextVal(USR_SEQ_CODE, '0', 8);
        return DateUtils.parseDate(new Date()) + nextVal;
    }

    /**
     * 查询账户信息
     * @param args
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> gets(Map<String, Object> args) {
        return this.userMapper.gets(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> list(User entity) {
        return this.userMapper.list(entity);
    }

    /**
     * 分页查询账户列表
     * @param entity
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<User> pages(User entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.userMapper.pages(entity);
    }

    @Override
    public List<User> gets(final List<String> userIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

    /**
     * 获取用户详情信息及权限列表
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User get(final String userId) {
        return get(new HashMap<String, Object>() {
            {
                put("userId", userId);
            }
        });
    }

    /**
     * 获取账户详情信息
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User getById(final String userId) {
        Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("userId", userId);
            }
        };
        //查询账户信息
        List<User> list = this.userMapper.gets(params);
        for (User user : list) {
            //查询账户拥有角色
            List<String> rlist = this.userMapper.rlist(user.getUserId());
            user.setRoleIds(rlist);
        }
        return CollectionUtils.isEmpty(list) ? null : (User) list.get(0);
    }

    /**
     * 新增账户
     * @param entity
     * @param roleIds
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final User entity, List<String> roleIds) throws ServiceException {
        saveImagetoNas(entity);
        //查询用户信息
        List<User> list = this.userMapper.gets(new HashMap<String, Object>(CommonConstant.COMMON_1) {
            {
                put("loginName", entity.getLoginName());
            }
        });
        if (!CollectionUtils.isEmpty(list)) {
            throw new ServiceException("账户账号[" + entity.getLoginName() + "]已存在");
        }
        if (!StringUtils.isEmpty(entity.getWorkCode())) {
            User user = new User();
            user.setWorkCode(entity.getWorkCode());
            //查询用户信息
            list = this.userMapper.list(user);
            if (!CollectionUtils.isEmpty(list)) {
                throw new ServiceException("账户警号[" + entity.getWorkCode() + "]已存在");
            }
        }
        entity.setUserId(getNewUsrId());
        //entity.setSalt(UUIDUtils.getRangeString(CommonConstant.COMMON_5));
        //String password = this.bcrypt.encode(entity.getPassword() + entity.getSalt());
        //entity.setPassword(password);
        List<UserRole> userRolelist = new ArrayList<UserRole>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(entity.getUserId());
            userRolelist.add(userRole);
        }
        try {
            //新建账户角色关联信息
            this.userRoleMapper.batchSave(userRolelist);
            //新建账户
            this.userMapper.save(entity);
        } catch (Exception e) {
            String message = "新增账户失败";
            if (e instanceof org.springframework.dao.DuplicateKeyException) {
                message = "由于以前已存在该账户,并且可能有历史数据,所以不能以现有账号再次创建,请重新填写账号.";
            }
            this.logger.error("新增账户失败,原因:", e);
            throw new ServiceException(message, e);
        }
        return entity.getUserId();
    }

    /**
     * 存储照片
     * @param entity
     * @throws ServiceException
     */
    private void saveImagetoNas(User entity) throws ServiceException {
        if (!StringUtils.isEmpty(entity.getPictureUrl())) {
            NasstorageFile imgPath = (new NasstorageUtil()).nasstorageUser(this.nasproperties, entity.getPictureUrl(), null);
            if (imgPath == null) {
                throw new ServiceException("Nas 存储照片失败");
            }
            entity.setPictureUrl(imgPath.getStorageRelativePath());
        }
    }

    /**
     * 重置账户密码
     * @param entity
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int updateNotNull(User entity) throws ServiceException {
        try {
            return this.userMapper.updateNotNull(entity);
        } catch (Exception e) {
            this.logger.error("修改账户信息失败,原因:", e);
            throw new ServiceException("修改账户信息失败", e);
        }
    }

    /**
     * 修改账户信息
     * @param entity
     * @param roleIds
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final User entity, List<String> roleIds) throws ServiceException {
        saveImagetoNas(entity);
        Example example = new Example(User.class);
        example.createCriteria().andNotEqualTo("userId", entity.getUserId()).andEqualTo("loginName", entity.getLoginName());
        //查询账户
        List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(users)) {
            throw new ServiceException("账户账号[" + entity.getLoginName() + "]已存在");
        }
        if (!StringUtils.isEmpty(entity.getWorkCode())) {
            User user = new User();
            user.setWorkCode(entity.getWorkCode());
            //查询账户信息
            users = this.userMapper.list(user);
            if (!CollectionUtils.isEmpty(users) && !((User) users.get(0)).getUserId().equals(entity.getUserId())) {
                throw new ServiceException("账户警号[" + entity.getWorkCode() + "]已存在");
            }
        } else if (entity.getWorkCode() == null) {
            entity.setWorkCode("");
        }
        List<UserRole> userRolelist = new ArrayList<UserRole>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(entity.getUserId());
            userRolelist.add(userRole);
        }
        try {
            //删除账户拥有角色
            this.userRoleMapper.remove(new HashMap<String, Object>() {
                {
                    put("userId", entity.getUserId());
                }
            });
            //添加账户拥有角色
            this.userRoleMapper.batchSave(userRolelist);
            //修改账户信息
            return this.userMapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            this.logger.error("修改账户信息失败,原因:", e);
            throw new ServiceException("修改账户信息失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(User args) throws ServiceException {
        try {
            if (StringUtils.hasText(args.getUserId())) {

                UserRole userRole = new UserRole();
                userRole.setUserId(args.getUserId());
                this.userRoleMapper.delete(userRole);
            }

            return this.userMapper.delete(args);
        } catch (Exception e) {
            this.logger.error("物理删除用户信息失败,原因:", e);
            throw new ServiceException("物理删除用户信息失败", e);
        }
    }

    /**
     * 逻辑删除用户信息
     * @param userIds
     * @param operId
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final List<String> userIds, final String operId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {
            {
                put("deletedTime", DateUtils.getCurrentTime());
                put("deletedUserid", operId);
                put("userIds", userIds);
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final List<String> userIds) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final String userId, final String operId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final String userId) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    /**
     * 通过主键获取用户信息
     * @param params
     * @return
     */
    private User get(Map<String, Object> params) {
        //查询用户信息
        List<User> list = this.userMapper.gets(params);
        for (User user : list) {
            //查询用户拥有角色
            List<String> rlist = this.userMapper.rlist(user.getUserId());
            user.setRoleIds(rlist);
        }
        List<RoleResource> allRoleResourceList = null;
        if (list.get(0).getRoleIds().size() > 0){
            for (String roleId: list.get(0).getRoleIds()) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                //查询用户角色
                List<RoleResource> roleResourceList = this.roleResourceService.list(roleResource);
                if (allRoleResourceList == null){
                    allRoleResourceList = roleResourceList;
                } else {
                    allRoleResourceList.addAll(roleResourceList);
                }
            }
        }
        List<Resource> resourceList = new ArrayList<>();
        List<String> resIds = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(allRoleResourceList)) {
            for (RoleResource rr : allRoleResourceList) {
                resIds.add(rr.getResourceId());
            }
            //查询权限列表
            resourceList = this.resourceService.gets(resIds);
        }
        //List<ResourceTree> resourceTrees = RoleResourceServiceImpl.treeList(resourceList);
        list.get(0).setResourceList(resourceList);
        return CollectionUtils.isEmpty(list) ? null : (User) list.get(0);
    }

    /**
     * 逻辑删除用户信息
     * @param args
     * @return
     * @throws ServiceException
     */
    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            this.userRoleMapper.remove(args);
            return this.userMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除用户信息失败,原因:", e);
            throw new ServiceException("逻辑删除用户信息失败", e);
        }
    }


    private int delete(Map<String, Object> args) throws ServiceException {
        try {
            this.userRoleMapper.remove(args);
            return this.userMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("物理删除用户信息失败,原因:", e);
            throw new ServiceException("物理删除用户信息失败", e);
        }
    }

    @Override
    public void editMyUserInfo(User user) throws ServiceException {
        User temp = (User) this.userMapper.selectByPrimaryKey(user.getUserId());
        if (temp == null) {
            throw new ServiceException("修改信息失败:当前用户信息不存在.");
        }
        if (!StringUtils.isEmpty(user.getWorkCode()) && !user.getWorkCode().equals(temp.getWorkCode())) {
            User u = new User();
            u.setWorkCode(user.getWorkCode());
            List<User> users = this.userMapper.list(u);
            if (!CollectionUtils.isEmpty(users)) {
                throw new ServiceException("用户警号[" + user.getWorkCode() + "]已存在");
            }
        } else if (user.getWorkCode() == null) {
            user.setWorkCode("");
        }
        try {
            saveImagetoNas(user);
            this.userMapper.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            this.logger.error("修改信息失败,原因:", e);
            throw new ServiceException("修改信息失败:", e);
        }
    }

    @Override
    public List<UserFuzzyMatchVO> userFuzzyMatch(User entity) {
        List<User> list = list(entity);
        List<UserFuzzyMatchVO> res = new ArrayList<UserFuzzyMatchVO>();
        for (User user : list) {
            UserFuzzyMatchVO userFuzzyMatchVO = new UserFuzzyMatchVO();
            BeanUtils.copyProperties(user, userFuzzyMatchVO);
            res.add(userFuzzyMatchVO);
        }
        return res;
    }

    @Override
    public Integer getUserCountByDepartIds(List<String> departIds) {
        int count = -1;
        if (CollectionUtils.isNotEmpty(departIds)) {
            Example example = new Example(User.class);
            example.createCriteria().andIn("departId", departIds);
            count = this.userMapper.selectCountByExample(example);
        }
        return Integer.valueOf(count);
    }

    /**
     * 账户启用停用
     * @param userIds
     * @param status
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void opStatus(List<String> userIds, Integer status) throws ServiceException {
        Example example = new Example(User.class);
        example.createCriteria().andIn("userId", userIds);
        List<User> users = this.userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            throw new ServiceException("账户启用停用失败 没有该账户ID: " + (String) userIds.get(0) + " 请检查后再试.");
        }
        Set<String> collect = (Set) users.stream().map(User::getUserId).collect(Collectors.toSet());
        List<String> result = (List) userIds.stream().filter(userId -> !collect.contains(userId)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(result)) {
            throw new ServiceException("账户启用停用失败 失败的账户ID: " + (String) result.get(0) + " 请检查后再试.");
        }
        User user = new User();
        example.clear();
        example.createCriteria().andIn("userId", userIds);
        user.setStatus(status);
        this.userMapper.updateByExampleSelective(user, example);
    }

}
