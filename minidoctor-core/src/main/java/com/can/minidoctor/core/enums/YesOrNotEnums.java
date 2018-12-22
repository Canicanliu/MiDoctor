package com.can.minidoctor.core.enums;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:06 2018/12/18
 */
public enum YesOrNotEnums {
    No((byte)0,"no"),
    Yes((byte)1,"yes") ;

    private byte code;

    private String name;

    YesOrNotEnums(byte code,String name){
        this.code=code;
        this.name=name;
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
