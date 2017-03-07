package com.softuniGameStore.servicesImpl;

import com.softuniGameStore.mapper.ModelParser;
import com.softuniGameStore.models.dtos.bindingModels.game.AddGameModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameCartView;
import com.softuniGameStore.models.dtos.viewModels.game.GameEditModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListHomeModel;
import com.softuniGameStore.models.dtos.viewModels.game.GameListModel;
import com.softuniGameStore.models.entities.Game;
import com.softuniGameStore.models.entities.User;
import com.softuniGameStore.repositories.GameRepository;
import com.softuniGameStore.repositories.UserRepository;
import com.softuniGameStore.services.GameService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Stateless
@Local(GameService.class)
public class GameServiceImpl implements GameService {

    @Inject
    private GameRepository gameRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(AddGameModel addGameModel, UserLoginModel userLoginModel) {

//        Game game = this.modelParser.convert(addGameModel, Game.class);
        String youtubePattern = "https://www.youtube.com/watch?v";
        Game game = new Game();
        Double price = Double.valueOf(addGameModel.getPrice());
        Double size = Double.valueOf(addGameModel.getSize());
        String stringDate = addGameModel.getReleaseDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;

        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        game.setPrice(price);
        game.setSize(size);
        game.setReleaseDate(date);
        game.setImageThumbnail(addGameModel.getImageThumbnail());
        game.setTitle(addGameModel.getTitle());
        game.setDescription(addGameModel.getDescription());
        game.setTrailer(youtubePattern + addGameModel.getTrailer());

        User user = this.userRepository.getByEmailAndPassword(userLoginModel.getEmail(), userLoginModel.getPassword());
        game.setUser(user);
        this.gameRepository.create(game);
    }

    @Override
    public GameEditModel getById(Long id) {

        Game game = this.gameRepository.getById(id);
        GameEditModel gameEditModel = this.modelParser.convert(game, GameEditModel.class);

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String stringDate = sdf.format(game.getReleaseDate());

        gameEditModel.setReleaseDate(stringDate);
        return gameEditModel;
    }

    @Override
    public GameCartView getCartViewById(Long id) {

        Game game = this.gameRepository.getById(id);
        GameCartView gameCartView = this.modelParser.convert(game, GameCartView.class);
        return gameCartView;
    }

    @Override
    public void delete(Long id) {

        this.gameRepository.delete(id);
    }

    @Override
    public boolean exists(String title) {

        return this.gameRepository.exists(title);
    }

    @Override
    public List<GameListModel> getAllGames() {

        List<Game> games = this.gameRepository.getAllGames();
        List<GameListModel> gameListModels = new ArrayList<>();

        for (Game game : games) {

            GameListModel gameListModel = this.modelParser.convert(game, GameListModel.class);
            gameListModels.add(gameListModel);
        }

        return gameListModels;
    }

    @Override
    public List<GameListHomeModel> getAllGamesInHome() {

        List<Game> games = this.gameRepository.getAllGames();
        List<GameListHomeModel> gameListHomeModels = new ArrayList<>();

        for (Game game : games) {

            GameListHomeModel gameListHomeModel = this.modelParser.convert(game, GameListHomeModel.class);
            gameListHomeModels.add(gameListHomeModel);
        }

        return gameListHomeModels;
    }

    @Override
    public void editGame(AddGameModel addGameModel, Long id) {

        String youtubeLink = "https://www.youtube.com/watch?v";
        Game game = new Game();
        Double price = Double.valueOf(addGameModel.getPrice());
        Double size = Double.valueOf(addGameModel.getSize());
        String stringDate = addGameModel.getReleaseDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;

        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        game.setPrice(price);
        game.setSize(size);
        game.setReleaseDate(date);
        game.setImageThumbnail(addGameModel.getImageThumbnail());
        game.setTitle(addGameModel.getTitle());
        game.setDescription(addGameModel.getDescription());
        game.setTrailer(youtubeLink + addGameModel.getTrailer());

        this.gameRepository.editGame(game, id);
    }

    @Override
    public void deleteGame(Long id) {

        this.gameRepository.deleteGame(id);
    }
}
