package com.issueTracker.controllers;
import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;

import javax.ejb.Stateless;

@Stateless
@Controller
public class HomeController {

    @GetMapping("/")
    public String details(){

        return "jsp/home";
    }
}
