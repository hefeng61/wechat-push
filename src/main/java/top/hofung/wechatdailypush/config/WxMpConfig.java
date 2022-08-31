package top.hofung.wechatdailypush.config;


import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.hofung.wechatdailypush.handler.MsgHandler;

import javax.annotation.Resource;

@Configuration
public class WxMpConfig {

    @Resource
    private WxConfig wxConfig;

    @Resource
    private MsgHandler msgHandler;


    @Bean
    public WxMpService wxMpService() {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(wxConfig.getAppId());
        config.setSecret(wxConfig.getSecret());
        config.setToken(wxConfig.getToken());
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter messageRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
        newRouter.rule().async(false).handler(this.msgHandler).end();
        return newRouter;
    }

}
