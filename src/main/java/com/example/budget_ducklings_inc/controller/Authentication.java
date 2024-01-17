package com.example.budget_ducklings_inc.controller;

import java.io.*;
import java.sql.Connection;
import java.util.List;

import com.example.budget_ducklings_inc.model.Components;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "authentication", urlPatterns = "/auth-servlet/*")
public class Authentication extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       switch (req.getPathInfo()){
           case "/login": login(req,resp);
           break;
           case "/logOut": logOut(req,resp);
           break;
       }
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName=req.getParameter("UserName");
        String password = req.getParameter("password");

        DataBaseConnector dataBaseConnector = new DataBaseConnector();
        List<Components> employeeList = dataBaseConnector.getAll(userName, password);

        for (Components employee: employeeList){

            if(employee.getUserName().equals(userName) && employee.getPassword().equals(password)){
                    HttpSession session = req.getSession(true);
                    session.setAttribute("userName", userName);
                    resp.sendRedirect("/InvoicePage.jsp");
                    System.out.println("Användaren finns och är inloggad");
                    return;
            }
            else{
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
                System.out.println("Användaren finns inte och är inte inloggad");
            }
         }
    }

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        HttpSession session = req.getSession(true);

        session.setAttribute("userName", null);
        session.invalidate();
        resp.sendRedirect("/login.jsp");

    }
    public void destroy(){
    }
}