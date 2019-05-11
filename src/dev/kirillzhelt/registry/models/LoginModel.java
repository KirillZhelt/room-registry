package dev.kirillzhelt.registry.models;

import dev.kirillzhelt.registry.jdbcconnections.UsersSQLiteJDBCConnection;

public class LoginModel {

    private UsersSQLiteJDBCConnection usersSQLiteJDBCConnection;

    public LoginModel() {
        usersSQLiteJDBCConnection = new UsersSQLiteJDBCConnection();
    }

    public UserType authenticateUser(String login, String password) {
        String userTypeString = usersSQLiteJDBCConnection.selectUserType(login, password);

        if (userTypeString == null) {
            return null;
        } else if (userTypeString.equals("Manager")) {
            return UserType.Manager;
        } else if (userTypeString.equals("Administrator")) {
            return UserType.Administrator;
        } else if (userTypeString.equals("Superintendant")) {
            return UserType.Superintendent;
        }

        return null;
    }
}
