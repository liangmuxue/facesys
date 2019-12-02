package com.ss.isc.data.resource.service;

import com.ss.isc.data.baseinfo.common.model.User;
import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.resource.client.IVehicleTollgateService;
import com.ss.isc.data.resource.common.model.Tollgate;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.isc.data.resource.common.web.TollgateQueryVO;
import com.ss.isc.data.resource.mapper.TollgateMapper;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.ss.isc.util.file.FileConstant;
import com.ss.isc.util.file.FilePropertiesUtil;
import com.ss.isc.util.jedis.JedisUtil;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VehicleTollgateServiceImpl
 * @author FrancisYs
 * @date 2019/8/16
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VehicleTollgateServiceImpl extends BaseServiceImpl implements IVehicleTollgateService {

    @Autowired
    private TollgateMapper tollgateMapper;
    @Resource
    public JedisUtil jedisUtil;

    @Override
    public List<Tollgate> findAllVehicleTollgates(Tollgate tollgate1) {
        PageHelper.startPage(tollgate1.getPageNum().intValue(), tollgate1.getPageSize().intValue());


        User user = new User();
        user.setUserId(tollgate1.getUserIds());
        Map<String, String> map = new HashMap<String, String>();
        map.put("dsf", dataScopeFilter(user).replace("t1", "a"));
        tollgate1.setSqlMap(map);

        List<Tollgate> findAllVehicleTollgates = this.tollgateMapper.findAllVehicleTollgates(tollgate1);
        for (Tollgate tollgate : findAllVehicleTollgates) {

            if (StringUtils.isNotBlank(tollgate.getTollgatePicUrl()) &&
                    !tollgate.getTollgatePicUrl().contains("http")) {
                tollgate.setTollgatePicUrl(FilePropertiesUtil.getHttpUrl() + tollgate.getTollgatePicUrl());
            }
        }
        return findAllVehicleTollgates;
    }

    /**
     * 查询车辆卡口分页列表
     * @param queryVO
     * @return
     */
    @Override
    public List<Tollgate> pages(TollgateQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        if (StringUtils.isNotBlank(queryVO.getUserIds())) {
            User user = new User();
            user.setUserId(queryVO.getUserIds());
            String sqlString = dataScopeFilter(user);
            queryVO.setSqlString(sqlString);
        }
        List<Tollgate> tList = this.tollgateMapper.pages(queryVO);
        // 处理图片路径为完整路径
        for (Tollgate tollgate : tList) {
            if (StringUtils.isNotBlank(tollgate.getTollgatePicUrl()) && !tollgate.getTollgatePicUrl().contains(FileConstant.FILE_HTTPADD)) {
                tollgate.setTollgatePicUrl(FilePropertiesUtil.getHttpUrl() + tollgate.getTollgatePicUrl());
            }
        }
        return tList;
    }

    /**
     * 新增车辆卡口
     * @param dto
     */
    @Override
    public void add(Tollgate dto) {
        this.tollgateMapper.add(dto);
    }

    /**
     * 查询车辆卡口详情
     * @param dto
     * @return
     */
    @Override
    public Tollgate selectOne(Tollgate dto) {
        dto.setIsDelete(NumberConstant.ZERO);
        return this.tollgateMapper.selectOne(dto);
    }

    /**
     * 修改车辆卡口
     * @param dto
     */
    @Override
    public void update(Tollgate dto) {
        this.tollgateMapper.update(dto);
    }

    /**
     * 删除车辆卡口
     * @param dto
     */
    @Override
    public void delete(Tollgate dto) {
        dto.setIsDelete(CommonConstant.COMMON_1);
        this.tollgateMapper.deletes(dto);
    }


    @Override
    public Map<String, String> batchImport(List<Tollgate> tempList, Map<String, String> imagePaths) {
        String vehicleTollgate = FilePropertiesUtil.getLocation() + "/" + "resource" + "/" + "vehicle_tollgate";


        List<Tollgate> updateList = new ArrayList<Tollgate>();
        List<Tollgate> insertList = new ArrayList<Tollgate>();
        Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

        boolean flag = true;
        StringBuilder failureMsg = new StringBuilder();
        int index = NumberConstant.TWO.intValue();
        for (Tollgate tollgate : tempList) {

            if (StringUtils.isBlank(tollgate.getVillageCode()) && StringUtils.isBlank(tollgate.getTollgateName()) &&
                    StringUtils.isBlank(tollgate.getTollgateID())) {
                break;
            }
            index++;
            if (StringUtils.isBlank(tollgate.getTollgateID())) {
                failureMsg.append(index + "行“卡口”列数据格式非法");
                flag = false;
            }
            if (StringUtils.isBlank(tollgate.getVillageCode())) {
                failureMsg.append(index + "行“小区编号”列数据格式非法");
                flag = false;
            }
            if (StringUtils.isBlank(tollgate.getTollgateName())) {
                failureMsg.append(index + "行“卡口名称”列数据格式非法");
                flag = false;
            }
            if (tollgate.getGisType() == null) {
                failureMsg.append(index + "行“坐标类型”列数据格式非法");
                flag = false;
            }
            if (tollgate.getStatus() == null) {
                failureMsg.append(index + "行“状态”列数据格式非法");
                flag = false;
            }
            if (tollgate.getTollgateType() == null) {
                failureMsg.append(index + "行“卡口类型”列数据格式非法");
                flag = false;
            }
            tollgate.setTollgatePicUrl((String) imagePaths.get(tollgate.getTollgateID()));

            Tollgate dto = new Tollgate();
            dto.setTollgateID(tollgate.getTollgateID());
            dto.setIsDelete(NumberConstant.ZERO);
            if (flag) {
                Tollgate entity = this.tollgateMapper.check(dto);
                if (entity == null) {
                    insertList.add(tollgate);
                    continue;
                }
                tollgate.setId(entity.getId());
                updateList.add(tollgate);
            }
        }

        if (flag) {
            if (insertList.size() > 0) {
                this.tollgateMapper.insertList(insertList);
            }
            if (updateList.size() > 0) {
                this.tollgateMapper.updateBatch(updateList);
            }
            map.put("result", "success");
            map.put("message", "成功导入" + (index - NumberConstant.TWO.intValue()) + "条数据，新增" + insertList.size() + "条，更新" + updateList
                    .size() + "条");
        } else {

            map.put("result", "failed");
            map.put("message", "导入失败：失败原因" + failureMsg.toString());
        }
        return map;
    }


    @Override
    public Tollgate getTollgateInfo(String tollgateID) {
        Tollgate tollgate = null;
        boolean isMatch = true;
        if (StringUtils.isNotBlank(tollgateID)) {
            List<Tollgate> tollgates = new ArrayList<Tollgate>();
            Object object = this.jedisUtil.get("ALL_VEHICLE_TOLLGATE");
            if (object == null) {
                Village village = new Village();
                village.setSqlMap(village.getSqlMap());
                tollgates = this.tollgateMapper.findTollgates(new Village());
                this.jedisUtil.set("ALL_VEHICLE_TOLLGATE", tollgates);
            }
            for (Tollgate tg : tollgates) {
                if (tollgateID.equals(tg.getTollgateID())) {
                    tollgate = tg;
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                tollgates = this.tollgateMapper.findTollgates(new Village());
                this.jedisUtil.set("ALL_VEHICLE_TOLLGATE", tollgates);
                for (Tollgate tg : tollgates) {
                    if (tollgateID.equals(tg.getTollgateID())) {
                        tollgate = tg;
                        break;
                    }
                }
            }
        }
        return tollgate;
    }


    @Override
    public Tollgate checkUpdate(Tollgate dto) {
        return this.tollgateMapper.checkUpdate(dto);
    }

}
