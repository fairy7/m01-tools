package tools.dd.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * MsgText 文本消息
 *
 * {
 *     "msgtype": "text",
 *     "text": {
 *         "content": "张三的请假申请"
 *     }
 * }
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class MsgText extends MsgTemplate {

    /**
     * 消息内容，建议500字符以内
     */
    private String content;

    public MsgText(String content) {
        super("text");
        this.content = content;
    }

    @Override
    public String getContent() {
        JSONObject msg = new JSONObject();
        msg.put("msgtype", type);
        JSONObject contentJson = new JSONObject();
        contentJson.put("content", content);
        msg.put(type, contentJson);
        return msg.toJSONString();
    }
}
