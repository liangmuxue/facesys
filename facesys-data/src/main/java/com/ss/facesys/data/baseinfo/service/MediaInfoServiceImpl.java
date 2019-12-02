package com.ss.facesys.data.baseinfo.service;

import com.ss.facesys.data.baseinfo.client.MediaInfoService;
import com.ss.facesys.data.baseinfo.common.model.MediaInfo;
import com.ss.facesys.data.baseinfo.mapper.MediaInfoMapper;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




















@Service
@Transactional(rollbackFor = {Exception.class})
public class MediaInfoServiceImpl
  implements MediaInfoService
{
  @Autowired
  private MediaInfoMapper mediaInfoMapper;
  
  public MediaInfo save(MediaInfo mediaInfo) {
    mediaInfo.setCreateTime(new Date());
    mediaInfo.setOperateTime(new Date());
    mediaInfo.setCreateUserId(mediaInfo.getOperateUserId());
    boolean flag = (this.mediaInfoMapper.insertSelective(mediaInfo) > 0);
    return mediaInfo;
  }

  
  public List<MediaInfo> findMediaInfos(MediaInfo mediaInfo) {
    mediaInfo.setStatus(CommonConstant.COMMON_1);
    List<MediaInfo> list = this.mediaInfoMapper.select(mediaInfo);
    for (MediaInfo mInfo : list) {
      mInfo.setDateAttachmentUrl(FilePropertiesUtil.getHttpUrl() + mInfo.getDateAttachmentPath());
    }
    return list;
  }
}
