package com.can.minidoctor.core.service.wxmini;


import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.MessageData;
import com.can.minidoctor.api.dto.request.miniwx.WxMessageSendReq;
import com.can.minidoctor.api.dto.response.wxmini.MessageResp;
import com.can.minidoctor.api.utils.JsonUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.core.common.http.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * @Author: LIUTM1
 * @Descripion:
 * @Date: Created in 16:17 2018-11-2
 */
public class MessageSenderService {

    private static final Logger LOGGER=LoggerFactory.getLogger(MessageSenderService.class);
    public static final int ERROR40001 = 40001;

    @Value("${wx.miniprogram.sendWxTemplateNotice}")
    String sendWxMessageUrl;
    @Autowired
    WxMiniService wxMiniService;

    @Autowired
    HttpClientService clientService;

    /**
     * 给用户发送小程序模板消息
     * @param openId
     * @param template_id
     * @param data
     * @return
     */
    @LogParams("给用户发送小程序模板消息")
    public MessageResp sendMessageByOpenId(String openId, String formId, String template_id, String page, MessageData data){
        WxMessageSendReq req=new WxMessageSendReq();
        req.setForm_id(formId);
        req.setData(data);
        req.setTemplate_id(template_id);
        req.setTouser(openId);
        req.setPage(page);
        return sendAMessageByWx(req);
    }

    /**
     * 通过微信给用户发一个消息
     * @param sendReq
     * @return
     */
    public MessageResp sendAMessageByWx(WxMessageSendReq sendReq){
        Result result=wxMiniService.getMiniAccessToken();
        if(ResultUtils.OK==result.getCode()){
            String accessToken=result.getData().toString();
            Map<String,String> param= JsonUtils.toObject(JsonUtils.toJson(sendReq),Map.class);
            String resp=clientService.httpsPost(sendWxMessageUrl+accessToken,param);
            return JsonUtils.toObject(resp,MessageResp.class);
        }
        return null;
    }

}
