package com.issueTracker.controllers;

import com.issueTracker.constants.Constants;
import com.issueTracker.entities.enums.Role;
import com.issueTracker.models.UserModel;
import com.issueTracker.services.UserService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class UserController {

    @Inject
    private UserService userService;

    @GetMapping("/login")
    public String getLogin() {

        return "jsp/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {

        UserModel userModel = this.userService.getByUsernameAndPassword(username, password);

        if (userModel != null) {

            model.addAttribute("user", userModel);
            session.setAttribute("loggedUser", userModel);
            return "jsp/home";
        }

        model.addAttribute("errors", Constants.NON_EXISTING_USER);
        return "jsp/login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {

        session.setAttribute("loggedUser", null);
        return "redirect:/login";
    }

    @GetMapping("/create-user")
    public String createUser() {

        return "jsp/create-user";
    }

    @PostMapping("/create-user")
    public String verifyCreate(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("fullName") String fullName,
                               @RequestParam("password") String password,
                               @RequestParam("repeatPassword") String repeatPassword) {

        boolean existingUser = this.userService.exists(username);
        List<String> errors = new ArrayList<>();

        if (!existingUser) {

            boolean userNameValidation = this.userNameValidation(errors, username);
            boolean userFullNameValidation = this.userFullNameValidation(errors, fullName);
            boolean passwordValidation = this.passwordValidation(errors, password, repeatPassword);

            if (errors.size() == 0) {

                Role role = this.setUserRole();
                UserModel userModel = new UserModel(username, fullName, password, role);
                this.userService.create(userModel);
                return "redirect:/login";

            } else {

                model.addAttribute("errors", errors);
                return "jsp/create-user";
            }

        } else {

            errors.add(Constants.EXISTING_USER);
        }

        model.addAttribute("errors", errors);
        return "jsp/create-user";
    }

    private Role setUserRole() {

        boolean hasAdmin = this.userService.hasAdmin();
        Role role = Role.USER;

        if (!hasAdmin) {

            role = Role.ADMIN;
            return role;
        }

        return role;
    }

    private boolean userNameValidation(List<String> errors, String username) {

        boolean isValid;

        if (username.length() >= 5 && username.length() <= 30) {

            isValid = true;
        } else {

            isValid = false;
            errors.add(Constants.WRONG_USERNAME_LENGTH);
        }

        return isValid;
    }

    private boolean userFullNameValidation(List<String> errors, String fullName) {

        boolean isValid;

        if (fullName.length() < 5) {

            isValid = false;
            errors.add(Constants.WRONG_FULLNAME_LENGTH);
        } else {

            isValid = true;
        }

        return isValid;
    }

    private boolean passwordValidation(List<String> errors, String password, String repeatPassword) {

        boolean isValid = true;

        if (password.length() < 8) {

            isValid = false;
            errors.add(Constants.WRONG_PASSWORD_LENGTH);
        }

        if (!password.contains("[A-Z]+") && password.contains("[!@#$%^&*,.]+")) {

            isValid = false;
            errors.add(Constants.WRONG_PASSWORD_CONTAINS_LENGTH);
        }

        if (!password.equals(repeatPassword)) {

            isValid = false;
            errors.add(Constants.MISSMATCH_PASSWORDS);
        }

        return isValid;
    }
}
