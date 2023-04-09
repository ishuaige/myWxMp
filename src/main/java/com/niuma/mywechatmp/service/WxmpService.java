package com.niuma.mywechatmp.service;

/**
 * @author niuma
 * @create 2023-04-06 21:36
 */
public interface WxmpService {
    /**
     * 获取AccessToken
     *
     * @return
     */
    String getAccessToken();

    /**
     * 群发消息
     *
     * @param text
     */
    void sendAllMsg(String text);
}
