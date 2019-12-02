package com.ss.isc.data.resource.service;

import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.resource.client.IVillageEntranceService;
import com.ss.isc.data.resource.common.model.CameraRef;
import com.ss.isc.data.resource.common.model.VillageEntrance;
import com.ss.isc.data.resource.common.web.VillageEntranceQueryVO;
import com.ss.isc.data.resource.mapper.CameraRefMapper;
import com.ss.isc.data.resource.mapper.VillageEntranceMapper;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.file.FileConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VillageEntranceServiceImpl
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VillageEntranceServiceImpl extends BaseServiceImpl implements IVillageEntranceService {

    @Autowired
    private VillageEntranceMapper vEntranceMapper;
    @Autowired
    private CameraRefMapper cameRefMapper;

    /**
     * 查询出入口分页列表
     * @param queryVO
     * @return
     */
    @Override
    public List<VillageEntrance> pages(VillageEntranceQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        User user = new User();
        user.setUserId(queryVO.getUserIds());
        String sqlString = dataScopeFilter(user);
        queryVO.setSqlString(sqlString);
        List<VillageEntrance> eList = this.vEntranceMapper.pages(queryVO);
        // 处理小区图片路径为完整路径
        for (VillageEntrance villageEntrance : eList) {
            if (StringUtils.isNotBlank(villageEntrance.getEntrancePicUrl()) && !villageEntrance.getEntrancePicUrl().contains(FileConstant.FILE_HTTPADD)) {
                villageEntrance.setEntrancePicUrl(FilePropertiesUtil.getHttpUrl() + villageEntrance.getEntrancePicUrl());
            }
        }
        return eList;
    }

    /**
     * 新增小区出入口
     * @param dto
     */
    @Override
    public void add(VillageEntrance dto) {
        this.vEntranceMapper.inserts(dto);
    }

    /**
     * 修改小区出入口
     * @param dto
     * @return
     */
    @Override
    public int update(VillageEntrance dto) {
        return this.vEntranceMapper.updateVillageEntrance(dto);
    }

    /**
     * 删除小区出入口
     * @param dto
     */
    @Override
    public void delete(VillageEntrance dto) {
        dto.setState(CommonConstant.COMMON_1);
        this.vEntranceMapper.deletes(dto);
    }

    /**
     * 查询小区出入口详情
     * @param dto
     * @return
     */
    @Override
    public VillageEntrance selectOne(VillageEntrance dto) {
        return this.vEntranceMapper.findById(dto);
    }


    @Override
    public List<CameraRef> selectCameraRef(VillageEntrance dto) {
        CameraRef record = new CameraRef();
        record.setLocationId(String.valueOf(dto.getId()));
        return this.cameRefMapper.select(record);
    }

    /**
     * 出入口关联设备
     * @param dto
     */
    @Override
    public void ref(CameraRef dto) {
        // 查询当前关联情况
        CameraRef record = new CameraRef();
        record.setLocationId(dto.getLocationId());
        record.setType(dto.getType());
        CameraRef entity = this.cameRefMapper.selectOne(record);
        if (entity == null) {
            // 未关联时：新增关联数据
            if (StringUtils.isNotBlank(dto.getCameraId())) {
                this.cameRefMapper.insert(dto);
            }
        } else if (StringUtils.isBlank(dto.getCameraId())) {
            // 已关联但是未传入相机id：即清空关联（删除）
            this.cameRefMapper.deletes(record);
        } else {
            // 已关联且相机id不为空：即更新关联关系
            this.cameRefMapper.updates(dto);
        }
    }

    /**
     * 查询出入口关联设备列表
     * @param dto
     * @return
     */
    @Override
    public List<CameraRef> refList(CameraRef dto) {
        return this.cameRefMapper.select(dto);
    }

}
