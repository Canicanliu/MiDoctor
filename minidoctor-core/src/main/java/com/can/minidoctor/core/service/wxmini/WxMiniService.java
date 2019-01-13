package com.can.minidoctor.core.service.wxmini;

import com.can.minidoctor.api.aop.log.LogParams;
import com.can.minidoctor.api.commons.base.CacheConstants;
import com.can.minidoctor.api.commons.base.Constants;
import com.can.minidoctor.api.commons.base.Result;
import com.can.minidoctor.api.dto.request.miniwx.WxMiniLoginReq;
import com.can.minidoctor.api.dto.response.BaseWxResponse;
import com.can.minidoctor.api.dto.response.wxmini.AccessTokenResp;
import com.can.minidoctor.api.dto.response.wxmini.LoginInfoResp;
import com.can.minidoctor.api.dto.response.wxmini.WxMiniLoginResp;
import com.can.minidoctor.api.dto.response.wxmini.WxMiniUserInfoResp;
import com.can.minidoctor.api.utils.*;
import com.can.minidoctor.core.common.http.HttpClientService;
import com.can.minidoctor.core.dao.mapper.MinidoctorArrangementMapper;
import com.can.minidoctor.api.dto.response.ArrangementResp;
import com.can.minidoctor.core.dao.user.MinidoctorUserDao;
import com.can.minidoctor.core.entity.MinidoctorArrangement;
import com.can.minidoctor.core.entity.MinidoctorArrangementExample;
import com.can.minidoctor.core.entity.MinidoctorUser;
import com.can.minidoctor.core.enums.DigitalEnums;
import com.can.minidoctor.core.enums.YesOrNotEnums;
import com.can.minidoctor.core.service.common.JedisClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.*;

import static com.can.minidoctor.api.utils.AesUtils.decrypt;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:47 2018/12/8
 */
@Component
public class WxMiniService {
    private static final Logger LOGGER= LoggerFactory.getLogger(WxMiniService.class);
    @Value("${wx.miniprogram.loginUrl}")
    String loginUrl;
    @Value("${wx.miniprogram.accessTokenUrl}")
    String getAccessTokenUrl;
    @Value("${wx.miniprogram.getWXACodeUnlimit}")
    String getWXACodeUnlimit;
    @Value("${wx.miniprogram.appId}")
    String appId;
    @Value("${wx.miniprogram.appSecret}")
    String appSecret;
    @Autowired
    HttpClientService clientService;
    @Autowired
    MinidoctorArrangementMapper mapper;
    @Autowired
    JedisClusterService jedisClusterService;
    @Autowired
    MinidoctorUserDao minidoctorUserDao;


    public Result doMiniLogin(WxMiniLoginReq req) {
        Map<String,String> param=new HashMap<>();
        param.put("appid",appId);
        param.put("secret",appSecret);
        param.put("js_code",req.getCode());
        String wxResp=clientService.httpGet(loginUrl,param);
        LOGGER.info("wx:{}",wxResp);

        BaseWxResponse baseWxResponse=JsonUtils.toObject(wxResp,BaseWxResponse.class);
        if(null!=baseWxResponse&&ResultUtils.OK!=baseWxResponse.getErrcode()){
            LOGGER.info("微信登录失败",wxResp);
        }

        String key= CanStringUtils.getRandomStr();
        jedisClusterService.saveOrUpdate(key,wxResp);

        WxMiniLoginResp resp=JsonUtils.toObject(wxResp,WxMiniLoginResp.class);
        MinidoctorUser user=minidoctorUserDao.getUserByOpenId(resp.getOpenid());
        if(!CanStringUtils.nullOrEmpty(req.getEncryptedData())){
            String raw= AesUtils.decrypt(req.getEncryptedData(),resp.getSession_key(),req.getIv());
            WxMiniUserInfoResp userInfo=JsonUtils.toObject(raw,WxMiniUserInfoResp.class);
            if(null!=userInfo){
                if(user==null){
                    user=new MinidoctorUser();
                    BeanUtils.copyProperties(userInfo,user);
                    user.setHeadUrl(userInfo.getAvatarUrl());
                    user.setUserRole(YesOrNotEnums.No.getCode());
                    user.setCreateTime(new Date());
                    user.setUpdateTime(new Date());
                    user.setGender((byte)userInfo.getGender());
                    minidoctorUserDao.addUser(user);
                }
            }
        }

        LoginInfoResp loginInfo=new LoginInfoResp();
        loginInfo.setSessionId(key);
        loginInfo.setAdmin(null!=user.getUserRole()?user.getUserRole():0);
        return ResultUtils.getOkResult(loginInfo);
    }

    @LogParams("获取小程序accessToken")
    public String getMiniAccessToken(){

        String value=jedisClusterService.get(CacheConstants.getAccessTokenKey());
        if(!CanStringUtils.nullOrEmpty(value)){
            return value;
        }
        String url=getAccessTokenUrl.replace(Constants.wxAppId,appId);
        url=url.replace(Constants.wxAppSecret,appSecret);
        String resp=clientService.httpGet(url,null);
        if(CanStringUtils.nullOrEmpty(resp)){
            LOGGER.error("获取accesstoken失败");
            return null;
        }
        AccessTokenResp accessTokenResp= JsonUtils.toObject(resp,AccessTokenResp.class);
        //成功
        if(null!=accessTokenResp
                &&!CanStringUtils.nullOrEmpty(accessTokenResp.getAccess_token())){
            jedisClusterService.saveOrUpdate(CacheConstants.getAccessTokenKey(),accessTokenResp.getAccess_token(),5400);
            return accessTokenResp.getAccess_token();
        }else {
            LOGGER.error("获取accesstoken失败");
            return null;
        }

    }

    public BufferedImage getWxaCodeUnlimit(String scene, String page, int width){
        Map param = new HashMap();
        // 参数
        param.put("scene", scene);
        // 小程序存在的页面
        param.put("page", page);
        // 	二维码的宽度
        param.put("width", width);
        // 自动配置线条颜色
        param.put("auto_color", false);
        // 颜色 例如 {"r":"0","g":"0","b":"0"}
        Map<String, String> colorMap = new HashMap();
        colorMap.put("r", "0");
        colorMap.put("g", "0");
        colorMap.put("b", "0");
        param.put("line_color", colorMap);
        // 是否需要透明底色 为true时，生成透明底色的小程序码
        param.put("is_hyaline", false);

        String accessToken=getMiniAccessToken();
        String url=getWXACodeUnlimit+accessToken;
        BufferedImage image=clientService.httpsPostImage(url,param);
        return image;
    }



}
