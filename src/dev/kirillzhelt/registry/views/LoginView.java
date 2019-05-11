package dev.kirillzhelt.registry.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JPanel jPanelMain;
    private JPanel jPanelFields;
    private JTextField jTextFieldLogin;
    private JPasswordField jPasswordField;
    private JButton jButtonLogin;
    private JLabel jLabelError;

    public LoginView() {
        super("Login");

        setContentPane(jPanelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);

        jButtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
