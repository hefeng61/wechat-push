package top.hofung.wechatdailypush.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WxTemplate implements Serializable {

    private String touser;

    private String template_id;

    private String url;

    private List<WeatherInfo> data;


}
