package com.niuma.mywechatmp.wxmp.msgbuilder;

import com.niuma.mywechatmp.model.enums.MsgKeyEnum;
import org.springframework.stereotype.Component;

/**
 * @author niuma
 * @create 2023-04-08 14:53
 */
@Component
public class OtherMsgBuilder implements MsgBuilder{

    @Override
    public String buildMsg(String className) {
        StringBuilder res = new StringBuilder();

        res.append("请输入以下关键数字获取数据").append("\n");
        MsgKeyEnum[] values = MsgKeyEnum.values();

        for (MsgKeyEnum value : values) {
            if(value.getValue().equals(MsgKeyEnum.OTHER.getValue())){
                continue;
            }
            res.append("["+value.getValue()+"] "+ value.getText()).append("\n");
        }
        return res.toString();
    }
}
