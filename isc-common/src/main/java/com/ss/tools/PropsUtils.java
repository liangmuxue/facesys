package com.ss.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class PropsUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtils.class);


    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();
        InputStream is = null;
        try {
            if (!StringUtils.hasText(propsPath)) {
                throw new IllegalArgumentException();
            }

            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath = propsPath + suffix;
            }

            is = Thread.class.getResourceAsStream(propsPath);
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            logger.error("加载属性文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("释放资源出错！", e);
            }
        }
        return props;
    }


    public static Map<String, String> loadPropsToMap(String propsPath) {
        Map<String, String> map = new HashMap<String, String>(6);
        Properties props = loadProps(propsPath);
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }


    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }


    public static String getString(Properties props, String key, String defalutValue) {
        String value = defalutValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }


    public static int getNumber(Properties props, String key) {
        int value = 0;
        if (props.containsKey(key)) {
            value = Integer.valueOf(props.getProperty(key)).intValue();
        }
        return value;
    }


    public static int getNumber(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = Integer.valueOf(props.getProperty(key)).intValue();
        }
        return value;
    }


    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }


    public static boolean getBoolean(Properties props, String key, boolean defalutValue) {
        boolean value = defalutValue;
        if (props.containsKey(key)) {
            value = Boolean.valueOf(props.getProperty(key)).booleanValue();
        }
        return value;
    }


    public static Map<String, Object> getMap(Properties props, String prefix) {
        Map<String, Object> kvMap = new LinkedHashMap<String, Object>();
        Set<String> keySet = props.stringPropertyNames();
        if (!CollectionUtils.isEmpty(keySet)) {
            for (String key : keySet) {
                if (key.startsWith(prefix)) {
                    String value = props.getProperty(key);
                    kvMap.put(key, value);
                }
            }
        }

        return kvMap;
    }

}
