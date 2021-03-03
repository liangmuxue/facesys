package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.HomePageScene;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomePageMapper {

  List<HomePageScene> deviceTotalByScene(HomePageScene homePageScene);

  List<HomePageScene> captureTotalByScene(HomePageScene homePageScene);
}
