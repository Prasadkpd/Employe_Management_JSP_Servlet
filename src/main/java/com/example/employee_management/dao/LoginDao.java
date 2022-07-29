package com.example.employee_management.dao;

import com.example.employee_management.model.Login;
import com.example.employee_management.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean validate(Login login){
        boolean status = false;

        String SELECT_USER_SQL = "SELECT * FROM users where username = ? and password = ?";

        try(Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL);
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();

        } catch (SQLException throwable) {
            JDBCUtils.printSQLException(throwable);
        }

        return status;
    }
}
