package com.can.minidoctor.web.config;


import com.can.minidoctor.web.interceptor.WxAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by Tianjin Su on 2018-06-12
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public WxAuthInterceptor wxAuthenticationInterceptor(){
        return new WxAuthInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(wxAuthenticationInterceptor()).addPathPatterns("/wx/**")
                .excludePathPatterns("/wx/mini/doLogin");
        super.addInterceptors(registry);
    }

}
