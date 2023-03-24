package tools.dd.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * MsgLink 链接消息
 *
 * {
 *     "msgtype": "link",
 *     "link": {
 *         "messageUrl": "http://s.dingtalk.com/market/dingtalk/error_code.php",
 *         "picUrl":"@lALOACZwe2Rk",
 *         "title": "测试",
 *         "text": "测试"
 *     }
 * }
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class MsgLink extends MsgTemplate {

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接
     */
    private String messageUrl;
    /**
     * 图片地址
     */
    private String picUrl;
    /**
     * 消息标题，建议100字符以内
     */
    private String title;
    /**
     * 消息描述，建议500字符以内
     */
    private String text;

    public MsgLink(String messageUrl, String picUrl, String title, String text) {
        super("link");
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getContent() {
        JSONObject msg = new JSONObject();
        msg.put("msgtype", type);
        JSONObject linkJson = new JSONObject();
        linkJson.put("messageUrl", messageUrl);
        linkJson.put("picUrl", picUrl);
        linkJson.put("title", title);
        linkJson.put("text", text);
        msg.put(type, linkJson);
        return msg.toJSONString();
    }
}
