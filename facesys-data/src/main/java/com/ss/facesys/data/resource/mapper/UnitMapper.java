package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.model.Unit;
import com.ss.facesys.data.resource.common.web.UnitQueryVO;
import com.ss.mapper.SsMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * UnitMapper
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface UnitMapper extends SsMapper<Unit> {

    /**
     * 查询单元列表
     * @param queryVO
     * @return
     */
    List<Unit> pages(UnitQueryVO queryVO);

    /**
     * 查询最大单元编号
     * @param paramUnit
     * @return
     */
    Unit maxUnitNo(Unit paramUnit);

    /**
     * 根据主键ID查单元
     * @param paramUnit
     * @return
     */
    Unit findById(Unit paramUnit);

    /**
     * 新增单元
     * @param paramUnit
     */
    void inserts(Unit paramUnit);

    /**
     * 修改单元
     * @param paramUnit
     * @return
     */
    int updateUnit(Unit paramUnit);

    /**
     * 删除单元
     * @param paramUnit
     */
    void deletes(Unit paramUnit);

    List<Unit> getUnitNumByBuilding(Building paramBuilding);

}
