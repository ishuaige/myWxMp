package com.niuma.mywechatmp.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类，读取配置文件application.properties中的配置
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${wx.mp.app-id}")
    private String appid;

    @Value("${wx.mp.secret}")
    private String appsecret;

    @Value("${wx.mp.token}")
    private String token;

    @Value("${wx.mp.aes-key}")
    private String aes_key;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String TOKEN;
    public static String AES_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = appid;
        ACCESS_KEY_SECRET = appsecret;
        TOKEN = token;
        AES_KEY = aes_key;
    }
}
