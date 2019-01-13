package com.can.minidoctor.api.dto.response.wxmini;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 14:07 2019/1/13
 */
public class LoginInfoResp implements Serializable {
    private String sessionId;
    private int admin;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
