package dev.kirillzhelt.registry.models;

public class Room {

    private int number;
    private String type;
    private int headsRoom;
    private double square;

    public Room(int number, String type, int headsRoom, double square) {
        this.number = number;
        this.type = type;
        this.headsRoom = headsRoom;
        this.square = square;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public int getHeadsRoom() {
        return headsRoom;
    }

    public double getSquare() {
        return square;
    }

    @Override
    public String toString() {
        return "Room{" +
            "number=" + number +
            ", type='" + type + '\'' +
            ", headsRoom=" + headsRoom +
            ", square=" + square +
            '}';
    }
}
