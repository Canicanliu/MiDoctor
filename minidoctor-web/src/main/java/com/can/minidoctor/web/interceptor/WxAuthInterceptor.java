package com.can.minidoctor.web.interceptor;



import com.alibaba.dubbo.config.annotation.Reference;
import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.response.wxmini.WxMiniLoginResp;
import com.can.minidoctor.api.facade.wxmini.WxMiniServiceFacade;
import com.can.minidoctor.api.utils.JsonUtils;
import com.can.minidoctor.api.utils.MdcUtils;
import com.can.minidoctor.api.utils.ResultUtils;
import com.can.minidoctor.web.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 微信权限拦截器
 * <p>
 * Created by Tianjin Su on 2018-06-12
 **/
public class WxAuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER= LoggerFactory.getLogger(WxAuthInterceptor.class);

	@Reference(version = "1.0.0")
	WxMiniServiceFacade wxMiniService;

    /**
     * 拦截微信请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
		Result ret=wxMiniService.isSessionValid(request.getParameter("sessionId"));
		if(ResultUtils.OK!=ret.getCode()){
			LOGGER.error("session校验不通过");
			return false;
		}else {
			getMetaInfo(request,ret);
		}
        return true;
    }

	private void getMetaInfo(HttpServletRequest request,Result result){
    	Object data=result.getData();
    	if(null!=data){
			WxMiniLoginResp wxMiniLoginResp= JsonUtils.toObject(data.toString(),WxMiniLoginResp.class);
			if(null!=wxMiniLoginResp)
				MdcUtils.put(MdcUtils.OPENID,wxMiniLoginResp.getOpenid());
		}

	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {		
		super.afterCompletion(request, response, handler, ex);
	}


}
