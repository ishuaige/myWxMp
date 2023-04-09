package com.niuma.mywechatmp.controller;

import com.niuma.mywechatmp.common.ErrorCode;
import com.niuma.mywechatmp.exception.BusinessException;
import com.niuma.mywechatmp.wxmp.WxMpConstant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号相关接口
 *
 * @author niumazlb
 */
@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin
public class WxMpController {

    @Resource
    private WxMpService wxMpService;

    @Resource
    private WxMpMessageRouter router;

    /**
     * 接收微信发来的消息
     * @param request
     * @param response
     * @param requestBody
     * @return
     */
    @PostMapping("/")
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody String requestBody) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // 校验消息签名，判断是否为公众平台发的消息
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "非法的请求！");
        }
        // 加密类型
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
                : request.getParameter("encrypt_type");
        String out = null;
        // 明文消息
        if ("raw".equals(encryptType)) {
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            log.info("message content = {}", inMessage.getContent());
            // 路由消息并处理
            WxMpXmlOutMessage outMessage = router.route(inMessage);
            if (outMessage == null) {
                return "";
            } else {
                out = outMessage.toXml();
            }
        }
        // aes 加密消息
        if ("aes".equals(encryptType)) {
            // 解密消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage
                    .fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(), timestamp,
                            nonce,
                            msgSignature);
            log.info("message content = {}", inMessage.getContent());
            // 路由消息并处理
            WxMpXmlOutMessage outMessage = router.route(inMessage);
            if (outMessage == null) {
                return "";
            } else {
                out = outMessage.toXml();
            }
        }
        log.info("\n组装回复信息：{}", out);
        return out;
    }

    @GetMapping
    public String check(String timestamp, String nonce, String signature, String echostr) {
        log.info("check,timestamp:{},nonce:{},signature:{},echostr:{}", timestamp, nonce, signature, echostr);
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            log.info("check success,echostr:{}", echostr);
            return echostr;
        } else {
            return "";
        }
    }


    /**
     * 设置公众号菜单
     *
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/setMenu")
    public String setMenu() throws WxErrorException {
        log.info("setMenu");
        WxMenu wxMenu = new WxMenu();
        // 菜单一
        WxMenuButton wxMenuButton1 = new WxMenuButton();
        wxMenuButton1.setType(MenuButtonType.CLICK);
        wxMenuButton1.setName("今日课程");
        wxMenuButton1.setKey(WxMpConstant.CLICK_COURSE_KEY);


        // 菜单二
        WxMenuButton wxMenuButton2 = new WxMenuButton();
        wxMenuButton2.setType(MenuButtonType.CLICK);
        wxMenuButton2.setName("作业");
        wxMenuButton2.setKey(WxMpConstant.CLICK_HOMEWORK_KEY);

        wxMenuButton2.setUrl();
        // 设置主菜单
        wxMenu.setButtons(Arrays.asList(wxMenuButton1, wxMenuButton2));
        wxMpService.getMenuService().menuCreate(wxMenu);
        return "ok";
    }

    private Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();
        InputStream inputStream = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

}
