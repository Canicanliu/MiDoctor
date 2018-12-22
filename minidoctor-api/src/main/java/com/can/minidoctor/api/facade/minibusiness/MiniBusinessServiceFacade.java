package com.can.minidoctor.api.facade.minibusiness;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.DateDetailReq;
import com.can.minidoctor.api.dto.request.miniwx.GetDateListReq;
import com.can.minidoctor.api.dto.request.miniwx.MakeAnArrangeReq;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 20:59 2018/12/18
 */
public interface MiniBusinessServiceFacade {

    public Result makeAnArrangeMemt(MakeAnArrangeReq req);

    public Result getFutureArrange(int hospital);

    public Result getMyDates(GetDateListReq req);

    public Result getDateDetail(DateDetailReq req);

}
