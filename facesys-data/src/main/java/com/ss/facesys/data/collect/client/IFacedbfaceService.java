package com.ss.facesys.data.collect.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.common.model.FacedbFace;

import java.util.List;

/**
 * IFacedbfaceService
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
public interface IFacedbfaceService {

    /**
     * 查询人像集分页列表
     *
     * @param facedbFace
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<FacedbFace> pages(FacedbFace facedbFace, int currentPage, int pageSize);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     *
     * @param facedbFace
     * @return
     */
    FacedbFace selectOne(FacedbFace facedbFace);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param facedbFace
     * @return
     */
    List<FacedbFace> selectList(FacedbFace facedbFace);

    /**
     * 新增人像集
     *
     * @param facedbFace
     * @return
     * @throws ServiceException
     */
    String insert(FacedbFace facedbFace) throws ServiceException;

    /**
     * 修改人像集
     *
     * @param facedbFace
     * @return
     * @throws ServiceException
     */
    void update(FacedbFace facedbFace) throws ServiceException;

    /**
     * 删除人像集
     *
     * @param facedbFace
     * @return
     * @throws ServiceException
     */
    void delete(FacedbFace facedbFace) throws ServiceException;

    /**
     * 批量删除人像集
     *
     * @param facedbFaceIds
     * @param deleteUserId
     * @return
     * @throws ServiceException
     */
    void deleteBatch(List<Integer> facedbFaceIds, String deleteUserId) throws ServiceException;

    /**
     * 重提人像集特征
     *
     * @param faceId
     * @return
     * @throws ServiceException
     */
    void reFeature(String faceId) throws ServiceException;

}
