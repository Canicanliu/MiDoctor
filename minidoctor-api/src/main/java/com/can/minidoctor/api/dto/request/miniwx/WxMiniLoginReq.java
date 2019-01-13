package com.can.minidoctor.api.dto.request.miniwx;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:00 2019/1/13
 */
public class WxMiniLoginReq implements Serializable {
    /**
     * 临时登录凭证
     */
    @NotNull
    private String code ;
    /**
     * 用户敏感信息 ，包括了微信小程序获取的完整用户信息  -- MiniprogramUserInfo
     */
    private String encryptedData ;

    /**
     * 解密算法的向量
     */
    private String iv ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
