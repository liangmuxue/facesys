package com.ss.isc.data.resource.service;

import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.resource.client.IUnitService;
import com.ss.isc.data.resource.common.model.Unit;
import com.ss.isc.data.resource.common.web.UnitQueryVO;
import com.ss.isc.data.resource.mapper.UnitMapper;
import com.ss.isc.util.constant.CommonConstant;
import com.github.pagehelper.PageHelper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UnitServiceImpl
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UnitServiceImpl extends BaseServiceImpl implements IUnitService {

    @Autowired
    private UnitMapper unitMapper;

    /**
     * 查询单元分页列表
     * @param queryVO
     * @return
     */
    @Override
    public List<Unit> pages(UnitQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        User user = new User();
        user.setUserId(queryVO.getUserIds());
        String sqlString = dataScopeFilter(user);
        queryVO.setSqlString(sqlString);
        return this.unitMapper.pages(queryVO);
    }

    /**
     * 新增单元
     * @param dto
     */
    @Override
    public void add(Unit dto) {
        // 查询并设置当前单元编号
        Unit uDTO = new Unit();
        if (StringUtils.isNotBlank(dto.getVillageCode())) {
            uDTO.setVillageCode(dto.getVillageCode());
        }
        String maxUnitNo = null;
        Unit entity = this.unitMapper.maxUnitNo(uDTO);
        if (entity != null) {
            maxUnitNo = entity.getUnitNo();
        }
        int nowunitNo;
        if (StringUtils.isNotBlank(maxUnitNo)) {
            nowunitNo = Integer.parseInt(maxUnitNo) + 1;
        } else {
            nowunitNo = 1;
        }
        dto.setUnitNo(String.valueOf(nowunitNo));
        this.unitMapper.inserts(dto);
    }

    /**
     * 修改单元
     * @param dto
     * @return
     */
    @Override
    public int update(Unit dto) {
        return this.unitMapper.updateUnit(dto);
    }

    /**
     * 删除单元
     * @param dto
     */
    @Override
    public void delete(Unit dto) {
        dto.setIsDelete(CommonConstant.COMMON_1);
        this.unitMapper.deletes(dto);
    }


    @Override
    public List<Unit> list(Unit dto) {
        dto.setIsDelete(CommonConstant.COMMON_0);
        return this.unitMapper.select(dto);
    }

    /**
     * 查询单元详情
     * @param dto
     * @return
     */
    @Override
    public Unit selectOne(Unit dto) {
        return this.unitMapper.findById(dto);
    }

}
