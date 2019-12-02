package com.ss.facesys.data.process.client;

import com.ss.facesys.data.process.common.model.AddPersonRecog;
import com.ss.facesys.data.process.common.model.PeopleProcess;
import com.ss.facesys.data.process.common.model.Vehicle;
import com.ss.facesys.data.process.common.web.PeopleProcessVO;

/**
 * IPeopleProcessService
 * @author FrancisYs
 * @date 2019/8/23
 * @email yaoshuai@ss-cas.com
 */
public interface IPeopleProcessService {

    /**
     * 人员处置
     * @param paramPeopleProcessVO
     * @return
     */
    String peopleProcess(PeopleProcessVO paramPeopleProcessVO);

    /**
     * 查询预警处置记录（此处实际包含了人员预警和车辆预警）
     * @param paramPeopleProcess
     * @return
     */
    PeopleProcess selectPeopleProcess(PeopleProcess paramPeopleProcess);

    /**
     * 车辆处置
     * @param paramVehicle
     * @return
     */
    boolean vehicleProcess(Vehicle paramVehicle);

    AddPersonRecog selectAddPersonRecogMapper(String paramString);

    String saveRecogInfo(AddPersonRecog paramAddPersonRecog);

}
