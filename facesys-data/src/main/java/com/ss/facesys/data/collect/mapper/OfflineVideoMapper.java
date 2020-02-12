package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.resource.common.web.OfflineVideoVO;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 离线视频
* @author chao
* @create 2020/2/7
* @email lishuangchao@ss-cas.com
**/
@Mapper
public interface OfflineVideoMapper extends SsMapper<OfflineVideo> {
  /**
   * 离线视频分页查询
   * @param para
   * @return
   */
  List<OfflineVideo> offlineVideoPage(OfflineVideoVO para);

  /**
   * 添加离线视频
   * @param para
   * @return
   */
  int insertOfflineVideo(OfflineVideoVO para);

  /**
   * 查询离线视频详情
   * @param internetBarVO
   * @return
   */
  OfflineVideo detail(OfflineVideoVO internetBarVO);

  /**
   * 修改离线视频信息
   * @param para
   * @return
   */
  int updateOfflineVideo(OfflineVideoVO para);

  /**
   * 删除离线视频
   * @param internetBarVO
   * @return
   */
  int deleteOfflineVideo(OfflineVideoVO internetBarVO);
}
