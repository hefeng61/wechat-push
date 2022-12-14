package top.hofung.wechatdailypush.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx.config")
@Data
public class WxConfig {
    private String appId;

    private String secret;

    private String openId;

    private String openId1;

    private String templateId;

    private String token;
}
