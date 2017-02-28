package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class SignoutController {

    @PostMapping("/user/signout")
    public String signout(Model model, HttpSession session) {

            session.invalidate();
            model.addAttribute("view", "user/login.jsp");

        return "base-layout";
    }
}
