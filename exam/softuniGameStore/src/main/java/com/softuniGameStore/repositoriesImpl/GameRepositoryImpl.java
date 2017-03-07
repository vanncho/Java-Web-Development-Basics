package com.softuniGameStore.repositoriesImpl;

import com.softuniGameStore.models.entities.Game;;
import com.softuniGameStore.repositories.GameRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(GameRepository.class)
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Game game) {

        this.entityManager.persist(game);
    }

    @Override
    public void delete(Long id) {

        Query query = this.entityManager.createQuery("DELETE FROM Game WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Game getById(Long id) {

        Game game = this.entityManager.find(Game.class, id);
        return game;
    }

    @Override
    public boolean exists(String title) {

        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g Where g.title = :title");

        query.setParameter("title", title);
        Game game = null;

        if (query.getResultList().size() > 0) {

            game = (Game) query.getSingleResult();
        }

        return game != null;
    }

    @Override
    public List<Game> getAllGames() {

        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g");
        List<Game> games = query.getResultList();

        return games;
    }

    @Override
    public void editGame(Game game, Long id) {

        Query query = this.entityManager.createQuery("UPDATE Game AS g SET g.title = :title, " +
                "g.description = :description, g.imageThumbnail = :imageThumbnail, " +
                "g.price = :price, g.size = :sizeImg, g.releaseDate = :releaseDate, " +
                "g.trailer = :trailer WHERE g.id = :id");

        query.setParameter("id", id);
        query.setParameter("title", game.getTitle());
        query.setParameter("description", game.getDescription());
        query.setParameter("imageThumbnail", game.getImageThumbnail());
        query.setParameter("price", game.getPrice());
        query.setParameter("sizeImg", game.getSize());
        query.setParameter("releaseDate", game.getReleaseDate());
        query.setParameter("trailer", game.getTrailer());

        query.executeUpdate();
    }

    @Override
    public void deleteGame(Long id) {

        Query query = this.entityManager.createQuery("SELECT g FROM Game AS g WHERE g.id = :id");
        query.setParameter("id", id);
        Game game = null;

        if (query.getResultList().size() > 0) {

            game = (Game) query.getSingleResult();
        }

        this.entityManager.remove(game);
    }
}
