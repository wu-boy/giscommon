package com.wu.giscommon.shapefile;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.shapefile.files.ShpFiles;
import org.geotools.data.shapefile.shp.ShapefileReader;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * ShapeFile工具类
 * @date 2018-02-24
 */
public class ShapeFileUtils {

    /**
     * 获取shape文件feature集合
     * @param shapeFile shape文件路径
     * @param charSet 读取shape文件编码
     * @return SimpleFeatureCollection
     */
    public static SimpleFeatureCollection getFeatures(String shapeFile, String charSet){
        SimpleFeatureCollection sfc = null;
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        ShapefileDataStore sds = null;
        try {
            sds = (ShapefileDataStore)dataStoreFactory.createDataStore(new File(shapeFile).toURI().toURL());
            sds.setCharset(Charset.forName(charSet));
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            sfc = featureSource.getFeatures();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sds.dispose();
        }
        return sfc;
    }

    /**
     * 读取ShapeFile中的空间数据
     * @param shapeFile shape文件路径
     * @return List<Geometry>
     */
    public static List<Geometry> getGeometries(String shapeFile){
        List<Geometry> result = new ArrayList<Geometry>();
        try {
            ShpFiles file = new ShpFiles(shapeFile);
            ShapefileReader reader = new ShapefileReader(file, false, false, new GeometryFactory());
            while(reader.hasNext()){
                result.add((Geometry)reader.nextRecord().shape());
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

        // getFeatures方法测试
        /*SimpleFeatureCollection sfc = getFeatures("D:\\shapetest\\district\\bou.shp", "GBK");
        SimpleFeatureIterator iterator = sfc.features();
        while(iterator.hasNext()) {
            SimpleFeature feature = iterator.next();
            System.out.println(feature.getAttribute("NAME"));//获取属性名称
            Geometry g = (Geometry) feature.getDefaultGeometry();//获取空间数据
            System.out.println(g.toString());
        }
        iterator.close();*/

        // getGeometries方法测试
        List<Geometry> list = getGeometries("D:\\test\\wuhan\\wuhan_boundary.shp");
        for(Geometry o:list){
            System.out.println(o.toString());
        }

    }
}
