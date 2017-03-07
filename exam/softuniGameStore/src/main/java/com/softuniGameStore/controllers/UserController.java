package com.softuniGameStore.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.softuniGameStore.constants.Constants;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserRegisterModel;
import com.softuniGameStore.services.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping("/logout")
    public String getLogout(Model model, HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        model.addAttribute("title", "Login");
        model.addAttribute("view", "templates/login.jsp");

        return "base-layout";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute UserLoginModel userLoginModel,
                          HttpSession session,
                          Model model) {

        model.addAttribute("title", "Login");
        UserLoginModel userToLogIn = this.userService.getByUsernameAndPassword(userLoginModel.getEmail(), userLoginModel.getPassword());
        List<String> errors = new ArrayList<>();

        if (userToLogIn != null) {

            model.addAttribute("user", userToLogIn);
            session.setAttribute("loggedUser", userToLogIn);
            return "redirect:/";

        } else {

            errors.add(Constants.NON_EXISTING_USER);
            model.addAttribute("errors", errors);
            model.addAttribute("view", "/templates/login.jsp");
        }

        return "base-layout";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute("view", "templates/register.jsp");

        return "base-layout";
    }

    @PostMapping("/register")
    public String doUserRegister(@ModelAttribute UserRegisterModel userRegisterModel,
                                 Model model) {

        List<String> errors = new ArrayList<>();
        boolean exitUser = this.userService.exists(userRegisterModel.getEmail());

        if (!exitUser) {

            this.userEmailValidation(errors, userRegisterModel.getEmail());
            this.passwordValidation(errors, userRegisterModel.getPassword(), userRegisterModel.getConfirmPassword());

            if (errors.size() > 0) {

                model.addAttribute("errors", errors);
                model.addAttribute("view", "/templates/register.jsp");
            } else {

                this.userService.create(userRegisterModel);
                return "redirect:/login";
            }

        } else {

            errors.add(Constants.EXISTING_USER);
            model.addAttribute("errors", errors);
            model.addAttribute("view", "/templates/register.jsp");
        }

        return "base-layout";
    }

    private boolean userEmailValidation(List<String> errors, String email) {

        boolean isValid;

        if (!email.contains("@") && !email.contains(".")) {

            isValid = false;
            errors.add(Constants.WRONG_EMAIL);
        } else {

            isValid = true;
        }

        return isValid;
    }

    private boolean passwordValidation(List<String> errors, String password, String repeatPassword) {

        boolean isValid = true;
        String passPattern = "^[a-zA-Z0-9]{6,}$";
        Pattern pattern = Pattern.compile(passPattern);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.find()) {

            isValid = false;
            errors.add(Constants.WRONG_PASSWORD);
        }

        if (!password.equals(repeatPassword)) {

            isValid = false;
            errors.add(Constants.MISMATCH_PASSWORDS);
        }

        return isValid;
    }
}
