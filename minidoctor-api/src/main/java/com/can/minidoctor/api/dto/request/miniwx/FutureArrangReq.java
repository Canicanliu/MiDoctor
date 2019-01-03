package com.can.minidoctor.api.dto.request.miniwx;

import com.can.minidoctor.api.dto.BaseDto;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 17:45 2018/12/22
 */
public class FutureArrangReq extends BaseDto {
    private byte hospital;

    public byte getHospital() {
        return hospital;
    }

    public void setHospital(byte hospital) {
        this.hospital = hospital;
    }
}
