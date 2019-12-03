package com.ss.spider.system.user.mapper;

import com.ss.mapper.SsMapper;
import com.ss.spider.system.user.model.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
/**
* 账户管理
* @author chao
* @create 2019/12/3
* @email lishuangchao@ss-cas.com
**/
@Mapper
@Component
public interface UserMapper extends SsMapper<User> {
    /**
     * 查询账户信息
     * @param paramMap
     * @return
     */
    List<User> gets(Map<String, Object> paramMap);

    /**
     * 查询账户信息
     * @param paramUser
     * @return
     */
    List<User> list(User paramUser);

    /**
     * 分页查询账户列表
     * @param paramUser
     * @return
     */
    List<User> pages(User paramUser);

    /**
     * 新建账户
     * @param paramUser
     * @return
     */
    int save(User paramUser);

    /**
     * 重置账户密码
     * @param paramUser
     * @return
     */
    int updateNotNull(User paramUser);

    int update(User paramUser);

    int remove(Map<String, Object> paramMap);

    int discard(Map<String, Object> paramMap);

    /**
     * 查询账户拥有角色
     * @param userId
     * @return
     */
    List<String> rlist(@Param("userId")String userId);

}
