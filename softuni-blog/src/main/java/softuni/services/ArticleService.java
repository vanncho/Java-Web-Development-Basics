package softuni.services;

import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;

import java.util.List;

public interface ArticleService {

    ArticleModel create(String title, String content, UserModel userModel);

    void delete(ArticleModel article);

    ArticleModel getById(Long id);

    List<ArticleModel> findAllByUserId(Long id);
}
