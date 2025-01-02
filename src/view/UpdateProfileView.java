package view;

import javax.swing.*;
import java.awt.*;

public class UpdateProfileView extends JFrame {
    private JTextField txtUsername = new JTextField(20);
    private JTextField txtName = new JTextField(20);
    private JTextField txtAddress = new JTextField(20);
    private JTextField txtPhone = new JTextField(20);
    private JButton btnUpdate = new JButton("Update Profile");

    public UpdateProfileView() {
        setTitle("Update Profile");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Address:"));
        panel.add(txtAddress);
        panel.add(new JLabel("Phone:"));
        panel.add(txtPhone);
        panel.add(new JLabel());
        panel.add(btnUpdate);

        add(panel);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getName() {
        return txtName.getText();
    }

    public String getAddress() {
        return txtAddress.getText();
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public JButton getUpdateButton() {
        return btnUpdate;
    }
}
