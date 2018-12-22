package com.can.minidoctor.api.dto.request.miniwx;

import com.can.minidoctor.api.dto.BaseDto;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:30 2018/12/22
 */
public class DateDetailReq extends BaseDto {
    private Long dateMentId;

    public Long getDateMentId() {
        return dateMentId;
    }

    public void setDateMentId(Long dateMentId) {
        this.dateMentId = dateMentId;
    }
}
