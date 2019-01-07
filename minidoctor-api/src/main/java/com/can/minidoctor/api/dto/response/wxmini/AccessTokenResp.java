package com.can.minidoctor.api.dto.response.wxmini;

import com.can.minidoctor.api.dto.response.BaseWxResponse;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:45 2019/1/7
 */
public class AccessTokenResp extends BaseWxResponse {

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
