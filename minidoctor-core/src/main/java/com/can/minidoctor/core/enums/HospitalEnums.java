package com.can.minidoctor.core.enums;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:06 2018/12/18
 */
public enum HospitalEnums {
    HongMian((byte)0,"红棉社康"),
    YiJin((byte)1,"怡锦社康") ;

    private byte code;

    private String name;

    HospitalEnums(byte code, String name){
        this.code=code;
        this.name=name;
    }

    public static String getNameByCode(byte code){
        for(HospitalEnums enums:HospitalEnums.values()){
            if(enums.getCode()==code){
                return enums.getName();
            }
        }
        return null;
    }

    public static byte getCodeByName(String name){
        for(HospitalEnums enums:HospitalEnums.values()){
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
