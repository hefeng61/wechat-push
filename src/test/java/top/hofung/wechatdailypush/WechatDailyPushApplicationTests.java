package top.hofung.wechatdailypush;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.hofung.wechatdailypush.domain.LiveWeather;
import top.hofung.wechatdailypush.util.WeahterUtil;

@SpringBootTest
class WechatDailyPushApplicationTests {

    @Test
    void contextLoads() {
        LiveWeather liveWeatherInfo = WeahterUtil.getLiveWeatherInfo();
        System.out.println(liveWeatherInfo);
    }

}
