package com.ss.facesys.data.archives.service;

import com.ss.facesys.data.archives.client.IPersonService;
import com.ss.facesys.data.archives.common.dto.PersonHouseDTO;
import com.ss.facesys.data.archives.common.dto.PersonHousePeopleDTO;
import com.ss.facesys.data.archives.common.dto.PersonInfraDTO;
import com.ss.facesys.data.archives.common.dto.VillageCodeDTO;
import com.ss.facesys.data.archives.common.model.Electric;
import com.ss.facesys.data.archives.common.model.Gas;
import com.ss.facesys.data.archives.common.model.People;
import com.ss.facesys.data.archives.common.model.Water;
import com.ss.facesys.data.archives.common.model.WifiCollect;
import com.ss.facesys.data.baseinfo.common.dto.EnumVO;
import com.ss.facesys.data.archives.common.web.HouseVO;
import com.ss.facesys.data.archives.common.web.InfrastructureVO;
import com.ss.facesys.data.archives.common.web.PeopleVO;
import com.ss.facesys.data.archives.common.web.PersonVO;
import com.ss.facesys.data.archives.common.web.WarningVO;
import com.ss.facesys.data.archives.mapper.ArchiveHouseMapper;
import com.ss.facesys.data.archives.mapper.InfrastructureMapper;
import com.ss.facesys.data.archives.mapper.PersonMapper;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.util.PropertiesUtil;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description 实有人口接口实现
 * @author FrancisYs
 * @date 2019/8/12
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class PersonServiceImpl extends BaseServiceImpl implements IPersonService {

    private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private ArchiveHouseMapper houseMapper;
    @Resource
    public JedisUtil jedisUtil;
    @Autowired
    private InfrastructureMapper infraMapper;
    @Autowired
    private ArchiveHouseMapper archivehouseMapper;
    /**
     * 查询实有人口详情
     * @param peopleDTO
     * @return
     */
    @Override
    public Map<String, Object> detail(People peopleDTO) {
        // 加入用户权限小区相关sql条件
        User user = new User();
        user.setUserId(peopleDTO.getUserIds());
        String sqlString = dataScopeFilter(user);
        peopleDTO.setSqlString(sqlString);
        // 查询用户数据并返回视图对象
        List<PeopleVO> pvList = this.personMapper.voDetail(peopleDTO);
        PeopleVO peopleVO = new PeopleVO();
        if (CollectionUtils.isNotEmpty(pvList)) {
            peopleVO = pvList.get(0);
            peopleVO.setIdCardPicFull(dealPicUrl(peopleVO.getIdCardPic()));
            peopleVO.setFacePicFull(dealPicUrl(peopleVO.getFacePic()));
        }
        Map<String, Object> peopleMap = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        peopleMap.put("peopleVO", peopleVO);
        return peopleMap;
    }

    /**
     * 查询房屋详情
     * @param personHouseDTO
     * @return
     */
    @Override
    public List<HouseVO> house(PersonHouseDTO personHouseDTO) {
        User user = new User();
        user.setUserId(personHouseDTO.getUserIds());
        String sqlString = dataScopeFilter(user).replace("t1", "h");
        personHouseDTO.setSqlString(sqlString);
        //查询并返回房屋详情
        return this.houseMapper.getHouseByPeopleId(personHouseDTO);
    }

    @Override
    public InfrastructureVO infrastructure(PersonInfraDTO personInfraDTO) {
        User user = new User();
        user.setUserId(personInfraDTO.getUserIds());
        String sqlString = dataScopeFilter(user);
        personInfraDTO.setSqlString(sqlString);

        InfrastructureVO infra = new InfrastructureVO();
        Water water = this.infraMapper.getWater(personInfraDTO);
        Electric electric = this.infraMapper.getElectric(personInfraDTO);
        Gas gas = this.infraMapper.getGas(personInfraDTO);
        if (water != null) {
            infra.setWater(water.getUsage());
        }
        if (electric != null) {
            infra.setElectric(electric.getUsage());
        }
        if (gas != null) {
            infra.setCoalGas(gas.getUsage());
        }
        return infra;
    }

    /**
     * 查询房屋关联人员
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> getPeopleByHouseId(PersonHousePeopleDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserIds());
        String sqlString = dataScopeFilter(user).replace("t1", "p");
        dto.setSqlString(sqlString);
        List<PersonVO> personVOList = this.personMapper.getPeopleByHouseId(dto);
        for (PersonVO people : personVOList) {
            List<EnumVO> enumVO = new ArrayList<EnumVO>();
            //取得人员标签编号
            String label = people.getLabel();
            if (StringUtils.isNotBlank(label)) {
                String[] labels = label.trim().split(",");
                if (labels != null && labels.length > 0) {
                    for (int i = 0; i < labels.length; i++) {
                        //取得人员标签内容
                        enumVO.add(new EnumVO(this.archivehouseMapper.getLabel(labels[i]), labels[i]));
                    }
                    people.setLabels(enumVO);
                }
            }
            if (StringUtils.isNotBlank(people.getIdCardPic()) && !people.getIdCardPic().contains("http")) {
                //取得证件照相对路径
                people.setIdCardPic(FilePropertiesUtil.getHttpUrl() + people.getIdCardPic());
            }
        }
        Map<String, Object> hpMap = new HashMap<String, Object>(CommonConstant.HASHMAP_INITIALCAPACITY.intValue());
        hpMap.put("personVOList", personVOList);
        return hpMap;
    }


    @Override
    public List<People> getPeople() {
        List<People> list = null;
        this.jedisUtil.del(new String[]{"ALL_PEOPLE"});

        if (this.jedisUtil.hasKey("ALL_PEOPLE")) {
            list = (List) this.jedisUtil.get("ALL_PEOPLE");
        } else {

            list = this.personMapper.getPeople();

            this.jedisUtil.set("ALL_PEOPLE", list);
        }
        return list;
    }


    @Override
    public List<People> getPeopleDiscoveryLeave(String villageCode) {
        List<People> list = getPeople();

        List<People> resertlist = new ArrayList<People>();
        for (People p : list) {

            if (p.getIsLeave() == 0) {
                if (null != villageCode) {
                    if (villageCode.equals(p.getVillageCode())) {
                        resertlist.add(p);
                    }
                    continue;
                }
                resertlist.add(p);
            }
        }


        return resertlist;
    }

    /**
     * 一人一档-预警信息
     * @param dto
     * @return
     */
    @Override
    public Map<String, Object> warning(VillageCodeDTO dto) {
        Map<String, Object> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        List<WarningVO> sList, aList, lList, oList;
        // 陌生人预警
        sList = this.personMapper.strangeList(dto);
        map.put("strangeCount", sList.isEmpty() ? 0 : sList.get(0).getAmount());
        // 疑似新增预警
        dto.setDays(PropertiesUtil.getAddPersonJudgedDays());
        aList = this.personMapper.addList(dto);
        map.put("addCount", aList.isEmpty() ? 0 : aList.get(0).getDays());
        // 疑似离开预警
        dto.setDays(PropertiesUtil.getLeaveDays());
        lList = this.personMapper.leaveList(dto);
        map.put("leaveCount", lList.isEmpty() ? 0 : lList.get(0).getLeaveDays());
        // 高龄老人预警
        dto.setDays(PropertiesUtil.getOldManDays());
        oList = this.personMapper.oldList(dto);
        map.put("oldCount", oList.isEmpty() ? 0 : oList.get(0).getDays());

        // 预警数据汇总
        List<WarningVO> warningList = new ArrayList<>();
        warningList.addAll(sList);
        warningList.addAll(aList);
        warningList.addAll(lList);
        warningList.addAll(oList);
        if (CollectionUtils.isNotEmpty(warningList)) {
            for (WarningVO warningVO : warningList) {
                String captureUrlFull = warningVO.getCaptureUrlFull();
                if (StringUtils.isNoneBlank(captureUrlFull) && !captureUrlFull.contains(FileConstant.FILE_HTTPADD)) {
                    warningVO.setCaptureUrlFull(FilePropertiesUtil.getHttpUrl() + captureUrlFull);
                }
            }
        }
        map.put("warningList", warningList);
        return map;
    }


    @Override
    public List<WifiCollect> mac(VillageCodeDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserIds());
        String sqlString = dataScopeFilter(user);
        dto.setSqlString(sqlString);

        return this.personMapper.mac(dto);
    }

    /**
     * 处理图片地址
     * @param origin 原始地址
     * @return 完整访问路径
     */
    private String dealPicUrl(String origin) {
        if (com.ss.facesys.util.StringUtils.isBlank(origin)) {
            return "";
        }
        if (!origin.contains(FileConstant.FILE_HTTPADD)) {
            return FilePropertiesUtil.getHttpUrl() + origin;
        }
        return origin;
    }

}
