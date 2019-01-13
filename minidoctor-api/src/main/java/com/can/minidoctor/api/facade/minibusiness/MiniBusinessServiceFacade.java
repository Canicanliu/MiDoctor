package com.can.minidoctor.api.facade.minibusiness;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.BaseDto;
import com.can.minidoctor.api.dto.request.miniwx.*;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:59 2018/12/18
 */
public interface MiniBusinessServiceFacade {

    public Result makeAnArrangeMemt(MakeAnArrangeReq req);

    public Result cancelAnArrangeMemt(CancelDatementReq req);

    public Result getFutureArrange(FutureArrangReq hospital);

    public Result getMyDates(GetDateListReq req);

    public Result getDateDetail(DateDetailReq req);

    public Result iniData();

    public Result getUserInfo(BaseDto req);

}
