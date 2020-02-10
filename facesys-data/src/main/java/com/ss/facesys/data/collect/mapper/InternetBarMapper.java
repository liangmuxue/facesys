package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.InternetBar;
import com.ss.facesys.data.resource.common.web.InternetBarVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 网吧
* @author chao
* @create 2020/2/6
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface InternetBarMapper extends SsMapper<InternetBar> {
  /**
   * 网吧分页查询
   * @param para
   * @return
   */
  List<InternetBar> internetBarPage(InternetBarVO para);

  /**
   * 添加网吧
   * @param para
   * @return
   */
  int insertInternetBar(InternetBarVO para);

  /**
   * 修改网吧信息
   * @param para
   * @return
   */
  int updateInternetBar(InternetBarVO para);

  /**
   * 删除网吧
   * @param para
   * @return
   */
  int deleteInternetBar(InternetBarVO para);

  /**
   * 查询网吧详情
   * @param para
   * @return
   */
  InternetBar detail(InternetBarVO para);
}
