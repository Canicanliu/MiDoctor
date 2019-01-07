package com.can.minidoctor.api.dto.response;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:06 2019/1/7
 */
public class BaseWxResponse implements Serializable{
    protected int errcode;
    protected String errmsg;

    public int getErrcode() {
        return errcode;
    }
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
