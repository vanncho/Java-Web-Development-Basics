package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class ArticleEditController {

    @Inject
    private ArticleService articleService;

    @GetMapping("/article/edit-article/{id}")
    public String getEdit(Model model,
                          HttpSession session,
                          @PathVariable("id") long id) {

        model.addAttribute("title", "Edit Article");

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            ArticleModel articleModel = this.articleService.getById(id);

            if (articleModel != null) {

                model.addAttribute("title", articleModel.getTitle());
                model.addAttribute("content", articleModel.getContent());
                model.addAttribute("author", articleModel.getAuthor().getFullName());
                model.addAttribute("articleId", articleModel.getId());
                model.addAttribute("view", "article/edit-article.jsp");
            }

        } else {

            model.addAttribute("view", "home/index.jsp");
        }

        return "base-layout";
    }
}
