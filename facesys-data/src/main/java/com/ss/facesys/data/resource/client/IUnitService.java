package com.ss.facesys.data.resource.client;

import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.web.UnitQueryVO;

import java.util.List;

/**
 * IUnitService
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public interface IUnitService {

    /**
     * 查询单元分页列表
     * @param queryVO
     * @return
     */
    List<Unit> pages(UnitQueryVO queryVO);

    /**
     * 新增单元
     * @param paramUnit
     */
    void add(Unit paramUnit);

    /**
     * 修改单元
     * @param paramUnit
     * @return
     */
    int update(Unit paramUnit);

    /**
     * 删除单元
     * @param paramUnit
     */
    void delete(Unit paramUnit);

    /**
     * 查询单元列表
     * @param paramUnit
     * @return
     */
    List<Unit> list(Unit paramUnit);

    /**
     * 查询单元详情
     * @param paramUnit
     * @return
     */
    Unit selectOne(Unit paramUnit);

}
