package com.ss.isc.web.manage.baseinfo.controller;

import com.ss.controller.AbstractController;
import com.ss.isc.data.resource.client.IRegionService;
import com.ss.isc.data.resource.client.IVillageService;
import com.ss.isc.data.resource.common.model.Region;
import com.ss.isc.util.StringUtils;
import com.ss.isc.util.constant.CommonConstant;
import com.ss.isc.util.jedis.JedisUtil;
import com.ss.spider.system.user.model.User;
import com.ss.tools.MD5Utils;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * BaseController：controller通用基础父类
 * @author FrancisYs
 * @date 2019/8/13
 * @email yaoshuai@ss-cas.com
 */
public class BaseController extends AbstractController {

    @Resource
    public JedisUtil jedisUtil;
    @Resource
    private IRegionService regionService;
    @Resource
    private IVillageService villageService;

    public User getUser(String userid) {
        String key = "USERINFO_" + userid;
        if (this.jedisUtil.hasKey(key)) {
            return JSONObject.parseObject(this.jedisUtil.get(key).toString(), User.class);
        }
        return null;
    }

    public static String stringFilter(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.encode("123456"));
        System.out.println(DigestUtils.md5("e10adc3949ba59abbe56e057f20f883e"));
    }

    /**
     * 获取多个区划/小区下的全部小区条件："小区编号1","小区编号2","小区编号3"...
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
                } else if (villageService.findVillageByCode(code) != null){
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

}
