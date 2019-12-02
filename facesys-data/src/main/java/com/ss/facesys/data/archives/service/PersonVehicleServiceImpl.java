package com.ss.facesys.data.archives.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.archives.client.IPersonVehicleService;
import com.ss.facesys.data.archives.common.model.Vehicle;
import com.ss.facesys.data.archives.common.model.VehicleRecord;
import com.ss.facesys.data.archives.mapper.ArchivesVehicleMapper;
import com.ss.facesys.data.archives.mapper.ArchivesVehicleRecordMapper;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.util.file.FilePropertiesUtil;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class PersonVehicleServiceImpl extends BaseServiceImpl implements IPersonVehicleService {
  @Autowired
  private ArchivesVehicleMapper archivesVehicleMapper;
  @Autowired
  private ArchivesVehicleRecordMapper archivesVehicleRecordMapper;

  /**
   * 分页查询车辆信息
   * @param dto
   * @param currPage
   * @param pageSize
   * @return
   */
  @Override
  public List<Vehicle> select(Vehicle dto, int currPage, int pageSize) {
    PageHelper.startPage(currPage, pageSize);
    //查询车辆信息
    List<Vehicle> vList = this.archivesVehicleMapper.list(dto);
    for (Vehicle vehicle : vList) {
      //获取图片路径
      if (StringUtils.isNotBlank(vehicle.getPlatePic()) && !vehicle.getPlatePic().contains("http")) {
        vehicle.setPlatePic(FilePropertiesUtil.getHttpUrl() + vehicle.getPlatePic());
      }
    } 
    return vList;
  }

  @Override
  public List<VehicleRecord> list(VehicleRecord vehicleRecordDTO) {
    User user = new User();
    user.setUserId(vehicleRecordDTO.getUserIds());
    String sqlString = dataScopeFilter(user).replace("t1", "d");
    vehicleRecordDTO.setSqlString(sqlString);
    return this.archivesVehicleRecordMapper.selectByParam(vehicleRecordDTO);
  }

  @Override
  public List<Vehicle> selectById(Vehicle dto) {
    //查询车辆信息
    List<Vehicle> vList = this.archivesVehicleMapper.selectById(dto);
    for (Vehicle vehicle : vList) {
      //获取图片路径
      if (StringUtils.isNotBlank(vehicle.getPlatePic()) && !vehicle.getPlatePic().contains("http")) {
        vehicle.setPlatePic(FilePropertiesUtil.getHttpUrl() + vehicle.getPlatePic());
      }
    }
    return vList;
  }

    /**
     * 新增车辆
     * @param vehicle
     * @return
     */
  @Override
  public String save(Vehicle vehicle) {
    String msg = "";
    int count = 0;
    vehicle.setCreateTime(new Date());
    vehicle.setUpdateTime(new Date());
    //检查新增车辆是否已经存在
    Vehicle entity = this.archivesVehicleMapper.check(vehicle);
    if (entity != null) {
        msg = "ishave";
    } else {
      //新增车辆
      count = this.archivesVehicleMapper.insertSelective(vehicle);
    }
    if (count > 0) {
      msg = "success";
    }
    return msg;
  }

    /**
     * 修改车辆信息
     * @param vehicle
     * @return
     */
    @Override
    public String edit(Vehicle vehicle) {
        String msg = "";
        int count = 0;
        vehicle.setUpdateTime(new Date());
        //检查车辆车牌号是否已经存在
        Vehicle entity = this.archivesVehicleMapper.check(vehicle);
        if (entity != null) {
            msg = "ishave";
        } else {
            //修改车辆信息
            count = this.archivesVehicleMapper.update(vehicle);
        }
        if (count > 0) {
            msg = "success";
        }
        return msg;
    }

    /**
     * 删除车辆
     * @param vehicle
     * @return
     */
    @Override
    public String delete(Vehicle vehicle) {
        String msg = "";
        //删除车辆
        int count = this.archivesVehicleMapper.deleteById(vehicle.getId());
        if (count > 0){
            msg = "success";
        }
        return msg;
    }

    /**
     * 查询车辆详情
     * @param dto
     * @return
     */
    @Override
    public List<Vehicle> detail(Vehicle dto) {
        //查询车辆信息
        List<Vehicle> vList = this.archivesVehicleMapper.detail(dto);
        for (Vehicle vehicle : vList) {
            //获取图片路径
            if (StringUtils.isNotBlank(vehicle.getPlatePic()) && !vehicle.getPlatePic().contains("http")) {
                vehicle.setPlatePic(FilePropertiesUtil.getHttpUrl() + vehicle.getPlatePic());
            }
        }
        return vList;
    }
}
