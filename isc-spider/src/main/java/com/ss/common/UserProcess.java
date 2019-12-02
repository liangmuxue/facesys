package com.ss.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UserProcess {

    @Value("${nginx.root.url}")
    protected String nginxRootUrl;
    @Value("${upload.image.type}")
    private String imageType;
    @Value("${upload.image.max.size}")
    private Integer imageMaxSize;

    @PostConstruct
    public void init() {
        Constants.NGINX_IMAGE_PATH = this.nginxRootUrl.trim();
        Constants.IMAGE_TYPE = this.imageType.trim();
        Constants.IMAGE_MAX_SIZE = this.imageMaxSize;
    }

}
