package com.softuniGameStore.repositories;

import com.softuniGameStore.models.entities.Game;

import java.util.List;

public interface GameRepository {

    void create(Game game);

    void delete(Long id);

    Game getById(Long id);

    boolean exists(String title);

    List<Game> getAllGames();

    void editGame(Game game, Long id);

    void deleteGame(Long id);
}
