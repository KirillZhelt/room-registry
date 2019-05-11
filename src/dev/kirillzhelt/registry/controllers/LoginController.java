package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.LoginModel;
import dev.kirillzhelt.registry.models.UserType;
import dev.kirillzhelt.registry.views.LoginView;

import java.awt.event.WindowEvent;

public class LoginController {
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginController() {
        loginView = new LoginView(this);
        loginModel = new LoginModel();
    }

    public void loginUser(String login, String password) {
        UserType userType = loginModel.authenticateUser(login, password);

        if (userType == null) {
            loginView.showInvalidLoginOrPassword();

            return;
        }

        loginView.dispatchEvent(new WindowEvent(loginView, WindowEvent.WINDOW_CLOSING));

        System.out.println(userType);
    }
}
