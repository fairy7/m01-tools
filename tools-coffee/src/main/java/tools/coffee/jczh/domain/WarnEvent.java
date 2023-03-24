package tools.coffee.jczh.domain;

import java.util.List;

/**
 * WarnEvent
 *
 * @author: hyr
 * @date: 2022-11-24
 **/
public class WarnEvent {

    private Integer order;

    private String pkid;

    private String checkPersonIds;

    private String checkPersonNames;

    private String content;

    private String qyscqk;

    private String jcjg;

    private String jcyj;

    private String operator;

    private String confirm;

    private List<String> taskFilePath;


    private String enterpriseName;

    public WarnEvent(Integer order) {
        this.order = order;
    }

    public WarnEvent(Integer order, String pkid, String checkPersonIds, String checkPersonNames, String content,
        String qyscqk, String jcjg, String jcyj, List<String> taskFilePath) {
        this.order = order;
        this.pkid = pkid;
        this.checkPersonIds = checkPersonIds;
        this.checkPersonNames = checkPersonNames;
        this.content = content;
        this.qyscqk = qyscqk;
        this.jcjg = jcjg;
        this.jcyj = jcyj;
        this.taskFilePath = taskFilePath;
    }

    public WarnEvent(Integer order, String pkid, String checkPersonIds, String checkPersonNames, String content,
        String qyscqk, String jcjg, String jcyj, String operator, List<String> taskFilePath) {
        this.order = order;
        this.pkid = pkid;
        this.checkPersonIds = checkPersonIds;
        this.checkPersonNames = checkPersonNames;
        this.content = content;
        this.qyscqk = qyscqk;
        this.jcjg = jcjg;
        this.jcyj = jcyj;
        this.operator = operator;
        this.taskFilePath = taskFilePath;
    }

    public WarnEvent() {
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public String getCheckPersonIds() {
        return checkPersonIds;
    }

    public void setCheckPersonIds(String checkPersonIds) {
        this.checkPersonIds = checkPersonIds;
    }

    public String getCheckPersonNames() {
        return checkPersonNames;
    }

    public void setCheckPersonNames(String checkPersonNames) {
        this.checkPersonNames = checkPersonNames;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQyscqk() {
        return qyscqk;
    }

    public void setQyscqk(String qyscqk) {
        this.qyscqk = qyscqk;
    }

    public String getJcjg() {
        return jcjg;
    }

    public void setJcjg(String jcjg) {
        this.jcjg = jcjg;
    }

    public String getJcyj() {
        return jcyj;
    }

    public void setJcyj(String jcyj) {
        this.jcyj = jcyj;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<String> getTaskFilePath() {
        return taskFilePath;
    }

    public void setTaskFilePath(List<String> taskFilePath) {
        this.taskFilePath = taskFilePath;
    }
}
