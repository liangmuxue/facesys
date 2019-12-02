package com.ss.facesys.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class JsonUtil {

    public static final Log LOG = LogFactory.getLog(JsonUtil.class);


    public static JSONArray getJsonArray(JSONObject obj) {
        JSONArray arr = null;
        if ("00000000".equals(obj.getString("code")) || "0"
                .equals(obj.getString("code"))) {
            Object object = obj.get("data");
            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
                if (jsonObject.containsKey("datas")) {
                    arr = jsonObject.getJSONArray("datas");
                }
            } else if (object instanceof JSONArray) {
                arr = (JSONArray) object;
            }
        }
        return arr;
    }

    public static JSONArray getJsonArray(String jsonStr) {
        JSONArray arr = null;
        try {
            if (null != jsonStr && jsonStr.length() > 0) {
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                if (jsonObject.containsKey("datas")) {
                    arr = jsonObject.getJSONArray("datas");
                }
            }
        } catch (Exception e) {
            LOG.error("jsonUtil转换异常：", e);
        }
        return arr;
    }

}
