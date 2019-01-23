package com.imyeego.util;

import com.imyeego.annotation.HandlerMapping;
import com.imyeego.handler.DataHandler;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerUtil {

    private final static Map<String,DataHandler> instanceCache = new ConcurrentHashMap<>();

    static {
        try {
            //扫描指定包下的所有DataHandler实现类
            List<Class> classes = ClassUtil.getAllClassBySubClass(DataHandler.class, true,"com.imyeego.handler");
            for (Class claz : classes) {
                HandlerMapping annotation = (HandlerMapping) claz.getAnnotation(HandlerMapping.class);
                //以其HandlerMapping的value为key handler实例为value缓存到map中
                instanceCache.put(annotation.value(), (DataHandler) claz.newInstance());
            }
            System.out.println("handler init success handler Map: " +  instanceCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataHandler getHandlerInstance(String name) {
        return instanceCache.get(name);
    }


}
