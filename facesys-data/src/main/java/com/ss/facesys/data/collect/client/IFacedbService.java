package com.ss.facesys.data.collect.client;

import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.common.model.Facedb;
import com.ss.facesys.data.collect.common.model.FacedbPeople;
import com.ss.facesys.data.collect.common.web.FacedbPeopleVO;

import java.util.List;
import java.util.Map;

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
     * @throws ServiceException
     * @return
     */
    String insertFacedb(Facedb facedb) throws ServiceException;

    /**
     * 修改人像库
     *
     * @param facedb
     * @throws ServiceException
     * @return
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
     * 查询重点人员列表
     *
     * @param facedbPeople
     * @return
     */
    List<FacedbPeople> selectFacedbPeopleList(FacedbPeople facedbPeople);

    /**
     * 查询重点人员分页列表
     *
     * @param vo
     * @return
     */
    List<FacedbPeopleVO> getFacedbPeoplePage(FacedbPeopleVO vo);

    /**
     * 新增重点人员
     *
     * @param facedbPeople
     * @return
     * @throws Exception
     */
    Map<String, Object> insertFacedbPeople(FacedbPeople facedbPeople) throws Exception;

    /**
     * 移除重点人员
     *
     * @param facedbPeople
     * @return
     */
    Map<String, Object> deleteFacedbPeople(FacedbPeople facedbPeople);

}
