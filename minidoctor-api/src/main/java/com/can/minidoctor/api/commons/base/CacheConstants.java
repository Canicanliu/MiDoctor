package com.can.minidoctor.api.commons.base;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:52 2019/1/7
 */
public class CacheConstants {
    private static final String prefix="miniDoctor:";

    public static String getAccessTokenKey(){
        return prefix+"miniAccessToken";
    }

}
