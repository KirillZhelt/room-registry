package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuView extends JFrame {

    public MenuView(RegistryController registryController, ArrayList<ActionListener> listeners,
                    ArrayList<String> buttonTexts, boolean isVisible, int actionOnClose) {
        super("Registry");

        setDefaultCloseOperation(actionOnClose);
        setBounds(700, 200, 500, 600);

        GridLayout gridLayout = new GridLayout(listeners.size(), 1, 20, 20);

        setLayout(gridLayout);

        for (int i = 0; i < listeners.size(); i++) {
            JButton jButton = new JButton(buttonTexts.get(i));

            jButton.addActionListener(listeners.get(i));

            add(jButton);
        }

        setVisible(isVisible);
    }
}
