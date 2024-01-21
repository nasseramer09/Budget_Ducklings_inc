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
@WebServlet(name = "EditServlet" , urlPatterns = {"/EditPayment", "/EditPayment/*"})

public class EditPayment extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hej från början i editPayment doget metod");

        resp.setContentType("text/html");
        PrintWriter out =resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title> Edit payment </title>");
        out.println("</head>");
        out.println("<body>");

        String id=req.getParameter("id");
        DataBaseConnector connector = new DataBaseConnector();
        Components payment=connector.getAllById(id);

        if (payment!=null) {
            out.println("<h1> Edit payment</h1>");
            out.println("<form action='" + req.getContextPath() + "/EditPayment' method='post'>");
            out.println("Titel: <input type='text' name='titel' value='"+payment.getTitle() + "'><br>");
            out.println("Kategori: <input type='text' name='kategori' value='"+ payment.getKategori() + "'><br>");
            out.println("Beskrivning: <input type='text' name='beskrivning' value='" +payment.getBeskrivning() + "'><br>");
            out.println("Pris: <input type='text' name='pris' value='"+ payment.getPris() + "'><br>");
            out.println("Datum: <input type='text' name='datum' value='" + payment.getDatum() + "'><br>");
            out.println("<input type='hidden' name='id' value='" + id + "'>");
            out.println("<input type='submit' value='Uppdatera betalning'>");
            out.println("</form>");

        }else {
            out.println("<p>Betalning hittades inte </p>");
        }
        out.println("</body>");
        out.println("</html>");
        System.out.println("hej från slutet av doGet i editPayment metod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hej från början av doPost i editPayment metod");
           try {
               DataBaseConnector connector = new DataBaseConnector();
               HttpSession session = req.getSession(true);

               String userName  =(String) session.getAttribute("userName");
               String id=req.getParameter("id");
               String titel=req.getParameter("titel");
               String kategori=req.getParameter("kategori");
               String beskrivning=req.getParameter("beskrivning");
               String pris=req.getParameter("pris");
               String datum=req.getParameter("datum");

               if (userName==null){
                   resp.sendRedirect("/InvoicePageServices");
               }else {
                   connector.EditPayment(id,titel,kategori,userName,datum,beskrivning,pris);
                   resp.sendRedirect(req.getContextPath()+ "/InvoicePageServices");
               }
           }

           catch (Exception e){
               System.out.println("hej från slutet av try-blocket i doPost i editPayment metod");

               e.printStackTrace();
           }
        System.out.println("hej från slutet av doPost i editPayment metod");
        }

}
