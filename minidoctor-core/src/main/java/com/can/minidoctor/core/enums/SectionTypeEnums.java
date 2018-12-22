package com.can.minidoctor.core.enums;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:06 2018/12/18
 */
public enum SectionTypeEnums {
    beforeNoon((byte)0,"上午"),
    afterNoon((byte)1,"下午") ,
    beforeSupper((byte)2,"傍晚"),
    afterSupper((byte)3,"晚上");


    private byte code;

    private String name;

    SectionTypeEnums(byte code, String name){
        this.code=code;
        this.name=name;
    }

    public static String getNameByCode(byte code){
        for(SectionTypeEnums enums:SectionTypeEnums.values()){
            if(enums.getCode()==code){
                return enums.getName();
            }
        }
        return null;
    }

    public static byte getCodeByName(String name){
        for(SectionTypeEnums enums:SectionTypeEnums.values()){
            if(enums.getName().equals(name)){
                return enums.getCode();
            }
        }
        return -1;
    }
    
    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
