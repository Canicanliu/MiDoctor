package com.can.minidoctor.core.service.minibusiness.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.DateDetailReq;
import com.can.minidoctor.api.dto.request.miniwx.FutureArrangReq;
import com.can.minidoctor.api.dto.request.miniwx.GetDateListReq;
import com.can.minidoctor.api.dto.request.miniwx.MakeAnArrangeReq;

import com.can.minidoctor.api.facade.minibusiness.MiniBusinessServiceFacade;
import com.can.minidoctor.core.service.minibusiness.MiniBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 21:10 2018/12/18
 */
@Component
@Service(version = "1.0.0",interfaceClass = MiniBusinessServiceFacade.class)
public class MiniBusinessServiceImpl implements MiniBusinessServiceFacade {
    @Autowired
    MiniBusinessService miniBusinessService;

    @Override
    @LogParams("在线预约")
    public Result makeAnArrangeMemt(MakeAnArrangeReq req) {
        return miniBusinessService.makeAnArrangeMemt(req);
    }

    @Override
    @LogParams("获取排班")
    public Result getFutureArrange(FutureArrangReq hospital) {
        return miniBusinessService.getFutureArrange(hospital);
    }

    @Override
    @LogParams("获取个人预约")
    public Result getMyDates(GetDateListReq req) {
        return miniBusinessService.getMyDates(req);
    }

    @Override
    @LogParams("获取预约详情")
    public Result getDateDetail(DateDetailReq req) {
        return miniBusinessService.getDateDetail(req);
    }
}
