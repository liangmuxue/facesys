package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.Hotel;
import com.ss.facesys.data.resource.common.web.HotelVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 酒店
* @author chao
* @create 2020/2/4
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface HotelMapper extends SsMapper<Hotel> {
  /**
   * 酒店分页查询
   * @param para
   * @return
   */
  List<Hotel> hotelPage(HotelVO para);

  /**
   * 添加酒店
   * @param para
   * @return
   */
  int insertHotel(HotelVO para);

  /**
   * 修改酒店信息
   * @param para
   * @return
   */
  int updateHotel(HotelVO para);

  /**
   * 删除酒店
   * @param para
   * @return
   */
  int deleteHotel(HotelVO para);

  /**
   * 查询酒店详情
   * @param para
   * @return
   */
  Hotel detail(HotelVO para);
}
