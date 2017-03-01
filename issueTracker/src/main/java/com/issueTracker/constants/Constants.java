package com.issueTracker.constants;

public interface Constants {

    String EXISTING_USER = "Error! User with this username already exists.";

    String NON_EXISTING_USER = "Error! User with this username and password not exists.";

    String WRONG_USERNAME_LENGTH = "Error! Username must be between 5 and 30 symbols.";

    String WRONG_FULLNAME_LENGTH = "Error! Full Name must be at least 5 symbols.";

    String WRONG_PASSWORD_LENGTH = "Error! Password must be at least 8 symbols.";

    String WRONG_PASSWORD_CONTAINS_LENGTH = "Error! Password should contain a capital letter, a number and a sign: [!@#$%^&*,.]";

    String MISSMATCH_PASSWORDS = "Error! Passwords are not matching.";
}
