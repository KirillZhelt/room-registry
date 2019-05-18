package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ComboBoxView extends JFrame {

    private JComboBox jComboBox;
    private JPanel jPanelMain;
    private JButton jButton;
    private JLabel jLabel;

    public ComboBoxView(RegistryController registryController, ActionListener listener,
                        ArrayList<Integer> options, String text, boolean isVisible) {
        super(text);

        setContentPane(jPanelMain);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(isVisible);

        for (Integer option : options)
            jComboBox.addItem(option);

        jLabel.setText(text);

        jButton.addActionListener(listener);

        pack();
    }
}
