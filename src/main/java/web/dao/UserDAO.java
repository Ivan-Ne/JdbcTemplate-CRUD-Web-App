package web.dao;

import com.mysql.cj.protocol.Resultset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import web.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id) {
        return jdbcTemplate.query("SELECT * FROM user WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user VALUES(1, ?, ?, ?)", user.getName(),
                user.getAge(), user.getEmail());
    }

    public void update(int id, User updatedUser) {
        jdbcTemplate.update("UPDATE user SET name=?, age=?, email=? WHERE id=?",
                updatedUser.getName(), updatedUser.getAge(),updatedUser.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }
}