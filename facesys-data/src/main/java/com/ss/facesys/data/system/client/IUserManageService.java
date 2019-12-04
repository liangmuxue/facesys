package com.ss.facesys.data.system.client;

import com.ss.facesys.data.system.common.model.User;
import java.util.List;

public interface IUserManageService {
  /**
   * 账户分页列表
   * @param paramUser
   * @return
   */
  List<User> pages(User paramUser);

  User detail(User paramUser);
}
