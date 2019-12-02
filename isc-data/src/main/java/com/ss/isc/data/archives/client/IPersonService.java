package com.ss.isc.data.archives.client;

import com.ss.isc.data.archives.common.dto.PersonHouseDTO;
import com.ss.isc.data.archives.common.dto.PersonHousePeopleDTO;
import com.ss.isc.data.archives.common.dto.PersonInfraDTO;
import com.ss.isc.data.archives.common.dto.VillageCodeDTO;
import com.ss.isc.data.archives.common.model.People;
import com.ss.isc.data.archives.common.model.WifiCollect;
import com.ss.isc.data.archives.common.web.HouseVO;
import com.ss.isc.data.archives.common.web.InfrastructureVO;

import java.util.List;
import java.util.Map;

/**
 * @Description 实有人口接口
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
public interface IPersonService {

    /**
     * 查询实有人口详情
     * @param paramPeople
     * @return
     */
    Map<String, Object> detail(People paramPeople);

    List<HouseVO> house(PersonHouseDTO paramPersonHouseDTO);

    InfrastructureVO infrastructure(PersonInfraDTO paramPersonInfraDTO);

    /**
     * 房屋居住人员信息
     * @param paramPersonHousePeopleDTO
     * @return
     */
    Map<String, Object> getPeopleByHouseId(PersonHousePeopleDTO paramPersonHousePeopleDTO);

    List<People> getPeople();

    List<People> getPeopleDiscoveryLeave(String paramString);

    /**
     * 一人一档-预警信息
     * @param paramVillageCodeDTO
     * @return
     */
    Map<String, Object> warning(VillageCodeDTO paramVillageCodeDTO);

    List<WifiCollect> mac(VillageCodeDTO paramVillageCodeDTO);

}
