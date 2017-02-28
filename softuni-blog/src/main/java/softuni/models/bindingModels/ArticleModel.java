package softuni.models.bindingModels;

import java.util.ArrayList;
import java.util.List;

public class ArticleModel {

    private Long id;

    private String title;

    private String content;

    private UserModel author;

    private List<String> errors;

    public ArticleModel() {
    }

    public ArticleModel(String title, String content, UserModel author) {

        this.title = title;
        this.content = content;
        this.author = author;
        this.errors = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
