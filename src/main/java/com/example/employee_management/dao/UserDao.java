package com.example.employee_management.dao;

import com.example.employee_management.model.User;
import com.example.employee_management.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO users " +
                "(first_name, last_name, username, password) " +
                "VALUES (?, ?, ?, ?);";
        int result = 0;
        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
