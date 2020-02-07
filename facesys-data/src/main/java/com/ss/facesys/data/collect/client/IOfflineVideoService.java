package com.ss.facesys.data.collect.client;

import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.resource.common.web.OfflineVideoVO;

import java.util.List;

/**
* 离线视频
* @author chao
* @create 2020/2/7
* @email lishuangchao@ss-cas.com
**/
public interface IOfflineVideoService {
  /**
   * 离线视频分页查询
   * @param para
   * @return
   */
  List<OfflineVideo> offlineVideoPage(OfflineVideoVO para);
}
