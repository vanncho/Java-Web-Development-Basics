package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;
import softuni.services.ArticleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class ArticleCreateController {

    @Inject
    private ArticleService articleService;

    @GetMapping("/article/create-article")
    public String details(Model model, HttpSession session){

        model.addAttribute("title", "New Article");

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            model.addAttribute("view", "article/create-article.jsp");
        } else {

            model.addAttribute("view", "home/index.jsp");
        }

        return "base-layout";
    }

    @PostMapping("/article/create-article")
    public String processArticle(@RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 HttpSession session,
                                 Model model) {

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");
        ArticleModel article = this.articleService.create(title, content, userModel);

        if (article.getTitle() == null || article.getContent() == null) {

            List<String> errors = article.getErrors();

            if (errors.size() > 0) {

                model.addAttribute("errors", errors);
            }
        }

        return "redirect:/";
    }
}
