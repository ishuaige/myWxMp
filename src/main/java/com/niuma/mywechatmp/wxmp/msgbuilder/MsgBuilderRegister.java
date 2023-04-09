package com.niuma.mywechatmp.wxmp.msgbuilder;

import com.niuma.mywechatmp.model.enums.MsgKeyEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author niuma
 * @create 2023-04-08 14:42
 */
@Component
public class MsgBuilderRegister {
    @Resource
    CourseMsgBuilder courseMsgBuilder;
    @Resource
    HomeWorkMsgBuilder homeWorkMsgBuilder;
    @Resource
    OtherMsgBuilder otherMsgBuilder;

    Map<String, MsgBuilder> typeMsgBuilderMap;

   public MsgBuilder getMsgBuilderByType(String type) {
        if (typeMsgBuilderMap == null) {
            return null;
        }
        return typeMsgBuilderMap.get(type);
    }

    @PostConstruct
    public void doInit() {
        typeMsgBuilderMap = new HashMap() {
            {
                put(MsgKeyEnum.COURSE.getValue(), courseMsgBuilder);
                put(MsgKeyEnum.HOMEWORK.getValue(), homeWorkMsgBuilder);
                put(MsgKeyEnum.OTHER.getValue(), otherMsgBuilder);
            }
        };
    }

}
