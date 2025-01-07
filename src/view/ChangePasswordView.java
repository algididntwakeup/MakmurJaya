package view;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordView extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JPasswordField txtOldPassword = new JPasswordField(20);
    private JPasswordField txtNewPassword = new JPasswordField(20);
    private JButton btnChangePassword = new JButton("Change Password");

    public ChangePasswordView() {
        setTitle("Change Password");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Old Password:"));
        panel.add(txtOldPassword);
        panel.add(new JLabel("New Password:"));
        panel.add(txtNewPassword);
        panel.add(new JLabel());
        panel.add(btnChangePassword);

        add(panel);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getOldPassword() {
        return new String(txtOldPassword.getPassword());
    }

    public String getNewPassword() {
        return new String(txtNewPassword.getPassword());
    }

    public JButton getChangePasswordButton() {
        return btnChangePassword;
    }

    public void setModal(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setModal'");
    }
}
