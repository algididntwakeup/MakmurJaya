package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import view.ChangePasswordView;
import util.MyBatisUtil;

import javax.swing.*;

public class ChangePasswordController {
    private ChangePasswordView view;
    private UserMapper mapper;

    public ChangePasswordController(ChangePasswordView view, UserMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        view.getChangePasswordButton().addActionListener(e -> changePassword());
    }

    private void changePassword() {
        String username = view.getUsername();
        String oldPassword = view.getOldPassword();
        String newPassword = view.getNewPassword();

        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty()) {
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

            if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
                JOptionPane.showMessageDialog(view, "Old password is incorrect!");
                return;
            }

            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            mapper.updatePassword(username, hashedPassword);
            session.commit();

            JOptionPane.showMessageDialog(view, "Password updated successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }
}
