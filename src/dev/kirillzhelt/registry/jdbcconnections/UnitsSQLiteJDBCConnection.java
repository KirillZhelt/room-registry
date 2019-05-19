package dev.kirillzhelt.registry.jdbcconnections;

import dev.kirillzhelt.registry.models.Room;
import dev.kirillzhelt.registry.models.Unit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

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

    public ArrayList<Integer> selectUnitRooms(int unitNumber) {
        ArrayList<Integer> roomsNumbers = new ArrayList<>();

        String selectRoomsSql = "SELECT rooms_room_number FROM rooms_for_units WHERE units_unit_id=?";

        try (PreparedStatement selectRoomsStmt = connection.prepareStatement(selectRoomsSql)) {
            selectRoomsStmt.setInt(1, unitNumber);

            ResultSet rs = selectRoomsStmt.executeQuery();

            while (rs.next())
                roomsNumbers.add(rs.getInt("rooms_room_number"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return roomsNumbers;
    }

    public void deleteRoomForUnit(int unitNumber, int roomNumber) {
        String deleteRoomSql = "DELETE FROM rooms_for_units WHERE units_unit_id=? and rooms_room_number=?";

        executeSql(deleteRoomSql, unitNumber, roomNumber);
    }

    public void addRoomForUnit(int unitNumber, int roomNumber) {
        String addRoomSql = "INSERT INTO rooms_for_units(units_unit_id, rooms_room_number) VALUES(?, ?)";

        executeSql(addRoomSql, unitNumber, roomNumber);
    }

    public TreeMap<Integer, ArrayList<Integer>> selectRoomsForUnits() {
        TreeMap<Integer, ArrayList<Integer>> roomsForUnits = new TreeMap<>();

        String selectRoomsSql = "SELECT units_unit_id, rooms_room_number FROM rooms_for_units";

        try (PreparedStatement selectRoomsStmt = connection.prepareStatement(selectRoomsSql)) {
            ResultSet rs = selectRoomsStmt.executeQuery();

            while (rs.next()) {
                int unitNumber = rs.getInt("units_unit_id");
                int roomNumber = rs.getInt("rooms_room_number");

                ArrayList<Integer> roomsNumbers = roomsForUnits.get(unitNumber);
                if (roomsNumbers == null) {
                    roomsNumbers = new ArrayList<>();

                    roomsForUnits.put(unitNumber, roomsNumbers);
                }

                roomsNumbers.add(roomNumber);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return roomsForUnits;
    }

    public int selectUnitSuperior(int unitNumber) {
        String selectSuperiorSql = "SELECT superior_unit_id FROM units where unit_id=?";

        try (PreparedStatement selectSuperiorStmt = connection.prepareStatement(selectSuperiorSql)) {
            selectSuperiorStmt.setInt(1, unitNumber);

            ResultSet rs = selectSuperiorStmt.executeQuery();
            if (rs.next())
                return rs.getInt("superior_unit_id");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        throw new NoSuchElementException("No unit with such unitNumber");
    }

    private void executeSql(String sql, int firstParameter, int secondParameter) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, firstParameter);
            stmt.setInt(2, secondParameter);

            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
