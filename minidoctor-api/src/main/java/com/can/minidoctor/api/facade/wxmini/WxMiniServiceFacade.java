package com.can.minidoctor.api.facade.wxmini;

import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.WxMiniLoginReq;

public interface WxMiniServiceFacade {
    public Result doMiniLogin(WxMiniLoginReq req);

    public Result isSessionValid(String sessionId);
}
