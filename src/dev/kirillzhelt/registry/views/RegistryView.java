package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.Command;
import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.util.ArrayList;

public class RegistryView extends JFrame {

    private JPanel jPanelMain;

    public RegistryView(RegistryController registryController, ArrayList<Command> commandHub,
                        ArrayList<String> commandNames) {
        super("Registry");

        setContentPane(jPanelMain);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // TODO: add buttons from commands and create listeners
        
        pack();
        setVisible(true);
    }
}
