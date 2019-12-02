package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.access.client.IAccessService;
import com.ss.facesys.data.baseinfo.common.model.User;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IFacedbService;
import com.ss.facesys.data.collect.mapper.FacedbMapper;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.data.resource.common.model.Facedb;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.Village;
import com.ss.facesys.data.resource.common.model.VillageEntrance;
import com.ss.facesys.data.resource.common.web.PeopleImageVO;
import com.ss.facesys.data.resource.common.web.VillageQueryVO;
import com.ss.facesys.data.resource.mapper.RegionMapper;
import com.ss.facesys.data.resource.mapper.VillageEntranceMapper;
import com.ss.facesys.data.resource.mapper.VillageMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.file.FileConstant;
import com.ss.facesys.util.file.FilePropertiesUtil;
import com.ss.facesys.util.jedis.JedisUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.j7cai.common.util.JsonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * VillageServiceImpl
 * @author FrancisYs
 * @date 2019/8/15
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class VillageServiceImpl extends BaseServiceImpl implements IVillageService {

    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private VillageEntranceMapper villageEntranceMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Resource
    private IAccessService accessService;
    @Resource
    private FacedbMapper facedbMapper;
    @Resource
    private IFacedbService facedbService;

    @Override
    public List<Village> select(Village dto) {
        dto.setState(CommonConstant.COMMON_0);
        return this.villageMapper.select(dto);
    }

    /**
     * 查询小区分页列表
     * @param queryVO
     * @return
     */
    @Override
    public List<Village> findAllVillage(VillageQueryVO queryVO) {
        PageHelper.startPage(queryVO.getCurrentPage(), queryVO.getPageSize());
        // 查询并设置权限条件
        if (queryVO.getUserIds() != null) {
            User user = new User();
            user.setUserId(queryVO.getUserIds());
            Map<String, String> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            map.put("dsf", dataScopeFilter(user).replace("t1", "a"));
            queryVO.setSqlMap(map);
        }
        // 查询小区列表
        List<Village> list = this.villageMapper.findAllVillage(queryVO);
        // 处理结果
        for (Village village1 : list) {
            // 小区照片调整为完整路径
            if (StringUtils.isNotBlank(village1.getVillagePicUrl()) && !village1.getVillagePicUrl().contains(FileConstant.FILE_HTTPADD)) {
                village1.setVillagePicUrl(FilePropertiesUtil.getHttpUrl() + village1.getVillagePicUrl());
            }
        }
        return list;
    }

    /**
     * 新增小区
     * @param village
     * @return
     * @throws Exception
     */
    @Override
    public int insertVillage(Village village) throws Exception {
        // 设置当前社区编号
        String codeTemp;
        codeTemp = village.getOrgCode();
        int villageCount = this.villageMapper.getVillageNumByArea(village.getOrgCode());
        villageCount++;
        // 查看当前社区所属区划级别：若是[区]级别为了避免街道编号冲突，编号前缀默认为 区划编号+999
        Region parentRegion = regionMapper.isRepeat(codeTemp);
        if (parentRegion != null && parentRegion.getRegionType() == Enums.regionType.DISTRICT.getCode()) {
            codeTemp += "999";
        }
        village.setVillageCode(codeTemp + String.format("%03d", villageCount));
        // 设置拼音首字母
        village.setPinyin(StringUtils.getPinYinHeadChar(village.getVillageName()));
        village.setCreateTime(new Date());
        int result = this.villageMapper.insertSelective(village);
        if (result > 0) {

            // 删除redis中与小区相关的数据
            this.jedisUtil.del(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
            this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
            this.jedisUtil.del(CacheConstant.REDIS_KEY_SUPERHOME_SEVEN);
            this.jedisUtil.del(CacheConstant.REDIS_KEY_VILLAGE_FACEDB);
            this.jedisUtil.del(CacheConstant.REDIS_KEY_ALL_VILLAGE);

            // 新增小区欧神人像库
            PeopleImageVO peopleImageVO = new PeopleImageVO();
            peopleImageVO.setName(village.getVillageName());
            String parmJson = JsonUtils.getFastjsonFromObject(peopleImageVO);
            JSONObject jsonObject = this.accessService.facedInsert(parmJson);

            // 欧神中添加人像库成功后：新增小区关联人像库数据
            String villageCode = village.getVillageCode();
            if (StringUtils.checkSuccess(jsonObject)) {
                String facedbId = jsonObject.getString("data");
                // 新增小区-欧神人像库关联数据
                Facedb facedb = new Facedb(villageCode, facedbId);
                Integer num = this.villageMapper.insertFacedb(facedb);
                // 新增社区人像库数据
                com.ss.facesys.data.collect.common.model.Facedb ssFacedb = new com.ss.facesys.data.collect.common.model.Facedb();
                ssFacedb.setName(village.getVillageName());
                ssFacedb.setModel(CommonConstant.FACEDB_MODEL_GENERAL);
                ssFacedb.setRemark(village.getVillageName());
                ssFacedb.setFacedbId(facedbId);
                ssFacedb.setCreateTime(System.currentTimeMillis());
                ssFacedb.setCreateUser(village.getUserIds());
                int ssNum = facedbMapper.insertSelective(ssFacedb);
                if (num > 0 && ssNum > 0) {
                    return result;
                }
                throw new Exception("人像库已添加失败！！请重新添加小区！！");
            }
            throw new Exception("人像库已添加失败！！请重新添加小区！！");
        }
        throw new Exception("小区添加失败！！！请重新添加！！");
    }

    /**
     * 删除小区
     * @param village
     * @param facedbId
     * @return
     */
    @Override
    public Integer deleteVillage(Village village, String facedbId) {
        Integer deleteVillage = 0;
        // 成功删除小区后删除小区人像库数据
        if (StringUtils.isNotBlank(facedbId)) {
            Map<String, String[]> map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
            map.put("facedbIds", new String[]{facedbId});
            String parmJson = JsonUtils.getFastjsonFromObject(map);
            // 删除欧神人像库数据
            JSONObject jsonObject = this.accessService.facedDelete(parmJson);
            if (!StringUtils.checkSuccess(jsonObject)) {
                return deleteVillage;
            }
        }
        deleteVillage = this.villageMapper.deleteVillage(village);
        if (deleteVillage > 0) {
            // 删除社区人像库
            Facedb facedb = villageMapper.findFaceDbByCode(village.getVillageCode());
            com.ss.facesys.data.collect.common.model.Facedb ssFacedb = new com.ss.facesys.data.collect.common.model.Facedb();
            ssFacedb.setFacedbId(facedb.getFacedbId());
            ssFacedb.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
            ssFacedb = facedbMapper.selectOne(ssFacedb);
            ssFacedb.setDeleteTime(System.currentTimeMillis());
            ssFacedb.setDeleteUser(village.getUserIds());
            ssFacedb.setDeleteFlag(CommonConstant.DELETE_FLAG_DELETE);
            facedbMapper.updateByPrimaryKeySelective(ssFacedb);
            // 删除小区关联欧神人像库数据
            this.villageMapper.deleteFaceDb(village.getVillageCode());
        }
        return deleteVillage;
    }

    /**
     * 修改小区
     * @param village
     * @return
     */
    @Override
    public Integer updateVillage(Village village) {
        // 若小区名称修改：则对应修改欧神人像库与小区人像库
        Village original = new Village();
        original.setId(village.getId());
        original = villageMapper.selectByPrimaryKey(original);
        if (!original.getVillageName().equals(village.getVillageName())) {
            Facedb facedb = villageMapper.findFaceDbByCode(village.getVillageCode());
            com.ss.facesys.data.collect.common.model.Facedb ssFacedb = new com.ss.facesys.data.collect.common.model.Facedb();
            ssFacedb.setFacedbId(facedb.getFacedbId());
            ssFacedb.setDeleteFlag(CommonConstant.DELETE_FLAG_EXIST);
            ssFacedb = facedbMapper.selectOne(ssFacedb);
            ssFacedb.setName(village.getVillageName());
            Map<String, Object> resultMap = facedbService.updateFacedb(ssFacedb);
            if (!(boolean)(resultMap.get(CommonConstant.SUCCESS_EN_CODE))) {
                return 0;
            }
        }
        return this.villageMapper.updateVillage(village);
    }

    /**
     * 查询小区详情
     * @param map
     * @return
     */
    @Override
    public List<Village> findVillage(Map<String, String> map) {
        return this.villageMapper.findCurrentVillage(map);
    }


    @Override
    public List<VillageEntrance> findAllVillageEntrances(VillageEntrance villageEntrance) {
        PageHelper.startPage(villageEntrance.getPageNum().intValue(), villageEntrance.getPageSize().intValue());


        if (villageEntrance.getUserIds() != null) {
            User user = new User();
            user.setUserId(villageEntrance.getUserIds());
            Map<String, String> map = new HashMap<String, String>();
            map.put("dsf", dataScopeFilter(user).replace("t1", "a"));
            villageEntrance.setSqlMap(map);
        }

        List<VillageEntrance> findAllVillageEntrances = this.villageEntranceMapper.findAllVillageEntrances(villageEntrance);
        for (VillageEntrance villageEntrance1 : findAllVillageEntrances) {

            if (StringUtils.isNotBlank(villageEntrance1.getEntrancePicUrl()) &&
                    !villageEntrance1.getEntrancePicUrl().contains("http")) {
                villageEntrance1
                        .setEntrancePicUrl(FilePropertiesUtil.getHttpUrl() + villageEntrance1.getEntrancePicUrl());
            }
        }
        return findAllVillageEntrances;
    }


    @Override
    public Village findVillageByCode(String villageCode) {
        Village village = null;
        if (StringUtils.isNotBlank(villageCode)) {
            List<Village> list = new ArrayList<Village>();
            Object villageObj = this.jedisUtil.get("ALL_VILLAGE");
            if (villageObj == null) {
                list = this.villageMapper.findList(null);
                this.jedisUtil.set("ALL_VILLAGE", list);
            } else {
                list = (List) villageObj;
            }
            for (Village v : list) {
                if (villageCode.equals(v.getVillageCode())) {
                    village = v;
                    break;
                }
            }
        }
        return village;
    }


    @Override
    public int getVillageNumByArea(String areaCode) {
        return this.villageMapper.getVillageNumByArea(areaCode);
    }


    @Override
    public Facedb findFaceDbByCode(String villageCode) {
        return this.villageMapper.findFaceDbByCode(villageCode);
    }


    @Override
    public Village selectBythirdId(String thirdId) {
        return this.villageMapper.selectBythirdId(thirdId);
    }


    @Override
    public List<Village> findList(Village village) {
        return this.villageMapper.findList(village);
    }


    @Override
    public String getVillageFacedbId(String villageCode) {
        String facedbId = "";
        if (StringUtils.isNotBlank(villageCode)) {
            List<Facedb> facedbs = new ArrayList<Facedb>();
            Object faceobj = this.jedisUtil.get("VILLAGE_FACEDB");
            if (faceobj == null) {
                facedbs = this.villageMapper.selectFacedb();
                this.jedisUtil.set("VILLAGE_FACEDB", facedbs);
            } else {
                facedbs = (List) faceobj;
            }
            for (Facedb facedb : facedbs) {
                if (villageCode.equals(facedb.getVillageCode())) {
                    facedbId = facedb.getFacedbId();
                    break;
                }
            }
            if (StringUtils.isBlank(facedbId)) {
                facedbs = this.villageMapper.selectFacedb();
                this.jedisUtil.set("VILLAGE_FACEDB", facedbs);
                for (Facedb facedb : facedbs) {
                    if (villageCode.equals(facedb.getVillageCode())) {
                        facedbId = facedb.getFacedbId();
                        break;
                    }
                }
            }
        }
        return facedbId;
    }

}
