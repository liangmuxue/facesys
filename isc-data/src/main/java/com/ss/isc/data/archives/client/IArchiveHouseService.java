package com.ss.isc.data.archives.client;

import com.ss.isc.data.archives.common.dto.PersonInfraDTO;
import java.util.Map;
/**
 * 房屋关联信息查询
 * @author chao
 * @create 2019/8/16
 * @email lishuangchao@ss-cas.com
 **/
public interface IArchiveHouseService {
  /**
   * 房屋关联信息查询
   * @param paramPersonInfraDTO
   * @return
   */
  Map<String, Object> detail(PersonInfraDTO paramPersonInfraDTO);
}
