package com.softuniGameStore.services;

import com.softuniGameStore.models.dtos.bindingModels.game.AddGameModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameCartView;
import com.softuniGameStore.models.dtos.viewModels.game.GameEditModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListHomeModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListModel;

import java.util.List;

public interface GameService {

    void create(AddGameModel game, UserLoginModel userLoginModel);

    GameEditModel getById(Long id);

    GameCartView getCartViewById(Long id);

    void delete(Long id);

    boolean exists(String title);

    List<GameListModel> getAllGames();

    List<GameListHomeModel> getAllGamesInHome();

    void editGame(AddGameModel addGameModel, Long id);

    void deleteGame(Long id);
}
