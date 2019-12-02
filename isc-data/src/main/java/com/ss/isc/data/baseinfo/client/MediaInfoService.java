package com.ss.isc.data.baseinfo.client;

import com.ss.isc.data.baseinfo.common.model.MediaInfo;
import java.util.List;

public interface MediaInfoService {
  MediaInfo save(MediaInfo paramMediaInfo);
  
  List<MediaInfo> findMediaInfos(MediaInfo paramMediaInfo);
}
