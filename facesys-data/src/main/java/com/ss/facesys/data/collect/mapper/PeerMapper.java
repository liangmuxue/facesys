package com.ss.facesys.data.collect.mapper;

import com.ss.facesys.data.collect.common.dto.PeerDTO;
import com.ss.facesys.data.collect.common.model.AddPersonDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PeerMapper {
  List<PeerDTO> findPeer(@Param("addPersonId") String paramString);
  
  List<AddPersonDetail> findPeerDetails(PeerDTO paramPeerDTO);
}
