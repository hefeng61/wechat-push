package top.hofung.wechatdailypush.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;
import top.hofung.wechatdailypush.config.WechatConfig;
import top.hofung.wechatdailypush.domain.WxTemplate;
import top.hofung.wechatdailypush.service.WxTemplateService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WxTemplateServiceImpl implements WxTemplateService {
    public static final String ACCESS_TOKEN = "access_token";

    private static final String ACCESS_TOKEN_API = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String TEMPLATE_PUSH_API = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    private static final String APPID = "wxfbf0951089dbb3d3";

    private static final String SECRET = "af9e4d84d7e690c027ab0ba2ed8b8773";

    private static final String openid = "";

    @Override
    public void sendTemplate(WxTemplate template) {
        String accessToken;
        if (cache().getIfPresent(ACCESS_TOKEN) == null) {
            accessToken = getAccessToken();
        } else {
            accessToken = (String) cache().getIfPresent(ACCESS_TOKEN);
        }

//        HttpUtil.post(String.format(wechatConfig.getTemplatePushApi(), accessToken),)
    }

    public String getAccessToken() {
        String res = HttpUtil.get(String.format(ACCESS_TOKEN_API, APPID, SECRET));
        String accessToken = JSONUtil.parseObj(res).getStr(ACCESS_TOKEN);
        cache().put(ACCESS_TOKEN, accessToken);
        return accessToken;
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

    public WxTemplate buildTemplate() {
        WxTemplate template = new WxTemplate();
        template.setTouser("oAHa45va5Dx3hQ5xKYfCTjvHOdG0");
        template.setTemplate_id("xl5WVcUPzZn3FhMDXZ8vnVAjB0ui121-ID5fK6x-oFs");
//        template.setData();
        return null;
    }
}
