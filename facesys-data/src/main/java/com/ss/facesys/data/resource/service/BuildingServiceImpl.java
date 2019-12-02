package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.resource.client.IBuildingService;
import com.ss.facesys.data.resource.common.model.Building;
import com.ss.facesys.data.resource.common.web.BuildingQueryVO;
import com.ss.facesys.data.resource.mapper.BuildingMapper;
import com.ss.facesys.util.constant.CommonConstant;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BuildingServiceImpl
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class BuildingServiceImpl extends BaseServiceImpl implements IBuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * 查询楼栋列表
     * @param buildingQueryVO
     * @return
     */
    @Override
    public List<Building> pages(BuildingQueryVO buildingQueryVO) {
        PageHelper.startPage(buildingQueryVO.getCurrentPage(), buildingQueryVO.getPageSize());
        // 封装用户权限条件
        User user = new User();
        user.setUserId(buildingQueryVO.getUserIds());
        String sqlString = dataScopeFilter(user);
        buildingQueryVO.setSqlString(sqlString);
        return this.buildingMapper.pages(buildingQueryVO);
    }

    /**
     * 新增楼栋
     * @param dto
     */
    @Override
    public void add(Building dto) {
        // 查询并设置当前楼栋编号
        Building bDTO = new Building();
        if (StringUtils.isNotBlank(dto.getVillageCode())) {
            bDTO.setVillageCode(dto.getVillageCode());
        }
        String maxBuildingNo = null;
        Building entity = this.buildingMapper.maxBuildingNo(bDTO);
        if (entity != null) {
            maxBuildingNo = entity.getBuildingNo();
        }
        long nowBuildingNo;
        if (StringUtils.isNotBlank(maxBuildingNo)) {
            nowBuildingNo = Long.parseLong(maxBuildingNo) + 1;
        } else {
            nowBuildingNo = 1;
        }
        dto.setBuildingNo(String.valueOf(nowBuildingNo));
        // 新增楼栋数据
        this.buildingMapper.inserts(dto);
    }

    /**
     * 修改楼栋
     * @param dto
     * @return
     */
    @Override
    public int update(Building dto) {
        return this.buildingMapper.updateBuilding(dto);
    }

    /**
     * 删除楼栋
     * @param dto
     */
    @Override
    public void delete(Building dto) {
        dto.setIsDelete(CommonConstant.COMMON_1);
        this.buildingMapper.deletes(dto);
    }


    @Override
    public List<Building> list(Building dto) {
        dto.setIsDelete(CommonConstant.COMMON_0);
        return this.buildingMapper.select(dto);
    }

    /**
     * 查询楼栋详情
     * @param dto
     * @return
     */
    @Override
    public Building selectOne(Building dto) {
        return this.buildingMapper.findById(dto);
    }


    @Override
    public Building getByThirdId(String thirdId) {
        return this.buildingMapper.getByThirdId(thirdId);
    }

}
