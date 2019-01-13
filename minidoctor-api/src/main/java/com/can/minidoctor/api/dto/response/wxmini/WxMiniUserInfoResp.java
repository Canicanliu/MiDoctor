package com.can.minidoctor.api.dto.response.wxmini;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 13:41 2019/1/13
 */
public class WxMiniUserInfoResp implements Serializable {

    /**
     * 小程序openid
     */
    private String openId ;

    /**
     * 用户昵称
     */
    private String nickName	;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表132*132正方形头像），
     * 用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String avatarUrl ;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int gender ;

    /**
     * 用户所在城市
     */
    private String city ;

    /**
     * 用户所在省份
     */
    private String province	;

    /**
     * 用户所在国家
     */
    private String country ;

    /**
     * unionId
     */
    private String unionId ;

    /**
     * 数据水印
     * watermark参数说明:
     * appid	String	敏感数据归属appid
     * timestamp Long	敏感数据获取的时间戳
     */
    private Map<String,Object> watermark ;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Map<String, Object> getWatermark() {
        return watermark;
    }

    public void setWatermark(Map<String, Object> watermark) {
        this.watermark = watermark;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
