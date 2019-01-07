package com.can.minidoctor.api.dto.request.miniwx;

import com.can.minidoctor.api.dto.BaseDto;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 21:09 2019/1/7
 */
public class CancelDatementReq extends BaseDto {
    private Long datementId;

    public Long getDatementId() {
        return datementId;
    }

    public void setDatementId(Long datementId) {
        this.datementId = datementId;
    }
}
