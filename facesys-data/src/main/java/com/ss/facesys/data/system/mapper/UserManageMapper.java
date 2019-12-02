package com.ss.facesys.data.system.mapper;

import com.ss.facesys.data.system.common.model.User;
import com.ss.mapper.SsMapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
/**
* 查询用户列表
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface UserManageMapper extends SsMapper<User> {
  List<User> pages(User paramUser);

  User detail(User paramUser);
}
