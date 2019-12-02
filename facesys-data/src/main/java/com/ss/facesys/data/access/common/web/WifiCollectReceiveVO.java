package com.ss.facesys.data.access.common.web;

import com.google.common.collect.Lists;

import java.util.List;


public class WifiCollectReceiveVO {

    private List<WifiCollectReceive> wifiReceives;

    public List<WifiCollectReceive> getWifiReceives() {
        if (this.wifiReceives == null) {
            this.wifiReceives = Lists.newArrayList();
        }
        return this.wifiReceives;
    }


    public void setWifiReceives(List<WifiCollectReceive> wifiReceives) {
        this.wifiReceives = wifiReceives;
    }

}
