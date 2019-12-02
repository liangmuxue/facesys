package com.ss.facesys.util.export;

import java.util.HashMap;
import java.util.Map;


public final class TemplateUtils {

    private static Map<String, String> cache = new HashMap();

    static {
        cache.put("resource_camera_template", "xiangji.xlsx");
        cache.put("resource_people_template", "renkou.xlsx");
        cache.put("resource_house_template", "fangwu.xlsx");
        cache.put("resource_company_template", "danwei.xlsx");
        cache.put("resource_company_people_template", "danweirenyuan.xlsx");
        cache.put("resource_vehicle_tollgate_template", "kakou.xlsx");
        cache.put("resource_wifi_template", "wifi.xlsx");
        cache.put("resource_peoplehouse_template", "renfangguanxi.xlsx");
    }

    public static String download(String templateType) {
        return cache.get(templateType);
    }

}
