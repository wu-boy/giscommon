package com.wu.giscommon.jts;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTReader;

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

    /**
     * 根据wkt创建Polygon
     * @param wkt
     * @return Polygon
     */
    public static Polygon createPolygonByWKT(String wkt){
        WKTReader reader = new WKTReader(geometryFactory);
        Polygon polygon = null;
        try {
            // wkt = "POLYGON((20 10, 30 0, 40 10, 30 20, 20 10))")
            polygon = (Polygon) reader.read(wkt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return polygon;
    }
}
