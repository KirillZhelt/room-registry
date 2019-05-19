package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ComboBoxView extends JFrame {

    private JComboBox jComboBox;
    private JPanel jPanelMain;
    private JButton jButton;
    private JLabel jLabel;

    public ComboBoxView(RegistryController registryController, InformationGetter informationGetter,
                        ArrayList<Integer> options, String text, boolean isVisible) {
        super(text);

        setContentPane(jPanelMain);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(750, 250, 500, 600);

        setVisible(isVisible);

        for (Integer option : options)
            jComboBox.addItem(option);

        jLabel.setText(text);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    informationGetter.get((Integer)jComboBox.getSelectedItem());
                    setVisible(false);
                } catch (NullPointerException ex) {

                }
            }
        });

        pack();
    }
}
