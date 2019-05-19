package dev.kirillzhelt.registry.models;

import dev.kirillzhelt.registry.jdbcconnections.UnitsSQLiteJDBCConnection;

import java.util.ArrayList;
import java.util.TreeMap;

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
        return unitsSQLiteJDBCConnection.selectUnit(unitNumber);
    }

    public ArrayList<Integer> getUnitRooms(int unitNumber) {
        return unitsSQLiteJDBCConnection.selectUnitRooms(unitNumber);
    }

    public void transferRoom(int unitFrom, int room, int unitTo) {
        unitsSQLiteJDBCConnection.deleteRoomForUnit(unitFrom, room);
        unitsSQLiteJDBCConnection.addRoomForUnit(unitTo, room);
    }

    public TreeMap<Integer, ArrayList<Integer>> getRoomsForUnits() {
        return unitsSQLiteJDBCConnection.selectRoomsForUnits();
    }
}
