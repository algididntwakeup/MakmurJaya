package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.RegisterView;
import util.MyBatisUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.util.regex.Pattern;

public class RegisterController {
    private RegisterView view;
    private UserMapper mapper;

    public RegisterController(RegisterView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        view.getRegisterButton().addActionListener(e -> handleRegistration());
    }

    private void handleRegistration() {
        String username = view.getUsername().trim();
        String password = view.getPassword().trim();
        String email = view.getEmail().trim();

        // Validation
        if (!validateInputs(username, password, email)) {
            return;
        }

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);

            // Check if username already exists
            if (userMapper.findUserByUsername(username) != null) {
                JOptionPane.showMessageDialog(view, 
                    "Username already exists. Please choose another username.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create new user
            User user = new User();
            user.setUsername(username);
            // Hash the password before storing
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setEmail(email);

            userMapper.registerUser(user);
            session.commit();

            JOptionPane.showMessageDialog(view, 
                "Registration successful! You can now login.",
                "Registration Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            view.clearFields();
            view.dispose(); // Close the registration dialog
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Registration failed: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInputs(String username, String password, String email) {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "All fields are required!",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Username validation (alphanumeric and at least 3 characters)
        if (!username.matches("^[a-zA-Z0-9]{3,}$")) {
            JOptionPane.showMessageDialog(view,
                "Username must be at least 3 characters long and contain only letters and numbers",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Password validation (at least 6 characters)
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(view,
                "Password must be at least 6 characters long",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(emailRegex, email)) {
            JOptionPane.showMessageDialog(view,
                "Please enter a valid email address",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
