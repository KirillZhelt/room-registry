package dev.kirillzhelt.registry.jdbcconnections;

import dev.kirillzhelt.registry.models.Room;
import dev.kirillzhelt.registry.models.Unit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitsSQLiteJDBCConnection extends SQLiteJDBCConnection {

    public UnitsSQLiteJDBCConnection() {
        super("databases/units.db");
    }

    public ArrayList<Integer> selectRoomsNumbers() {
        return selectPrimaryKeys("rooms", "room_number");
    }

    public ArrayList<Integer> selectUnitsNumbers() {
        return selectPrimaryKeys("units", "unit_id");
    }

    public Room selectRoom(int roomNumber) {
        String selectRoomSql = "SELECT type, heads_room, square FROM rooms WHERE room_number=?";

        try (PreparedStatement selectRoomStmt = connection.prepareStatement(selectRoomSql)) {
            selectRoomStmt.setInt(1, roomNumber);

            ResultSet rs = selectRoomStmt.executeQuery();

            if (rs.next())
                return new Room(roomNumber, rs.getString("type"), rs.getInt("heads_room"),
                    rs.getDouble("square"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Unit selectUnit(int unitNumber) {
        String selectUnitSql = "SELECT superior_unit_id, full_name, short_name, name_in_genetive, name_in_dative FROM units WHERE unit_id=?";

        try (PreparedStatement selectUnitStmt = connection.prepareStatement(selectUnitSql)) {
            selectUnitStmt.setInt(1, unitNumber);

            ResultSet rs = selectUnitStmt.executeQuery();

            if (rs.next())
                return new Unit(unitNumber, rs.getInt("superior_unit_id"),
                    rs.getString("full_name"), rs.getString("short_name"),
                    rs.getString("name_in_genetive"), rs.getString("name_in_dative"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private ArrayList<Integer> selectPrimaryKeys(String tableName, String primaryKey) {
        ArrayList<Integer> primaryKeys = new ArrayList<>();

        String selectKeysSql = String.format("SELECT %s FROM %s", primaryKey, tableName);

        try (PreparedStatement selectUserTypeStmt = connection.prepareStatement(selectKeysSql)) {
            ResultSet rs = selectUserTypeStmt.executeQuery();

            while (rs.next())
                primaryKeys.add(Integer.parseInt(rs.getString(primaryKey)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return primaryKeys;
    }

}
