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

    public String toHtmlString() {
        return "<html>Room " + number + ":<br>" +
            "type: \'" + type + '\'' + "<br>" +
            "heads room: " + headsRoom + "<br>" +
            "square: " + square + "<br>" +
            "</html>";
    }
}
