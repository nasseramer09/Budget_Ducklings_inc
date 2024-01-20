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

        System.out.println("Inside doPost of InvoicePageServices");
        // Existing code...

        HttpSession session = req.getSession();
        String userName=(String) session.getAttribute("userName");
        DataBaseConnector connector = new DataBaseConnector();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        try {
            System.out.println("Before getting payment details");
            List<Components>invoiceList = connector.getPaymentDetails(userName);
            System.out.println("Before getting payment details");
            if (invoiceList!=null){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invoice Page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Välkommen till Budget Ducklings inc.  " + userName + "</h1>");
                out.println("<h2> Här är samtliga betalningar </h2>");
                out.println("<table border='1'>");

                for (Components comp: invoiceList) {
                    out.println("<tr>");
                    out.println("<th> Id-Värde </th>");
                    out.println("<th> Title </th>");
                    out.println("<th> Kategori </th>");
                    out.println("<th> Beskrivning </th>");
                    out.println("<th> Pris </th>");
                    out.println("<th> Datum </th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td> " + comp.getId() + " </td>");
                    out.println("<td> " + comp.getTitle() + " </td>");
                    out.println("<td> " + comp.getKategori() + " </td>");
                    out.println("<td> " + comp.getBeskrivning() + " </td>");
                    out.println("<td> " + comp.getPris() + " </td>");
                    out.println("<td> " + comp.getDatum() + " </td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<br><br>");
                out.println("<button type=\"button\" onclick=\"location.href='/URLForAddPaymentServlet'\">Lägg till ny betalning</button>");
                out.println("<br><br>");
                out.println("<button type=\"button\" onclick=\"location.href='/URLForEditPaymentServlet'\">Ändra betalning</button>");
                out.println("</body>");
                out.println("</html>");
            } else if (invoiceList.isEmpty()){
                out.println("<p> Inga betalningar hittades försök att lägga till betalningar genom att trycka på lägg till knappen</p>");
            }else {
                System.out.println("No payments found");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Exiting doGet of InvoicePageServices");

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* System.out.println("Inside doPost of InvoicePageServices");
        // Existing code...

        HttpSession session = req.getSession();
        String userName=(String) session.getAttribute("userName");
        DataBaseConnector connector = new DataBaseConnector();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


            try {
                System.out.println("Before getting payment details");
                List<Components>invoiceList = connector.getPaymentDetails(userName);
                System.out.println("Before getting payment details");
                if (invoiceList!=null){
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Invoice Page</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Samtliga betalningar för " + userName + "</h1>");
                out.println("<table border=1 >");

                for (Components comp: invoiceList) {
                    out.println("<tr>");
                    out.println("<th> Id-Värde </th>");
                    out.println("<th> Title </th>");
                    out.println("<th> Kategori </th>");
                    out.println("<th> Beskrivning </th>");
                    out.println("<th> Pris </th>");
                    out.println("<th> Datum </th>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td> " + comp.getId() + " </td>");
                    out.println("<td> " + comp.getTitle() + " </td>");
                    out.println("<td> " + comp.getKategori() + " </td>");
                    out.println("<td> " + comp.getBeskrivning() + " </td>");
                    out.println("<td> " + comp.getPris() + " </td>");
                    out.println("<td> " + comp.getDatum() + " </td>");
                    out.println("</tr>");

                    System.out.println(comp.getId());
                    System.out.println(comp.getTitle());
                    System.out.println(comp.getKategori());
                    System.out.println(comp.getBeskrivning());
                    System.out.println(comp.getPris());
                    System.out.println(comp.getDatum());


                } out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                } else if (invoiceList.isEmpty()){
                    out.println("<p> Inga betalningar hittades försök att lägga till betalningar genom att trycka på lägg till knappen</p>");
                }else {
                    System.out.println("No payments found");
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        System.out.println("Exiting doPost of InvoicePageServices");*/

    }
}
