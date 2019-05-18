package dev.kirillzhelt.registry.views;

import javax.swing.*;

public class LabelView extends JFrame {

    private JPanel jPanelMain;
    private JLabel jLabel;

    public LabelView(String title, String text) {
        super(title);

        setContentPane(jPanelMain);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jLabel.setText(text);

        pack();
        setVisible(true);
    }

}
