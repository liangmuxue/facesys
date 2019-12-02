package com.ss.facesys.data.resource.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.common.dto.RegionTree;
import com.ss.facesys.data.resource.common.model.Region;
import com.ss.facesys.data.resource.common.model.ThirdRegion;
import com.ss.facesys.data.resource.common.web.RegionVO;
import com.ss.facesys.data.resource.mapper.RegionMapper;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.constant.NumberConstant;
import com.ss.facesys.util.coordinate.GetCenterCoordinates;
import com.ss.facesys.util.coordinate.IscCoordinate;
import com.ss.facesys.util.em.Enums;
import com.ss.facesys.util.jedis.JedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = {Exception.class})
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;
    @Resource
    private JedisUtil jedisUtil;

    /**
     * 查询行政区划树
     * @param region
     * @return
     */
    @Override
    public List<RegionTree> regionTree(Region region) {
        List<RegionTree> allList;
        Object object = this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE);
        if (object != null) {
            allList = (List) this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE);
        } else {
            allList = this.regionMapper.treeList(null);
            this.jedisUtil.set(CacheConstant.REDIS_KEY_REGION_TREE, allList);
        }
        return buildRegionTree(allList);
    }

    /**
     * 创建区划树数据
     * @param allList
     * @return
     */
    private List<RegionTree> buildRegionTree(List<RegionTree> allList) {
        List<RegionTree> treeData = new ArrayList<>();
        RegionTree currentCity = new RegionTree();
        RegionTree currentArea = new RegionTree();
        RegionTree currentStreet = new RegionTree();
        RegionTree currentVillage;

        for (RegionTree regionTree : allList) {
            // 市级别
            if (regionTree.getRegionType() == Enums.regionType.CITY.getCode()) {
                currentCity = regionTree;
                if (currentCity.getChildren() == null) {
                    currentCity.setChildren(new ArrayList<RegionTree>());
                }
                treeData.add(currentCity);
                continue;
            }
            // 区级别
            if (regionTree.getRegionType() == Enums.regionType.DISTRICT.getCode()) {
                currentArea = regionTree;
                if (currentArea.getChildren() == null) {
                    currentArea.setChildren(new ArrayList<RegionTree>());
                }
                currentCity.getChildren().add(currentArea);
                continue;
            }
            // 街道级别
            if (regionTree.getRegionType() == Enums.regionType.STREET.getCode()) {
                currentStreet = regionTree;
                if (currentStreet.getChildren() == null) {
                    currentStreet.setChildren(new ArrayList<RegionTree>());
                }
                currentArea.getChildren().add(currentStreet);
                continue;
            }
            // 小区级别
            if (regionTree.getRegionType() == Enums.regionType.VILLAGE.getCode()) {
                currentVillage = regionTree;
                if (currentVillage.getParentId().equals(currentArea.getRegionId())) {
                    currentArea.getChildren().add(currentVillage);
                } else if (currentVillage.getParentId().equals(currentStreet.getRegionId())) {
                    currentStreet.getChildren().add(currentVillage);
                }
            }
        }
        return treeData;
    }

    /**
     * 查询行政区划树（包含小区）
     * @param region
     * @param villageCodes
     * @return
     */
    @Override
    public List<RegionTree> getTreeData(Region region, String[] villageCodes) {
        List<RegionTree> allList;
        List<RegionTree> regionList;
        List<RegionTree> villageList;

        // 查询行政区划树信息
        Object regionObj = this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE);
        if (regionObj != null) {
            regionList = (List) this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE);
        } else {
            regionList = this.regionMapper.treeList(null);
            this.jedisUtil.set(CacheConstant.REDIS_KEY_REGION_TREE, regionList);
        }
        // 查询包含小区的区划树信息
        Object villageObj = this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
        if (villageObj != null) {
            villageList = (List) this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
        } else {
            villageList = this.regionMapper.findVillageRegion();
            this.jedisUtil.set(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE, villageList);
        }
        allList = regionList;
        // 将属于用户权限区划且包含小区的区划加入结果集合中
        if (villageCodes != null) {
            for (String villageCode : villageCodes) {
                for (RegionTree v : villageList) {
                    if (villageCode.equals(v.getRegionCode())) {
                        allList.add(v);
                    }
                }
            }
        } else {
            allList.addAll(villageList);
        }
        // 根据regionCode进行排序（创建树需要）
        allList.sort((o1, o2) -> Integer.compare(o1.getRegionCode().compareTo(o2.getRegionCode()), 0));
        return buildRegionTree(allList);
    }


    @Override
    public RegionTree getThirdTreeData() {
        RegionTree root = null;
        List<ThirdRegion> list = this.regionMapper.getThirdTreeData(null, null);
        if (null != list && list.size() > 0) {
            root = getChildNode((ThirdRegion) list.get(0));
        }
        return root;
    }

    private RegionTree getChildNode(ThirdRegion third) {
        RegionTree node = getRegionNode(third);
        if (null != node && node.getRegionType().intValue() != Enums.regionType.STREET.getCode()) {
            List<ThirdRegion> list = this.regionMapper.getThirdTreeData(third.getId(), null);

            if (StringUtils.isEmpty(list)) {
                list = this.regionMapper.getThirdTreeData(null, third.getRegionCode());
            }

            if (null != list) {
                List<RegionTree> child = new ArrayList<RegionTree>();
                for (ThirdRegion tr : list) {
                    child.add(getChildNode(tr));
                    node.setChildren(child);
                }
            }
        }

        return node;
    }


    private RegionTree getRegionNode(ThirdRegion third) {
        IscCoordinate re = null;
        if (!StringUtils.isEmpty(third.getCoordinates())) {
            re = GetCenterCoordinates.getCoordinate(third.getCoordinates());
        }
        RegionTree node = new RegionTree();
        node = new RegionTree();
        node.setRegionId(third.getId());
        node.setRegionName(third.getRegionName());
        node.setRegionCode(third.getRegionCode());
        node.setGisType(Integer.valueOf(Enums.gisType.WGS.getCode()));
        node.setRegionType(Integer.valueOf(third.getRegionType()));
        if (null != re) {
            node.setLat(Double.valueOf(re.getLatitude()));
            node.setLon(Double.valueOf(re.getLongitude()));
        } else {
            node.setLat(Double.valueOf(0.0D));
            node.setLon(Double.valueOf(0.0D));
        }

        if (!StringUtils.isEmpty(third.getCoordinates())) {
            node.setGisArea(third.getCoordinates().replaceAll("@", ";"));
        }

        if (null != third.getParentId()) {
            node.setParentId(third.getParentId());
        }

        node.setRemark(third.getRemark());

        return node;
    }

    /**
     * 查询区划下的全部小区编号
     * @param regionCode
     * @return
     */
    @Override
    public String getVilllageCodes(String regionCode) {
        List<RegionTree> allList;
        List<RegionTree> regionList;
        List<RegionTree> villageList;

        Object regionObj = this.jedisUtil.get("REGION_TREE");
        if (regionObj != null) {
            regionList = (List) this.jedisUtil.get("REGION_TREE");
        } else {
            Region entity = new Region();
            regionList = this.regionMapper.treeList(entity);
            this.jedisUtil.set("REGION_TREE", regionList);
        }

        Object villageObj = this.jedisUtil.get("REGION_TREE_VILLAGE");
        if (villageObj != null) {
            villageList = (List) this.jedisUtil.get("REGION_TREE_VILLAGE");
        } else {
            villageList = this.regionMapper.findVillageRegion();
            this.jedisUtil.set("REGION_TREE_VILLAGE", villageList);
        }
        allList = regionList;
        allList.addAll(villageList);
        Region region = new Region();
        region.setRegionCode(regionCode);
        Region r = this.regionMapper.selectOne(region);
        String villages = "";
        List<String> villageCodeList = new ArrayList<String>();
        if (r != null) {
            RegionTree regionTree = new RegionTree();
            BeanUtils.copyProperties(r, regionTree);
            villageCodeList = getChildVillageRegion(regionTree, allList, villageCodeList);

            if (villageCodeList.size() > 0) {
                villages = StringUtils.join(villageCodeList.toArray(), ",");
            }
        }
        return villages;
    }

    /**
     * 查询区划下的全部小区编号
     * @param regionCode 区划编号
     * @return
     */
    @Override
    public String getVilllageCodesByRegionCode(String regionCode) {
        String villages = "";
        List<RegionTree> villageList;
        List<String> villageCodeList = new ArrayList<>();
        Object villageObj = this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
        if (villageObj != null) {
            villageList = (List) this.jedisUtil.get(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE);
        } else {
            villageList = this.regionMapper.findVillageRegion();
            this.jedisUtil.set(CacheConstant.REDIS_KEY_REGION_TREE_VILLAGE, villageList);
        }
        if (CollectionUtils.isNotEmpty(villageList)) {
            for (RegionTree regionTree : villageList) {
                if (regionTree != null && StringUtils.isNotBlank(regionTree.getRegionCode()) && regionTree.getRegionCode().startsWith(regionCode)) {
                    villageCodeList.add(regionTree.getRegionCode());
                }
            }
            if (villageCodeList.size() > 0) {
                villages = String.join(CommonConstant.SPLIT_COMMA, villageCodeList.toArray(new String[0]));
            }
        }
        return villages;
    }

    private List<String> getChildVillageRegion(RegionTree regionTree, List<RegionTree> allList, List<String> villageCodeList) {
        for (RegionTree it : allList) {
            if (regionTree.getRegionId().equals(it.getParentId())) {
                if (it.getRegionType() != null && it.getRegionType().intValue() == NumberConstant.SEVEN.intValue()) {
                    villageCodeList.add(it.getRegionCode());
                }
                getChildVillageRegion(it, allList, villageCodeList);
            }
        }
        return villageCodeList;
    }

    /**
     * 创建区划树数据
     * @param region
     * @param allList
     * @return
     */
    private List<RegionTree> buildRegionTree(Region region, List<RegionTree> allList) {
        List<RegionTree> treeData;
        List<RegionTree> regionList = new ArrayList<>();
        List<RegionTree> regions = new ArrayList<>();
        Map<String, String> mapallRegionCode = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        if (StringUtils.isNotBlank(region.getRegionName())) {
            // 查询指定区划名称（模糊匹配）的区划集合
            for (RegionTree r : allList) {
                boolean isAdd = false;
                if (r.getRegionName().contains(region.getRegionName())) {
                    isAdd = true;
                } else if (StringUtils.isNotBlank(r.getPinyin()) && r.getPinyin().contains(region.getRegionName().toUpperCase())) {
                    isAdd = true;
                }
                if (isAdd && !mapallRegionCode.containsKey(r.getRegionId())) {
                    mapallRegionCode.put(r.getRegionId(), r.getRegionId());
                    regions.add(r);
                    regionList.add(r);
                }
            }
            // 查找目标区划的父子级区划
            for (RegionTree regionTree : regionList) {
                // 增加父区划信息
                regions = getParentRegion(regionTree, allList, regions, mapallRegionCode);
                // 增加子区划信息
                regions = getChildRegion(regionTree, allList, regions, mapallRegionCode);
            }
            treeData = buildByRecursive(regions);
        } else {
            treeData = buildByRecursive(allList);
        }
        return treeData;
    }

    /**
     * 查找父级区划
     * @param regionTree
     * @param allList
     * @param regions
     * @param mapallRegionCode
     * @return
     */
    private List<RegionTree> getParentRegion(RegionTree regionTree, List<RegionTree> allList, List<RegionTree> regions, Map<String, String> mapallRegionCode) {
        for (RegionTree it : allList) {
            if (StringUtils.isNotBlank(regionTree.getParentId()) && regionTree.getParentId().equals(it.getRegionId())) {
                if (!mapallRegionCode.containsKey(it.getRegionId())) {
                    mapallRegionCode.put(it.getRegionId(), it.getRegionId());
                    regions.add(it);
                }
                getParentRegion(it, allList, regions, mapallRegionCode);
            }
        }
        return regions;
    }

    /**
     * 查找子区划
     * @param regionTree
     * @param allList
     * @param regions
     * @param mapallRegionCode
     * @return
     */
    private List<RegionTree> getChildRegion(RegionTree regionTree, List<RegionTree> allList, List<RegionTree> regions, Map<String, String> mapallRegionCode) {
        for (RegionTree it : allList) {
            if (regionTree.getRegionId().equals(it.getParentId())) {
                if (!mapallRegionCode.containsKey(it.getRegionId())) {
                    mapallRegionCode.put(it.getRegionId(), it.getRegionId());
                    regions.add(it);
                }
                getChildRegion(it, allList, allList, mapallRegionCode);
            }
        }
        return regions;
    }

    /**
     * 递归创建区划
     * @param treeNodes
     * @return
     */
    private List<RegionTree> buildByRecursive(List<RegionTree> treeNodes) {
        List<RegionTree> trees = new ArrayList<>();
        for (RegionTree treeNode : treeNodes) {
            // RegionCode=0（中国）且区域类型是居委会以上（<7）时，进入递归创建子集
            if (String.valueOf(NumberConstant.ZERO).equals(treeNode.getRegionCode()) && treeNode.getRegionType() < CommonConstant.COMMON_7) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 查找子区划（递归直到找到最后一层（且类型在居委会以上））
     * @param treeNode
     * @param treeNodes
     * @return
     */
    private RegionTree findChildren(RegionTree treeNode, List<RegionTree> treeNodes) {
        treeNode.setChildren(new ArrayList());
        for (RegionTree it : treeNodes) {
            if (treeNode.getRegionId().equals(it.getParentId()) && treeNode.getRegionType() < CommonConstant.COMMON_7) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    @Override
    public List<Region> findCurrentRegion(Region region) {
        List<Region> findCurrentRegion = this.regionMapper.findCurrentRegion(region);
        for (Region region2 : findCurrentRegion) {
            Integer regionType = region2.getRegionType();
            String thirdId = region2.getThirdId();
            region2.getRegionType();

            if (StringUtils.isNotBlank(thirdId)) {
                List<ThirdRegion> ids = this.regionMapper.selectIdByRegionCode5(thirdId);
                if (ids != null && ids.size() > 0) {
                    if (ids.size() > 1) {
                        for (ThirdRegion thirdRegion2 : ids) {
                            if (thirdRegion2.getRegionType() == 5) {
                                if (regionType.intValue() == 5) {
                                    region2.setThirdName(thirdRegion2.getRegionName());
                                    region2.setThirdId(thirdRegion2.getId());
                                }
                                continue;
                            }
                            if (regionType.intValue() != 5) {
                                region2.setThirdName(thirdRegion2.getRegionName());
                                region2.setThirdId(thirdRegion2.getId());
                            }
                        }
                        continue;
                    }
                    if (ids.size() == 1) {
                        region2.setThirdName(((ThirdRegion) ids.get(0)).getRegionName());
                        region2.setThirdId(((ThirdRegion) ids.get(0)).getId());
                    }
                }
            }
        }
        return findCurrentRegion;
    }


    @Override
    public Integer insertRegion(Region region) {
        return this.regionMapper.insertRegion(region);
    }


    @Override
    public Integer deleteRegion(Region region) {
        return this.regionMapper.deleteRegion(region);
    }


    @Override
    public Integer deleteChildrenRegion(Region region) {
        return this.regionMapper.deleteChildrenRegion(region);
    }


    @Override
    public Integer updateRegion(Region region) {
        return this.regionMapper.updateRegion(region);
    }


    @Override
    public List<Region> list(Region region) {
        return this.regionMapper.selectRegionList(region);
    }

    /**
     * 查询行政区划分页列表
     * @param region
     * @return
     */
    @Override
    public List<Region> regionInfolist(RegionVO region) {
        PageHelper.startPage(region.getCurrentPage(), region.getPageSize());
        return this.regionMapper.selectRegionInfoList(region);
    }


    @Override
    public Region selectOne(Region region) {
        return (Region) this.regionMapper.selectOne(region);
    }


    @Override
    public int matchRegion(Region local, ThirdRegion third, int regionType) {
        local.setThirdId(third.getRegionCode());
        local.setGisArea(third.getCoordinates());
        local.setGisType(Integer.valueOf(regionType));

        IscCoordinate re = GetCenterCoordinates.getCoordinate(third.getCoordinates());
        local.setGisCenter(re.getLatitude() + "," + re.getLongitude());
        return this.regionMapper.updateRegion(local).intValue();
    }


    @Override
    public List<Region> getBindThirdRegion() {
        return this.regionMapper.getBindThirdRegion();
    }


    @Override
    public int getThirdRegionNumber() {
        return this.regionMapper.getThirdRegionNumber();
    }


    @Override
    public List<Region> getRegion(Region region) {
        return this.regionMapper.getRegion(region);
    }


    @Override
    public Region getRegionParent(String regionCode) {
        return this.regionMapper.getRegionParent(regionCode);
    }


    @Override
    public Region selectRegionByCode(String regionCode) {
        return this.regionMapper.selectRegionByCode(regionCode);
    }


    @Override
    public Region isRepeat(String regionCode) {
        return this.regionMapper.isRepeat(regionCode);
    }


    @Override
    public String selectRegionCodeByThirdId(String thirdId) {
        return this.regionMapper.selectRegionCodeByThirdId(thirdId);
    }

}
