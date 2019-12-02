package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Tollgate;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.web.TollgateQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TollgateMapper
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Mapper
public interface TollgateMapper {

    List<Tollgate> findTollgates(Village paramVillage);

    List<Tollgate> findAllVehicleTollgates(Tollgate paramTollgate);

    /**
     * 查询车辆卡口分页列表
     * @param queryVO
     * @return
     */
    List<Tollgate> pages(TollgateQueryVO queryVO);

    /**
     * 新增车辆卡口
     * @param paramTollgate
     */
    void add(Tollgate paramTollgate);

    /**
     * 查询车辆卡口详情
     * @param paramTollgate
     * @return
     */
    Tollgate selectOne(Tollgate paramTollgate);

    /**
     * 修改车辆卡口
     * @param paramTollgate
     */
    void update(Tollgate paramTollgate);

    void delete(Tollgate paramTollgate);

    Tollgate check(Tollgate paramTollgate);

    void insertList(List<Tollgate> paramList);

    void updateBatch(List<Tollgate> paramList);

    /**
     * 删除车辆卡口
     * @param paramTollgate
     */
    void deletes(Tollgate paramTollgate);

    Tollgate checkUpdate(Tollgate paramTollgate);

}
