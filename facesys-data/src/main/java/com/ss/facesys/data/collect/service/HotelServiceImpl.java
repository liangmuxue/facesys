package com.ss.facesys.data.collect.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IHotelService;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.collect.mapper.HotelMapper;
import com.ss.facesys.data.resource.common.web.HotelVO;
import com.ss.facesys.util.constant.NumberConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
* 酒店
* @author chao
* @create 2020/2/4
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class HotelServiceImpl extends BaseServiceImpl implements IHotelService {
  public static final Log logger = LogFactory.getLog(HotelServiceImpl.class);

  @Autowired
  private HotelMapper hotelMapper;

  /**
   * 酒店分页查询
   * @param hotelVO
   * @return
   */
  @Override
  public List<Hotel> hotelPage(HotelVO hotelVO) {
    PageHelper.startPage(hotelVO.getCurrentPage(), hotelVO.getPageSize());
    List<Hotel> houses = this.hotelMapper.hotelPage(hotelVO);
    return houses;
  }

  /**
   * 添加酒店
   * @param hotelVO
   * @return
   */
  @Override
  public int insertHotel(HotelVO hotelVO) {
    hotelVO.setCreateTime(String.valueOf(System.currentTimeMillis()));
    hotelVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
    hotelVO.setStatus(NumberConstant.ONE);
    int result = this.hotelMapper.insertHotel(hotelVO);
    return result;
  }

  /**
   * 修改酒店信息
   * @param hotelVO
   * @return
   */
  @Override
  public int updateHotel(HotelVO hotelVO) {
    hotelVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
    int result = this.hotelMapper.updateHotel(hotelVO);
    return result;
  }

  /**
   * 删除酒店
   * @param para
   * @return
   */
  @Override
  public String deleteHotel(HotelVO para) {
    String message;
    List<DevicePersoncard> devicePersoncards = this.hotelMapper.checkDevice(para);
    if (devicePersoncards.size() > 0){
      message = "删除酒店信息失败，酒店已经关联设备信息";
    } else {
      int num = this.hotelMapper.deleteHotel(para);
      if (num > 0) {
        message = "删除成功";
      } else {
        message = "删除失败，请联系管理员";
      }
    }
    return message;
  }

  /**
   * 查询酒店详情
   * @param para
   * @return
   */
  @Override
  public Hotel detail(HotelVO para) {
    Hotel result = this.hotelMapper.detail(para);
    return result;
  }
}
