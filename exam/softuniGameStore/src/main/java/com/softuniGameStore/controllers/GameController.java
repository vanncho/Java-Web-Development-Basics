package com.softuniGameStore.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.ModelAttribute;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.parameters.RequestParam;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.annotations.request.PostMapping;
import com.mvcFramework.models.Model;
import com.softuniGameStore.constants.Constants;
import com.softuniGameStore.models.dtos.bindingModels.game.AddGameModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameEditModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListHomeModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListModel;
import com.softuniGameStore.services.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class GameController {

    @Inject
    private GameService gameService;

    @GetMapping("/games")
    public String getGames(Model model) {

        model.addAttribute("title", "Games");
        List<GameListModel> gameListModels = this.gameService.getAllGames();
        model.addAttribute("games", gameListModels);
        model.addAttribute("view", "templates/games.jsp");

        return "base-layout";
    }

    @GetMapping("/details/{id}")
    public String getDetailsGame(@PathVariable("id") Long id,
                                HttpSession session,
                                Model model) {

            GameEditModel gameEditModel = this.gameService.getById(id);
            model.addAttribute("game", gameEditModel);
            model.addAttribute("view", "templates/game-details.jsp");

        return "base-layout";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteGame(@PathVariable("id") Long id,
                              HttpSession session,
                              Model model) {

        model.addAttribute("title", "Delete game");
        UserLoginModel userModel = (UserLoginModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            GameEditModel gameEditModel = this.gameService.getById(id);
            model.addAttribute("game", gameEditModel);
            model.addAttribute("view", "templates/delete-game.jsp");

        } else {

            return "redirect:/login";
        }

        return "base-layout";
    }

    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id,
                           Model model) {

        model.addAttribute("title", "Delete game");

        this.gameService.deleteGame(id);

        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String getEditGame(@PathVariable("id") Long id,
                              HttpSession session,
                              Model model) {

        model.addAttribute("title", "Edit game");
        UserLoginModel userModel = (UserLoginModel) session.getAttribute("loggedUser");

        if (userModel != null) {

            GameEditModel gameEditModel = this.gameService.getById(id);
            model.addAttribute("game", gameEditModel);
            model.addAttribute("view", "templates/edit-game.jsp");

        } else {

            return "redirect:/login";
        }

        return "base-layout";
    }

    @PostMapping("/edit/{id}")
    public String editGame(@PathVariable("id") Long id,
                           @RequestParam("title") String title,
                           @RequestParam("description") String description,
                           @RequestParam("imageThumbnail") String imageThumbnail,
                           @RequestParam("price") String price,
                           @RequestParam("size") String size,
                           @RequestParam("trailer") String trailer,
                           @RequestParam("releaseDate") String releaseDate,
                           Model model) {

        model.addAttribute("title", "Edit game");

        //GameEditModel gameEditModel = this.gameService.getById(id);
        AddGameModel addGameModel = new AddGameModel();
        addGameModel.setTitle(title);
        addGameModel.setDescription(description);
        addGameModel.setTrailer(trailer);
        addGameModel.setPrice(price);
        addGameModel.setSize(size);
        addGameModel.setImageThumbnail(imageThumbnail);
        addGameModel.setReleaseDate(releaseDate);

        this.gameService.editGame(addGameModel, id);

        return "redirect:/games";
    }

    @GetMapping("/add-game")
    public String getAddGame(Model model) {

        model.addAttribute("title", "Add game");
        model.addAttribute("view", "templates/add-game.jsp");

        return "base-layout";
    }

    @PostMapping("/add-game")
//    public String addGame(@ModelAttribute AddGameModel addGameModel
    public String addGame(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("imageThumbnail") String imageThumbnail,
                          @RequestParam("price") String price,
                          @RequestParam("size") String size,
                          @RequestParam("trailer") String trailer,
                          @RequestParam("releaseDate") String releaseDate,
                          Model model,
                          HttpSession session) {

        model.addAttribute("title", "Add game");

        List<String> errors = new ArrayList<>();
        boolean existGame = this.gameService.exists(title);

        if (!title.equals("") && description.equals("") &&
                imageThumbnail.equals("") && price.equals("") &&
                size.equals("") && trailer.equals("") && releaseDate.equals("")) {

            if (!existGame) {

                AddGameModel addGameModel = new AddGameModel();
                addGameModel.setTitle(title);
                addGameModel.setDescription(description);
                addGameModel.setTrailer(trailer);
                addGameModel.setPrice(price);
                addGameModel.setSize(size);
                addGameModel.setImageThumbnail(imageThumbnail);
                addGameModel.setReleaseDate(releaseDate);

                this.gameTitleValidation(errors, addGameModel.getTitle());
                this.gamePriceValidation(errors, addGameModel.getPrice());
                this.gameSizeValidation(errors, addGameModel.getSize());
                this.gameTrailerValidation(errors, addGameModel.getTrailer());
                this.gameDescriptionValidation(errors, addGameModel.getDescription());
                this.gameImageThumbnailValidation(errors, addGameModel.getImageThumbnail());

                if (errors.size() > 0) {

                    model.addAttribute("errors", errors);
                    model.addAttribute("view", "templates/add-game.jsp");

                } else {

                    UserLoginModel userLoginModel = (UserLoginModel) session.getAttribute("loggedUser");
                    this.gameService.create(addGameModel, userLoginModel);
                    return "redirect:/games";
                }

            } else {

                errors.add(Constants.EXISTING_GAME);
                model.addAttribute("errors", errors);
                model.addAttribute("view", "templates/add-game.jsp");
            }

            return "redirect:/games";

        } else {

            errors.add(Constants.WRONG_TITLE);
            errors.add(Constants.WRONG_PRICE);
            errors.add(Constants.WRONG_SIZE);
            errors.add(Constants.WRONG_TRAILER);
            errors.add(Constants.WRONG_DESCRIPTION);
            errors.add(Constants.WRONG_THUMBNAIL_URL);

            model.addAttribute("errors", errors);
            model.addAttribute("view", "templates/add-game.jsp");
        }

        return "base-layout";
    }

    private boolean gameImageThumbnailValidation(List<String> errors, String imageThumbnail) {

        boolean isValid;

        if (!imageThumbnail.startsWith("http://") || !imageThumbnail.startsWith("https://")) {

            isValid = false;
            errors.add(Constants.WRONG_THUMBNAIL_URL);
        } else {

            isValid = true;
        }

        return isValid;

    }

    private boolean gameDescriptionValidation(List<String> errors, String description) {

        boolean isValid;

        if (description.length() < 20) {

            isValid = false;
            errors.add(Constants.WRONG_DESCRIPTION);

        } else {

            isValid = true;
        }

        return isValid;
    }

    private boolean gameTrailerValidation(List<String> errors, String trailer) {

        boolean isValid;

        if (trailer.length() != 11) {

            isValid = false;
            errors.add(Constants.WRONG_TRAILER);

        } else {

            isValid = true;
        }

        return isValid;

    }

    private boolean gameSizeValidation(List<String> errors, String size) {

        boolean isValid;
        String[] tokens = size.split("\\.");
        Double sizeAsDouble = Double.valueOf(size);

        if (sizeAsDouble < 0 || tokens[1].length() > 1) {

            isValid = false;
            errors.add(Constants.WRONG_SIZE);

        } else {

            isValid = true;
        }

        return isValid;
    }

    private boolean gamePriceValidation(List<String> errors, String price) {

        boolean isValid;
        Double priceAsDouble = Double.valueOf(price);
        String[] tokens = price.split("\\.");

        if (priceAsDouble < 0 || tokens[1].length() > 2) {

            isValid = false;
            errors.add(Constants.WRONG_PRICE);

        } else {

            isValid = true;
        }

        return isValid;
    }

    private boolean gameTitleValidation(List<String> errors, String title) {

        boolean isValid;
        char firstLetter = title.charAt(0);

        if (title.length() < 3 || title.length() > 100) {

            isValid = false;
            errors.add(Constants.WRONG_TITLE_LENGTH);

        } else if (firstLetter < 65 || firstLetter > 90) {

            isValid = false;
            errors.add(Constants.WRONG_TITLE);
        } else {

            isValid = true;
        }

        return isValid;
    }
}
