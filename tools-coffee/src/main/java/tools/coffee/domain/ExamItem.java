package tools.coffee.domain;

import java.util.List;

/**
 * ExamItem
 *
 * @author: hyr
 * @date: 2022-08-10
 **/
public class ExamItem {

    private String id;
    private Integer type;
    private Integer sort;
    private String content;
    private List<String> correctAnswer;
    private List<SelectOption> option;

    @Override
    public String toString() {
        return "ExamItem{" +
            "id='" + id + '\'' +
            ", type=" + type +
            ", sort=" + sort +
            ", content='" + content + '\'' +
            ", correctAnswer=" + correctAnswer +
            ", option=" + option +
            '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<SelectOption> getOption() {
        return option;
    }

    public void setOption(List<SelectOption> option) {
        this.option = option;
    }
}
