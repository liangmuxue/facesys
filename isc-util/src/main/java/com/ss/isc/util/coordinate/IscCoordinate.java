package com.ss.isc.util.coordinate;

public class IscCoordinate {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return this.latitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return this.longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public IscCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
