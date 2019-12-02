package com.ss.facesys.util.coordinate;

import com.ss.facesys.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetCenterCoordinates {

    private static Logger logger = LoggerFactory.getLogger(GetCenterCoordinates.class);


    public static IscCoordinate getCenterPoint(List<IscCoordinate> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double pointx = 0.0D, pointy = 0.0D, pointz = 0.0D;
        for (IscCoordinate g : geoCoordinateList) {

            double lat = g.getLatitude() * Math.PI / 180.0D;
            double lon = g.getLongitude() * Math.PI / 180.0D;
            double x = Math.cos(lat) * Math.cos(lon);
            double y = Math.cos(lat) * Math.sin(lon);
            double z = Math.sin(lat);
            pointx += x;
            pointy += y;
            pointz += z;
        }

        pointx /= total;
        pointy /= total;
        pointz /= total;
        double lon = Math.atan2(pointy, pointx);
        double hyp = Math.sqrt(pointx * pointx + pointy * pointy);
        double lat = Math.atan2(pointz, hyp);
        return new IscCoordinate(lat * 180.0D / Math.PI, lon * 180.0D / Math.PI);
    }


    public static IscCoordinate getCenterPoint400(List<IscCoordinate> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double lat = 0.0D, lon = 0.0D;
        for (IscCoordinate g : geoCoordinateList) {
            lat += g.getLatitude() * Math.PI / 180.0D;
            lon += g.getLongitude() * Math.PI / 180.0D;
        }
        lat /= total;
        lon /= total;
        return new IscCoordinate(lat * 180.0D / Math.PI, lon * 180.0D / Math.PI);
    }


    public static IscCoordinate getCoordinate(String coordinate) {
        try {
            List<IscCoordinate> coordinateList = null;
            IscCoordinate cooObj = null;
            if (null != coordinate) {
                coordinateList = new ArrayList<IscCoordinate>();
                String[] coo = coordinate.split("@");
                String[] atom = null;
                if (null != coo && coo.length > 0) {
                    for (int i = 0; i < coo.length; i++) {
                        atom = coo[i].split(",");
                        cooObj = new IscCoordinate(Double.valueOf(atom[0]).doubleValue(), Double.valueOf(atom[1]).doubleValue());
                        coordinateList = StringUtils.pickList(coordinateList, cooObj);
                    }
                }

                return getCenterPoint(coordinateList);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return null;
    }

    public static void main(String[] args) {
    }

}
