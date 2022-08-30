package top.hofung.wechatdailypush.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * ONE~一句
 */
@Data
public class Essay implements Serializable {

    private String id;
    /**
     * 图片地址
     */
    private String src;
    /**
     * 文字
     */
    private String text;
    /**
     * 年
     */
    private String year;
    /**
     * 月
     */
    private String day;
    /**
     * 日
     */
    private String month;
}
