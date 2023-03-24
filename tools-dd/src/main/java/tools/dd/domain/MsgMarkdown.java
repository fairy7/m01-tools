package tools.dd.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * MsgMarkdown markdown消息
 *
 * {
 *   "msgtype": "markdown",
 *   "markdown": {
 *     "title": "首屏会话透出的展示内容",
 *     "text": "# 一级标题 \n## 二级标题 \n### 三级标题 \n#### 四级标题\n\n>一级引用文本引用文本引用文本引用文本引用文本引用文本引用文本引用文本引用文本引用文本\n>>二级引用文本\n>>二级引用文本\n\n>>二级引用文本\n>>>三级引用文本\n\n引用外的内容\n\n**加粗文字**\n\n*斜体字*\n\n***加粗且斜体***\n\n[链接](https://www.taobao.com) \n\n ![](https://img.alicdn.com/imgextra/i4/O1CN015WOeyb215MevMCN54_!!6000000006933-2-tps-170-170.png)\n- 无序列表1\n- 无序列表2\n1. 有序列表1\n2. 有序列表2"
 *   }
 * }
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class MsgMarkdown extends MsgTemplate {

    /**
     * 标题，首屏会话透出的展示内容
     */
    private String title;
    /**
     * 	markdown的文本。
     */
    private String text;

    public MsgMarkdown(String title, String text) {
        super("markdown");
        this.title = title;
        this.text = text;
    }

    @Override
    public String getContent() {
        JSONObject msg = new JSONObject();
        msg.put("msgtype", type);
        JSONObject markdownJson = new JSONObject();
        markdownJson.put("title", title);
        markdownJson.put("text", text);
        msg.put(type, markdownJson);
        return msg.toJSONString();
    }
}
