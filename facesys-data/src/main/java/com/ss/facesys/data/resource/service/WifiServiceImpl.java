package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.common.dto.CaptureSumDTO;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.client.IWifiService;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.Wifi;
import com.ss.facesys.data.resource.mapper.WifiMapper;
import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.NumberConstant;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;















@Service
@Transactional(rollbackFor = {Exception.class})
public class WifiServiceImpl
  extends BaseServiceImpl
  implements IWifiService
{
  @Autowired
  private WifiMapper wifiMapper;
  @Resource
  private IRegionService regionService;
  @Resource
  private IVillageService villageService;
  @Resource
  private IOrganizationRegionService organizationRegionService;
  
  public int insertWifi(Wifi wifi) { return this.wifiMapper.insertWifi(wifi); }




  
  public int updateWifi(Wifi wifi) { return this.wifiMapper.updateWifi(wifi); }




  
  public int deleteWifi(Wifi wifi) { return this.wifiMapper.deleteWifi(wifi); }



  
  public List<Wifi> findAllWifis(Wifi wifi) {
    PageHelper.startPage(wifi.getPageNum().intValue(), wifi.getPageSize().intValue());

    
    if (wifi.getUserIds() != null) {
      User user = new User();
      user.setUserId(wifi.getUserIds());
      Map<String, String> map = new HashMap<String, String>();
      map.put("dsf", dataScopeFilter(user).replace("t1", "a"));
      wifi.setSqlMap(map);
    } 
    return this.wifiMapper.findAllWifis(wifi);
  }



  
  public List<CaptureSumDTO> getWifiSum() { return this.wifiMapper.getWifiSum(); }



  
  public List<Wifi> pages(Wifi wifi) {
    PageHelper.startPage(wifi.getCurrentPage().intValue(), wifi.getPageSize().intValue());
    
    List<Wifi> pages = this.wifiMapper.pages(wifi);
    
    for (Wifi wifi2 : pages) {
      String villageCode = wifi2.getVillageCode();
      Village findVillageByCode = this.villageService.findVillageByCode(villageCode);
      if (StringUtils.isNotBlank(findVillageByCode.getVillageName())) {
        wifi2.setVillageName(findVillageByCode.getVillageName());
      }
    } 
    
    return pages;
  }


  
  public String batchImport(List<Wifi> tempList, Map<String, String> imagePaths) {
    List<Wifi> updateList = new ArrayList<Wifi>();
    List<Wifi> insertList = new ArrayList<Wifi>();
    
    boolean flag = true;
    int insertNum = 0;
    int updateNum = 0;
    StringBuilder failureMsg = new StringBuilder();
    int index = NumberConstant.TWO.intValue();
    Date date = new Date();
    for (Wifi wifi : tempList) {
      
      if (StringUtils.isBlank(wifi.getVillageCode()) && StringUtils.isBlank(wifi.getDeviceId())) {
        break;
      }
      index++;
      if (StringUtils.isBlank(wifi.getVillageCode())) {
        failureMsg.append("<br/>第" + index + "小区编号为空");
        flag = false;
      } 
      if (StringUtils.isBlank(wifi.getDeviceId())) {
        failureMsg.append("<br/>第" + index + "探针的mac12位16进制字符为空");
        flag = false;
      } 
      
      wifi.setDevicePicUrl((String)imagePaths.get(wifi.getDeviceId()));
      
      if (flag) {
        Wifi entity = this.wifiMapper.check(wifi);
        if (entity == null) {
          wifi.setCreateTime(date);
          wifi.setUpdateTime(date);
          insertList.add(wifi);
          insertNum++; continue;
        } 
        wifi.setId(entity.getId());
        wifi.setUpdateTime(date);
        updateList.add(wifi);
        updateNum++;
      } 
    } 
    
    if (flag) {
      if (insertList.size() > 0) {
        this.wifiMapper.insertList(insertList);
      }
      if (updateList.size() > 0) {
        this.wifiMapper.updateBatch(updateList);
      }
    } 
    return flag ? ("成功导入" + (index - NumberConstant.TWO.intValue()) + "条数据，新增" + insertNum + "条，更新" + updateNum + "条") : ("导入失败" + failureMsg
      .toString());
  }


  
  public List<CaptureSumDTO> getWifiAllSum() { return this.wifiMapper.getWifiAllSum(); }
}
