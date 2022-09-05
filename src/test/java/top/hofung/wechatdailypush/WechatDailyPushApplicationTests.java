package top.hofung.wechatdailypush;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Pattern;

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

    @Test
    void test(){
        String reg = "/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\\\\\\\.[a-zA-Z0-9-]+)*\\\\.[a-zA-Z0-9]{2,6}$/";
        boolean matches = Pattern.matches(reg, "229865816@qq.com");
        System.out.println(matches);
    }

}
