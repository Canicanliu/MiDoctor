package com.can.minidoctor.api.utils;

import java.security.MessageDigest;
import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:57 2018/12/18
 */
public class CanStringUtils {

//    public static String getEncodeStr(List<String> raws){
//        StringBuilder stringBuilder=new StringBuilder();
//        for(String raw:raws){
//            stringBuilder.append(raw);
//        }
//        String str=stringBuilder.toString();
//        try {
//            MessageDigest mDigest=MessageDigest.getInstance("MD5");
//            byte[] hash = mDigest.digest(str.getBytes("UTF-8"));
//            code=Hex.encodeHexString(hash);
//
//        } catch (Exception e) {
//            LOGGER.error("生成分享编码异常",e);
//        }
//        return code;
//    }

    public static String getRandomStr(){
        return System.currentTimeMillis()+"";
    }

    public static boolean nullOrEmpty(String value){
        if(null==value){
            return true;
        }
        if("".equals(value.trim())){
            return true;
        }
        return false;
    }
}
