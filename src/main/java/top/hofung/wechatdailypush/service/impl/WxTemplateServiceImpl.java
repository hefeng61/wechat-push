package top.hofung.wechatdailypush.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.hofung.wechatdailypush.config.WxConfig;
import top.hofung.wechatdailypush.domain.LiveWeather;
import top.hofung.wechatdailypush.service.WxTemplateService;
import top.hofung.wechatdailypush.util.EssayUtil;
import top.hofung.wechatdailypush.util.WeahterUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxTemplateServiceImpl implements WxTemplateService {

    @Resource
    private WxConfig config;

    @Resource
    WxMpService wxMpService;

    @Override
    @Scheduled(cron = "0 40 8 * * ? ")
    public void sendTemplate() {
        try {
            String s1 = wxMpService.getTemplateMsgService().sendTemplateMsg(this.wxMpTemplateMessage("oAHa45s8Tn4as8dkm3XuHgo99u24", "盼盼", "101200905"));
            System.out.println("盼盼--->" + s1);
            String s2 = wxMpService.getTemplateMsgService().sendTemplateMsg(this.wxMpTemplateMessage("oAHa45va5Dx3hQ5xKYfCTjvHOdG0", "大佬", "101200905"));
            System.out.println("大佬--->" + s2);
            String s3 = wxMpService.getTemplateMsgService().sendTemplateMsg(this.wxMpTemplateMessage("oAHa45ur28eiWIdRjCv13sUzIZkY", "成哥", "101200113"));
            System.out.println("成哥--->" + s3);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

    }

    public WxMpTemplateMessage wxMpTemplateMessage(String openId, String name, String adcode) {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(openId)
                .templateId(config.getTemplateId())
                .url("https://api.dujin.org/bing/m.php")
                .build();

        getWeatherTemplate(templateMessage, name, adcode);
        return templateMessage;
    }

    private void getWeatherTemplate(WxMpTemplateMessage templateMessage, String name, String adcode) {
        LiveWeather weather = WeahterUtil.getLiveWeatherInfo(adcode);
        List<String> list = EssayUtil.getEssay();
        templateMessage.addData(new WxMpTemplateData("name", name, "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("fxDate", weather.getFxDate(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("sunrise", weather.getSunrise(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("sunset", weather.getSunset(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("moonrise", weather.getMoonrise(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("moonset", weather.getMoonset(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("moonPhase", weather.getMoonPhase(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tempMax", weather.getTempMax(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tempMin", weather.getTempMin(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("textDay", weather.getTextDay(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("textNight", weather.getTextNight(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("windDirDay", weather.getWindDirDay(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("windScaleDay", weather.getWindScaleDay(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("humidity", weather.getHumidity(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("uvIndex", weather.getUvIndex(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("essay", list.get(0)));
    }

}
