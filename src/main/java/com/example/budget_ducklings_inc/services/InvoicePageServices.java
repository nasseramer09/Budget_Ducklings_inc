package com.example.budget_ducklings_inc.services;

import com.example.budget_ducklings_inc.model.Components;
import com.example.budget_ducklings_inc.repository.DataBaseConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InvoicePageServlet", urlPatterns = "/InvoicePageServices/*")

public class InvoicePageServices extends HttpServlet  {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String userName=(String) session.getAttribute("userName");
        DataBaseConnector connector = new DataBaseConnector();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        try {
            List<Components>invoiceList = connector.getPaymentDetails(userName);
            if (invoiceList!=null){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invoice Page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Välkommen till Budget Ducklings inc.  " + userName + "</h1>");
                out.println("<h2> Här är samtliga betalningar </h2>");

                out.println("<table border='2'>");
                out.println("<tr>");
                out.println("<th> Id-Värde </th>");
                out.println("<th> Title </th>");
                out.println("<th> Kategori </th>");
                out.println("<th> Beskrivning </th>");
                out.println("<th> Pris </th>");
                out.println("<th> Datum </th>");
                out.println("<th> Actions </th>");
                out.println("</tr>");
                for (Components comp: invoiceList) {

                    out.println("<tr>");
                    out.println("<td> " + comp.getId() + " </td>");
                    out.println("<td> " + comp.getTitle() + " </td>");
                    out.println("<td> " + comp.getKategori() + " </td>");
                    out.println("<td> " + comp.getBeskrivning() + " </td>");
                    out.println("<td> " + comp.getPris() + " </td>");
                    out.println("<td> " + comp.getDatum() + " </td>");

                    //Delete knappen
                    out.println("<td>");
                    out.println("<form action='/DeletePayment' method='post'>");
                    out.println("<input type='hidden' name='id' value='"+ comp.getId() + "'>");
                    out.println("<input type='submit' value='Delete'>");
                    out.println("</form>");
                    out.println("</td>");

                    //Edit knappen
                    out.println("<td>");
                    out.println("<form action='" + req.getContextPath() + "/EditPayment' method='get'>");
                    out.println("<input type='hidden' name='id' value='"+ comp.getId() + "'>");
                    out.println("<input type='submit' value='Edit'>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<br><br>");
                out.println("<button type=\"button\" onclick=\"location.href='/AddPaymentServlet/*'\">Lägg till nya betalningar</button>");
                out.println("<br><br>");
                out.println("<button type=\"button\" onclick=\"location.href='/login.jsp'\">Log out</button>");
                out.println("</body>");
                out.println("</html>");
            }
            else if (invoiceList.isEmpty()){
                out.println("<p> Inga betalningar hittades försök att lägga till betalningar genom att trycka på lägg till knappen</p>");
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
