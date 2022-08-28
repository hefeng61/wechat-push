package top.hofung.wechatdailypush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class WechatDailyPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatDailyPushApplication.class, args);
    }

}
