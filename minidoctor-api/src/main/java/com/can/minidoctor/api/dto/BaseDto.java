package com.can.minidoctor.api.dto;

import com.can.minidoctor.api.utils.MdcUtils;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 21:04 2018/12/18
 */
public class BaseDto implements Serializable {
    protected String mdc_openId;
    protected String mdc_requestId;

    protected String formId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public BaseDto(){
        this.mdc_openId= MdcUtils.get(MdcUtils.OPENID);
    }

    public String getMdc_openId() {
        return mdc_openId;
    }

    public void setMdc_openId(String mdc_openId) {
        this.mdc_openId = mdc_openId;
    }

    public String getMdc_requestId() {
        return mdc_requestId;
    }

    public void setMdc_requestId(String mdc_requestId) {
        this.mdc_requestId = mdc_requestId;
    }
}
