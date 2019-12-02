package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.AccessDevice;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.mapper.SsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessDeviceMapper extends SsMapper<AccessDevice> {
  List<AccessDevice> findAccessDevice(Village paramVillage);
}
