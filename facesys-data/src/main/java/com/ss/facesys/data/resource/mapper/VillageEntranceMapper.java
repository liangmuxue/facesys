package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.VillageEntrance;
import com.ss.facesys.data.resource.common.web.VillageEntranceQueryVO;
import com.ss.mapper.SsMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * VillageEntranceMapper
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Component
@Mapper
public interface VillageEntranceMapper extends SsMapper<VillageEntrance> {

    List<VillageEntrance> findVillageEntrance(Village paramVillage);

    List<VillageEntrance> findAllVillageEntrances(VillageEntrance paramVillageEntrance);

    /**
     * 查询出入口分页列表
     * @param queryVO
     * @return
     */
    List<VillageEntrance> pages(VillageEntranceQueryVO queryVO);

    /**
     * 新增小区出入口
     * @param paramVillageEntrance
     */
    void inserts(VillageEntrance paramVillageEntrance);

    /**
     * 查询小区出入口详情
     * @param paramVillageEntrance
     * @return
     */
    VillageEntrance findById(VillageEntrance paramVillageEntrance);

    /**
     * 修改小区出入口
     * @param paramVillageEntrance
     * @return
     */
    int updateVillageEntrance(VillageEntrance paramVillageEntrance);

    /**
     * 删除小区出入口
     * @param paramVillageEntrance
     */
    void deletes(VillageEntrance paramVillageEntrance);

}
