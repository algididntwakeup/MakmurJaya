package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import view.ForgotPasswordView;
import util.MyBatisUtil;

import javax.swing.*;
import java.util.Random;

public class ForgotPasswordController {
    private ForgotPasswordView view;
    private UserMapper mapper;

    public ForgotPasswordController(ForgotPasswordView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        view.getResetPasswordButton().addActionListener(e -> resetPassword());
    }

    private void resetPassword() {
        String email = view.getEmail();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Email is required!");
            return;
        }

        try (SqlSession session = MyBatisUtil.getSqlSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            // Cari user berdasarkan email
            User user = mapper.findUserByEmail(email);

            if (user == null) {
                JOptionPane.showMessageDialog(view, "Email not found!");
                return;
            }

            // Generate password sementara
            String tempPassword = generateTemporaryPassword();
            String hashedPassword = BCrypt.hashpw(tempPassword, BCrypt.gensalt());

            // Update password di database
            mapper.updatePassword(user.getUsername(), hashedPassword);
            session.commit();

            JOptionPane.showMessageDialog(view, "Temporary password: " + tempPassword + "\nPlease change it immediately.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }

    private String generateTemporaryPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
