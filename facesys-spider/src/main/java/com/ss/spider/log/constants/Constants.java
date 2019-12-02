package com.ss.spider.log.constants;

import com.ss.spider.log.bean.ReqestInfo;

import java.util.ArrayList;
import java.util.List;


public class Constants {

    public static final ThreadLocal<ReqestInfo> REQEST_INFO = new ThreadLocal();

    public static final List<String> FILTER_PACKAGE_PREFIXS = new ArrayList<String>() {

    };
    public static final String RESPONSE_ENTITY_NAME = "com.ss.response.ResponseEntity";

}
