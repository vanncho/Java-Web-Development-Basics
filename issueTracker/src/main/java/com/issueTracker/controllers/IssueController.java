package com.issueTracker.controllers;

import com.issueTracker.entities.enums.Priority;
import com.issueTracker.entities.enums.Status;
import com.issueTracker.models.IssueModel;
import com.issueTracker.models.UserModel;
import com.issueTracker.services.IssueService;
import com.issueTracker.services.UserService;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class IssueController {

    @Inject
    private IssueService issueService;

    @Inject
    private UserService userService;

    @GetMapping("/issues/issues")
    public String getIssue(Model model, HttpSession session) {

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            List<IssueModel> issueModels = this.issueService.getAllIssues();
            model.addAttribute("user", userModel);
            model.addAttribute("allIssues", issueModels);

            return "/issues/issues";
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping("/issues/add")
    public String getAddIssue(HttpSession session) {

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            return "/issues/add-issue";
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping("/issues/add")
    public String addIssue(@RequestParam("nameIssue") String nameIssue,
                           @RequestParam("status") String status,
                           @RequestParam("priority") String priority,
                           HttpSession session) {


        Priority priorityObj = Priority.valueOf(priority.toUpperCase());
        Status statusObj = Status.valueOf(status.toUpperCase());
        IssueModel issueModel = new IssueModel(nameIssue, priorityObj, statusObj);
        UserModel userModel = (UserModel) session.getAttribute("loggedUser");
        issueModel.setAuthor(userModel);
        this.issueService.create(issueModel);

        return "redirect:/issues/issues";
    }

    @GetMapping("/issues/delete/{id}")
    public String deleteIssue(@PathVariable("id") Long id,
                              HttpSession session) {

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            IssueModel issueModel = this.issueService.getById(id);
            this.issueService.delete(issueModel);

            return "redirect:/issues/issues";
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping("/issues/edit-issue/{id}")
    public String editIssue(@PathVariable("id") Long id,
                            HttpSession session,
                            Model model) {

        UserModel userModel = (UserModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            IssueModel issueModel = this.issueService.getById(id);
            model.addAttribute("issue", issueModel);
            return "issues/edit-issue";

        } else {

            return "redirect:/login";
        }
    }

    @PostMapping("/issues/edit-issue/{id}")
    public String changeIssue(@PathVariable("id") Long id,
                              @RequestParam("issueName") String issueName,
                              @RequestParam("status") String status,
                              @RequestParam("priority") String priority) {

        IssueModel issueModel = this.issueService.getById(id);
        this.issueService.edit(issueModel, issueName, status, priority);
        return "redirect:/issues/issues";
    }
}
