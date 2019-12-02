package com.ss.isc.data.resource.client;

import com.ss.isc.data.resource.common.model.Tollgate;
import com.ss.isc.data.resource.common.web.TollgateQueryVO;

import java.util.List;
import java.util.Map;

/**
 * IVehicleTollgateService
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public interface IVehicleTollgateService {

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

    /**
     * 删除车辆卡口
     * @param paramTollgate
     */
    void delete(Tollgate paramTollgate);

    Map<String, String> batchImport(List<Tollgate> paramList, Map<String, String> paramMap);

    Tollgate getTollgateInfo(String paramString);

    Tollgate checkUpdate(Tollgate paramTollgate);

}
