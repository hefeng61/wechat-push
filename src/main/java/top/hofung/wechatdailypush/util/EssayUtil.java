package top.hofung.wechatdailypush.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import top.hofung.wechatdailypush.domain.Essay;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取ONE一句好
 */
public class EssayUtil {

    public static List<String> getEssay() {
        String res = HttpUtil.get("https://apier.youngam.cn/essay/one");
        List<Essay> essayList = JSONUtil.parseObj(res).getJSONArray("dataList").toList(Essay.class);
        List<String> list = essayList.stream().map(item -> item.getText()).collect(Collectors.toList());
        return list;
    }
}
