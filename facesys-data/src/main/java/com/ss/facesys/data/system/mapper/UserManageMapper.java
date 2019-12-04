package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.model.User;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/**
* 账户
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface UserManageMapper extends SsMapper<User> {

  /**
   * 账户分页列表
   * @param paramUser
   * @return
   */
  List<User> pages(User paramUser);

  User detail(User paramUser);
}
