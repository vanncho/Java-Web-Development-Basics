package softuni.repositories;

import softuni.entities.Article;
import softuni.models.bindingModels.ArticleModel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(ArticleRepository.class)
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Article article) {

        this.entityManager.persist(article);
    }

    @Override
    public void delete(Article article) {

        this.entityManager.remove(article);
    }

    @Override
    public Article getById(Long id) {

        return this.entityManager.find(Article.class, id);
    }

    @Override
    public List<Article> findAllByUserId(Long id) {

        Query query = this.entityManager.createQuery("SELECT a FROM Article AS a WHERE a.author.id = :id");
        query.setParameter("id", id);
        List<Article> articles = query.getResultList();
        return articles;
    }
}
