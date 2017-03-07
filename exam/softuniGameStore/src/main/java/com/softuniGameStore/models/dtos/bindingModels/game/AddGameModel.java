package com.softuniGameStore.models.dtos.bindingModels.game;


import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;

public class AddGameModel {

    private String title;

    private String description;

    private String imageThumbnail;

    private String price;

    private String size;

    private String trailer;

    private String releaseDate;

    private UserLoginModel user;

    public AddGameModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public UserLoginModel getUser() {
        return user;
    }

    public void setUser(UserLoginModel user) {
        this.user = user;
    }
}
