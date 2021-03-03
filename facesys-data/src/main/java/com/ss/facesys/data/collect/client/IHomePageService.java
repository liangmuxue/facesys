package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.HomePageBase;
import com.ss.facesys.data.collect.common.model.HomePageDevice;
import com.ss.facesys.data.collect.common.model.HomePageScene;

import java.util.List;

public interface IHomePageService {

  HomePageBase get(HomePageBase homePageBase);

  List<HomePageScene> get(HomePageScene homePageScene);

  HomePageDevice get(HomePageDevice homePageDevice);
}
