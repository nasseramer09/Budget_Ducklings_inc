package com.example.budget_ducklings_inc.repository;

import java.io.PrintWriter;
import java.sql.*;

public class DataBaseConnector {
    private Connection connection;

    public DataBaseConnector(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Budget_Ducklings_inc");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean UserKontroll(String userName, String passWord) {
        String sqlCommand="select from employees_table where UserName=? and PASSWORD=?";

        try {

                PreparedStatement statement = connection.prepareStatement(sqlCommand);
                statement.setString(1, userName);
                statement.setString(2, passWord);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
