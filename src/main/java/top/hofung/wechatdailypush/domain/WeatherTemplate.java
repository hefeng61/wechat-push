package top.hofung.wechatdailypush.domain;

import lombok.Data;

@Data
public class WeatherTemplate {
    private String name = "盼盼";

    private String date;
    /**
     * 天气
     */
    private String weather;
    /**
     * 实时气温
     */
    private String temperature;
    /**
     * 湿度
     */
    private String humidity;

}
