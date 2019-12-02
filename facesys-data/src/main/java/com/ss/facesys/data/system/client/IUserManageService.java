package com.ss.facesys.data.system.client;

import com.ss.facesys.data.system.common.model.User;
import java.util.List;

public interface IUserManageService {
  List<User> pages(User paramUser);

  User detail(User paramUser);
}
