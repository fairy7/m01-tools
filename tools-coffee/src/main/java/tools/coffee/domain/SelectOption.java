package tools.coffee.domain;

/**
 * SelectOption
 *
 * @author: hyr
 * @date: 2022-08-10
 **/
public class SelectOption {

    private String questionId;
    private String id;
    private String content;

    @Override
    public String toString() {
        return "SelectOption{" +
            "questionId='" + questionId + '\'' +
            ", id='" + id + '\'' +
            ", content='" + content + '\'' +
            '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
