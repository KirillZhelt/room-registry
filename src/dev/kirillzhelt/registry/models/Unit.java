package dev.kirillzhelt.registry.models;

import java.util.ArrayList;

public class Unit {

    private int id;
    private int superiorUnitId;
    private String fullName;
    private String shortName;
    private String nameInGenetive;
    private String nameInDative;

    private ArrayList<Integer> roomsNumbers;

    public Unit(int id, int superiorUnitId, String fullName, String shortName, String nameInGenetive,
                String nameInDative) {
        this.id = id;
        this.superiorUnitId = superiorUnitId;
        this.fullName = fullName;
        this.shortName = shortName;
        this.nameInGenetive = nameInGenetive;
        this.nameInDative = nameInDative;

        this.roomsNumbers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getSuperiorUnitId() {
        return superiorUnitId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getNameInGenetive() {
        return nameInGenetive;
    }

    public String getNameInDative() {
        return nameInDative;
    }

    public ArrayList<Integer> getRoomsNumbers() {
        return roomsNumbers;
    }

    public void setRoomsNumbers(ArrayList<Integer> roomsNumbers) {
        this.roomsNumbers = roomsNumbers;
    }
}
