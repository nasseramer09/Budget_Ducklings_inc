package com.example.budget_ducklings_inc.model;

import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet (name = "DeleteServlet" , urlPatterns = "/DeletePayment")

public class DeletePayments extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseConnector connector = new DataBaseConnector();
        HttpSession session = req.getSession(true);
        String userName  =(String) session.getAttribute("userName");
        String id=req.getParameter("id");

        if (userName==null){
            resp.sendRedirect("/InvoicePageServices");
        }else {
            connector.DeletePayment(userName, id);
            resp.sendRedirect(req.getContextPath()+ "/InvoicePageServices");
        }
    }

}
