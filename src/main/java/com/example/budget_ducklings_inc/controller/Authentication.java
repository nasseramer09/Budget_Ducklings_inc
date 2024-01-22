package com.example.budget_ducklings_inc.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.budget_ducklings_inc.model.Components;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "authentication", urlPatterns = "/auth-servlet/*")
public class Authentication extends HttpServlet {

    private Map<String, String> users;
    public Authentication(){
        users = new HashMap<>(){{

            put("nasser", "nasser123456");
            put("bob", "bob123456");
            put("demo", "demo123456");
            put("testkonto", "testkonto123");

        }
        };
    }

    DataBaseConnector dataBaseConnector = new DataBaseConnector();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       switch (req.getPathInfo()){
           case "/login":
               login(req,resp);
               break;
           case "/logOut":
               logOut(req,resp);
           break;
       }
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userName=req.getParameter("userName");
        String password = req.getParameter("password");

       if (users.get(userName)==null){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else if (users.get(userName).equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userName",userName);
            resp.sendRedirect("/InvoicePageServices");
        } else {
            resp.sendRedirect("/login.jsp?error=invalid%20login");
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