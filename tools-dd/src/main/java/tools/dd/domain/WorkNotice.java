package tools.dd.domain;

/**
 * WorkNotice
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class WorkNotice {

    private String organizationCodes;

    private String receiverIds;

    private String bizMsgId;

    private MsgTemplate msg;

    public WorkNotice(String receiverIds, MsgTemplate msg) {
        this.receiverIds = receiverIds;
        this.msg = msg;
    }

    public WorkNotice() {
    }

    public String getOrganizationCodes() {
        return organizationCodes;
    }

    public void setOrganizationCodes(String organizationCodes) {
        this.organizationCodes = organizationCodes;
    }

    public String getReceiverIds() {
        return receiverIds;
    }

    public void setReceiverIds(String receiverIds) {
        this.receiverIds = receiverIds;
    }

    public String getBizMsgId() {
        return bizMsgId;
    }

    public void setBizMsgId(String bizMsgId) {
        this.bizMsgId = bizMsgId;
    }

    public MsgTemplate getMsg() {
        return msg;
    }

    public void setMsg(MsgTemplate msg) {
        this.msg = msg;
    }
}
