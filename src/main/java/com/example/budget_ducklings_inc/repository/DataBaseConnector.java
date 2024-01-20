package com.example.budget_ducklings_inc.repository;

import com.example.budget_ducklings_inc.model.Components;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnector {
    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    public DataBaseConnector(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Budget_Ducklings_inc");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Components> getAll(String userName, String password) {
        List<Components> checkUser = new ArrayList<>();
        String sqlCommand="SELECT UserName, PASSWORD FROM employees_table WHERE UserName=? AND PASSWORD=?";

        try {
                PreparedStatement statement = connection.prepareStatement(sqlCommand);
                statement.setString(1, userName);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    Components components = new Components();

                    components.setUserName(resultSet.getString("UserName"));
                    components.setPassword(resultSet.getString("PASSWORD"));
                    checkUser.add(components);}
            System.out.println("hej från getAll");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Components comp:checkUser
        ) {
            System.out.println(comp);
        }
        return checkUser;
    }

    public List<Components> getPaymentDetails(String userName) throws SQLException {

        List<Components>paymentDetails=new ArrayList<>();

        String sqlCommand="SELECT id,titel,kategori, beskrivning, pris, datum FROM employees_table WHERE userName=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1,userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Components components = new Components();
                components.setId(resultSet.getString("id"));
                components.setTitle(resultSet.getString("titel"));
                components.setKategori(resultSet.getString("kategori"));
                components.setBeskrivning(resultSet.getString("beskrivning"));
                components.setPris(resultSet.getString("pris"));
                components.setDatum(resultSet.getString("datum"));
                paymentDetails.add(components);
                System.out.println("hej från getPaymentDetails");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paymentDetails;
    }
}
