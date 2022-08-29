package top.hofung.wechatdailypush.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 实时天气
 */
@Data
public class LiveWeather implements Serializable {
    /**
     * 预报日期
     */
    private String fxDate;
    /**
     * 当天最高温度
     */
    private String tempMax;
    /**
     * 当天最低温度
     */
    private String tempMin;
    /**
     * 白天天气状况
     */
    private String textDay;
    /**
     * 晚间天气状况
     */
    private String textNight;
    /**
     * 白天风向
     */
    private String windDirDay;
    /**
     * 白天风力等级
     */
    private String windScaleDay;
    /**
     * 紫外线强度指数
     */
    private String uvIndex;
    /**
     * 空气湿度
     */
    private String humidity;
    /**
     * 日出时间
     */
    private String sunrise;
    /**
     * 日落时间
     */
    private String sunset;
    /**
     * 月升时间
     */
    private String moonrise;
    /**
     * 月落时间
     */
    private String moonset;
    /**
     * 月相名称
     */
    private String moonPhase;
}
