package dev.kirillzhelt.registry.jdbcconnections;

public class UnitsSQLiteJDBCConnection extends SQLiteJDBCConnection {

    public UnitsSQLiteJDBCConnection(String databasePath) {
        super("databases/units.db");
    }



}
