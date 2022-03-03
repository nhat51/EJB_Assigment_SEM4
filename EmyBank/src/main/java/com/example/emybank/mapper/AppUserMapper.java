package com.example.emybank.mapper;

import com.example.emybank.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<User> {
    public static final String BASE_SQL = "SELECT u.id, u.name, u.password, u.accountNumber, u.email, u.phone, u.balance, u.role, u.status from amy_bank u";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String password = rs.getString("password");
        String accountNumber = rs.getString("accountNumber");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        double balance = rs.getDouble("balance");
        String role = rs.getString("role");
        int status = rs.getInt("status");

        return new User(id, name, password, accountNumber, email, phone, balance, role, status);

    }
}
