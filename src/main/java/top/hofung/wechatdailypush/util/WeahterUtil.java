package top.hofung.wechatdailypush.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import top.hofung.wechatdailypush.domain.LiveWeather;

import java.util.List;

/**
 * 获取天气信息
 */
public class WeahterUtil {

    private static final String KEY = "79c0b3d550bd7479721806f078b460c6";

    private static final String ADCODE = "420502";

    private static final String WEATHER_API = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%s";

    public static LiveWeather getLiveWeatherInfo() {
        String res = HttpUtil.get(String.format(WEATHER_API, KEY, ADCODE));
        List<LiveWeather> weathers = JSONUtil.parseObj(res).getJSONArray("lives").toList(LiveWeather.class);
        return weathers.get(0);
    }
}
