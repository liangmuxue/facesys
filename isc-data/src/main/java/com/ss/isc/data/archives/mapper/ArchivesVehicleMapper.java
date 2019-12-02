package com.ss.isc.data.archives.mapper;

import com.ss.isc.data.archives.common.model.Vehicle;
import com.ss.isc.data.archives.common.model.VehicleDiscovery;
import com.ss.isc.data.archives.common.model.VehicleLeave;
import com.ss.isc.data.archives.common.model.VehicleRetation;
import com.ss.mapper.CWMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ArchivesVehicleMapper extends CWMapper<Vehicle> {
  List<Vehicle> list(Vehicle paramVehicle);
  
  List<VehicleDiscovery> getDiscovery(VehicleDiscovery paramVehicleDiscovery);
  
  List<VehicleLeave> getLeave(VehicleLeave paramVehicleLeave);
  
  List<VehicleRetation> getRetation(VehicleRetation paramVehicleRetation);

  List<Vehicle> getVehicleInformation (Vehicle paramVehicleRetation);

  List<Vehicle> selectById(Vehicle paramVehicle);

  List<Vehicle> detail(Vehicle paramVehicle);

  Vehicle check(Vehicle param);

  int update(Vehicle param);

  int updateVehicle(Vehicle param);

  int deleteById(Integer id);

}
