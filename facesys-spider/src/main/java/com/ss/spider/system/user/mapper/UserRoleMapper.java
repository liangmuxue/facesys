package com.ss.spider.system.user.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.user.model.UserRole;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserRoleMapper extends SsMapper<UserRole> {

    int total(Map<String, Object> paramMap);

    List<UserRole> list(UserRole paramUserRole);

    int save(UserRole paramUserRole);

    /**
     * 新建账户角色关联信息
     * @param paramList
     * @return
     */
    int batchSave(List<UserRole> paramList);

    /**
     * 删除用户信息
     * @param paramMap
     * @return
     */
    int remove(Map<String, Object> paramMap);

}
