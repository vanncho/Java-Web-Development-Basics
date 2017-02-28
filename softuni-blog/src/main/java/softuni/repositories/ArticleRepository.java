package softuni.repositories;

import softuni.entities.Article;

import java.util.List;

public interface ArticleRepository {

    void create(Article article);

    void delete(Article article);

    Article getById(Long id);

    List<Article> findAllByUserId(Long id);
}
