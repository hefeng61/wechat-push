package top.hofung.wechatdailypush.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import top.hofung.wechatdailypush.domain.LiveWeather;

import java.util.List;

/**
 * 获取天气信息
 */
public class WeahterUtil {

    private static final String KEY = "88f9582b16f34900ba810e68793612f1";

    private static final String ADCODE = "101200905";

    private static final String WEATHER_API = "https://devapi.qweather.com/v7/weather/3d?key=%s&location=%s";

    public static LiveWeather getLiveWeatherInfo(String adcode) {
        String res = HttpUtil.get(String.format(WEATHER_API, KEY, adcode));
        List<LiveWeather> weathers = JSONUtil.parseObj(res).getJSONArray("daily").toList(LiveWeather.class);
        return weathers.get(0);
    }
}
