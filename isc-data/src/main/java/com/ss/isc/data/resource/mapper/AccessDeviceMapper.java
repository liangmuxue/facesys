package com.ss.isc.data.resource.mapper;

import com.ss.isc.data.resource.common.model.AccessDevice;
import com.ss.isc.data.resource.common.model.Village;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessDeviceMapper extends CWMapper<AccessDevice> {
  List<AccessDevice> findAccessDevice(Village paramVillage);
}
