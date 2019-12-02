package com.ss.facesys.data.archives.service;

import com.ss.facesys.data.archives.client.IArchiveHouseService;
import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.archives.common.model.Vehicle;
import com.ss.facesys.data.archives.mapper.ArchiveHouseMapper;
import com.ss.facesys.data.archives.mapper.ArchivesVehicleMapper;
import com.ss.facesys.data.baseinfo.common.dto.EnumVO;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ArchiveHouseServiceImpl implements IArchiveHouseService {
  @Autowired
  private ArchiveHouseMapper archivehouseMapper;
  @Autowired
  private ArchivesVehicleMapper vehicleMapper;
  /**
   * 房屋关联信息查询
   * @param dto
   * @return
   */
  @Override
  public Map<String, Object> detail(PersonInfraDTO dto) {
    Map<String, Object> returnMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());

    //ArchiveHouse houseEntity = this.archivehouseMapper.houseDetail(dto);
    List<People> pList = new ArrayList<People>();
    //查询房屋关系人员
    pList = this.archivehouseMapper.getPeopleInformation(dto);
    for (People people : pList) {
      List<EnumVO> enumVO = new ArrayList<EnumVO>();
      //取得人员标签编号
      String label = people.getLabel();
      if (StringUtils.isNotBlank(label)) {
        String[] labels = label.trim().split(",");
        if (labels != null && labels.length > 0) {
          for (int i = 0; i < labels.length; i++) {
            //取得人员标签内容
            enumVO.add(new EnumVO(this.archivehouseMapper.getLabel(labels[i]), labels[i]));
          }
          people.setLabels(enumVO);
        } 
      }
      if (StringUtils.isNotBlank(people.getIdCardPic()) && !people.getIdCardPic().contains("http")) {
        //取得证件照相对路径
        people.setIdCardPic(FilePropertiesUtil.getHttpUrl() + people.getIdCardPic());
      }
    }
    Vehicle vehicleDTO = new Vehicle();
    vehicleDTO.setHouseId(dto.getHouseId());
    vehicleDTO.setVillageCode(dto.getVillageCode());
    vehicleDTO.setBuildingNo(dto.getBuildingNo());
    vehicleDTO.setUnitNo(dto.getUnitNo());
    vehicleDTO.setHouseNo(dto.getHouseNo());
    vehicleDTO.setIsLeave(CommonConstant.COMMON_0);
    //查询房屋登记车辆信息
    List<Vehicle> vList = this.vehicleMapper.getVehicleInformation(vehicleDTO);
    /*for (Vehicle vehicle : vList) {
      if (StringUtils.isNotBlank(vehicle.getPlateNo())) {
        List<VehicleDTO> vDtos = this.archivehouseMapper.getVehicleDTO(vehicle.getPlateNo(), dto.getVillageCode());
        if (CollectionUtils.isNotEmpty(vDtos)) {
          VehicleDTO vDTO = (VehicleDTO)vDtos.get(0);
          if (vDTO != null) {
            vehicle.setInOutType(vDTO.getInOutType());
            vehicle.setInTime(vDTO.getInType());
            vehicle.setOutTime(vDTO.getOutType());
          } 
        }
      }
    }*/
    //returnMap.put("house", houseEntity);
    returnMap.put("pList", pList);
    returnMap.put("vList", vList);
    return returnMap;
  }
}
