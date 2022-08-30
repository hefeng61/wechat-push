package top.hofung.wechatdailypush;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat.config")
@Data
public class WxConfig {
    private String appId;

    private String secret;

    private String openId;

    private String templateId;
}
