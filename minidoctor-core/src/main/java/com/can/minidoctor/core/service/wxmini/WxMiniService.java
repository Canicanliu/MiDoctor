package com.can.minidoctor.core.service.wxmini;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.utils.CanStringUtils;
import com.can.minidoctor.api.utils.DateUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.core.common.http.HttpClientService;
import com.can.minidoctor.core.dao.mapper.MinidoctorArrangementMapper;
import com.can.minidoctor.api.dto.response.ArrangementResp;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import com.can.minidoctor.core.entity.MinidoctorArrangementExample;
import com.can.minidoctor.core.enums.DigitalEnums;
import com.can.minidoctor.core.service.common.JedisClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:47 2018/12/8
 */
@Component
public class WxMiniService {
    private static final Logger LOGGER= LoggerFactory.getLogger(WxMiniService.class);
    @Value("${wx.miniprogram.loginUrl}")
    String loginUrl;
    @Value("${wx.miniprogram.appId}")
    String appId;
    @Value("${wx.miniprogram.appSecret}")
    String appSecret;
    @Autowired
    HttpClientService clientService;
//    @Autowired
//    MiniArrangeMentDao miniArrangeMentDao;
    @Autowired
    MinidoctorArrangementMapper mapper;
    @Autowired
    JedisClusterService jedisClusterService;


    public Result doMiniLogin(String code) {
        Map<String,String> param=new HashMap<>();
        param.put("appid",appId);
        param.put("secret",appSecret);
        param.put("js_code",code);
        String wxResp=clientService.httpGet(loginUrl,param);
        LOGGER.info("wx:{}",wxResp);
        String key= CanStringUtils.getRandomStr();
        jedisClusterService.saveOrUpdate(key,wxResp);
        String value=jedisClusterService.get(key);
        return ResultUtils.getOkResult(key);
    }



}
