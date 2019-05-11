package dev.kirillzhelt.registry.jdbcconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteJDBCConnection {
    protected Connection connection;

    public SQLiteJDBCConnection(String databasePath) {
        String url = String.format("jdbc:sqlite:%s", databasePath);

        try {
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
