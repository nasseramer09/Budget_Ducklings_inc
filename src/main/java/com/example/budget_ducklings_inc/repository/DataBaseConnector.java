package com.example.budget_ducklings_inc.repository;

import java.io.PrintWriter;
import java.sql.*;

public class DataBaseConnector {
    private Connection connection;

    public DataBaseConnector(){

        String Jdbc="com.mysql.jdbc.Driver";
        try {
            Class.forName(Jdbc);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Budget_Ducklings_inc");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select from emoplyees_table where UserName=? and PASSWORD=?");

            while (resultSet.next()){
               // int id=resultSet.getInt("id");
                String UserName=resultSet.getString("UserName");
                String password=resultSet.getString("PASSWORD");

                System.out.println(UserName);
                System.out.println(password);

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
