package com.can.minidoctor.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.dto.request.miniwx.DateDetailReq;
import com.can.minidoctor.api.dto.request.miniwx.GetDateListReq;
import com.can.minidoctor.api.dto.request.miniwx.MakeAnArrangeReq;
import com.can.minidoctor.api.facade.minibusiness.MiniBusinessServiceFacade;
import com.can.minidoctor.api.facade.wxmini.WxMiniServiceFacade;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:53 2018/12/18
 */
@RestController
@RequestMapping("/wx/business")
public class MiniBusinessController {

    @Reference(version = "1.0.0")
    MiniBusinessServiceFacade miniBusinessServiceFacade;


    @RequestMapping("/makeADate")
    @LogParams("在线预约")
    public Object makeAnArrangeMemt(@RequestBody MakeAnArrangeReq req){

        return miniBusinessServiceFacade.makeAnArrangeMemt(req);
    }

    @RequestMapping("/getMyDatements")
    @LogParams("获取我的预约")
    public Object getMyDates(@RequestBody GetDateListReq req){
        return miniBusinessServiceFacade.getMyDates(req);
    }

    @RequestMapping("/getDateDetail")
    @LogParams("获取预约详情")
    public Object getDateDetail(@RequestBody DateDetailReq req){
        return miniBusinessServiceFacade.getDateDetail(req);
    }

    @RequestMapping("/getFutureArrange")
    @LogParams("获取排班")
    public Object getFutureArrange(int hospital){
        return miniBusinessServiceFacade.getFutureArrange(hospital);
    }

}
