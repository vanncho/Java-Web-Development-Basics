package com.softuniGameStore.controllers;

import com.mvcFramework.annotations.controller.Controller;
import com.mvcFramework.annotations.parameters.PathVariable;
import com.mvcFramework.annotations.request.GetMapping;
import com.mvcFramework.models.Model;
import com.softuniGameStore.constants.Constants;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameCartView;
import com.softuniGameStore.services.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Controller
public class CartController {

    @Inject
    private GameService gameService;

    @GetMapping("/remove-game/{title}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable("title") String title) {

        UserLoginModel userLoginModel = (UserLoginModel) session.getAttribute("loggedUser");

        if (userLoginModel != null) {

            try {

                title = URLDecoder.decode(title, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            userLoginModel.getGamesInCart().remove(title);
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {

        model.addAttribute("title", "Your cart");

        UserLoginModel userLoginModel = (UserLoginModel) session.getAttribute("loggedUser");

        if (userLoginModel != null) {

            model.addAttribute("gamesInCart", userLoginModel.getGamesInCart());
            Double sumOfGames = userLoginModel.getGamesInCart()
                    .entrySet()
                    .stream()
                    .mapToDouble(x -> x.getValue().getPrice()).sum();
            model.addAttribute("sumOfGames", sumOfGames);

        }

        model.addAttribute("view", "templates/cart.jsp");
        return "base-layout";
    }

    @GetMapping("/cart/{id}")
    public String getCart(@PathVariable("id") Long id,
                          Model model,
                          HttpSession session) {

        model.addAttribute("title", "Your cart");

        UserLoginModel userLoginModel = (UserLoginModel) session.getAttribute("loggedUser");
        Double sumOfGames = null;

        if (userLoginModel != null) {
            List<String> errors = new ArrayList<>();
            sumOfGames = userLoginModel.getGamesInCart()
                    .entrySet()
                    .stream()
                    .mapToDouble(x -> x.getValue().getPrice()).sum();

            GameCartView gameCartView = this.gameService.getCartViewById(id);

            if (!userLoginModel.getGamesInCart().containsKey(gameCartView.getTitle())) {

                userLoginModel.getGamesInCart().put(gameCartView.getTitle(), gameCartView);

            } else {

                errors.add(String.format(Constants.GAME_EXIST_IN_SHOPPING_CART, gameCartView.getTitle()));
                model.addAttribute("errors", errors);
            }

            model.addAttribute("gamesInCart", userLoginModel.getGamesInCart());
            model.addAttribute("sumOfGames", sumOfGames);
            model.addAttribute("view", "templates/cart.jsp");

            return "base-layout";
        }

        return "redirect:/login";
    }
}
