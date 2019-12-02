package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.model.FacedbPeople;
import com.ss.isc.data.collect.common.web.FacedbPeopleVO;
import com.ss.isc.data.collect.common.web.FacedbVO;
import com.ss.isc.data.collect.common.model.Facedb;

import java.util.List;
import java.util.Map;

/**
 * IFacedbService
 *
 * @author FrancisYs
 * @date 2019/9/3
 * @email yaoshuai@ss-cas.com
 */
public interface IFacedbService {

    /**
     * 查询人像库列表
     * @param facedb
     * @return
     */
    List<Facedb> getFacedbList(Facedb facedb);

    /**
     * 查询重点人员库分页列表
     * @param vo
     * @return
     */
    List<Facedb> getFacedbPage(FacedbVO vo);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，查询条件使用等号
     * @param facedb
     * @return
     */
    Facedb selectOne(Facedb facedb);

    /**
     * 新增重点人员库
     * @param facedb
     * @return
     */
    Map<String, Object> insertFacedb(Facedb facedb);

    /**
     * 修改重点人员库信息
     * @param facedb
     * @return
     */
    Map<String, Object> updateFacedb(Facedb facedb);

    /**
     * 删除重点人员库
     * @param facedb
     * @return
     * @throws Exception
     */
    void deleteFacedb(Facedb facedb) throws Exception;

    /**
     * 查询重点人员列表
     * @param facedbPeople
     * @return
     */
    List<FacedbPeople> selectFacedbPeopleList(FacedbPeople facedbPeople);

    /**
     * 查询重点人员分页列表
     * @param vo
     * @return
     */
    List<FacedbPeopleVO> getFacedbPeoplePage(FacedbPeopleVO vo);

    /**
     * 新增重点人员
     * @param facedbPeople
     * @return
     * @throws Exception
     */
    Map<String, Object> insertFacedbPeople(FacedbPeople facedbPeople) throws Exception ;

    /**
     * 移除重点人员
     * @param facedbPeople
     * @return
     */
    Map<String, Object> deleteFacedbPeople(FacedbPeople facedbPeople);

}
