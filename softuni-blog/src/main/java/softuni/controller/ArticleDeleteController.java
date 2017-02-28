package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.entities.Article;
import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class ArticleDeleteController {

    @Inject
    private ArticleService articleService;

    @GetMapping("/article/delete-article/{id}")
    public String getDelete(Model model,
                          HttpSession session,
                          @PathVariable("id") long id) {

        model.addAttribute("title", "Delete Article");

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            ArticleModel articleModel = this.articleService.getById(id);

            if (articleModel != null) {

                model.addAttribute("title", articleModel.getTitle());
                model.addAttribute("content", articleModel.getContent());
                model.addAttribute("author", articleModel.getAuthor().getFullName());
                model.addAttribute("articleId", articleModel.getId());
                model.addAttribute("view", "article/delete-article.jsp");
            }

        } else {

            model.addAttribute("view", "home/index.jsp");
        }

        return "base-layout";
    }

    @PostMapping("/article/delete-article/{id}")
    public String postDelete(Model model,
                             HttpSession session,
                             @PathVariable("id") long id) {

        ArticleModel articleModel = this.articleService.getById(id);
        this.articleService.delete(articleModel);

        return "redirect:/";
    }
}
