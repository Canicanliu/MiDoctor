package com.can.minidoctor.core.service.wxmini.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.WxMiniLoginReq;
import com.can.minidoctor.api.dto.response.wxmini.WxMiniLoginResp;
import com.can.minidoctor.api.facade.wxmini.WxMiniServiceFacade;
import com.can.minidoctor.api.utils.CanStringUtils;
import com.can.minidoctor.api.utils.JsonUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.core.service.common.JedisClusterService;
import com.can.minidoctor.core.service.wxmini.WxMiniService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",interfaceClass = WxMiniServiceFacade.class)
public class WxMiniServiceFacadeImpl implements WxMiniServiceFacade {
    private static final Logger LOGGER= LoggerFactory.getLogger(WxMiniServiceFacadeImpl.class);
    @Autowired
    WxMiniService wxMiniService;
    @Autowired
    JedisClusterService jedisClusterService;

    @Override
    @LogParams("登录")
    public Result doMiniLogin(WxMiniLoginReq req) {
        return wxMiniService.doMiniLogin(req);
    }



    @Override
    public Result isSessionValid(String sessionId) {
        if(!CanStringUtils.nullOrEmpty(sessionId)){
            String value=jedisClusterService.get(sessionId);
            if(!CanStringUtils.nullOrEmpty(value)){
                LOGGER.info("current user:{}",value);

                return ResultUtils.getOkResult(value);
            }
        }
        return ResultUtils.getFailedResult(8888,"不合法的sessionId");
    }

}
