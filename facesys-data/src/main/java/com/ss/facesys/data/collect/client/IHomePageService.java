package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.HomePageBase;
import com.ss.facesys.data.collect.common.model.HomePageDevice;
import com.ss.facesys.data.collect.common.model.HomePageScene;

import java.util.List;

/**
 * 首页
 *
 * @author 李爽超 chao
 * @create 2021/03/02
 * @email lishuangchao@ss-cas.com
 **/
public interface IHomePageService {

  /**
   * 首页基础数据统计查询
   * @param homePageBase
   * @return
   * @throws Exception
   */
  HomePageBase get(HomePageBase homePageBase);

  /**
   * 首页应用场景统计查询
   * @param homePageScene
   * @return
   */
  List<HomePageScene> get(HomePageScene homePageScene);

  /**
   * 首页单个设备统计查询
   * @param homePageDevice
   * @return
   */
  HomePageDevice get(HomePageDevice homePageDevice);
}
