package com.ss.isc.data.system.service;

import com.ss.exception.ServiceException;
import com.ss.isc.data.system.client.IOrganizationRegionService;
import com.ss.isc.data.system.common.dto.UserPermission;
import com.ss.isc.data.system.common.model.OrganizationRegion;
import com.ss.isc.data.system.common.model.Region;
import com.ss.isc.data.system.mapper.OrganizationRegionMapper;
import com.ss.isc.data.system.mapper.SystemRegionMapper;
import com.ss.isc.util.PropertiesUtil;
import com.ss.isc.util.constant.CacheConstant;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrganizationRegionServiceImpl implements IOrganizationRegionService {

    @Resource
    private JedisUtil jedisUtil;
    private List<Region> treeList = new ArrayList();

    @Autowired
    private OrganizationRegionMapper oMapper;
    @Autowired
    private SystemRegionMapper regionMapper;

    @Override
    public List<OrganizationRegion> getList(OrganizationRegion dto) {
        return this.oMapper.seleall(dto.getOrgId());
    }

    @Override
    public List<Region> getTree() {
        this.treeList = this.regionMapper.selectAll();
        if (this.treeList == null) {
            return null;
        }

        String regionCode = PropertiesUtil.getRegionCode();
        Region dto = new Region();
        dto.setState(Double.valueOf(1.0D));
        dto.setRegionCode(regionCode);
        List<Region> parentList = this.regionMapper.select(dto);
        List<Region> list = new ArrayList<Region>();
        if (parentList != null) {
            for (int i = 0; i < parentList.size(); i++) {
                list.add(recursiveTree(((Region) parentList.get(i)).getRegionId()));
            }
        }
        return list;
    }

    public Region recursiveTree(String cid) {
        Region node = getRegionById(cid);
        List<Region> childTreeNodes = getChildTreeById(cid);
        for (Region child : childTreeNodes) {
            Region n = recursiveTree(child.getRegionId());
            List<Region> list = node.getChildren();
            list.add(n);
        }
        return node;
    }

    public Region getRegionById(String cid) {
        Map<String, Region> map;
        map = getTreeMap();
        return map.get(cid);
    }

    public Map<String, Region> getTreeMap() {
        Map<String, Region> map;
        map = new HashMap<>(CommonConstant.HASHMAP_INITIALCAPACITY);
        if (null != this.treeList) {
            for (Region d : this.treeList) {
                map.put(d.getRegionId(), d);
            }
        }
        return map;
    }

    public List<Region> getChildTreeById(String cid) {
        List<Region> list = new ArrayList<Region>();
        if (null != this.treeList) {
            for (Region d : this.treeList) {
                if (null != cid &&
                        cid.equals(d.getParentId())) {
                    list.add(d);
                }
            }
        }

        return list;
    }

    @Override
    public void update(OrganizationRegion dto) {
        try {
            if ("".equals(dto.getOrgId())) {
                throw new ServiceException("请选择组织机构");
            }
            List<OrganizationRegion> entity = this.oMapper.seleall(dto.getOrgId());
            if (entity.size() == 0) {
                this.oMapper.insert(dto);
            } else {
                this.oMapper.update(dto);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询机构权限小区
     *
     * @return
     */
    @Override
    public List<UserPermission> findUserPermission() {
        List<UserPermission> list;
        Object object = this.jedisUtil.get(CacheConstant.REDIS_KEY_USERPERMISSION);
        if (object != null) {
            list = (List) object;
        } else {
            // 从库中查询
            list = this.oMapper.findUserPermission(null);
            for (UserPermission userPermission : list) {
                String villageCodesStr = userPermission.getVillageCodesStr();
                if (StringUtils.isNotBlank(villageCodesStr)) {
                    userPermission.setVillageCodes(userPermission.getVillageCodesStr().split(","));
                    continue;
                }
                userPermission.setVillageCodes(new String[0]);
            }
            this.jedisUtil.set(CacheConstant.REDIS_KEY_USERPERMISSION, list);
        }
        return list;
    }

    /**
     * 查询当前用户机构权限
     *
     * @param userId
     * @return
     */
    @Override
    public UserPermission findCurrentPersion(String userId) {
        UserPermission userPermission = null;
        if (StringUtils.isNotBlank(userId)) {
            List<UserPermission> permissions = findUserPermission();
            for (UserPermission entity : permissions) {
                if (userId.equals(entity.getUserId())) {
                    userPermission = entity;
                    break;
                }
            }
            if (userPermission == null) {
                this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
            }
        }
        return userPermission;
    }

    /**
     * 根据用户操作权限操作redis
     *
     * @param userId
     * @return
     */
    @Override
    public String dataScopeFilter(String userId) {
        UserPermission userPermission = findCurrentPersion(userId);
        String villageCodes = "";
        if (userPermission == null) {
            this.jedisUtil.del(CacheConstant.REDIS_KEY_USERPERMISSION);
        } else if (StringUtils.isNotBlank(userPermission.getVillageCodesStr())) {
            villageCodes = userPermission.getVillageCodesStr();
        }
        return villageCodes;
    }

    @Override
    public List<String> findPermissionByVillageCode(String villageCode) {
        List<String> userIds = new ArrayList<String>();
        if (StringUtils.isNotBlank(villageCode)) {
            List<UserPermission> permissions = findUserPermission();
            for (UserPermission user : permissions) {
                String[] villageCodes = user.getVillageCodes();
                for (int i = 0; i < villageCodes.length; i++) {
                    if (villageCode.equals(villageCodes[i])) {
                        userIds.add(user.getUserId());
                        break;
                    }
                }
            }
        }
        return userIds;
    }

    @Override
    public String cameraIdsString(String userId) {
        List<String> cameraIdList = this.regionMapper.getCameraIds(userId);
        return StringUtils.join(cameraIdList.toArray(), ",");
    }

}
