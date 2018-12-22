package com.can.minidoctor.core.config.http;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 9:25 2018/12/8
 */
@Configuration
public class HttpClientConfig {

    @Bean
    public CloseableHttpClient getReqConfig(){
        Builder builder=RequestConfig.custom();
        builder.setSocketTimeout(10000);
        builder.setConnectTimeout(5000);
        builder.setConnectionRequestTimeout(500);
        RequestConfig requestConfig=builder.build();
        HttpClientBuilder hcBuilder=HttpClientBuilder.create();
        hcBuilder.setDefaultRequestConfig(requestConfig);

        CanRequestInterceptor requestInter=new CanRequestInterceptor();
        hcBuilder.addInterceptorLast(requestInter);
        CanResponseInterceptor responeInter=new CanResponseInterceptor();
        hcBuilder.addInterceptorLast(responeInter);


        PoolingHttpClientConnectionManager conmgr=new PoolingHttpClientConnectionManager();
        conmgr.setMaxTotal(1000);
        conmgr.setDefaultMaxPerRoute(600);
        hcBuilder.setConnectionManager(conmgr);


        return hcBuilder.build();

    }
}
