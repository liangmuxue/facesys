package com.ss.facesys.data.system.service;

import com.ss.facesys.data.system.client.IUserManageService;
import com.ss.facesys.data.system.common.model.Role;
import com.ss.facesys.data.system.common.model.User;
import com.ss.facesys.data.system.mapper.RoleManageMapper;
import com.ss.facesys.data.system.mapper.UserManageMapper;
import com.github.pagehelper.PageHelper;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 账户
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@Service
public class UserManageServiceImpl implements IUserManageService {

  @Autowired
  private UserManageMapper userManageMapper;
  @Autowired
  private RoleManageMapper roleMapper;

  /**
   * 账户分页列表
   * @param dto
   * @return
   */
  @Override
  public List<User> pages(User dto) {
    PageHelper.startPage(dto.getCurrentPage(), dto.getPageSize());
    //查询账户信息
    List<User> uList = this.userManageMapper.pages(dto);
    for (User user : uList) {
      Role role = new Role();
      role.setUserId(user.getUserId());
      //查询账户拥有角色
      List<Role> rList = this.roleMapper.list(role);
      StringBuffer roleName = new StringBuffer();
      Iterator<Role> iterator = rList.iterator();
      while(iterator.hasNext()){
        Role temp = iterator.next();
        roleName.append(temp.getRoleCName());
        if(iterator.hasNext()){
          roleName.append(",");
        }
      }
      user.setRoleName(String.valueOf(roleName));
    }
    return uList;
  }

  @Override
  public User detail(User dto) {
    //查询用户信息
    User user = this.userManageMapper.detail(dto);
    Role roleDTO = new Role();
    roleDTO.setUserId(user.getUserId());
    //查询用户拥有角色
    List<Role> rList = this.roleMapper.list(roleDTO);
    StringBuffer roleName = new StringBuffer();
    Iterator<Role> iterator = rList.iterator();
    while(iterator.hasNext()){
      Role temp = (Role) iterator.next();
      roleName.append(temp.getRoleCName());
      if(iterator.hasNext()){
        roleName.append(",");
      }
    }
    user.setRoleName(String.valueOf(roleName));
    return user;
  }
}
