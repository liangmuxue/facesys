package com.ss.facesys.web.manage.baseinfo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ss.controller.AbstractController;
import com.ss.facesys.data.resource.client.IRegionService;
import com.ss.facesys.data.resource.client.IVillageService;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.spider.system.user.mapper.UserResourceMapper;
import com.ss.spider.system.user.model.User;
import com.ss.spider.system.user.model.UserResource;
import com.ss.tools.MD5Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * controller通用基础父类
 *
 * @author FrancisYs
 * @date 2019/12/9
 * @email yaoshuai@ss-cas.com
 */
public class BaseController extends AbstractController {

    @Resource
    public JedisUtil jedisUtil;
    @Resource
    private IRegionService regionService;
    @Resource
    private IVillageService villageService;
    @Resource
    private UserResourceMapper userResourceMapper;

    protected User getUser(String userid) {
        String key = "USERINFO_" + userid;
        if (this.jedisUtil.hasKey(key)) {
            return JSONObject.parseObject(this.jedisUtil.get(key).toString(), User.class);
        }
        return null;
    }

    /**
     * 获取多个区划/小区下的全部小区条件："小区编号1","小区编号2","小区编号3"...
     *
     * @param codes 多个区划/小区编号以逗号分隔
     * @return 全部小区编号
     */
    protected String getVillageCodes(String codes) {
        if (StringUtils.isBlank(codes)) {
            return "";
        }
        List<String> result = new ArrayList<>();
        String[] codeArray = codes.split(CommonConstant.SPLIT_COMMA);
        for (String code : codeArray) {
            if (StringUtils.isNotBlank(code)) {
                if (regionService.isRepeat(code) != null) {
                    // 子条件为区划时查询其下的全部小区编号
                    String regionVillages = regionService.getVilllageCodesByRegionCode(code);
                    if (StringUtils.isNotBlank(regionVillages)) {
                        result.add(regionVillages);
                    }
                } else if (villageService.findVillageByCode(code) != null) {
                    // 子条件为有效小区编号时直接加入结果集
                    result.add(code);
                }
            }
        }
        if (result.size() > 0) {
            return String.join(CommonConstant.SPLIT_COMMA, result.toArray(new String[0]));
        }
        // 传入了非空的 区划/小区条件，未查到小区条件，即区划下无小区的情况
        return "0";
    }

    protected List<Integer> getAuthResources(final String userId, final ResourceType resourceType, final List<Integer> resourceIds) {
        UserResource userResource = new UserResource();
        userResource.setUserId(userId);
        userResource.setType(resourceType.getValue());
        List<UserResource> allResources = userResourceMapper.select(userResource);
        final List<Integer> allResourceIds = allResources.stream().map(UserResource::getResourceId).collect(Collectors.toList());
        // 无指定条件时查询用户权限下的全部数据
        if (CollectionUtils.isEmpty(resourceIds)) {
            return allResourceIds;
        }
        // 筛选有效数据
        return resourceIds.stream().filter(allResourceIds::contains).collect(Collectors.toList());
    }

}
