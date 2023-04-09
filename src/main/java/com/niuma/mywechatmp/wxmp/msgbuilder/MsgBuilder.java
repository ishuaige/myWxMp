package com.niuma.mywechatmp.wxmp.msgbuilder;

/**
 * @author niuma
 * @create 2023-04-08 14:21
 */
public interface MsgBuilder {
    /**
     * 构建消息
     * @param className
     * @return
     */
    String buildMsg(String className);
}
