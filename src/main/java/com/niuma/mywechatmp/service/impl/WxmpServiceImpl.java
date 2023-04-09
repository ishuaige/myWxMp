package com.niuma.mywechatmp.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.niuma.mywechatmp.common.ErrorCode;
import com.niuma.mywechatmp.exception.BusinessException;
import com.niuma.mywechatmp.service.WxmpService;
import com.niuma.mywechatmp.utils.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niuma
 * @create 2023-04-06 21:36
 */
@Service
@Slf4j
public class WxmpServiceImpl implements WxmpService {

    @Override
    public String getAccessToken() {
        //拼接请求地址
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://api.weixin.qq.com/cgi-bin/token");
        buffer.append("?grant_type=client_credential");
        buffer.append("&appid=%s");
        buffer.append("&secret=%s");
        //设置路径参数
        String url = String.format(buffer.toString(),
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //get请求
        try {
            String tokenString = HttpRequest.get(url).execute().body();
            //获取access_token
            Map map = JSONUtil.toBean(tokenString, Map.class);
            String accessToken = (String) map.get("access_token");
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取access_token错误！");
        }
    }

    @Override
    public void sendAllMsg(String text) {
        String accessToken = this.getAccessToken();
        String reqUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=" + accessToken;
        Map<String, Object> param = new HashMap<>();
        param.put("msgtype", "text");

        Map<String, Object> content = new HashMap<>();
        content.put("content", text);
        param.put("text", content);

        Map<String, Object> filter = new HashMap<>();
        filter.put("is_to_all", true);
        filter.put("tag_id", "");
        param.put("filter", filter);

        String json = JSONUtil.toJsonStr(param);
        String body = HttpRequest.post(reqUrl)
                .body(json)
                .execute()
                .body();

        log.info("群发消息返回：{}", body);

    }


}
