package com.example.budget_ducklings_inc.model;

public class Components {
    private String id;

    private String userName;
    private String password;
    private String title;
    private String kategori;
    private String datum;
    private String beskrivning;
    private String pris;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
    }
    public void setPassword(String password) {
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getBeskrivning() {
        return beskrivning;
    }

    public void setBeskrivning(String beskrivning) {
        this.beskrivning = beskrivning;
    }
}
