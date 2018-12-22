package com.can.minidoctor.core.config.http;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 9:41 2018/12/8
 */
public class CanResponseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        httpResponse.getEntity();
    }
}
