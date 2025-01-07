package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.UpdateProfileView;
import util.MyBatisUtil;

import javax.swing.*;

public class UpdateProfileController {
    private UpdateProfileView view;

    public UpdateProfileController(UpdateProfileView view, UserMapper mapper) {
        this.view = view;

        view.getUpdateButton().addActionListener(e -> {
            String username = view.getUsername();
            String name = view.getName();
            String address = view.getAddress();
            String phone = view.getPhone();

            if (username.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields are required!");
                return;
            }

            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                UserMapper userMapper = session.getMapper(UserMapper.class);

                User user = new User();
                user.setUsername(username);
                user.setName(name);
                user.setAddress(address);
                user.setPhone(phone);

                userMapper.updateUserProfile(user);
                session.commit();

                JOptionPane.showMessageDialog(view, "Profile updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
            }
        });
    }
}
