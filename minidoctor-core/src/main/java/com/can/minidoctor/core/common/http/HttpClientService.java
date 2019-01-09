package com.can.minidoctor.core.common.http;

import com.can.minidoctor.api.utils.JsonUtils;
import org.apache.commons.codec.Charsets;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 10:16 2018/12/8
 */
@Component
public class HttpClientService {

    private static final Logger LOGGER= LoggerFactory.getLogger(HttpClientService.class);

    @Autowired
    HttpClientCaller caller;

    public String httpGet(String path, Map<String, String> params) {
        try {

            if(null!=params&&!params.isEmpty()){
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(path);
                for(Map.Entry entry:params.entrySet()){
                    stringBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                path=stringBuffer.toString();
            }

            return caller.execute(path);
        }catch (Exception e){
            LOGGER.error("请求:{},{}异常",path,e);
        }
        return null;
    }

    public String httpsPost(String path, Map<String,String> param) {
        try{
            LOGGER.info("post请求:{},参数:{}",path, JsonUtils.toJson(param));
            HttpPost httpPost = new HttpPost(path);
            HttpHost host = new HttpHost(httpPost.getURI().getHost(), 443,"https");
            String result=new String(post(path,param,host),Charsets.UTF_8.name());
            LOGGER.info("post请求:{},结果:{}",path, result);
            return result;
        }catch (Exception e){
            LOGGER.error("请求{}异常,参数:{}",path, JsonUtils.toJson(param),e);
            return null;
        }
    }

    public String httpPost(String path, Map<String,String> param) throws IOException {
        HttpPost httpPost = new HttpPost(path);
        HttpHost host = new HttpHost(httpPost.getURI().getHost(), httpPost.getURI().getPort());
        return new String(post(path,param,host),Charsets.UTF_8.name());
    }

    private byte[] post(String path, Map<String,String> param,HttpHost host) throws IOException {
        HttpPost httpPost = new HttpPost(path);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Cache-Control", "max-age=0");
        httpPost.addHeader("Connection", "close");
        httpPost.addHeader("X-Req-Id", MDC.get("REQUEST_ID") );
        if(param.size()>0){
            // 设置参数编码字符集
            Charset charset = null;
            String encoding = (String)param.get("charset");
            if (encoding != null) {
                charset = Charset.forName(encoding);
            }

            StringEntity jsonParams = new StringEntity(JsonUtils.toJson(param),charset!=null?charset: Consts.UTF_8);
            httpPost.setEntity(jsonParams);
        }
        return caller.execute(host, httpPost, HttpClientContext.create());
    }
}
