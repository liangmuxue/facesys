package com.ss.spider.system.user.service;

import com.ss.exception.ServiceException;
import com.ss.service.SsService;
import com.ss.spider.system.user.service.vo.UserFuzzyMatchVO;

import java.util.List;
import java.util.Map;
/**
* 用户操作
* @author chao
* @create 2019/12/4
* @email lishuangchao@ss-cas.com
**/
public interface UserService<User> extends SsService<User> {

    String getNewUsrId() throws ServiceException;

    /**
     * 查询用户信息
     * @param paramMap
     * @return
     */
    List<User> gets(Map<String, Object> paramMap);

    List<User> gets(List<String> paramList);

    /**
     * 获取账户信息及路由权限
     * @param paramString
     * @return
     */
    User get(String paramString);

    /**
     * 获取账户详情信息
     * @param paramString
     * @return
     */
    User getById(String paramString);

    List<User> list(User paramUser);

    /**
     * 分页查询账户列表
     * @param paramUser
     * @param paramInt1
     * @param paramInt2
     * @return
     */
    List<User> pages(User paramUser, int paramInt1, int paramInt2);

    /**
     * 新增账户
     * @param paramUser
     * @param paramList
     * @return
     * @throws ServiceException
     */
    String save(User paramUser, List<String> paramList) throws ServiceException;

    /**
     * 重置账户密码
     * @param paramUser
     * @return
     * @throws ServiceException
     */
    int updateNotNull(User paramUser) throws ServiceException;

    /**
     * 修改账户信息
     * @param paramUser
     * @param paramList
     * @return
     * @throws ServiceException
     */
    int update(User paramUser, List<String> paramList) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

    int discard(List<String> paramList, String paramString) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int delete(User paramUser) throws ServiceException;

    void editMyUserInfo(User paramUser) throws ServiceException;

    List<UserFuzzyMatchVO> userFuzzyMatch(User paramUser);

    Integer getUserCountByDepartIds(List<String> paramList);

    /**
     * 账户启用停用
     * @param paramList
     * @param paramInteger
     * @throws ServiceException
     */
    void opStatus(List<String> paramList, Integer paramInteger) throws ServiceException;

}
