package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.HomePageScene;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
* 首页
* @author chao
* @create 2021/3/3
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface HomePageMapper {
  /**
   * 查询场景下设备总数
   * @param homePageScene
   * @return
   */
  List<HomePageScene> deviceTotalByScene(HomePageScene homePageScene);

  /**
   * 查询场景下抓拍总数
   * @param homePageScene
   * @return
   */
  List<HomePageScene> captureTotalByScene(HomePageScene homePageScene);
}
