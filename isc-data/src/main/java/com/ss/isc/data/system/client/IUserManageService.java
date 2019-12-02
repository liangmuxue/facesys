package com.ss.isc.data.system.client;

import com.ss.isc.data.system.common.model.User;
import java.util.List;

public interface IUserManageService {
  List<User> pages(User paramUser);

  User detail(User paramUser);
}
