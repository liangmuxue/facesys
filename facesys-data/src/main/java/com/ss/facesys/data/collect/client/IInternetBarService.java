package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.InternetBar;
import com.ss.facesys.data.resource.common.web.InternetBarVO;

import java.util.List;

/**
* 网吧
* @author chao
* @create 2020/2/6
* @email lishuangchao@ss-cas.com
**/
public interface IInternetBarService {
  /**
   * 网吧分页查询
   * @param internetBarVO
   * @return
   */
  List<InternetBar> internetBarPage(InternetBarVO internetBarVO);

  /**
   * 添加网吧
   * @param internetBarVO
   * @return
   */
  int insertInternetBar(InternetBarVO internetBarVO);

  /**
   * 修改网吧信息
   * @param internetBarVO
   * @return
   */
  int updateInternetBar(InternetBarVO internetBarVO);

  /**
   * 删除网吧
   * @param internetBarVO
   * @return
   */
  String deleteInternetBar(InternetBarVO internetBarVO);

  /**
   * 查询网吧详情
   * @param internetBarVO
   * @return
   */
  InternetBar detail(InternetBarVO internetBarVO);
}
