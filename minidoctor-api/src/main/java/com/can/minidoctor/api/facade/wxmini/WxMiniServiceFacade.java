package com.can.minidoctor.api.facade.wxmini;

import com.can.minidoctor.api.commons.base.Result;

public interface WxMiniServiceFacade {
    public Result doMiniLogin(String code);

    public Result isSessionValid(String sessionId);
}
