package com.ss.facesys.data.system.service;

import com.ss.facesys.data.system.client.ISysParaService;
import com.ss.facesys.data.system.common.model.SysPara;
import com.ss.facesys.data.system.mapper.SysParaMapper;
import com.ss.facesys.util.file.FilePropertiesUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = {Exception.class})
public class SysParaServiceImpl implements ISysParaService {

    private static final int PARA_TYPE_IMG = 2;

    @Resource
    private SysParaMapper sysParaMapper;

    public Map<String, String> getSysParaInfoMap(SysPara sysPara) {
        List<SysPara> paraList = sysParaMapper.selectAll();
        if (CollectionUtils.isNotEmpty(paraList)) {
            Map<String, String> infoMap = new HashMap<>(paraList.size());
            paraList.forEach(para -> infoMap.put(para.getParaCode(), para.getParaType() == PARA_TYPE_IMG ? FilePropertiesUtil.getHttpUrl() + para.getParaValue() : para.getParaValue()));
            return infoMap;
        }
        return Collections.emptyMap();
    }

    public String reset(SysPara sysPara) {
        sysParaMapper.reset();
        return "成功恢复至默认配置";
    }

    public String updateSysPara(List<SysPara> sysParaList) {
        sysParaList.forEach(sysPara -> sysParaMapper.updateParaValueByCode(sysPara));
        return "成功修改配置";
    }

}
