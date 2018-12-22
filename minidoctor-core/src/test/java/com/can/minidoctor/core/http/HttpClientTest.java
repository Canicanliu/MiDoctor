package com.can.minidoctor.core.http;

import com.can.minidoctor.core.BasicTest;
import com.can.minidoctor.core.common.http.HttpClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 11:04 2018/12/8
 */
public class HttpClientTest extends BasicTest {
    @Autowired
    HttpClientService httpClientService;

    @Test
    public void testGet(){
        try {
            String rep=httpClientService.httpGet("http://www.baidu.com", null);
            logger.info(rep);
        }catch (Exception e){
            logger.error("ssss",e);
        }
    }

}
