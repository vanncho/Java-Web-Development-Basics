package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.entities.User;
import softuni.models.bindingModels.UserModel;
import softuni.repositories.UserRepository;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class LoginController {

    @Inject
    private UserService userService;

    @GetMapping("/user/login")
    public String view(Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute("view", "user/login.jsp");
        return "base-layout";
    }

    @PostMapping("/user/login")
    public String processLogin(Model model,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session) {

        UserModel user = this.userService.getByEmailAndPassword(email, password);

        if (user != null) {

            model.addAttribute("userName", user.getFullName());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("view", "user/userprofile.jsp");
            session.setAttribute("loggedUser", user);

        } else {

            model.addAttribute("errors", "Invalid email or password.");
            model.addAttribute("view", "user/login.jsp");
        }

        return "base-layout";
    }
}
