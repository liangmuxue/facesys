package com.ss.isc.data.resource.client;

import com.ss.isc.data.resource.common.model.People;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.isc.data.resource.common.web.PeopleHouseVO;
import com.ss.isc.data.resource.common.web.PeopleQueryVO;
import com.ss.isc.data.resource.common.web.PeopleVO;

import java.util.List;
import java.util.Map;

/**
 * IResourcePeopleService：实有人口资源service接口
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
public interface IResourcePeopleService {

    /**
     * 查询实有人口分页列表
     * @param paramPeopleQueryVO
     * @return
     */
    List<People> page(PeopleQueryVO paramPeopleQueryVO);

    /**
     * 查询实有人口详情
     * @param paramInteger
     * @return
     */
    People get(Integer paramInteger);

    /**
     * 删除实有人口（支持批量）
     * @param param 批量删除时多个id以","分隔
     * @return
     */
    String delete(PeopleVO param);

    /**
     * 新增实有人口
     * @param paramPeople
     * @return
     */
    String save(People paramPeople) throws Exception;

    /**
     * 修改实有人口
     * @param paramPeople
     * @return
     */
    String edit(People paramPeople) throws Exception;

    Map<String, String> batchImport(List<People> paramList, Map<String, String> paramMap);

    /**
     * 查询人房关系待添加人员列表
     * @param paramPeopleHouseVO
     * @return
     */
    List<People> peopleList(PeopleHouseVO paramPeopleHouseVO);

    /**
     * 收藏/取消收藏实有人口
     * @param userIds 当前用户编号
     * @param peopleIds 实有人口编号
     * @param operationType 操作类型
     * @return 操作结果描述
     */
    String collect(String userIds, String peopleIds, Integer operationType);

    /**
     * 根据属性参数查询唯一人口对象
     * @param people
     * @return
     */
    People selectOne(People people);

    /**
     * 根据属性参数查询人口列表
     * @param people
     * @return
     */
    List<People> selectList(People people);

    /**
     * 查询人车关系待添加人车列表
     * @param paramPeopleVehicle
     * @return
     */
    List<People> peopleVehicleList(PeopleVehicle paramPeopleVehicle);
}
