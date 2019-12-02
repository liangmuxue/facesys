package com.ss.isc.data.system.mapper;

import com.ss.isc.data.system.common.model.Role;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
/**
* 查询用户拥有角色
* @author chao
* @create 2019/10/10
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface RoleManageMapper extends CWMapper<Role> {
  List<Role> list(Role paramRole);
}
