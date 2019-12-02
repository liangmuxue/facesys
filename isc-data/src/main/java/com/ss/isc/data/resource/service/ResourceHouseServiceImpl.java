package com.ss.isc.data.resource.service;

import com.ss.isc.data.baseinfo.service.BaseServiceImpl;
import com.ss.isc.data.resource.client.IResourceHouseService;
import com.ss.isc.data.resource.common.dto.HouseDTO;
import com.ss.isc.data.resource.common.dto.PeopleHouseDTO;
import com.ss.isc.data.resource.common.model.House;
import com.ss.isc.data.resource.common.model.PeopleHouse;
import com.ss.isc.data.resource.common.model.PeopleVehicle;
import com.ss.isc.data.resource.common.web.HouseEditVO;
import com.ss.isc.data.resource.common.web.HouseQueryVO;
import com.ss.isc.data.resource.common.web.PeopleHouseVO;
import com.ss.isc.data.resource.mapper.PeopleHouseMapper;
import com.ss.isc.data.resource.mapper.ResourceHouseMapper;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.constant.NumberConstant;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* 房屋、车辆有关操作
* @author chao
* @create 2019/9/24
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class ResourceHouseServiceImpl extends BaseServiceImpl implements IResourceHouseService {
  @Autowired
  private ResourceHouseMapper resourceHouseMapper;
  @Autowired
  private PeopleHouseMapper peopleHouseMapper;
  @Override
  public List<HouseDTO> pages(HouseQueryVO houseQueryVO) {
    House house = new House();
    BeanUtils.copyProperties(houseQueryVO, house);
    
    PageHelper.startPage(houseQueryVO.getCurrentPage().intValue(), houseQueryVO.getPageSize().intValue());
    house.getSqlMap().put("dsf", houseQueryVO.getVillageCodes());
    return this.resourceHouseMapper.pages(house);
  }

  /**
   * 查询房屋详情
   * @param id
   * @return
   */
  @Override
  public HouseDTO get(Integer id) { return this.resourceHouseMapper.selectById(id); }

  /**
   * 新增房屋
   * @param house
   * @return
   */
  @Override
  public String save(House house) {
    String msg = "";
    int count = 0;
    house.setCreateTime(new Date());
    house.setUpdateTime(new Date());
    //检查新增房屋是否已经存在
    House entity = this.resourceHouseMapper.check(house);
    if (entity != null) {
      msg = "ishave";
    } else {
      //新增房屋
      count = this.resourceHouseMapper.insertSelective(house);
    }
    if (count > 0) {
      msg = "success";
    }
    return msg;
  }

  /**
   * 修改房屋
   * @param house
   * @return
   */
  @Override
  public String edit(House house) {
    String msg = "";
    int count = 0;
    house.setUpdateTime(new Date());
    //检查修改房屋是否已经存在
    House entity = this.resourceHouseMapper.check(house);
    if (entity != null) {
      msg = "ishave";
    } else {
      //修改房屋
      count = this.resourceHouseMapper.update(house);
    }
    if (count > 0) {
      msg = "success";
    }
    return msg;
  }

  /**
   * 删除房屋
   * @param id
   * @return
   */
  @Override
  public String delete(Integer id) {
    String msg = "";
    HouseDTO houseDTO = this.resourceHouseMapper.selectById(id);
    PeopleHouse peopleHouse = new PeopleHouse();
    BeanUtils.copyProperties(houseDTO, peopleHouse);
    //查询要删除房屋下是否有人
    List<PeopleHouseDTO> list = this.peopleHouseMapper.selectPeopleHouse(peopleHouse);
    if (list.size() > 0) {
      msg = "havePeople";
    } else {
      //删除房屋
      int count = this.resourceHouseMapper.deleteById(id);
      msg = (count > 0) ? "success" : "failed";
    }
    return msg;
  }

  @Override
  public String batchDelete(Integer... ids){

    String msg = "";
    for (int id : ids){
      HouseDTO houseDTO = this.resourceHouseMapper.selectById(id);
      PeopleHouse peopleHouse = new PeopleHouse();
      BeanUtils.copyProperties(houseDTO, peopleHouse);

      List<PeopleHouseDTO> list = this.peopleHouseMapper.selectPeopleHouse(peopleHouse);
      if (list.size() > 0) {
        msg += houseDTO.getVillageName() + houseDTO.getBuildingName() + houseDTO.getUnitName() + houseDTO.getHouseNo() + "有人.";
      } else {
        int count = this.resourceHouseMapper.deleteById(id);
      }
    }
    return msg;
  }

  /**
   * 人房关系编辑
   * @param peopleHouseVO
   * @return
   */
  @Override
  public Object housePeopleRelation(PeopleHouseVO peopleHouseVO) {
    PeopleHouse peopleHouse = new PeopleHouse();
    BeanUtils.copyProperties(peopleHouseVO, peopleHouse);

    if ("VIEW".equals(peopleHouseVO.getOperationType())) {
      return this.peopleHouseMapper.selectPeopleHouse(peopleHouse);
    }
    boolean status = true;
    status = (this.peopleHouseMapper.deletePeopleRelation(peopleHouse) > 0);
    List<PeopleHouse> list = peopleHouseVO.getPeopleRelations();
    if (list != null && list.size() > 0) {
      for (PeopleHouse entity : list) {
        entity.setHouseId(peopleHouseVO.getHouseId());
        entity.setVillageCode(peopleHouseVO.getVillageCode());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
      }
      status = (this.peopleHouseMapper.insertPeopleRelation(list) > 0);
    } 
    return status ? "success" : "failed";
  }

  /**
   * 人车关系编辑
   * @param peopleVehicle
   * @return
   */
  @Override
  public Object vehiclePeopleRelation(PeopleVehicle peopleVehicle) {

    boolean status = true;
    int count = this.peopleHouseMapper.updateVehiclePeopleRelation(peopleVehicle);
    if (count < CommonConstant.COMMON_1){
      status = false;
    }
    return status;
  }

  @Override
  public List<House> list(House dto) {
    dto.setStatus(CommonConstant.COMMON_1);
    return this.resourceHouseMapper.select(dto);
  }

  @Override
  public Map<String, String> batchImport(List<House> tempList) {
    List<House> updateList = new ArrayList<House>();
    List<House> insertList = new ArrayList<House>();
    
    Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    boolean ckeckFlag = true;
    StringBuilder failureMsg = new StringBuilder();
    int index = NumberConstant.TWO.intValue();
    for (House house : tempList) {
      
      if (StringUtils.isBlank(house.getVillageCode()) && StringUtils.isBlank(house.getVillageCode()) && 
        StringUtils.isBlank(house.getBuildingNo()) && StringUtils.isBlank(house.getUnitNo())) {
        break;
      }
      index++;
      house.setUpdateTime(new Date());
      house.setCreateTime(new Date());
      house.setStatus(NumberConstant.ONE);
      if (StringUtils.isBlank(house.getVillageCode())) {
        failureMsg.append(index + "行“小区编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(house.getBuildingNo())) {
        failureMsg.append(index + "行“楼栋编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(house.getUnitNo())) {
        failureMsg.append(index + "行“单元号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(house.getHouseNo())) {
        failureMsg.append(index + "行“房屋编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (house.getLon() == null) {
        failureMsg.append(index + "行“经度格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (house.getLat() == null) {
        failureMsg.append(index + "行“纬度格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (ckeckFlag) {
        House entity = this.resourceHouseMapper.check(house);
        if (entity == null) {
          insertList.add(house);
          continue;
        } 
        house.setId(entity.getId());
        updateList.add(house);
      } 
    } 
    
    if (ckeckFlag) {
      if (insertList.size() > 0) {
        this.resourceHouseMapper.insertList(insertList);
      }
      if (updateList.size() > 0) {
        this.resourceHouseMapper.updateBatch(updateList);
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
  public Map<String, String> importPeopleRelation(List<PeopleHouse> tempList) {
    Map<String, String> map = new HashMap<String, String>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
    List<PeopleHouse> updateList = new ArrayList<PeopleHouse>();
    List<PeopleHouse> insertList = new ArrayList<PeopleHouse>();
    boolean ckeckFlag = true;
    StringBuilder failureMsg = new StringBuilder();
    int index = NumberConstant.TWO.intValue();
    for (PeopleHouse ph : tempList) {
      
      if (StringUtils.isBlank(ph.getVillageCode()) && StringUtils.isBlank(ph.getVillageCode()) && 
        StringUtils.isBlank(ph.getBuildingNo()) && StringUtils.isBlank(ph.getUnitNo()) && 
        StringUtils.isBlank(ph.getVillageCode())) {
        break;
      }
      index++;
      if (StringUtils.isBlank(ph.getVillageCode())) {
        failureMsg.append(index + "行“小区编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(ph.getBuildingNo())) {
        failureMsg.append(index + "行“楼栋编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(ph.getUnitNo())) {
        failureMsg.append(index + "行“单元号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(ph.getHouseNo())) {
        failureMsg.append(index + "行“房屋编号格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(ph.getCredentialType())) {
        failureMsg.append(index + "行“证件类型格式非法。");
        ckeckFlag = false;
        break;
      } 
      if (StringUtils.isBlank(ph.getCredentialNo())) {
        failureMsg.append(index + "行“证件号码格式非法。");
        ckeckFlag = false;
        
        break;
      } 
      PeopleHouse peopleHouse = this.resourceHouseMapper.checkPeopleHouse(ph);
      if (peopleHouse == null) {
        insertList.add(ph); continue;
      } 
      ph.setId(peopleHouse.getId());
      updateList.add(ph);
    } 

    if (ckeckFlag) {
      if (ckeckFlag) {
        if (insertList.size() > 0) {
          this.resourceHouseMapper.insertBatchPeopleRelation(insertList);
        }
        map.put("result", "success");
        map.put("message", "成功导入" + (index - NumberConstant.TWO.intValue()) + "条数据");
      } else {
        map.put("result", "failed");
        map.put("message", "导入失败：失败原因" + failureMsg.toString());
      } 
    }
    return map;
  }
}
