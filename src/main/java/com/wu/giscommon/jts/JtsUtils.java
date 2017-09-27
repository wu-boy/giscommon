package com.wu.giscommon.jts;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

/**
 * JTS工具类
 * @version 2017-09-27
 */
public class JtsUtils {

    private static GeometryFactory geometryFactory = new GeometryFactory();

    /**
     * 创建Point
     * @param lon
     * @param lat
     * @return Point
     */
    public static Point createPoint(double lon, double lat){
        Coordinate c = new Coordinate(lon, lat);
        return geometryFactory.createPoint(c);
    }
}
