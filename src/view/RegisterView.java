package view;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JTextField txtEmail = new JTextField(20);
    private JButton btnRegister = new JButton("Register");

    public RegisterView() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel());
        panel.add(btnRegister);

        add(panel);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public JButton getRegisterButton() {
        return btnRegister;
    }
}
    