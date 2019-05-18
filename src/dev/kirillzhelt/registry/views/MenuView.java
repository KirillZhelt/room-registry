package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuView extends JFrame {

    public MenuView(RegistryController registryController, ArrayList<ActionListener> commandHub,
                    ArrayList<String> commandNames) {
        super("Registry");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 50, 500, 600);

        GridLayout gridLayout = new GridLayout(commandHub.size(), 1, 20, 20);

        setLayout(gridLayout);

        for (int i = 0; i < commandHub.size(); i++) {
            JButton jButton = new JButton(commandNames.get(i));

            jButton.addActionListener(commandHub.get(i));

            add(jButton);
        }

        setVisible(true);
    }
}
