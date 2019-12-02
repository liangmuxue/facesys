package com.ss.spider.system.role.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.system.role.model.Role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface RoleMapper extends CWMapper<Role> {
    /**
     * 查询角色
     * @param paramMap
     * @return
     */
    List<Role> gets(Map<String, Object> paramMap);

    /**
     * 查询角色
     * @param paramRole
     * @return
     */
    List<Role> list(Role paramRole);

    /**
     * 权限角色分页查询
     * @param paramRole
     * @return
     */
    List<Role> pages(Role paramRole);

    /**
     * 添加权限角色
     * @param paramRole
     * @return
     */
    int save(Role paramRole);

    /**
     * 修改权限角色
     * @param paramRole
     * @return
     */
    int update(Role paramRole);

    int remove(Map<String, Object> paramMap);

    /**
     * 逻辑删除权限角色
     * @param paramMap
     * @return
     */
    int discard(Map<String, Object> paramMap);

}
