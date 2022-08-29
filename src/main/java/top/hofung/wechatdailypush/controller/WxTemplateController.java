package top.hofung.wechatdailypush.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hofung.wechatdailypush.service.WxTemplateService;

import javax.annotation.Resource;

@RestController
public class WxTemplateController {

    @Resource
    WxTemplateService templateService;

    @GetMapping
    public void send() {
        templateService.sendTemplate();
    }
}
