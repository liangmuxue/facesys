package com.ss.isc.data.collect.client;

import com.ss.isc.data.collect.common.dto.PeerDTO;
import com.ss.isc.data.collect.common.model.AddPersonDetail;
import java.util.List;
/**
* 同行人员查询
* @author chao
* @create 2019/8/21
* @email lishuangchao@ss-cas.com
**/
public interface IPeerService {
  /**
   * 同行人员查询
   * @param paramString
   * @return
   */
  List<PeerDTO> findPeer(String paramString);

  /**
   * 同行人员详细信息查询
   * @param paramPeerDTO
   * @return
   */
  List<AddPersonDetail> findPeerDetails(PeerDTO paramPeerDTO);
}
