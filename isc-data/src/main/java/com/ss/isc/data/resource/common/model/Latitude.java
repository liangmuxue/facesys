package com.ss.isc.data.resource.common.model;

/**
 * @description: 坐标
 * @author: 梁慕学
 * @create: 2019-07-15 13:33
 **/

public class Latitude {

  private Double lon;


  private Double lat;


  public Double getLon() {
    return this.lon;
  }


  public void setLon(Double lon) {
    this.lon = lon;
  }


  public Double getLat() {
    return this.lat;
  }


  public void setLat(Double lat) {
    this.lat = lat;
  }


  public Latitude() {
  }


  public Latitude(Double lon, Double lat) {
    this.lon = lon;
    this.lat = lat;
  }
}