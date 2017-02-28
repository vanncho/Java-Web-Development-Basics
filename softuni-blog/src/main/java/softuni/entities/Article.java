package softuni.entities;

import softuni.constants.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "authorId")
    private User author;

    @Transient
    private List<String> errors;

    public Article() {

        this.errors = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        if (title.length() < 30) {

            this.errors.add(Constants.WRONG_TITLE_LENGTH);
            this.title = "";

        } else {

            this.title = title;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        if (content.length() < 30) {

            this.errors.add(Constants.WRONG_CONTENT_LENGTH);
            this.content = "";

        } else {

            this.content = content;
        }
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
