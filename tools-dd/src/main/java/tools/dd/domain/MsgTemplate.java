package tools.dd.domain;

/**
 * MsgTemplate
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public abstract class MsgTemplate {

    /**
     * 消息类型
     */
    protected String type;

    public MsgTemplate(String type) {
        this.type = type;
    }

    /**
     * 获取消息字符串
     *
     * @return 结果
     */
    public abstract String getContent();
}
