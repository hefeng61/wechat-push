package top.hofung.wechatdailypush.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.hofung.wechatdailypush.WxConfig;
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

    @Override
    @Scheduled(cron = "0 0 8 * * ? ")
    public void sendTemplate() {

        //1，配置
        WxMpDefaultConfigImpl wxStorage = new WxMpDefaultConfigImpl();
        wxStorage.setAppId(config.getAppId());
        wxStorage.setSecret(config.getSecret());
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        try {
            String s = wxMpService.getTemplateMsgService().sendTemplateMsg(this.wxMpTemplateMessage());
            System.out.println(s);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

    }

    public WxMpTemplateMessage wxMpTemplateMessage() {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(config.getOpenId())
                .templateId(config.getTemplateId())
                .url("https://api.dujin.org/bing/m.php")
                .build();

        getWeatherTemplate(templateMessage);
        return templateMessage;
    }

    private void getWeatherTemplate(WxMpTemplateMessage templateMessage) {
        LiveWeather weather = WeahterUtil.getLiveWeatherInfo();
        List<String> list = EssayUtil.getEssay();
        templateMessage.addData(new WxMpTemplateData("name", "盼盼", "#00BFFF"));
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
