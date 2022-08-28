package top.hofung.wechatdailypush.service;

import top.hofung.wechatdailypush.domain.WxTemplate;

public interface WxTemplateService {

    void sendTemplate(WxTemplate template);
}
