package dev.kirillzhelt.registry.models;

import dev.kirillzhelt.registry.jdbcconnections.UnitsSQLiteJDBCConnection;

import java.util.ArrayList;

public class RegistryModel {

    private UnitsSQLiteJDBCConnection unitsSQLiteJDBCConnection;

    public RegistryModel() {
        unitsSQLiteJDBCConnection = new UnitsSQLiteJDBCConnection();
    }

    public ArrayList<Integer> getRoomsNumbers() {
        return unitsSQLiteJDBCConnection.selectRoomsNumbers();
    }

    public ArrayList<Integer> getUnitsNumbers() {
        return unitsSQLiteJDBCConnection.selectUnitsNumbers();
    }

    public Room getRoom(int roomNumber) {
        return unitsSQLiteJDBCConnection.selectRoom(roomNumber);
    }

    public Unit getUnit(int unitNumber) {
        return null;
    }
}
