package com.niuma.mywechatmp.wxmp.handler;

import com.niuma.mywechatmp.model.enums.MsgKeyEnum;
import com.niuma.mywechatmp.wxmp.msgbuilder.MsgBuilder;
import com.niuma.mywechatmp.wxmp.msgbuilder.MsgBuilderRegister;
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
 * 关注处理器
 *
 * @author niumazlb
 */
@Component
public class SubscribeHandler implements WxMpMessageHandler {
    @Resource
    MsgBuilderRegister msgBuilderRegister;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        final String content = "感谢关注";
        MsgBuilder msgBuilder = msgBuilderRegister.getMsgBuilderByType(MsgKeyEnum.OTHER.getValue());
        String msg = msgBuilder.buildMsg("");
        String res = content + "\n" + msg;
        // 调用接口，返回验证码
        return WxMpXmlOutMessage.TEXT().content(res)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
    }
}
