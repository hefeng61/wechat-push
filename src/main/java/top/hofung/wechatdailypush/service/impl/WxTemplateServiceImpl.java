package top.hofung.wechatdailypush.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Service;
import top.hofung.wechatdailypush.domain.LiveWeather;
import top.hofung.wechatdailypush.service.WxTemplateService;
import top.hofung.wechatdailypush.util.EssayUtil;
import top.hofung.wechatdailypush.util.WeahterUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WxTemplateServiceImpl implements WxTemplateService {
    public static final String ACCESS_TOKEN = "access_token";

    private static final String ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String TEMPLATE_PUSH_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    private static final String APPID = "wxfbf0951089dbb3d3";

    private static final String SECRET = "af9e4d84d7e690c027ab0ba2ed8b8773";

    private static final String OPENID = "oAHa45va5Dx3hQ5xKYfCTjvHOdG0";

//    private static final String OPENID = "oAHa45ur28eiWIdRjCv13sUzIZkY";

    public static final String TEMPLATE_ID = "_9uFKE24cDr0nJ6khEDM5pNfwJbNBwY1H6HVdbRhJIY";

    @Override
    public void sendTemplate() {

        //1，配置
        WxMpDefaultConfigImpl wxStorage = new WxMpDefaultConfigImpl();
        wxStorage.setAppId(APPID);
        wxStorage.setSecret(SECRET);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        try {
            String s = wxMpService.getTemplateMsgService().sendTemplateMsg(this.wxMpTemplateMessage());
            System.out.println(s);
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 本地缓存
     *
     * @return
     */
    public Cache<String, Object> cache() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .expireAfterWrite(7200, TimeUnit.SECONDS)
                .build();
    }

    public WxMpTemplateMessage wxMpTemplateMessage() {
        LiveWeather weather = WeahterUtil.getLiveWeatherInfo();
        List<String> list = EssayUtil.getEssay();
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(OPENID)
                .templateId(TEMPLATE_ID)
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
