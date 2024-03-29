package com.example.budget_ducklings_inc.repository;

import com.example.budget_ducklings_inc.model.Components;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnector {
    private Connection connection;

    public DataBaseConnector(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Budget_Ducklings_inc");

            connection.prepareStatement("DROP TABLE IF EXISTS employees_table").execute();

            connection.prepareStatement("CREATE TABLE employees_table (" +
                    "id int(11) AUTO_INCREMENT PRIMARY KEY, " +
                    "userName varchar(32) UNIQUE, " +
                    "password varchar(64)," +
                    "titel varchar(100)," +
                    "kategori varchar(100)," +
                    "beskrivning varchar(250)," +
                    "agare varchar(32),"+
                    "pris DECIMAL(10,2)," +
                    "datum varchar (32))").execute();

            create("bob", "bob123456");
            create("demo", "demo123456");
            create("nasser", "nasser123456");
            create("test", "test123456");

        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean create (String userName, String password){
        try {
            String sqlCommand="INSERT INTO employees_table(userName, password) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1,userName);
            statement.setString(2,password);
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException e){
        return false;}
        catch (Exception e) {
            throw new RuntimeException(e);
        }
            return true;
    }

    public String findPassword (String userName) {
       String password;
       try {
           String sqlCommand="SELECT password FROM employees_table WHERE userName=?";
           PreparedStatement statement= connection.prepareStatement(sqlCommand);
           statement.setString(1,userName);

           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()){
               password=resultSet.getString("password");

           }else {
               password=null;
           }

       }catch (SQLException e){
           throw new RuntimeException(e);
       }
        return password;
    }

    public Components getAllById (String id) {
        Components paymentsD=null;
        String sqlCommand="SELECT * FROM employees_table WHERE id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                paymentsD  = new Components();
                paymentsD.setId(resultSet.getString("id"));
                paymentsD.setTitle(resultSet.getString("titel"));
                paymentsD.setKategori(resultSet.getString("kategori"));
                paymentsD.setBeskrivning(resultSet.getString("beskrivning"));
                paymentsD.setPris(resultSet.getString("pris"));
                paymentsD.setDatum(resultSet.getString("datum"));
                }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return paymentsD;
    }

    public List<Components> getPaymentDetails(String userName) throws SQLException {

        List<Components>paymentDetails=new ArrayList<>();

        String sqlCommand="SELECT id,titel,kategori, beskrivning, pris, datum FROM employees_table WHERE agare=?";

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
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return paymentDetails;
    }

    public void addPayment(String agare, String titel, String kategori, String beskrivning, String pris, String datum){
        String sqlCommand="INSERT INTO employees_table (titel, kategori, beskrivning, pris, datum, agare) VALUES (?,?,?,?,?,?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1,titel);
            statement.setString(2,kategori);
            statement.setString(3,beskrivning);
            statement.setString(4,pris);
            statement.setString(5,datum);
            statement.setString(6,agare);
            statement.execute();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void DeletePayment(String agare, String id){

        String sqlCommand="DELETE FROM employees_table WHERE agare=? AND id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1,agare);
            statement.setString(2,id);
            statement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean EditPayment(String id, String titel, String kategori, String agare, String datum, String beskrivning, String pris){
        String sqlCommand="UPDATE `employees_table` SET `id`=?,`titel`=?," +
                "`kategori`=?,`agare`=?,`datum`=?,`beskrivning`=?,`pris`=? WHERE agare=? And id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
            statement.setString(1,id);
            statement.setString(2,titel);
            statement.setString(3,kategori);
            statement.setString(4,agare);
            statement.setString(5,datum);
            statement.setString(6,beskrivning);
            statement.setString(7,pris);
            statement.setString(8,agare);
            statement.setString(9,id);

            statement.execute();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
