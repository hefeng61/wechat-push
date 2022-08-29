package top.hofung.wechatdailypush.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Essay implements Serializable {

    private String id;

    private String src;

    private String text;

    private String year;

    private String day;

    private String month;
}
