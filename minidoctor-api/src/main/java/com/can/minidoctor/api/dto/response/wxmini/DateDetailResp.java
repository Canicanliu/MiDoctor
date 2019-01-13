package com.can.minidoctor.api.dto.response.wxmini;

import com.can.minidoctor.api.dto.BaseDto;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:34 2018/12/22
 */
public class DateDetailResp extends BaseDto {
    private String workDate;
    private String hospital;
    private String name;
    private String identification;
    private String mobile;
    private Long dateId;
    private int admin;

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }
}
