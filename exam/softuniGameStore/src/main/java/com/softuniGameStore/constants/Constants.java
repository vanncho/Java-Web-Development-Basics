package com.softuniGameStore.constants;

public interface Constants {

    //region USER

    String EXISTING_USER = "Oh snap! User with this email already exists.";

    String NON_EXISTING_USER = "Oh snap! User with this email and password not exists.";

    String WRONG_EMAIL = "Oh snap! Invalid email it must contain @ sign and a period.";

    String WRONG_PASSWORD = "Oh snap! Password length must be at least 6 symbols and must contain at least 1 uppercase, 1 lowercase letter and 1 digit";

    String MISMATCH_PASSWORDS = "Oh snap! Passwords are not matching.";

    //endregion

    //region GAME

    String EXISTING_GAME = "Oh snap! Game with this title already exists.";

    String WRONG_TITLE = "Oh snap! Title has to begin with uppercase letter.";

    String WRONG_TITLE_LENGTH = "Oh snap! Title length must be between 3 and 100 symbols (inclusive).";

    String WRONG_PRICE = "Oh snap! Price must be a positive number with precision up to 2 digits after floating point.";

    String WRONG_SIZE = "Oh snap! Size must be a positive number with precision up to 1 digit after floating point.";

    String WRONG_TRAILER = "Oh snap! Youtube link is not correct.";

    String WRONG_DESCRIPTION = "Oh snap! Description must be at least 20 symbols.";

    String WRONG_THUMBNAIL_URL = "Oh snap! Thumbnail URL should be a plain text starting with http://, https:// or null.";

    String GAME_EXIST_IN_SHOPPING_CART = "Oh snap! Game %s is already in your shopping cart.";

    //endregion
}
