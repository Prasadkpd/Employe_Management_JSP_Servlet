package com.example.employee_management.dao;

import com.example.employee_management.model.Employee;
import com.example.employee_management.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String INSERT_EMPLOYEE = "INSERT INTO employee " +
            "(name, position, department, hire_date) VALUES (?,?,?,?);";
    private static final String SELECT_EMP_BY_ID = "SELECT * from employee WHERE id=?;";
    private static final String SELECT_ALL_EMP_ = "SELECT * from employee;";
    private static final String DELETE_EMP = "DELETE from employee WHERE id=?;";
    private static final String UPDATE_EMP = "UPDATE employee SET name = ?, position = ?, department = ?  WHERE id=?;";

    public void insertEmp(Employee emp) throws SQLException {
        System.out.println(INSERT_EMPLOYEE);

        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getPosition());
            preparedStatement.setString(3, emp.getDepartment());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(emp.getHire_date()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public Employee selectEmp(int id) throws SQLException {
        Employee employee = null;

        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                LocalDate hire_date = JDBCUtils.getUtilDate(resultSet.getDate("hire_date"));
                employee = new Employee(id, name, position, department, hire_date);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return employee;
    }

    public List<Employee> selectAllEmp() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMP_);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                LocalDate hire_date = JDBCUtils.getUtilDate(resultSet.getDate("hire_date"));
                employees.add(new Employee(id, name, position, department, hire_date));
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return employees;
    }

    public boolean deleteEmp(int id) throws SQLException {
        boolean deletedEmp = false;
        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMP);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            deletedEmp = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return deletedEmp;
    }

    public boolean updateEmp(Employee employee) throws SQLException {
        boolean updatedEmp = false;
        try (Connection connection = JDBCUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getPosition());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setInt(4, employee.getId());
            System.out.println(preparedStatement);

            updatedEmp = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return updatedEmp;
    }
}
