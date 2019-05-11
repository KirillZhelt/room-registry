package dev.kirillzhelt.registry.models;

public class LoginModel {

    public LoginModel() {

    }

    public UserType authenticateUser(String login, String password) {
        return UserType.Manager;
    }
}
