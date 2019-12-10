package com.ss.facesys.data.collect.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.common.model.Facedb;

import java.util.List;

/**
 * IFacedbService
 *
 * @author FrancisYs
 * @date 2019/12/5
 * @email yaoshuai@ss-cas.com
 */
public interface IFacedbService {

    /**
     * 查询人像库列表
     *
     * @param facedb
     * @return
     */
    List<Facedb> getFacedbList(Facedb facedb);

    /**
     * 查询人像库分页列表
     *
     * @param facedb
     * @return
     */
    List<Facedb> getFacedbPage(Facedb facedb, int currentPage, int pageSize);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param facedb
     * @return
     */
    Facedb selectOne(Facedb facedb);

    /**
     * 新增人像库
     *
     * @param facedb
     * @return
     * @throws ServiceException
     */
    String insertFacedb(Facedb facedb) throws ServiceException;

    /**
     * 修改人像库
     *
     * @param facedb
     * @return
     * @throws ServiceException
     */
    void updateFacedb(Facedb facedb) throws ServiceException;

    /**
     * 删除人像库
     *
     * @param facedb
     * @return
     * @throws ServiceException
     */
    void deleteFacedb(Facedb facedb) throws ServiceException;

    /**
     * 人像库重提特征
     *
     * @param facedbId
     * @param faceDBFaceStateInvalid
     * @return
     * @throws ServiceException
     */
    void reFeature(String facedbId, Integer faceDBFaceStateInvalid) throws ServiceException;

    /**
     * 更新人像库人脸数量
     *
     * @param facedbId
     * @param num
     */
    void updateFaceCount(Integer facedbId, Integer num);

}