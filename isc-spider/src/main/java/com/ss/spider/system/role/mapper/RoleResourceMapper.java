package com.ss.spider.system.role.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.system.role.model.RoleResource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
/**
* 角色权限操作
* @author chao
* @create 2019/10/9
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface RoleResourceMapper extends CWMapper<RoleResource> {

    int total(Map<String, Object> paramMap);

    /**
     * 查询权限列表
     * @param paramRoleResource
     * @return
     */
    List<RoleResource> list(RoleResource paramRoleResource);

    int save(RoleResource paramRoleResource);

    /**
     * 添加权限
     * @param paramList
     * @return
     */
    int batchSave(List<RoleResource> paramList);

    /**
     * 删除角色与资源关联信息
     * @param paramMap
     * @return
     */
    int remove(Map<String, Object> paramMap);

}
