package com.softuniGameStore.models.dtos.bindingModels.user;

import com.softuniGameStore.models.dtos.viewModels.game.GameCartView;
import com.softuniGameStore.models.entities.enumerations.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLoginModel {

    private String email;

    private String password;

    private Role role;

    private Map<String, GameCartView> gamesInCart;

    public UserLoginModel() {

        this.gamesInCart = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Map<String, GameCartView> getGamesInCart() {
        return gamesInCart;
    }

    public void setGamesInCart(Map<String, GameCartView> gamesInCart) {
        this.gamesInCart = gamesInCart;
    }
}
