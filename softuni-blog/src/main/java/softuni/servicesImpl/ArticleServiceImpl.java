package softuni.servicesImpl;

import softuni.entities.Article;
import softuni.entities.User;
import softuni.mapper.ModelParser;
import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;
import softuni.repositories.ArticleRepository;
import softuni.repositories.UserRepository;
import softuni.services.ArticleService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(ArticleService.class)
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public ArticleModel create(String title, String content, UserModel userModel) {

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        User user = this.userRepository.getById(userModel.getId());
        article.setAuthor(user);


        if (article.getContent().equals("") || article.getTitle().equals("")) {

            ArticleModel nullModel = new ArticleModel(null, null, null);
            nullModel.setErrors(article.getErrors());
            return nullModel;
        }

        this.articleRepository.create(article);
        ArticleModel articleModel = this.modelParser.convert(article, ArticleModel.class);
        return articleModel;
    }

    @Override
    public void delete(ArticleModel articleModel) {

        Article article = this.articleRepository.getById(articleModel.getId());
        this.articleRepository.delete(article);
    }

    @Override
    public ArticleModel getById(Long id) {

        Article article = this.articleRepository.getById(id);
        ArticleModel articleModel = this.modelParser.convert(article, ArticleModel.class);
        return articleModel;
    }

    @Override
    public List<ArticleModel> findAllByUserId(Long id) {

        List<Article> articles = this.articleRepository.findAllByUserId(id);
        List<ArticleModel> articleModels = new ArrayList<>();

        for (Article article : articles) {

            ArticleModel articleModel = this.modelParser.convert(article, ArticleModel.class);
            articleModels.add(articleModel);
        }

        return articleModels;
    }
}
