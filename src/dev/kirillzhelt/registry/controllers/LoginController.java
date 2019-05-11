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
            System.out.println("Null");
            loginView.showInvalidLoginOrPassword();
            
            return;
        } else if (userType == UserType.Manager) {
            System.out.println("Manager");
        } else if (userType == UserType.Administrator) {
            System.out.println("Administrator");
        } else if (userType == UserType.Superintendent) {
            System.out.println("Superintendent");
        }

        loginView.dispatchEvent(new WindowEvent(loginView, WindowEvent.WINDOW_CLOSING));
    }
}
