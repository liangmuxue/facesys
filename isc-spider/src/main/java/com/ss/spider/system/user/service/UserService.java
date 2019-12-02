package com.ss.spider.system.user.service;

import com.ss.exception.ServiceException;
import com.ss.service.CWService;
import com.ss.spider.system.user.service.vo.UserFuzzyMatchVO;

import java.util.List;
import java.util.Map;

public interface UserService<User> extends CWService<User> {

    String getNewUsrId() throws ServiceException;

    List<User> gets(Map<String, Object> paramMap);

    List<User> gets(List<String> paramList);

    /**
     * 获取用户详情信息及权限列表
     * @param paramString
     * @return
     */
    User get(String paramString);

    /**
     * 获取用户详情信息
     * @param paramString
     * @return
     */
    User getById(String paramString);

    List<User> list(User paramUser);

    List<User> pages(User paramUser, int paramInt1, int paramInt2);

    /**
     * 新增用户
     * @param paramUser
     * @param paramList
     * @return
     * @throws ServiceException
     */
    String save(User paramUser, List<String> paramList) throws ServiceException;

    /**
     * 重置用户密码
     * @param paramUser
     * @return
     * @throws ServiceException
     */
    int updateNotNull(User paramUser) throws ServiceException;

    int update(User paramUser, List<String> paramList) throws ServiceException;

    int discard(String paramString1, String paramString2) throws ServiceException;

    int discard(List<String> paramList, String paramString) throws ServiceException;

    int delete(String paramString) throws ServiceException;

    int delete(List<String> paramList) throws ServiceException;

    int delete(User paramUser) throws ServiceException;

    void editMyUserInfo(User paramUser) throws ServiceException;

    List<UserFuzzyMatchVO> userFuzzyMatch(User paramUser);

    Integer getUserCountByDepartIds(List<String> paramList);

    void opStatus(List<String> paramList, Integer paramInteger) throws ServiceException;

}
