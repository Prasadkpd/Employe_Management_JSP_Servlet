package com.example.employee_management.web;

import com.example.employee_management.dao.EmployeeDao;
import com.example.employee_management.model.Employee;
import com.example.employee_management.utils.JDBCUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/")
public class EmpController extends HttpServlet {
    private static final long serialVersionID = 1L;
    private EmployeeDao employeeDao;

    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        try {
            switch (action){
                case "/add":
                    showAddForm(req,resp);
                    break;
                case "/insert":
                    insertEmp(req,resp);
                    break;
                case "/remove":
                    removeEmp(req,resp);
                    break;
                case "/edit":
                    showEditForm(req,resp);
                    break;
                case "/update":
                    updateEmp(req,resp);
                    break;
                case "/list":
                    listEmp(req,resp);
                    break;
                default:
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login/login.jsp");
                    dispatcher.forward(req,resp);
                    break;

            }
        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }

    private void removeEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.deleteEmp(id);
        response.sendRedirect("list");
    }

    private void updateEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        Employee employee = new Employee(id,name,position,department);

        employeeDao.updateEmp(employee);
        response.sendRedirect("list");
    }

    private void insertEmp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        LocalDate hire_date = LocalDate.parse(request.getParameter("hire_date"));

        Employee employee = new Employee(name,position,department,hire_date);
        employeeDao.insertEmp(employee);
        response.sendRedirect("list");
    }

    private void listEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List <Employee> employeeList = employeeDao.selectAllEmp();
        request.setAttribute("listEmp", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("emp/emp-list.jsp");
        dispatcher.forward(request,response);
    }

    public void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("emp/emp-form.jsp");
        dispatcher.forward(request,response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeDao.selectEmp(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("emp/emp-form.jsp");
        request.setAttribute("emp", employee);
        dispatcher.forward(request,response);
    }

}
