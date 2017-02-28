package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import softuni.models.bindingModels.ArticleModel;
import softuni.models.bindingModels.UserModel;
import softuni.services.ArticleService;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private ArticleService articleService;

    @Inject
    private UserService userService;

    @GetMapping("/")
    public String details(Model model, HttpSession session){

        model.addAttribute("title", "Softuni Blog");

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            List<ArticleModel> articles = this.articleService.findAllByUserId(userModel.getId());
            model.addAttribute("articles", articles);
            model.addAttribute("view", "article/article.jsp");

        } else {

            model.addAttribute("view", "home/index.jsp");
        }

        return "base-layout";
    }
}
