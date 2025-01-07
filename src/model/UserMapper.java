package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    void registerUser(User user);
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findUserByUsername(String username);
    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    void updatePassword(@Param("username") String username, @Param("password") String password);
    @Select("SELECT * FROM users WHERE email = #{email}")
    User findUserByEmail(String email);
    @Update("UPDATE users SET name = #{name}, address = #{address}, phone = #{phone} WHERE username = #{username}")
    void updateUserProfile(User user);
}
