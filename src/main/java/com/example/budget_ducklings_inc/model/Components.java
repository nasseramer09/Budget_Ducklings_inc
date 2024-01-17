package com.example.budget_ducklings_inc.model;

public class Components {
    private String userName;
    private String password;
    private String invoiceTyp;
    private double price;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvoiceTyp() {
        return invoiceTyp;
    }

    public void setInvoiceTyp(String invoiceTyp) {
        this.invoiceTyp = invoiceTyp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
