package com.wu.giscommon.jts;

import com.vividsolutions.jts.geom.*;

/**
 * JTS工具类
 * @version 2017-11-06
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

    /**
     * 根据multiLineString创建Polygon
     * @param multiLineString
     * @return Polygon
     */
    public static Polygon createPolygon(MultiLineString multiLineString){
        Polygon result = null;
        if(multiLineString != null){
            result = geometryFactory.createPolygon(multiLineString.getCoordinates());
        }
        return result;
    }
}
