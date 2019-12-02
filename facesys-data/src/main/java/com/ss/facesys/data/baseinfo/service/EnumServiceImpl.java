package com.ss.facesys.data.baseinfo.service;

import com.ss.facesys.data.baseinfo.client.IEnumService;
import com.ss.facesys.data.baseinfo.common.model.BaseEnums;
import com.ss.facesys.data.baseinfo.mapper.EnumMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 枚举服务接口实现
 * @author FrancisYs
 * @date 2019/08/08
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class EnumServiceImpl implements IEnumService {

    @Autowired
    private EnumMapper enumMapper;

    @Override
    public List<BaseEnums> findList(BaseEnums cwEnum) {
        return this.enumMapper.findList(cwEnum);
    }

}
