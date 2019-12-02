package com.ss.isc.data.resource.client;

import com.ss.isc.data.resource.common.model.CameraRef;
import com.ss.isc.data.resource.common.model.VillageEntrance;
import com.ss.isc.data.resource.common.web.VillageEntranceQueryVO;

import java.util.List;

/**
 * IVillageEntranceService
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
public interface IVillageEntranceService {

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
    void add(VillageEntrance paramVillageEntrance);

    /**
     * 修改小区出入口
     * @param paramVillageEntrance
     * @return
     */
    int update(VillageEntrance paramVillageEntrance);

    /**
     * 删除小区出入口
     * @param paramVillageEntrance
     */
    void delete(VillageEntrance paramVillageEntrance);

    /**
     * 查询小区出入口详情
     * @param paramVillageEntrance
     * @return
     */
    VillageEntrance selectOne(VillageEntrance paramVillageEntrance);

    /**
     * 查询出入口关联的摄像头列表
     * @param paramVillageEntrance
     * @return
     */
    List<CameraRef> selectCameraRef(VillageEntrance paramVillageEntrance);

    /**
     * 出入口关联设备
     * @param paramCameraRef
     */
    void ref(CameraRef paramCameraRef);

    /**
     * 查询出入口关联设备列表
     * @param paramCameraRef
     * @return
     */
    List<CameraRef> refList(CameraRef paramCameraRef);

}
