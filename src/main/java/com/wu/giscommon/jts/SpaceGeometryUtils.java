package com.wu.giscommon.jts;

import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.Point;

/**
 * 空间几何工具类
 * @date 2018-04-23
 */
public class SpaceGeometryUtils {

    /**
     * 求垂足
     * @param x0 经度
     * @param y0 纬度
     * @param x1 线段起点经度
     * @param y1 线段起点纬度
     * @param x2 线段终点经度
     * @param y2 线段终点纬度
     * @return
     */
    public static double[] getFootPoint(double x0, double y0, double x1, double y1, double x2, double y2) {
        if (x1 == x2) {
            return new double[]{x1, y0};
        } else if (y1 == y2) {
            return new double[]{x0, y1};
        } else {
            double k = (y2 - y1) / (x2 - x1);
            double nx = (k * k * x1 + k * (y0 - y1) + x0) / (k * k + 1.0D);
            double ny = k * (nx - x1) + y1;
            return new double[]{nx, ny};
        }
    }

    public static void main(String[] args) {
        double[] point = getFootPoint(116.30628,39.885541,116.305446,39.88445, 116.307446,39.885402);
        //System.out.println(point[0] + "," + point[1]);
        Point p0 = JtsUtils.createPoint(point[0], point[1]);
        Point p1 = JtsUtils.createPoint(116.30628,39.885541);
        System.out.println(p0.distance(p1));

        MultiLineString line = JtsUtils.createMultiLineStringByWKT("MULTILINESTRING((116.305446 39.88445,116.307446 39.885402))");
        System.out.println(line.distance(p1));
    }
}
