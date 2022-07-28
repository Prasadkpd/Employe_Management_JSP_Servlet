package com.example.employee_management.web;

import com.example.employee_management.dao.UserDao;
import com.example.employee_management.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        register(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register/register.jsp");
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(username);
        user.setPassword(password);

        try{
            int result = userDao.registerUser(user);
            if (result == 1){
                request.setAttribute("NOTIFICATION", "User Registered Successfully");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
    }
}
