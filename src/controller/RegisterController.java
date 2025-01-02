package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.RegisterView;

import javax.swing.*;

public class RegisterController {
    private RegisterView view;

    public RegisterController(RegisterView view, UserMapper mapper) {
        this.view = view;

        view.getRegisterButton().addActionListener(e -> {
            String username = view.getUsername();
            String password = view.getPassword();
            String email = view.getEmail();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields are required!");
                return;
            }

            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                UserMapper userMapper = session.getMapper(UserMapper.class);

                User user = new User();
                user.setUsername(username);
                user.setPassword(password); // Gunakan hashing
                user.setEmail(email);

                userMapper.registerUser(user);
                session.commit();

                JOptionPane.showMessageDialog(view, "Registration successful!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });
    }
}
