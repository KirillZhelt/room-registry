package dev.kirillzhelt.registry.views;

import javax.swing.*;

public class LabelView extends JFrame {

    private JPanel jPanelMain;
    private JLabel jLabel;

    public LabelView(String title, String text) {
        super(title);

        setContentPane(jPanelMain);

        setBounds(750, 250, 500, 600);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jLabel.setText(text);

        pack();
        setVisible(true);
    }

}
