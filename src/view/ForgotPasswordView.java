package view;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordView extends JFrame {
    private JTextField txtEmail = new JTextField(20);
    private JButton btnResetPassword = new JButton("Reset Password");

    public ForgotPasswordView() {
        setTitle("Forgot Password");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel());
        panel.add(btnResetPassword);

        add(panel);
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public JButton getResetPasswordButton() {
        return btnResetPassword;
    }
}
