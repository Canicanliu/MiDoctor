package com.can.minidoctor.api.dto.response.wxmini;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:13 2019/1/7
 */
public class MessageResp implements Serializable {
    private String msgid;

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
