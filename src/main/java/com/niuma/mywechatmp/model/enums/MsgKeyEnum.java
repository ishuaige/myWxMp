package com.niuma.mywechatmp.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author niuma
 * @create 2023-04-08 14:32
 */
public enum MsgKeyEnum {
    COURSE("获取今日课程","1"),
    HOMEWORK("获取作业信息","2"),
    OTHER("其他","-1");

    private final String text;
    private final String value;
    MsgKeyEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }
    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static MsgKeyEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (MsgKeyEnum anEnum : MsgKeyEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
