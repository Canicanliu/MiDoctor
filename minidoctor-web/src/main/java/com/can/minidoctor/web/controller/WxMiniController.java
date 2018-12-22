package com.can.minidoctor.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.facade.wxmini.WxMiniServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/mini")
public class WxMiniController {
    @Reference(version = "1.0.0")
    WxMiniServiceFacade wxMiniService;

    @RequestMapping("/doLogin")
    @LogParams("登录")
    public Object doLogin(String code){
        return wxMiniService.doMiniLogin(code);
    }

}
