package com.softuniGameStore.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import com.softuniGameStore.models.dtos.viewModels.game.GameListHomeModel;
import com.softuniGameStore.services.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    @Inject
    private GameService gameService;

    @GetMapping("/")
    public String getHome(Model model) {

        model.addAttribute("title", "SoftUni Game Store");
        List<GameListHomeModel> gameListHomeModels = this.gameService.getAllGamesInHome();
        model.addAttribute("homeGames", gameListHomeModels);
        model.addAttribute("view", "templates/home.jsp");

        return "base-layout";
    }
}
