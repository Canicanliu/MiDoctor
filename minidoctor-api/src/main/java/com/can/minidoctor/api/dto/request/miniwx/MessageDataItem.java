package com.can.minidoctor.api.dto.request.miniwx;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:09 2019/1/7
 */
public class MessageDataItem implements Serializable {
    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
