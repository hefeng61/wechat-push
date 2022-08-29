package top.hofung.wechatdailypush;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@SpringBootTest
class WechatDailyPushApplicationTests {

    @Test
    void contextLoads() {
//        LiveWeather liveWeatherInfo = WeahterUtil.getLiveWeatherInfo();
//        System.out.println(liveWeatherInfo);
        System.out.println(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(formatter);
        System.out.println(format);
    }

}
