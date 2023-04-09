package com.niuma.mywechatmp.wxmp.handler;

import com.niuma.mywechatmp.model.enums.MsgKeyEnum;
import com.niuma.mywechatmp.wxmp.msgbuilder.MsgBuilderRegister;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 消息处理器
 **/
@Component
@Slf4j
public class MessageHandler implements WxMpMessageHandler {

    @Resource
    MsgBuilderRegister msgBuilderRegister;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        String content = wxMpXmlMessage.getContent();
        String res;
        MsgKeyEnum enumByValue = MsgKeyEnum.getEnumByValue(content);
        if (enumByValue == null) {
            log.info("用户：{}，获取信息：{}", wxMpXmlMessage.getFromUser(), MsgKeyEnum.OTHER.getText());
            res = msgBuilderRegister.getMsgBuilderByType(MsgKeyEnum.OTHER.getValue()).buildMsg("");
        } else {
            log.info("用户：{}，获取信息：{}", wxMpXmlMessage.getFromUser(), enumByValue.getText());
            res = msgBuilderRegister.getMsgBuilderByType(enumByValue.getValue()).buildMsg("20计算机科学与技术1班");
        }

        return WxMpXmlOutMessage.TEXT().content(res)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
    }
}
