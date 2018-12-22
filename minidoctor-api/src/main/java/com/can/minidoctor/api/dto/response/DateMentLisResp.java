package com.can.minidoctor.api.dto.response;

import com.can.minidoctor.api.dto.BaseDto;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 10:34 2018/12/22
 */
public class DateMentLisResp extends BaseDto {
    private String name;
    private Byte enable;
    private Long dateMentId;

    public Long getDateMentId() {
        return dateMentId;
    }

    public void setDateMentId(Long dateMentId) {
        this.dateMentId = dateMentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }
}
