package com.example.budget_ducklings_inc.controller;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "AddPayment", urlPatterns = "/AddPaymentServlet/*")

public class AddPayment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out =resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title> Payment Page </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Lägg till ny betalning </h1>");
        out.println("<form action='/AddPaymentServlet' method='post'>");
        out.println("Titel: <input type='text' name='titel'><br>");
        out.println("Kategori: <input type='text' name='kategori'><br>");
        out.println("Beskrivning: <input type='text' name='beskrivning'><br>");
        out.println("Pris: <input type='text' name='pris'><br>");
        out.println("Datum: <input type='text' name='datum'><br>");
        out.println("<input type='submit' value='Lägg till betalning'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DataBaseConnector connector = new DataBaseConnector();
            HttpSession session = req.getSession(true);
            String userName = (String) session.getAttribute("userName");
            String title=req.getParameter("titel");
            String kategori=req.getParameter("kategori");
            String beskrivning=req.getParameter("beskrivning");
            String pris=req.getParameter("pris");
            String datum=req.getParameter("datum");
            connector.addPayment(userName,title,kategori,beskrivning,pris,datum);
            resp.sendRedirect(req.getContextPath() + "/InvoicePageServices");
        } catch (Exception e){
            e.printStackTrace();

            resp.getWriter().println("Error" + e.getMessage());
        }


    }
}
