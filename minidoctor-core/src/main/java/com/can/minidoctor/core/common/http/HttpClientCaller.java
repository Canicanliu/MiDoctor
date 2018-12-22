package com.can.minidoctor.core.common.http;

import org.apache.commons.codec.Charsets;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.cache.HttpCacheContext;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 9:54 2018/12/8
 */
@Component
public class HttpClientCaller {

    private static final Logger LOGGER= LoggerFactory.getLogger(HttpClientCaller.class);

    @Autowired
    CloseableHttpClient client;

    String execute(String path){
        HttpResponse response = null;
        HttpGet httpGet = new HttpGet(path);
        try {
            LOGGER.info("httpGet，请求:{}",path);
            response=client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity,Charsets.UTF_8.name());
            LOGGER.info("httpGet,返回:{}",content);
            return content;
        } catch (IOException e) {
            LOGGER.error("get请求异常",e);
            return null;
        }finally {
            httpGet.releaseConnection();
        }
    }

    byte[] execute(final HttpHost host, final HttpRequest request, final HttpContext context) throws IOException {

        CloseableHttpResponse response = null;
        HttpCacheContext cacheContext = HttpCacheContext.adapt(context);
        try {

            response = client.execute(host, request, cacheContext);
            if(response!=null){
                if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
                    LOGGER.error("["+request.getRequestLine().getUri()+"]-STATUS CODE："+response.getStatusLine().getStatusCode());
                    if(response.getEntity()!=null){
                        LOGGER.error("["+request.getRequestLine().getUri()+"]-RESPONSE："+new String(IOUtils.toByteArray(response.getEntity().getContent()), Charsets.UTF_8));
                        if(request.getRequestLine().getUri()!=null&&request.getRequestLine().getUri().contains("api.weibo.com")){
                            return IOUtils.toByteArray(response.getEntity().getContent());
                        }
                    }else{
                        LOGGER.error("["+request.getRequestLine().getUri()+"]-RESPONSE ENTITY NULL");
                    }
                }
            }else{
                LOGGER.error("["+request.getRequestLine().getUri()+"]-RESPONSE NULL");
            }
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // connected to host, start reading...
                HttpEntity entity = response.getEntity();
                if (entity.getContentEncoding() == null) {
                    // responses without useful info
                    LOGGER.trace("response not zipped...");
                    return IOUtils.toByteArray(entity.getContent());
                }
                // responses with contents have the 'Content-Encoding' header
                if (entity.getContentEncoding().getValue().equalsIgnoreCase("gzip")) {
                    LOGGER.trace("response zipped, start unzipping...");
                    InputStream is = new GzipDecompressingEntity(entity).getContent();
                    return IOUtils.toByteArray(is);
                }
            }
        } catch (IllegalStateException e) {
            LOGGER.error("state illegal:" + e.getMessage(),e);
        } catch (ClientProtocolException e) {
            LOGGER.error("wrong protocol!",e);
        } catch (IOException e) {
            LOGGER.error("error on I/O:", e);
        } catch (Exception e){
            LOGGER.error("error:", e);
        }
        finally {
            LOGGER.trace("closing connection");
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        throw new IOException("unable to get response from host correctly");
    }

}
