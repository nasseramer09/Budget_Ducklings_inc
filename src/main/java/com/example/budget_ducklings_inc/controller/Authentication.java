package com.example.budget_ducklings_inc.controller;

import java.io.*;


import com.example.budget_ducklings_inc.model.Components;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "authentication", urlPatterns = "/auth-servlet/*")
public class Authentication extends HttpServlet {
    DataBaseConnector dataBaseConnector = new DataBaseConnector();

    public boolean find(String userName, String password){
    if (userName==null){
        return false;
    } else if (password==null) {
        return false;
    }
String userPassword= dataBaseConnector.findPassword(userName);
    return userPassword.equals(password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       switch (req.getPathInfo()){
           case "/login":
               login(req,resp);
               break;
           case "/logOut":
               logOut(req,resp);
           break;
           case "/register":
               register(req,resp);
               break;
       }
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName=req.getParameter("userName");
        String password = req.getParameter("password");
       if (find(userName,password)){
           HttpSession session = req.getSession(true);
           session.setAttribute("userName",userName);
           resp.sendRedirect("/InvoicePageServices");

        } else  {
           req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String userName=req.getParameter("username");
        System.out.println("register " + userName);
        String password=req.getParameter("password");
        dataBaseConnector.create(userName,password);
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