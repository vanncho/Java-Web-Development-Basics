package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import softuni.models.bindingModels.UserModel;
import softuni.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Stateless
@Controller
public class RegisterController {

    @Inject
    private UserService userService;

    @GetMapping("/user/register")
    public String details(Model model){

        model.addAttribute("title", "Register User");
        model.addAttribute("view", "user/register.jsp");

        return "base-layout";
    }

    @PostMapping("/user/register")
    public String processData(@RequestParam("email") String email,
                              @RequestParam("fullname") String fullName,
                              @RequestParam("password-first") String passwordFirst,
                              @RequestParam("password-second") String passwordSecond,
                              Model model)throws IOException {
        UserModel user = null;

        if (passwordFirst.equals(passwordSecond)) {

                user = new UserModel(fullName, email, passwordFirst, passwordSecond);
                List<String> errors = user.getErrors();

                if (errors.size() > 0) {

                    model.addAttribute("errors", errors);
                    user = null;
                }

            if (user != null) {

                this.userService.create(user);
                model.addAttribute("view", "user/login.jsp");
            }
        } else {

            model.addAttribute("view", "user/register.jsp");
        }

        return "base-layout";
    }
}
