package com.can.minidoctor.core.config.http;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 9:31 2018/12/8
 */
public class CanRequestInterceptor implements HttpRequestInterceptor {

    private static final Logger LOGGER= LoggerFactory.getLogger(CanRequestInterceptor.class);

    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        try {

           LOGGER.info("http:",httpRequest.getRequestLine());


        } catch (Exception e) {
            LOGGER.error("初始化Request Header发生异常", e);
        }

    }
}
