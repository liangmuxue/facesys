package com.ss.spider.system.user.mapper;

import com.ss.mapper.CWMapper;
import com.ss.spider.system.user.model.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends CWMapper<User> {
    /**
     * 查询用户信息
     * @param paramMap
     * @return
     */
    List<User> gets(Map<String, Object> paramMap);

    /**
     * 查询用户信息
     * @param paramUser
     * @return
     */
    List<User> list(User paramUser);

    List<User> pages(User paramUser);

    /**
     * 新建用户
     * @param paramUser
     * @return
     */
    int save(User paramUser);

    /**
     * 重置用户密码
     * @param paramUser
     * @return
     */
    int updateNotNull(User paramUser);

    int update(User paramUser);

    int remove(Map<String, Object> paramMap);

    int discard(Map<String, Object> paramMap);

    /**
     * 查询用户拥有角色
     * @param userId
     * @return
     */
    List<String> rlist(@Param("userId")String userId);

}