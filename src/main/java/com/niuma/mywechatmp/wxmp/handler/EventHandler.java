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

import static com.niuma.mywechatmp.wxmp.WxMpConstant.CLICK_COURSE_KEY;
import static com.niuma.mywechatmp.wxmp.WxMpConstant.CLICK_HOMEWORK_KEY;

/**
 * 事件处理器
 **/
@Component
public class EventHandler implements WxMpMessageHandler {


    @Resource
    MsgBuilderRegister msgBuilderRegister;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        String eventKey = wxMpXmlMessage.getEventKey();
        String content = "";
        MsgBuilder msgBuilder;
        switch (eventKey) {
            case CLICK_COURSE_KEY:
                msgBuilder = msgBuilderRegister.getMsgBuilderByType(MsgKeyEnum.COURSE.getValue());
                content = msgBuilder.buildMsg("20计算机科学与技术1班");
                break;
            case CLICK_HOMEWORK_KEY:
                msgBuilder = msgBuilderRegister.getMsgBuilderByType(MsgKeyEnum.HOMEWORK.getValue());
                content = msgBuilder.buildMsg("20计算机科学与技术1班");
                break;
        }

        // 调用接口，返回验证码
        return WxMpXmlOutMessage.TEXT().content(content)
                .fromUser(wxMpXmlMessage.getToUser())
                .toUser(wxMpXmlMessage.getFromUser())
                .build();
    }
}
