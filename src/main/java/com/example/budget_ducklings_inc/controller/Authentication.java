package com.example.budget_ducklings_inc.controller;

import java.io.*;

import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "authentication", value = "/auth-servlet")
public class Authentication extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" +"Welcome" +  message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getPathInfo()){
            case "/login":login(req, resp);
        }

    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String userName=req.getParameter("UserName");
        String password = req.getParameter("password");



    }

    public void destroy() {
    }
}