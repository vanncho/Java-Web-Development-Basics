package softuni.entities;

import softuni.constants.Constants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author")
    private Set<Article> articles;

    @Transient
    private String confirmPassword;

    @Transient
    private List<String> errors;

    public User() {
        this.errors = new ArrayList<>();
    }

    public User(String fullName, String email, String password, String confirmPassword) {
        this();
        this.setFullName(fullName);
        this.setEmail(email);
        this.setPassword(password);
        this.confirmPassword = confirmPassword;
        this.articles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {

        String alphabets = "[a-zA-Z]+\\s+[a-zA-z]+$";
        boolean isMatch = getMatch(alphabets, fullName);

        if (fullName.length() < 5 || fullName.length() > 50) {

            this.errors.add(Constants.WRONG_FULL_NAME_LENGTH);
        }

        if (isMatch) {

            this.fullName = fullName;

        } else {

            this.errors.add(Constants.WRONG_FULL_NAME);
            this.fullName = "";
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        String emailPattern = "^[a-zA-z]+@[a-zA-Z]+\\.[a-z]+$";

        boolean isMatch = this.getMatch(emailPattern, email);

        if (isMatch) {

            this.email = email;

        } else {

            this.errors.add(Constants.WRONG_EMAIL);
            this.fullName = "";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if (fullName.length() < 3 || fullName.length() > 30) {

            this.errors.add(Constants.WRONG_PASSWORD_LENGTH);
            this.fullName = "";

        } else {

            this.password = password;
        }
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    private boolean getMatch(String regexPattern, String paramToMatch) {

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(paramToMatch);

        return matcher.find();
    }

    public List<String> getErrors() {

        return Collections.unmodifiableList(this.errors);
    }
}
