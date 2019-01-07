package com.can.minidoctor.api.dto.request.miniwx;

import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:07 2019/1/7
 */
public class WxMessageSendReq implements Serializable {
    private String touser;
    private String template_id;
    private String page;
    private String form_id;
    private MessageData data;
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getTouser() {
        return touser;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public String getTemplate_id() {
        return template_id;
    }

    public MessageData getData() {
        return data;
    }

    public void setData(MessageData data) {
        this.data = data;
    }
}
