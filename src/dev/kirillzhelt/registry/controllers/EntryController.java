package dev.kirillzhelt.registry.controllers;

import javax.swing.*;

public class EntryController {

    public static void main(String[] argv) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
            IllegalAccessException e) {
            System.exit(-1);
        }

        new LoginController();
    }

}
