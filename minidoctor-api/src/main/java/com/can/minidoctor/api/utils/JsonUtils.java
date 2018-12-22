package com.can.minidoctor.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:57 2018/12/18
 */
public class JsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
/*        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        String dateFormat = "yyyy-MM-dd HH:mm:ss";*/

    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.IgnoreNonFieldGetter // 跳过没有对应的属性的解析
    };


    /**
     * Object转为json字符串
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * json字符串Object转为
     */
    public static <T> T toObject(String str, Class<T> clazz) {
        return JSON.parseObject(str, clazz);
    }

    /**
     * json字符串Object转为数组
     */
    public static <T> Object[] toArray(String str, Class<T> clazz) {
        return JSON.parseArray(str, clazz).toArray();
    }

    /**
     * json字符串Object转为list
     */
    public static <T> List<T> toList(String str, Class<T> clazz) {
        return JSON.parseArray(str, clazz);
    }


    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static Map toMap(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

}
