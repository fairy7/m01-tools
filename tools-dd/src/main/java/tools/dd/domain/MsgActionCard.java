package tools.dd.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * MsgActionCard 卡片消息
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
//todo
@Deprecated
public class MsgActionCard extends MsgTemplate {

    public MsgActionCard() {
        super("action_card");
    }

    @Override
    public String getContent() {
        JSONObject msg = new JSONObject();
        msg.put("msgtype", type);
        JSONObject contentJson = new JSONObject();
//        contentJson.put("content", content);
        msg.put(type, contentJson);
        return msg.toJSONString();
    }
}
