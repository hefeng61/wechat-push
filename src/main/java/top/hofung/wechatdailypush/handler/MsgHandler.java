package top.hofung.wechatdailypush.handler;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;
import top.hofung.wechatdailypush.builder.ImgBuilder;
import top.hofung.wechatdailypush.builder.TextBuilder;

import java.util.Map;

@Slf4j
@Component
public class MsgHandler implements WxMpMessageHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        String msgType = wxMpXmlMessage.getMsgType();
        System.out.println(msgType);
        log.info("当前收到的消息类型是-----------------------》" + msgType);
        log.info("收到信息内容：" + wxMpXmlMessage);
        String content = "收到信息内容：" + JSONUtil.toJsonStr(wxMpXmlMessage);
        System.out.println(content);
        if (WxConsts.XmlMsgType.TEXT.equals(msgType)) {
            return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
        } else if (WxConsts.XmlMsgType.IMAGE.equals(msgType)) {
            return new ImgBuilder().build(content, wxMpXmlMessage, wxMpService);
        }
        return null;
    }
}
