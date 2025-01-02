package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import view.LoginView;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private UserMapper mapper;

    public LoginController(LoginView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        view.getLoginButton().addActionListener(e -> loginUser());
    }

    private void loginUser() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required!");
            return;
        }

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            User user = mapper.findUserByUsername(username);

            if (user == null) {
                JOptionPane.showMessageDialog(view, "User not found!");
                return;
            }

            if (BCrypt.checkpw(password, user.getPassword())) {
                JOptionPane.showMessageDialog(view, "Login successful!");
                // Arahkan ke halaman berikutnya (dashboard atau fitur lain)
            } else {
                JOptionPane.showMessageDialog(view, "Invalid password!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }
}
