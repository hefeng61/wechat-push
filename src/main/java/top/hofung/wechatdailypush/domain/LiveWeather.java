package top.hofung.wechatdailypush.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 实时天气
 */
@Data
public class LiveWeather implements Serializable {
    /**
     * 省份名
     */
    private String province;
    /**
     * 城市名
     */
    private String city;
    /**
     * 区域编码
     */
    private String adcode;
    /**
     * 天气现象（汉字描述）
     */
    private String weather;
    /**
     * 实时气温，单位：摄氏度
     */
    private String temperature;
    /**
     * 风向描述
     */
    private String winddirection;
    /**
     * 风力级别，单位：级
     */
    private String windpower;
    /**
     * 空气湿度
     */
    private String humidity;
    /**
     * 数据发布的时间
     */
    private String reporttime;
}
