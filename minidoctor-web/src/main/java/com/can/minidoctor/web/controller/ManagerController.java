package com.can.minidoctor.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.dto.request.miniwx.MakeAnArrangeReq;
import com.can.minidoctor.api.facade.minibusiness.MiniBusinessServiceFacade;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:36 2019/1/4
 */
@RestController
@RequestMapping("/admin/business")
public class ManagerController {

    @Reference(version = "1.0.0")
    MiniBusinessServiceFacade miniBusinessServiceFacade;

    @RequestMapping("/initData")
    @LogParams("在线预约")
    public Object initData(){
        return miniBusinessServiceFacade.iniData();
    }
}
