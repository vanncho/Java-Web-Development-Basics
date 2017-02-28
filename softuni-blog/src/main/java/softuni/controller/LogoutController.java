package softuni.controller;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.servlet.http.HttpSession;

@Stateless
@Controller
public class LogoutController {

    @GetMapping("/user/logout")
    public String logout(Model model, HttpSession session) {

        session.setAttribute("loggedUser", null);
        model.addAttribute("view", "user/login.jsp");
        return "base-layout";
    }
}
