package dev.kirillzhelt.registry.jdbcconnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersSQLiteJDBCConnection extends SQLiteJDBCConnection {

    public UsersSQLiteJDBCConnection() {
        super("databases/users.db");
    }

    public String selectUserType(String login, String password) {
        String selectUserTypeSql = "SELECT user_type from users where login=? and password=?";

        try (PreparedStatement selectUserTypeStmt = connection.prepareStatement(selectUserTypeSql)) {
            selectUserTypeStmt.setString(1, login);
            selectUserTypeStmt.setString(2, password);

            ResultSet rs = selectUserTypeStmt.executeQuery();

            if (rs.next())
                return rs.getString("user_type");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
