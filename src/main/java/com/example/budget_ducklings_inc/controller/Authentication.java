package com.example.budget_ducklings_inc.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import com.example.budget_ducklings_inc.model.Components;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "authentication", urlPatterns = "/auth-servlet/*")
public class Authentication extends HttpServlet {
    DataBaseConnector dataBaseConnector = new DataBaseConnector();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        System.out.println("hej från doGet i auth");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       switch (req.getPathInfo()){
           case "/login":
               login(req,resp);
               break;
           case "/logOut": logOut(req,resp);
           break;
       }
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName=req.getParameter("UserName");
        String password = req.getParameter("password");

        List<Components> employeeList = dataBaseConnector.getAll(userName, password);

        for (Components employee: employeeList){

            if(employee.getUserName().equals(userName) && employee.getPassword().equals(password)){
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userName", userName);
                    resp.sendRedirect(req.getContextPath() + "/InvoicePageServices");
                System.out.println("hej från login");
                    return;
            }
            else if (employee.getUserName()==null&&employee.getUserName().isEmpty()){
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }else {
                resp.sendRedirect("/login.jsp?error=invalid%20login");
            }
         }
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        HttpSession session = req.getSession(true);
        session.setAttribute("userName", null);
        session.invalidate();
        resp.sendRedirect("/login.jsp");
        System.out.println("hej från logout");
    }
    public void destroy(){
    }
}