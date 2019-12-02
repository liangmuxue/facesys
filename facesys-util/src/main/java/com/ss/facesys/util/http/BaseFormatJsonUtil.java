package com.ss.facesys.util.http;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseFormatJsonUtil {

    public static <T> List<T> formatList(Object json, Class<T> clazz) {
        List<T> result = null;
        String empty = "null";
        if (null != json && !empty.equals(json)) {
            List<JSONObject> list = (List) json;

            if (!list.isEmpty()) {
                result = new ArrayList<T>();
                for (JSONObject obj : list) {
                    T w = (T) JSONObject.toJavaObject(obj, clazz);
                    result.add(w);
                }
            }
        }
        return result;
    }


    public static <T> T formatEntity(Object json, Class<T> clazz) {
        JSONObject entity = (JSONObject) json;
        return (T) JSONObject.toJavaObject(entity, clazz);
    }

}
