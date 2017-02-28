package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;

@Stateless
@Controller
public class UserProfileController {

    @GetMapping("/user/userprofile")
    public String viewProfile(Model model) {

        model.addAttribute("title", "My Profile");
        model.addAttribute("view", "user/userprofile.jsp");

        return "base-layout";
    }
}
